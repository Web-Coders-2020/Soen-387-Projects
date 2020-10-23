<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>JSP Page</title>

    <style media="screen" type="text/css">
        .chat {
            width: 100%;
            height: 200px;
            border: 1px solid silver;
            overflow-y: scroll;
            background-color: black;
            color: white;
        }
        #msg {width: 99%;}
        h1 {text-align: center;}


        .container img {
            float: left;
            max-width: 60px;
            width: 100%;
            margin-right: 20px;
            border-radius: 50%;
        }

        .container::after {
            content: "";
            clear: both;
            display: table;
        }

        .container img.right {
            float: right;
            margin-left: 20px;
            margin-right:0;
        }

        .time-right {
            float: right;
            color: #aaa;
        }
    </style>

    <link rel="stylesheet" href="styles/main.css" type="text/css"/>

</head>

<%@ page import="java.util.*" %>
<%@ page import="java.time.ZonedDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.ZoneId" %>
<%@page import="com.assignment.one.ChatServlet"%>

<body id="home">
    <h1>Live Chat updates</h1>
<h2>


    <%
        String name=request.getParameter("user");
        out.print("Welcome " +name);
    %>
</h2>
    

<div>
    <div id="chat" class="chat">

        <%
            ZonedDateTime nowZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
            String formattedStringUTC = nowZonedDateTime.format(formatter);
        %>

       <p>${chats}<br> Time: <%= formattedStringUTC %></p>

    </div>
    <div>
        <form action="ChatServlet" id="myForm" method="post">
            <input type="hidden" name="checkRefHeader" value="check"/>
            <input type="text" name="msg" id="msg" placeholder=""/>
            <input type="text" name="user" id="user" placeholder="Enter user name here"/>

            <input type="submit"  name="submit" value="Enter">
            <input type="reset" name="reset" value="Reset">
        </form>
    </div>
</div>

</body>
</html>
