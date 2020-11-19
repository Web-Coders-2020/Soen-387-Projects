

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
 * Servlet implementation class RecentPost
 */
@WebServlet("/RecentPost")
public class RecentPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecentPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("dashboard.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		System.out.println("At recent post: " + user_id);
		
		String num = request.getParameter("num_recent");
		int max = 10;
		try {
			max = Integer.parseInt(num);
		} catch (Exception e) {
			max = 10;
		}
		
		PostController.MAX_RECENT = max;
		ArrayList<Post> posts = PostController.getRecent();
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i).title);
			System.out.println(posts.get(i).data);
		}
		
		request.setAttribute("posts", posts);
		RequestDispatcher view = request.getRequestDispatcher("viewrecentpost.jsp");
        view.forward(request, response);
	}

}
