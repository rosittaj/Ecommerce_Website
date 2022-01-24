<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
        <link rel="stylesheet" href="css/formStyle.css">
        <script src="js/Validation_JS.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
        <title>user registration</title>
       
</head>
    <body>
        <div class="topnav">
        <img  src="img/CompanyLogo.jpg" style="height:80px;width:90px;float: left;">
            <a  href="home.jsp">Home</a>
            <a href="#news">News</a>
            <a href="#contact">Contact</a>
            <a href="#about">About</a>
              <a href="login.jsp" style="float:right;"><i class='far fa-user-circle' style='font-size:24px'></i></a>
        </div><br><br><br><br>
     <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
        <div >
            <div class="container">
           
                <form action="Register" method="post">
                    <input   type="text"   name="name" placeholder="Full Name"  onfocus="this.value=''" required><br>
                    <input  type="email"  name="email" placeholder="Email" id="Email"  title="Please Enter Valid Email Id" onfocus="this.value=''" required><br>
			       	<input  type="password"  name="password" placeholder="Password" id="Password" onblur="validatePwd()"  onfocus="this.value=''" required><br>
                    <input  type="number"  name="phno" placeholder="Mobile Number" title="Phone number with 7-9 and remaing 9 digit with 0-9" id="phone" onfocus="this.value=''" required><br>
                    <input   type="text"  pattern="[a-zA-Z]*"name="country" placeholder="Country" id="Country" onfocus="this.value=''" required><br>
                    <input  type="number"  name="amount" placeholder="Balance" id="balance" onfocus="this.value=''" required><br>
                    <input class="btn"  type="submit" id="btn" value="Submit" onclick="alerting()">
			   </form>
           </div> 
       </div>

<br><br><br><br><br>

<script>
function validatePwd() {
    var p = document.getElementById('Password').value,errors = [];
    if (p.length < 8) {
        errors.push("Your password must be at least 8 characters"); 
    }
    if (p.search(/[a-z],[A,Z]/i) < 0) {
        errors.push("Your password must contain at least one uppercase and lowercase.");
    }
    if (p.search(/[0-9]/) < 0) {
        errors.push("Your password must contain at least one digit."); 
    }
    
}
</script>



        <div class="footer">
            <br>
            © 2021 Rositta Joy InApp TVM
        </div>
    </body>	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>