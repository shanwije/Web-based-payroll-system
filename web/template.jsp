<%-- 
    Document   : template
    Created on : Apr 24, 2016, 11:11:11 AM
    Author     : Shan Wijenayaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Cookie[] ck = request.getCookies();
        String nme=(String) session.getAttribute("user");
        out.println(nme);
        for(Cookie cook:ck){
            out.println(cook);
            if(cook.getValue()=="5"){
                out.println("yeah here it is 5");
            }
        }
            %>
        <h1>Hello World!</h1>
    </body>
</html>
