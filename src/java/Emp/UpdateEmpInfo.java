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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class UpdateEmpInfo extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         System.out.println("SSSSSSSSSSSSSSSSSSSS    start SSSSSSSSSSSSSSSSSSSSSSSSS"); 
        ArrayList<String> arr = new ArrayList<>();
            arr.add(request.getParameter("name"));
            arr.add(request.getParameter("email"));
            arr.add(request.getParameter("tel"));
            arr.add(request.getParameter("birthDay"));
            arr.add(request.getParameter("address"));
            
           System.out.println("SSSSSSSSSSSSSSSSSSSS    before   SSSSSSSSSSSSSSSSSSSSSSSSS"); 
            updateDB(arr, request, response);
           System.out.println("SSSSSSSSSSSSSSSSSSSS   After    SSSSSSSSSSSSSSSSSSSSSSSSS"); 
      // new Employee().UpdateInfo(arr);
    }
    
    public void updateDB(ArrayList<String> info ,HttpServletRequest request, HttpServletResponse response){
        HttpSession s= request.getSession(false);
        String empID = (String)s.getAttribute("user");
        
        Connection conn = new ConnectDB().connect();
        String sql1 = "update dbo.EmpInfo set "+
                
"      [name] = ?" +      
"      ,[email] = ?" +
"      ,[contactNo] = ?" +
"      ,[birthDay] = ?" +
"      ,[Address] = ?"+
        " where empID = ?";
       
        try{
            
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, info.get(0));
            
            ps1.setString(2, info.get(1));
            ps1.setString(3, info.get(2));
            ps1.setString(4, info.get(3));
            ps1.setString(5, info.get(4));
            ps1.setString(6,empID);
            
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
