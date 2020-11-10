<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <style>
        * {
            box-sizing: border-box;
        }
        /* Add a gray background color with some padding */
        body {
            font-family: Arial;
            padding: 20px;
            background: #f1f1f1;
        }
        /* Header/Blog Title */
        .header {
            padding: 10px;
            font-size: 40px;
            text-align: center;
            background: orange;
        }



        /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 800px) {
            .leftcolumn, .rightcolumn {
                width: 100%;
                padding: 0;
            }
        }

    </style>

    <title>Feature Not Implemented Yet</title>
</head>
<body>

<h2 style="color:green;" align ="center" >Please Login to Post Manually</h2>
<div class="header" >
    <h2>Your SOEN387 Blog</h2>
</div>
     <p>Sorry, the other features are not implemented yet! We will be releasing them soon!</p>
<p></p>

     <p></p>
     <a href="./login.jsp" class="fb btn">
         <i class="fa fa-facebook fa-fw"></i> Please click here to return to the Login Page
     </a>

</body>
</html>
