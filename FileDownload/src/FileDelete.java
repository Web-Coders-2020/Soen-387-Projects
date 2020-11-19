

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
 * Servlet implementation class FileDelete
 */
@WebServlet("/FileDelete")
public class FileDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDelete() {
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
		
		if(user_id.equals(post_user_id)) {
			try {
				Integer id = Integer.parseInt(post_id);
				Post post = PostController.searchById(id);
				Timestamp modified = new Timestamp(System.currentTimeMillis());
				PostController.update(post.id, post.title, user_id, modified, post.content, null);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.getSession().setAttribute("user_id", user_id);
		response.sendRedirect("/FileDownload/Login");
	}

}
