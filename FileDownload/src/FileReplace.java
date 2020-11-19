

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import library.PostController;

/**
 * Servlet implementation class FileReplace
 */
@WebServlet("/FileReplace")
public class FileReplace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileReplace() {
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
		
		Blob my_blob = null;
		
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
			    FileItem item = iter.next();
			    if (item.isFormField()) {
			    	String name = item.getFieldName();
			        String value = item.getString();
			        if(name.equals("user_id")) user_id = value;
			        else if(name.equals("post_id")) post_id = value;
			        else if(name.equals("post_user_id")) post_user_id = value;
			        else if(name.equals("title")) title = value;
			        else if(name.equals("content")) content = value;
			    } else {
			    	byte[] data = item.get();
			    	my_blob = new SerialBlob(data);
			    }
			}
			
			Integer id = Integer.parseInt(post_id);
			Timestamp modified = new Timestamp(System.currentTimeMillis());
			PostController.update(id, title, user_id, modified, content, my_blob);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("user_id", user_id);
		response.sendRedirect("/FileDownload/Login");
	}

}
