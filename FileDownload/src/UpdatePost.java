

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.Post;
import library.PostController;

/**
 * Servlet implementation class UpdatePost
 */
@WebServlet("/UpdatePost")
public class UpdatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePost() {
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
		String post_id = request.getParameter("post_id");
		String post_user_id = request.getParameter("post_user_id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String new_title = request.getParameter("new_title");
		String new_content = request.getParameter("new_content");
		
		if(user_id.equals(post_user_id)) {
			try {
				if(new_title.trim().length() > 0) {
					title = new_title;
				}
				
				if(new_content.trim().length() > 0) {
					content = new_content;
				}
				
				Integer id = Integer.parseInt(post_id);
				Post post = PostController.searchById(id);
				Timestamp modified = new Timestamp(System.currentTimeMillis());
				PostController.update(id, title, user_id, modified, content, post.data);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.getSession().setAttribute("user_id", user_id);
		response.sendRedirect("/FileDownload/Login");
		
	}

}
