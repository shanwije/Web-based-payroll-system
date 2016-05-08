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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class AddNewMember extends HttpServlet {

private String newID;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SSSSSSSSSSSSSSSSSSSS    start SSSSSSSSSSSSSSSSSSSSSSSSS"); 
        ArrayList<String> arr = new ArrayList<>();
            arr.add(request.getParameter("jobID"));
        arr.add(request.getParameter("name"));
            arr.add(request.getParameter("NIC"));
            
            arr.add(request.getParameter("email"));
            arr.add(request.getParameter("tel"));
            arr.add(request.getParameter("birthDay"));
            arr.add(request.getParameter("address"));
            
           System.out.println("SSSSSSSSSSSSSSSSSSSS    before   SSSSSSSSSSSSSSSSSSSSSSSSS"); 
            addToDB(arr,request , response);
           System.out.println("SSSSSSSSSSSSSSSSSSSS   After    SSSSSSSSSSSSSSSSSSSSSSSSS");
           
           
    }

  
    
    
    
    
    public void addToDB(ArrayList<String> info,HttpServletRequest request, HttpServletResponse response) throws IOException{
      //response.sendRedirect("ceo.jsp");
      
      //java.sql.Date sqlDate = java.sql.Date.valueOf(info.get(5));
        Connection conn = new ConnectDB().connect();
        String sql1 = "INSERT INTO [dbo].[EmpInfo]\n" +
"           ([jobID]\n" +
"           ,[name]\n" +
"           ,[nic]\n" +
"           ,[lastAccessedOn]\n" +
"           ,[email]\n" +
"           ,[contactNo]\n" +
"           ,[birthDay]\n" +
"           ,[Address])\n" +
"    VALUES(?,?,?,GETDATE(),?,?,?,?)" 
;
       
        try{
            
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, Integer.parseInt(info.get(0)));
            
            ps1.setString(2, info.get(1));
            ps1.setString(3, info.get(2));
            ps1.setString(4, info.get(3));
            ps1.setString(5, info.get(4));
            try{
            ps1.setString(6, info.get(5));
            }catch(Exception ex){
               System.out.println(info.get(5)+" SSSSSSSSSSSSSSSSSSSS    Error ,cant add birthday    SSSSSSSSSSSSSSSSSSSSSSSSS"+ex); 
            }
            ps1.setString(7, info.get(6));
            
           // ps1.setDate(3, ");
           
            try {
            ps1.executeUpdate();
            System.out.println("SSSSSSSSSSSSSSSSSSSS    Update SuccessFull    SSSSSSSSSSSSSSSSSSSSSSSSS");
              
            } catch (Exception ex) {
                System.out.println("SSSSSSSSSSSSSSSSSSSS    Error ,cant add new member    SSSSSSSSSSSSSSSSSSSSSSSSS"+ex);
            }   
              
           
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn adding new:"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error adding new:"+ez);
        }
        
        
        /////////////////////////////////////////////////////////////////////////////////////////
        
        
        String sql2 = "select EmpID from dbo.EmpInfo where nic = ?";
        
          try{
            
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1,(info.get(2)));
            
           ResultSet rs2 = ps2.executeQuery();
           if(rs2.next()){
               newID  = rs2.getString("empID");
           }
           
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn adding new:"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error adding new:"+ez);
        }       
        
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////  
        
        
        
        String sql3 = "INSERT INTO [dbo].[Login]\n" +
"           ([empID]\n" +
"           ,[password])\n" +
"     VALUES\n" +
"           (?,?)" 
;
       
        try{
            
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setString(1, newID);
            ps3.setString(2, "1111");
           
            
            ps3.executeUpdate();
            
              
           
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn adding new:"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error adding new:"+ez);
        }
        
        
        ////////////////////////////////////////////////////////////////////////////////
        
        request.setAttribute("newMem", info.get(1)+" has successfully add to the system. The related Employee ID is "+newID);
    
        
    try {
        request.getRequestDispatcher("ceo.jsp").forward(request, response);
    } catch (ServletException ex) {
        
        System.out.println("----------------------setting attribute for new member is failed: "+ex+"-------------------------");
    }
        
        
        
        
    
  }
}

