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

</body>
</html>
