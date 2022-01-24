<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/formStyle.css">
        <title>LOGIN PAGE </title>
     
       <script src="Validation_JS.js"></script>
    </head>
    <body>
        <div class="topnav">
        <img  src="img/CompanyLogo.jpg" style="height:80px;width:90px;float: left;">
            <a href="home.jsp">Home</a>
            <a href="#news">News</a>
            <a href="#contact">Contact</a>
            <a href="#about">About</a>
          </div>
        <h1>Login</h1>
        <div >
            <div class="container">
                <form action="Login" method="post">
                    <input  type="email"  name="email" placeholder="Email" id="Email" " onfocus="this.value=''"><br>
				    <input  type="password"  name="password" placeholder="Password" id="Password"  onfocus="this.value=''"><br>
                    <input class="btn"  type="submit" id="btn" value="Submit" ><br><br>
                    <b><a href="register.jsp" style="text-decoration:none">New user</a></b>
				</form>
            </div> 
        </div>
      
<br><br><br><br><br><br><br><br><br><br><br><br><br><br> 
        <div class="footer">
            <br>
            © 2021 Rositta Joy InApp TVM
        </div>
    </body>
</html>