
<%@page import="java.util.ArrayList"%>
<%@page import="Emp.Employee"%>
<%@page import="Login.Logger"%>
<%@page import="Login.GenCookie"%>
<% boolean allreadyLogged = false; %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache");
            
    System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        GenCookie gc = new GenCookie();
        String empID = "no Emp ID in database"; 
        String cookieValue ="noCookieValue";
        ArrayList<String> arrList = new ArrayList<>();
        Employee em = new Employee();
        
        Cookie[] cookies = request.getCookies();
            System.out.println("check for cookies");
            if (cookies != null) {
             for (Cookie cookie : cookies) {
               if (cookie.getName().equals("CID")) {
                 cookieValue = cookie.getValue();
                 System.out.println("cookie value is "+cookieValue);
                }
              }
            }
        System.out.println("cookie check is ended");
        arrList = gc.getCookieID();
        
        
        for(String g :arrList){
            System.out.println("Cookie ID :"+g);
        }
        System.out.println("check cookie id with empid");
        for(String cid: arrList){
            if(cid.equals(cookieValue)){
                empID = gc.getEmpID(cid);
                System.out.println("empID related to cookie is "+empID);
                allreadyLogged = true;
                new Logger(empID,empID).addCurrentDate(empID);
                session.setAttribute("user", empID);
                if(em.getJobID(empID).equals("2000")){
                    
                        response.sendRedirect("ceo.jsp");
                        
                    }
                    
                    else if(em.getJobID(empID).equals("2001")){
                        response.sendRedirect("timeKeeper.jsp");
                        
                    }
                    
                    else{
                        response.sendRedirect("employee.jsp");
                        
                    }
            }
        }
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        %>
        
        
        
        
        <%
    
    if(!allreadyLogged){
    empID = (String)session.getAttribute("user");
   Logger logg =  new Logger((String)session.getAttribute("user"),
                        (String) session.getAttribute("password"));
    if(logg.checkLogin()){
        logg.addCurrentDate(empID);
                   

                       // session.setAttribute("userName", request.getParameter("empID"));
                        //session.setAttribute("password", request.getParameter("password"));

                        // to get the username and password
                        
                    
                    
                    
                    if(em.getJobID(empID).equals("2000")){
                        response.sendRedirect("ceo.jsp");
                        if(request.getParameter("keepLogin")!=null && ((String)(request.getParameter("keepLogin"))).equals("keep")){
                         Cookie ck = new Cookie("username",empID);
                         response.addCookie(ck);
                          Cookie password = new Cookie("password",request.getParameter("password"));
                          response.addCookie(password);
                    }
                    }
                    else if(em.getJobID(empID).equals("2001")){
                        response.sendRedirect("timeKeeper.jsp");
                        if(request.getParameter("keepLogin")!=null && ((String)(request.getParameter("keepLogin"))).equals("keep")){
                        Cookie ck = new Cookie("username",empID);
                         response.addCookie(ck);
                    }
                    }
                    else{
                        response.sendRedirect("employee.jsp");
                        if(request.getParameter("keepLogin")!=null && ((String)(request.getParameter("keepLogin"))).equals("keep")){
                        Cookie ck = new Cookie("username",empID);
                         response.addCookie(ck);
                    }
                    }
                        //response.sendRedirect("employee.jsp");
                          
                        System.out.println("login successful");
                        
                    
                }
               
    
    }
    %>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Untitled Document</title>
    <!-- Bootstrap -->
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/index.css" rel="stylesheet" type="text/css">
        <!----
                <SCRIPT type="text/javascript">
                    window.history.forward();
                    function noBack() { window.history.forward(); }
                </SCRIPT>
        </head>
  <body class="fullBody" onload="noBack();"onpageshow="if (event.persisted) noBack();">
        --->
       
            
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
  <!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.--><script>var __adobewebfontsappname__="dreamweaver"</script><script src="http://use.edgefonts.net/judson:n4:default;droid-sans:n4:default;sorts-mill-goudy:n5:default;tangerine:n4:default;esteban:n4:default;adamina:n4:default.js" type="text/javascript"></script>
</head>

  <body class="fullBody">
  <div class="row container-fluid">
  
  	<div class="col-md-8 hidden-xs ImgCol" ></div>
    <div class="col-md-4 LoginCol">
   	  <div class="fullLoginRow">
        <h1 class="mainHeading">CILS PAYROLL SYSTEM</h1>
        <br><br>
        <form action="CheckLogin" method="post">
            <input type="name"  name = "empID" class="email center-block" placeholder="Your Employee ID" required>
            <br>
            <input type="password" name = "password" class="email" placeholder= "Password" required>
            <br>
            
            
            <br>
            <input type="checkbox" name= "keepLogin" value="keep">
          <label for="Checkbox1">Keep me Signed in</label>
            <br>
            
          	
          <button type="submit" class=" submitBtn" >Login</button>
        </form>
        <div class="row errorMsg">
        	<p> <% 
            if(request.getAttribute("err") != null){
                out.println(request.getAttribute("err")); 
            }
            
        %></p>
        </div>
        <br>
       <!--- 
        <a href="#"><p class="forgotPassword">Forgot your password?</p></a>
        --->
        
        </div>
    
    </div>
    </div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
	<script src="js/jquery-1.11.2.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed --> 
	<script src="js/bootstrap.js"></script>
</body>
</html>