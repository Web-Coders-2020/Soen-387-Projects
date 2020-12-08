<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


    <script type="text/javascript" src="api.js"></script>



</head>
<body>


<a href="/index.html" >go to home</a>
<br><br><br><br>


<a href="/logout" >Logout</a>
<br><br><br><br>

<br>
Posts List:

<br>
<br>
creatorId: <input type="text" name="creatorId" id="creatorId"><br>
hashTags: <input type="text" name="hashTags" id="hashTags"><br>
startDate: <input type="text" name="startDate" id="startDate"><br>
endDate: <input type="text"   name="endDate" id="endDate"><br>
<br>
<br>
<button onclick="callFetchPostsAndAppendResultToPage(
    document.getElementById('creatorId').value,
    document.getElementById('startDate').value,
    document.getElementById('endDate').value,document.getElementById('hashTags').value,)">fetch posts</button>


    <div id="postsDiv"/>

</body>
</html>
