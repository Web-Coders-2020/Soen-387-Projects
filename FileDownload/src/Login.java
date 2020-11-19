

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.User;
import library.UserController;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<User> loggedInUsers = new ArrayList();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		String logout = request.getParameter("logout");
		
		if(logout != null && logout.equals("logout")) {
			user = null;
			response.sendRedirect("login.html");
			return;
		}
		
		String user_id = (String) request.getSession().getAttribute("user_id");
		System.out.println("At login get recd: " + user_id);
		
		if(user_id != null) {
			for(int i = 0; i < loggedInUsers.size(); i++) {
				if(loggedInUsers.get(i).getId().equals(user_id)) {
					user = loggedInUsers.get(i);
				}
			}
		}
		
		if(user != null) {
			request.setAttribute("user_id", user.getId());
			request.setAttribute("user_name", user.fullName());
			request.setAttribute("user_email", user.getEmail());

			RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
            view.forward(request, response);
		} else {
			response.sendRedirect("login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);

		String user_id = request.getParameter("user_id");
		String pass = request.getParameter("pass");
		
		
		// For testing
		//user_id = "user1";
		//pass = "abc";
		
		UserController.addUsers("F:\\Development\\Eclipse_Java_ws\\FileDownload\\WebContent\\WEB-INF\\userdata.json");
		User user = null;
		
		for(int i = 0; i < loggedInUsers.size(); i++) {
			if(loggedInUsers.get(i).getId().equals(user_id)) {
				user = loggedInUsers.get(i);
			}
		}
		if(user != null) {
			request.setAttribute("user_id", user.getId());
			request.setAttribute("user_name", user.fullName());
			request.setAttribute("user_email", user.getEmail());

			RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
            view.forward(request, response);
		}
		
		if(user == null) {
			user = UserController.auth(user_id, pass);
		}
		
		if(user != null) {
			request.setAttribute("user_id", user.getId());
			request.setAttribute("user_name", user.fullName());
			request.setAttribute("user_email", user.getEmail());

			loggedInUsers.add(user);

			RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
            view.forward(request, response);
		} else {
			response.sendRedirect("login.html");
		}
		
	}

}
