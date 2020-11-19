

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.Post;
import library.PostController;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet("/FileDownload")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownload() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>HIIII</body></html>");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
				Blob blob = post.data;
				
				InputStream inputStream = blob.getBinaryStream();
                int fileLength = inputStream.available();
                 
                System.out.println("fileLength = " + fileLength);
 
                ServletContext context = getServletContext();
 
                // sets MIME type for the file download
                String mimeType = context.getMimeType("Attachment");
                if (mimeType == null) {        
                    mimeType = "application/octet-stream";
                }
                 
                // set content properties and header attributes for the response
                response.setContentType(mimeType);
                response.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", "Attachment");
                response.setHeader(headerKey, headerValue);
 
                // writes the file to the client
                OutputStream outStream = response.getOutputStream();
                 
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                 
                inputStream.close();
                outStream.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
