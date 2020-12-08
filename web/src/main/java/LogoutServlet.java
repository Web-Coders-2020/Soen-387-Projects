import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = {"logout"}, loadOnStartup = 1)
public class LogoutServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Cookie cookie = new Cookie("session","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }
}