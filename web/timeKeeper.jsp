


<%
        session = request.getSession();
        String user = (String)session.getAttribute("user");
        if(user.equals(null)){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        
        
    %>
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
        <a class="navbar-brand" href="#" style="color: white;font-size:30px;font-family: tangerine;
	font-style: normal;
	font-weight: 400;">
            <%@ page import="Emp.Employee"%>
            <%@ page import="Emp.TimeKeeper"%>
            <%
                String empID = (String)session.getAttribute("user");
                out.print(" "+new Employee().getName(empID));
                
                
            
            %>
        </a></div>
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="topFixedNavbar1">
        <ul class="nav navbar-nav">
          <li class="active"></li>
          <li></li>
        </ul>
       
        <ul class="nav navbar-nav navbar-right">
            <li><a><form action="ToMyProfileHandle" method="post">
                        <input type="submit" class="editProf" value="To my profile" />
                    </form></a></li>
         <li><a><form action="ChagePasswordHandle" method="post">
                        <input type="submit" class="editProf" value="Update Password" />
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
  
  <div class="col-md-4" id="leftCol">
  	
      <div class="row timeKeeperRow container-fluid center-block">
      <h2 class="timeKeeperHeading">Add New Record</h2>
      <form action="TimeKeeperHandle" method="get" class="center-block container-fluid" onSubmit="if(!confirm('Is the form filled out correctly?')){return false;};setTimeout(function () { window.location.reload(); }, 10);">
      <input type="number" name="empID" class="email timekeepertext" required placeholder="Employee ID"><br>
      <input type="number" name="hours" class="email timekeepertext" required placeholder="Working Hours(h)"><br>
      <input type="number" name="uPrice" class="email timekeepertext" required placeholder="Unit Price(Rs)"><br>
      <input type="number" name="taxes" class="email timekeepertext" required placeholder="Labour Taxrate(%)"><br>
      <button type="submit" class="submitBtn2 ">Submit</button>
      </form>
      <!---
      <br>
      <P class="CalculatedText">Gross Salary : </P><br>
      <P class="CalculatedText">Total Tax    : </P><br>
      <P class="CalculatedText">Net Salary   : </P><br>
      
      ---->
      </div>
          
      </div>
  
  <div class="col-md-8" id="rightCol">
  <div class="TotalDetails center-block container-fluid"><p class="empData">All Payrolls</p></div>
  <div class="row tableRow">
  		<table class="w3-table w3-striped">
                    <tr>
                      
                      <th>Transaction ID</th>
                      <th>Employee ID</th>
                      <th>Date and Time</th>
                      
                      <th>Gross Pay(Rs)</th>
                      <th>Labour Taxes(%)</th>
                      <th>Net Pay(Rs)</th>
                    </tr>
            <!-----jsp rows -------------shan---------------------->
                    <%@ page import="Tables.TimeKeeperTable"%>
                    <%@ page import="java.util.ArrayList"%>
                    <%
                    ArrayList <String> arr = new ArrayList<>();
                    arr = new TimeKeeperTable().setRows();
                    for(String zzz:arr){
                        if(zzz!=null){
                            out.print(zzz);
                        }
                    }
                    
                    %>
                    
                    <!-------------------------------->
        </table>
     </div>
    
     
  </div>
  
  
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
	<script src="js/jquery-1.11.2.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed --> 
	<script src="js/bootstrap.js"></script>
  </body>
</html>