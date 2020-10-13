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
        }
        #msg {width: 99%;}
        h1 {text-align: center;}
        </style>
    </head>
   
<%@ page import="java.util.*" %>
<%@ page import="java.time.ZonedDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.ZoneId" %>
    <body>
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
   
  <p>${chats}<br>UTC Time: <%= formattedStringUTC %></p>
      
      </div>
         <div>
           <form action="ChatServlet" id="myForm" method="post">
	            <input type="text" name="msg" id="msg" placeholder=""/>
	    	    <input type="text" name="user" id="user" placeholder="Enter user name here"/>
	    
                <input type="submit" value="Enter">
           </form> 
         </div>
      </div>
	    
 <%
    session.setAttribute("user",name);
%>    
	    
     </body>
</html>
