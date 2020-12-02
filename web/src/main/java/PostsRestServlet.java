import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.domain.entity.User;
import abir.shah.messageBoardSystem.usecase.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Posts", urlPatterns = {"posts"}, loadOnStartup = 1)
public class PostsRestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requesterUserId = Authentication.extractUserIdFromSession(request);

        String creatorId = request.getParameter("creatorId");
        String startDateParameter = request.getParameter("startDate");
        String endDateParameter = request.getParameter("endDate");
        Date startDate = (startDateParameter == null) ? null : Date.valueOf(startDateParameter);
        Date endDate = (endDateParameter == null) ? null : Date.valueOf(endDateParameter);

        String hashTagsParameter = request.getParameter("hashTags");

        List<String> hashTags = hashTagsParameter == null ? new ArrayList<>(): Arrays.asList(hashTagsParameter.split(","));

        FetchPostsUsecase usecase = new FetchPostsUsecase();
        List<Post> postList = usecase.execute(requesterUserId,creatorId, startDate,endDate, hashTags);

        List<JSONObject> postJsonList = postList.stream().map((p)->postToJsonObject(p)).collect(Collectors.toList());
        response.setContentType("application/json");
        response.getWriter().print(new JSONArray(postJsonList).toString());
    }

    private JSONObject postToJsonObject(Post post)
    {
        GetUserInfoUsecase usecase = new GetUserInfoUsecase();
        User user = usecase.execute(post.getCreatorUserId());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("postId",post.getId());
        jsonObject.put("attachmentId",post.getAttachementId());
        jsonObject.put("title",post.getTitle());
        jsonObject.put("content",post.getContent());
        jsonObject.put("lastModification",post.getLastModificationDate());
        jsonObject.put("creationDate",post.getCreationDate());
        jsonObject.put("creatorFullName",user.getFullName());

        return jsonObject;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requesterUserId = Authentication.extractUserIdFromSession(request);

        JSONObject jsonObject =  new JSONObject(new String(request.getInputStream().readAllBytes()));
        String title = jsonObject.getString("title");
        String content = jsonObject.getString("content");
        String authorizedGroup = jsonObject.getString("authorizedGroup");


        CreatePostUsecase usecase = new CreatePostUsecase();
        String postId = usecase.execute(title,content,requesterUserId,authorizedGroup);


        byte[] payload = ("{\"id\":\"" + postId + "\"}").getBytes();
        response.setContentType("application/json");
        response.getOutputStream().write(payload);
        response.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String postId = req.getParameter("postId");
        String requesterUserId = Authentication.extractUserIdFromSession(req);

        DeletePostUsecase usecase = new DeletePostUsecase();
        usecase.execute(postId,requesterUserId);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requesterUserId = Authentication.extractUserIdFromSession(req);

        JSONObject jsonObject =  new JSONObject(new String(req.getInputStream().readAllBytes()));
        String title = jsonObject.getString("title");
        String content = jsonObject.getString("content");
        String authorizedGroup = jsonObject.getString("authorizedGroup");
        String postId = jsonObject.getString("postId");

        UpdatePostUsecase usecase = new UpdatePostUsecase();
        usecase.execute(postId,title,content,authorizedGroup,requesterUserId);

        resp.setStatus(202);
    }
}