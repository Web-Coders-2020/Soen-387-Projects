<%@page import="library.Post"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<%
		//String user_id = (String)request.getAttribute("user_id");
		//String user_name = (String)request.getAttribute("user_name");
		//String user_email = (String)request.getAttribute("user_email");
		String user_id = (String)session.getAttribute("user_id");
		ArrayList<Post> posts = (ArrayList<Post>)request.getAttribute("posts");
	%>
<head>
<meta charset="ISO-8859-1">
<title>PostHub</title>
</head>
<body>
	<h2>Recent Posts</h2>
	<%
		session.setAttribute("user_id",user_id);
	%>
	<form action = "Login" method = "GET">
	   <input type = "submit" value = "Go To Dashboard" /> <br />
	</form>
	<br />
	<br />
	
	<form action="RecentPost" method = "POST">
		Max Recent: <input type="text" name="num_recent" /> <br />
		<input type="hidden" name="user_id" value=<%=user_id %> /> <br />
		<input type="submit" value="View Recent Posts" /> <br />
	</form>
	
	<%
		if(posts != null) {
			%>
			<table style="width:100%" >
			
			<%
			for(int i = 0; i < posts.size(); i++) {
	        	Post post = posts.get(i);
	        	%>
	        	
	        	<tr>
	        		<td>
	        			<tr>
		        			<td>
					        	<p><h3><%= post.title %> </h3><br />
					        		 By: <%= post.userId %> <br />
					        		 Created:  <%=post.created %> <br />
					        		 Modified:  <%=post.modified %> <br />
					        	</p>
			        			<p><%= post.content %></p>
			        		</td>
		        		</tr>
		        		<tr>
			        		<td>	
				        		<form action="FileDownload" method = "POST">
				        			<input type="hidden" name="user_id" value=<%=user_id %> />
				        			<input type="hidden" name="post_id" value=<%=post.id %> />
				        			<input type="hidden" name="post_user_id" value=<%=post.userId %> />
				        			<input type="submit" value="Download Attachment" />
				        		</form>
		        			</td>
		        			<td>
				        		<form action="FileDelete" method = "POST">
				        			<input type="hidden" name="user_id" value=<%=user_id %> />
				        			<input type="hidden" name="post_id" value=<%=post.id %> />
				        			<input type="hidden" name="post_user_id" value=<%=post.userId %> />
				        			<input type="submit" value="Delete Attachment" />
				        		</form>
		        			</td>
	        			</tr>
	        			<tr><td>
			        		<form action="FileReplace" method = "POST" enctype = "multipart/form-data">
			        			<input type="hidden" name="user_id" value=<%=user_id %> />
			        			<input type="hidden" name="post_id" value=<%=post.id %> />
			        			<input type="hidden" name="post_user_id" value=<%=post.userId %> />
			        			<input type="hidden" name="title" value=<%=post.title %> />
			        			<input type="hidden" name="content" value=<%=post.content %> />
			        			Replace file: <input type = "file" name = "file" id="file" size = "20" /><input type="submit" value="Go" />
			        		</form>
	        			</td></tr>
	        		</td>
	        	
	        		<td>
	        			<br />
	        			<br />
			        	<form action="UpdatePost" method = "POST">
			        		New Title: <input type="text" name="new_title" /> <br />
			        		New Content: <br />
			        		<textarea name="new_content" cols="80" rows="6"></textarea><br />
		        			<input type="hidden" name="user_id" value=<%=user_id %> />
		        			<input type="hidden" name="post_id" value=<%=post.id %> />
		        			<input type="hidden" name="post_user_id" value=<%=post.userId %> />
		        			<input type="hidden" name="title" value=<%=post.title %> />
		        			<input type="hidden" name="content" value=<%=post.content %> />
		        			
		        			<input type="submit" value="Update Post" />
	        			</form>
			        </td>
			        
			        <td>
	        			<br />
	        			<br />
			        	<form action="DeletePost" method = "POST">
		        			<input type="hidden" name="user_id" value=<%=user_id %> />
		        			<input type="hidden" name="post_id" value=<%=post.id %> />
		        			<input type="hidden" name="post_user_id" value=<%=post.userId %> />
		        			<input type="submit" value="Delete Post" />
	        			</form>
			        </td>
	        	
	        	</tr>
	        	<hr />
	        	<%
	        	//out.println(actor.getFirstname());
	        	//out.println(actor.getLastname());
			}
			%>
			
			</table>
			
		<%	
		}
    %>
    
</body>
</html>