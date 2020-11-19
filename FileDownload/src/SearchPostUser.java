

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.Post;
import library.PostController;

/**
 * Servlet implementation class SearchPostUser
 */
@WebServlet("/SearchPostUser")
public class SearchPostUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPostUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String search_user_id = request.getParameter("search_user_id");
		
		ArrayList<Post> posts = PostController.searchByUser(search_user_id);
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i).title);
			System.out.println(posts.get(i).data);
		}
		
		request.setAttribute("posts", posts);
		RequestDispatcher view = request.getRequestDispatcher("searchpostuser.jsp");
        view.forward(request, response);
	}

}
