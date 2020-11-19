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
	<h2>Search for Posts By Hashtag</h2>
	<%
		session.setAttribute("user_id",user_id);
	%>
	<form action = "Login" method = "GET">
	   <input type = "submit" value = "Go To Dashboard" /> <br />
	</form>
	<br />
	<br />
	
	<form action="SearchPostDate" method = "POST">
		StartDate: <input type="date" name="search_start_date" /> <br />
		EndDate: <input type="date" name="search_end_date" /> <br />
		<input type="hidden" name="user_id" value=<%=user_id %> /> <br />
		<input type="submit" value="Search" /> <br />
	</form>

</body>
</html>