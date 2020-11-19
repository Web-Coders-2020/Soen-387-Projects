<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<%
		//String user_id = (String)request.getAttribute("user_id");
		//String user_name = (String)request.getAttribute("user_name");
		//String user_email = (String)request.getAttribute("user_email");
		String user_id = (String)session.getAttribute("user_id");
	%>
<head>
<meta charset="ISO-8859-1">
<title>PostHub</title>
</head>
<body>
	<h2>Search for Posts</h2>
	<br />
	<br />
	<%
		session.setAttribute("user_id",user_id);
	%>
	<form action = "CreatePost" method = "POST" enctype = "multipart/form-data">
		Title: <br /><input type="text" name="title" /> <br />
		Content: <br />
		<!-- input type="text" name="content" /> <br /-->
		<textarea name="content" cols="80" rows="6"></textarea>
		<br />
		Attach file: <input type = "file" name = "file" id="file" size = "50" />
		<br />
		<input type="hidden" name="user_id" value=<%=user_id %> /> <br />
		<input type="submit" value="Create Post" /> <br />
	</form>
</body>
</html>