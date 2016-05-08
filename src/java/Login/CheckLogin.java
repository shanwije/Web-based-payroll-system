/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import DBConnection.ConnectDB;
import Emp.Employee;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shan Wijenayaka
 */
public class CheckLogin extends HttpServlet {

    
    String empID,CID;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        GenCookie gc = new GenCookie();
        Employee em = new Employee();
                
        Logger logger = new Logger(request.getParameter("empID"),request.getParameter("password"));
      //  System.out.println(request.getParameter("keepLogin"));
        
        empID=request.getParameter("empID");
        
      
                
            
        
                if(logger.checkLogin()){
                    
                    
                    logger.addCurrentDate(empID);
                    HttpSession session = request.getSession(true);

                        session.setAttribute("user", request.getParameter("empID"));
                        session.setAttribute("password", request.getParameter("password"));

                        // to get the username and password
            switch (em.getJobID(empID)) {
                case "2000":
                    session.setAttribute("job", "2000");
                    if(request.getParameter("keepLogin")!=null && ((String)(request.getParameter("keepLogin"))).equals("keep")){
                        CID = gc.genCookieID();
                        System.out.println("from CheckLogin generated CID:"+CID);
                        Cookie ck = new Cookie("CID",CID);
                        ck.setMaxAge(60*60*24);
                        gc.cookieIDToDB(CID, empID);
                        
                        response.addCookie(ck);
                    }
                    response.sendRedirect("ceo.jsp");
                    break;
                case "2001":
                    session.setAttribute("job", "2001");
                    if(request.getParameter("keepLogin")!=null && ((String)(request.getParameter("keepLogin"))).equals("keep")){
                        CID = gc.genCookieID();
                        System.out.println("from CheckLogin generated CID:"+CID);
                        Cookie ck = new Cookie("CID",CID);
                        ck.setMaxAge(60*60*24);
                        gc.cookieIDToDB(CID, empID);
                        
                        response.addCookie(ck);
                    }
                    response.sendRedirect("timeKeeper.jsp");
                    break;
                default:
                    session.setAttribute("job", "2002");
                    if(request.getParameter("keepLogin")!=null && ((String)(request.getParameter("keepLogin"))).equals("keep")){
                        CID = gc.genCookieID();
                        System.out.println("from CheckLogin generated CID:"+CID);
                        Cookie ck = new Cookie("CID",CID);
                        ck.setMaxAge(60*60*24);
                        gc.cookieIDToDB(CID, empID);
                        
                        response.addCookie(ck);
                    }
                    response.sendRedirect("employee.jsp");
                    break;
            }
                    
                        //response.sendRedirect("employee.jsp");
                          
                        System.out.println("login successful");
                        
                    
                }
                else{
                        request.setAttribute("err", "User ID or password is incorrect");
                        System.out.println("wrong email or password");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                }
        }
        
    

  

}
