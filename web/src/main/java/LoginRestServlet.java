import abir.shah.messageBoardSystem.exception.NoSuchUser;
import abir.shah.messageBoardSystem.usecase.AuthenticateUserUsecase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = {"login"}, loadOnStartup = 1)
public class LoginRestServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        AuthenticateUserUsecase userUsecase = new AuthenticateUserUsecase();

        try{
            AuthenticateUserUsecase.Token token = userUsecase.authenticate(userId,password);
            response.addCookie(new Cookie("session",token.getContent()));
            response.setStatus(200);
        }catch (NoSuchUser e)
        {
            response.setStatus(401);
        }
    }
}