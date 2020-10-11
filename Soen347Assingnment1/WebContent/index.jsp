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
   
    <body>
        <h1>Live Chat updates</h1>

<div>
  
    <div id="chat" class="chat">
   hh
  ${chats}<br>
      
      </div>
         <div>
           <form action="ChatServlet" method="post">
	            <input type="text" name="msg" id="msg" placeholder=""/>
	    	    <input type="text" name="user" id="user" placeholder="Enter user name here"/>
	    
                <input type="submit" value="Enter">
           </form> 
         </div>
      </div>
     </body>
</html>
