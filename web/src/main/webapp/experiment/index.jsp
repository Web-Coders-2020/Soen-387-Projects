<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


    <script type="text/javascript" src="api.js"></script>



</head>
<body>

Login
<br>
UserName: <input type="text" name="userName" id="userName"><br>
Password: <input type="text" name="password" id="password"><br>
<button onclick="callLogin(document.getElementById('userName').value,document.getElementById('password').value)">login</button>
<br>

<br>
<br>
<br>
<br>
CreatePost
<br>
Title: <input type="text" name="title" id="title"><br>
Content: <input type="text" name="content" id="content"><br>
Authorized Group: <input type="text" name="authorizedGroup" id="authorizedGroup"><br>
<button onclick="createPost(document.getElementById('title').value,document.getElementById('content').value,document.getElementById('authorizedGroup').value)">create post</button>
<br>
<br>
<br>
<br>
<button onclick="fetchPosts()">fetch posts</button>
<p id="p">Posts result here:</p>
</body>
</html>
