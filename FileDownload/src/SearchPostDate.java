

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.Post;
import library.PostController;

/**
 * Servlet implementation class SearchPostDate
 */
@WebServlet("/SearchPostDate")
public class SearchPostDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPostDate() {
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
		String search_start_date = request.getParameter("search_start_date");
		String search_end_date = request.getParameter("search_end_date");
		
		Date st;
		try {
			st = new SimpleDateFormat("yyyy-MM-dd").parse(search_start_date);
			Date en = new SimpleDateFormat("yyyy-MM-dd").parse(search_end_date);
			
			ArrayList<Post> posts = PostController.searchByDateRange(new Timestamp(st.getTime()), 
					new Timestamp(en.getTime()));
			
			request.setAttribute("posts", posts);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*ArrayList<Post> posts = PostController.searchByHashTag(search_hash);
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i).title);
			System.out.println(posts.get(i).data);
		}*/
		
		//request.setAttribute("posts", posts);
		RequestDispatcher view = request.getRequestDispatcher("searchpostuser.jsp");
        view.forward(request, response);
	}

}
