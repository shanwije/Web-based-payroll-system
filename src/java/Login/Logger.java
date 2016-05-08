/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import DBConnection.ConnectDB;
import java.awt.HeadlessException;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

/**
 *
 * @author Shan Wijenayaka
 */
public class Logger {
    private  String password,empID;
    private boolean auth,emailVal = false;
    private Cookie ck;
    
    public Logger(String empID1, String password1){
        empID = empID1;
        password = password1;
    }
    
    
    public boolean checkLogin(){
        
        
        
        Connection conn = new ConnectDB().connect();
        String sql1 = "select * from dbo.Login where empID = ? and password =?";
       
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,empID);
            
            ps1.setString(2, password);
            ResultSet rs = ps1.executeQuery();
           
            
            
                if(rs.next()){
                   System.out.println("yehhhh ");
                    auth = true;
                    
                }
                else{
                    
                    System.out.println("incorrect uname pword");
                                        
                }
                
                
                /*-----------------------------------------*/
                
                
            
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn :"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error :"+ez);
    }
        
        return auth;
    }
    
    protected Cookie LoggerCookie(){
        empID = getUserID();
        ck = new Cookie("user", empID);
        ck.setMaxAge(60*60*60*7);
        return ck;  
    }
    
   
    private String getUserID() {
        return empID;
    }
    public void addCurrentDate(String empID){
        
        this.empID = (String)empID;
        Connection conn = new ConnectDB().connect();
        String sql1 = "UPDATE dbo.EmpInfo SET lastAccessedOn=GETDATE() WHERE empID=?";

                    
                    
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,  empID);
            
            ps1.executeUpdate();


                /*-----------------------------------------*/



        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn :"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error :"+ez);
    }

    }
    
}
