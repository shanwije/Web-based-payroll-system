



<%
        session = request.getSession();
        String user = (String)session.getAttribute("user");
        if(user.equals(null)){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        
        
    %>
    
    <%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <title>Untitled Document</title>
    <!-- Bootstrap -->
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<link href="css/tmp.css" rel="stylesheet" type="text/css">

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
  <!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.-->
  <script>var __adobewebfontsappname__="dreamweaver"</script>
  <script src="http://use.edgefonts.net/adamina:n4:default.js" type="text/javascript"></script>

  </head>
  <body  class="fullBody" style="padding-top: 50px">
  
  <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#topFixedNavbar1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
        <a class="navbar-brand" href="#" style="color: white">
            <%@ page import="Emp.Employee"%>
            <%
                String empID = (String)session.getAttribute("user");
                out.print("Welcome "+new Employee().getName(empID));
                
                
            
            %>
        </a></div>
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="topFixedNavbar1">
        <ul class="nav navbar-nav">
          <li class="active"></li>
          <li></li>
        </ul>
       
        <ul class="nav navbar-nav navbar-right">
         
          <li><a><form action="ChagePasswordHandle" method="post" onSubmit="if(!confirm('Profile has updateted')){return false;};">
                        <input type="submit" class="editProf" value="Update Password" />
                    </form></a></li>
          <li><a><form action="AccessEditProf" method="post">
                        <input type="submit" class="editProf" value="Edit Profile" />
                    </form></a></li>
          <li><a><form action="Logout" method="post">
                        <input type="submit" class="editProf" value="Sign Out" />
                    </form></a> </li>
        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
  </nav>
  <!------------end of navBar------------------------------------------------------------>
  
  <div class="col-md-4 hidden-xs"></div>
  <div class="col-md-4 fullColqq">
  	<div class="row fullRowqq">
    <h3 class="headingqq">Update Information</h3>
    	<form action="UpdateEmpInfo" method="post"  >
        <table class="formTableqq" >
            <%
                Employee em = new Employee();

                %>
        <tr class="trqq">
            <td class="formTDqq"><p class="formTextqq">Full Name:</p>
                <input type="text" name="name" value="<%=em.getName(empID) %>" class="formFieldsqq email" required>
            </td>
        </tr>
        <tr>
            <td class="formTDqq"><p class="formTextqq">Email Address:</p>
                <input type="email" name="email" value=<%=em.getEmail(empID) %> class="formFieldsqq email" required>
            </td>
         </tr>
         <tr>   
            <td class="formTDqq"><p class="formTextqq">Contact No:</p>
                <input type="tel" name="tel" value=<%=em.getContactNo(empID)%> class="formFieldsqq email" required>
            </td>
         </tr>
         <tr>   
            <td class="formTDqq"><p class="formTextqq">Birthday:</p>
                <input type="date" name="birthDay" value=<%=em.getBirthDay(empID) %> max="2016-01-01" min="1950-01-01" class="formFieldsqq email" required>
            </td>
         </tr>
          <tr>   
            <td class="formTDqq"><p class="formTextqq">Address:</p>
                <input type="text" name="address" value="<%=em.getAddress(empID) %>" class="formFieldsqq email" required>
            </td>
         </tr>
         <tr>   
            <td class="formTDqq">
                <button type="submit" class="submitBtn " style="margin-top:35px">Update Information</button>
               
            </td>
         </tr>
            
        
        
        </table>
        </form>	
    
    </div>
  </div>
<div class="col-md-4 hidden-xs"></div>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
	<script src="js/jquery-1.11.2.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed --> 
	<script src="js/bootstrap.js"></script>
  </body>
</html>