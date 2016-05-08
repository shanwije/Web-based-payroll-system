/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emp;

import DBConnection.ConnectDB;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shan Wijenayaka
 */
public class UpdatePassword extends HttpServlet {

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY------"+request.getParameter("password")+"---------------------------------");
        updateP(request.getParameter("password"), request, response);
    
    }
    
    
    public void updateP(String password, HttpServletRequest request, HttpServletResponse response){
        HttpSession s= request.getSession(false);
        String empID = (String)s.getAttribute("user");
        
        Connection conn = new ConnectDB().connect();
        String sql1 = "UPDATE dbo.Login SET password =? WHERE empID = ? ;";
           
        try{
            
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, password);
            
            ps1.setString(2,empID);
            
            ps1.executeUpdate();
            System.out.println("SSSSSSSSSSSSSSSSSSSS    Update SuccessFull    SSSSSSSSSSSSSSSSSSSSSSSSS");
                
                
            try {
                Employee  em = new Employee();
                switch (em.getJobID(empID)) {
                case "2000":
                   
                    response.sendRedirect("ceo.jsp");
                    break;
                case "2001":
                    
                    response.sendRedirect("timeKeeper.jsp");
                    break;
                default:
                    
                    response.sendRedirect("employee.jsp");
                    break;
            }
            } catch (IOException ex) {
                Logger.getLogger(UpdateEmpInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn :"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error :"+ez);
    }
    }
   
}


