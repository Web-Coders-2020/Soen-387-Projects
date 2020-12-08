<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


    <script type="text/javascript" src="api.js"></script>



</head>

<body>

<a href="/index.html" >go to home</a>
<br><br><br><br>
<br>
<br>
<br>
<br>
Update Post
<br>
Title: <input type="text" name="title" id="title" value="<%= request.getAttribute("title") %>"><br>
Content: <input type="text" name="content" id="content" value="<%= request.getAttribute("content") %>"><br>
Authorized Group: <input type="text" name="authorizedGroup" id="authorizedGroup" value="<%= request.getAttribute("authorizedGroup") %>"><br>
<button onclick="callUpdatePostAndRedirectToMainPageIfSuccessful(
        <%= request.getAttribute("postId") %>,
        document.getElementById('title').value,
        document.getElementById('content').value,
        document.getElementById('authorizedGroup').value)">update post</button>
<br>
<br>

</body>
</html>
