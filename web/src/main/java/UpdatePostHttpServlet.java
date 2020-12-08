import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.usecase.FetchPostDetailsUsecase;
import abir.shah.messageBoardSystem.usecase.FetchPostsUsecase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdatePostHttpPage", urlPatterns = {"update-post"}, loadOnStartup = 1)
public class UpdatePostHttpServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requesterUserId = Authentication.extractUserIdFromSession(request);
        String postId = request.getParameter("postId");

        FetchPostDetailsUsecase usecase = new FetchPostDetailsUsecase();
        Post post = usecase.execute(requesterUserId,postId);

        request.setAttribute("postId","'"+postId+"'");
        request.setAttribute("title",post.getTitle());
        request.setAttribute("content",post.getContent());
        request.setAttribute("authorizedGroup",post.getAuthorizedGroupToView() == null ? "" : post.getAuthorizedGroupToView());
        request.getRequestDispatcher("/updatepost.jsp").forward(request,response);
    }
}