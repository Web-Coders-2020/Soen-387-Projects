import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.usecase.CreatePostUsecase;
import abir.shah.messageBoardSystem.usecase.FetchPostsUsecase;

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

@WebServlet(name = "Posts", urlPatterns = {"posts"}, loadOnStartup = 1)
public class PostsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


      /*  Authentication.authenticateUser(request);


        String creatorId = request.getParameter("creatorId");
        String startDateParameter = request.getParameter("startDate");
        String endDateParameter = request.getParameter("endDate");
        Date startDate = (startDateParameter == null) ? null : Date.valueOf(startDateParameter);
        Date endDate = (endDateParameter == null) ? null : Date.valueOf(endDateParameter);

        String hashTagsParameter = request.getParameter("hashTags");

        List<String> hashTags = hashTagsParameter == null ? new ArrayList<>(): Arrays.asList(hashTagsParameter.split(","));

        FetchPostsUsecase usecase = new FetchPostsUsecase();
       List<Post> postList = usecase.execute("",creatorId, startDate,endDate, hashTags);

        response.getWriter().print(postList.toString());*/
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String requesterUserId = Authentication.extractUserIdFromSession(request);


        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String authorizedGroup = request.getParameter("authorizedGroup");


        CreatePostUsecase usecase = new CreatePostUsecase();
        String postId = usecase.execute(title,content,requesterUserId,authorizedGroup);

        response.getOutputStream().write();
        response.setStatus(201);

        //request.setAttribute("postId", postId);
        //request.getRequestDispatcher("response.jsp").forward(request, response);
    }
}