<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<%
		String user_id = (String)request.getAttribute("user_id");
		String user_name = (String)request.getAttribute("user_name");
		String user_email = (String)request.getAttribute("user_email");
	%>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<h2>Logged In: <%=user_id %></h2>
	<h4><%=user_name %></h4>
	<h4><%=user_email %></h4>
	<br />
	<br />
	<%
		session.setAttribute("user_id",user_id);
	%>
	<a href="createpost.jsp?user_id=${user_id}">Create Post</a> <br />
	<a href="viewrecentpost.jsp?user_id=${user_id}">View Recent Posts</a> <br />
	<a href="searchpostuser.jsp?user_id=${user_id}">Search for Posts By UserId</a> <br />
	<a href="searchpostdate.jsp?user_id=${user_id}">Search for Posts By Date</a> <br />
	<a href="searchposthash.jsp?user_id=${user_id}">Search for Posts By Hashtags</a> <br />
	 <br />
	<form action="Login" method = "GET">
		<input type="hidden" name="user_id" value=<%=user_id %> /> <br />
		<input type="hidden" name="logout" value="logout" /> <br />
		<input type="submit" value="Logout" /> <br />
	</form>
	
</body>
</html>