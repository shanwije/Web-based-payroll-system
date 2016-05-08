package Emp;


import DBConnection.ConnectDB;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shan Wijenayaka
 */
public class Employee {
    int i;
    private String empID,jobName;
    private ArrayList<String> empInfo = new ArrayList<>();
    private boolean chk = false;
    
    
    public ArrayList<String> setInfo(String empID) {
        this.empID = empID;
        Connection conn = new ConnectDB().connect();
        String sql1 = "select jobID, name, nic, email, contactNo, lastAccessedOn, birthDay, Address from dbo.EmpInfo where empID = ? ";
       
      try{  
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,empID);
            
            ResultSet rs = ps1.executeQuery();
           
            
            
                while(rs.next()){
                
                    empInfo.add(rs.getString("jobID"));//0
                    empInfo.add(rs.getString("name"));//1
                    empInfo.add(rs.getString("nic"));//2
                    empInfo.add(rs.getString("lastAccessedOn"));//3
                    empInfo.add(rs.getString("contactNo"));//4
                    empInfo.add(rs.getString("email"));//5
                    empInfo.add(rs.getString("birthDay"));//6
                    empInfo.add(rs.getString("Address"));///7
                    
                } 
                
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn :"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error :"+ez);
    }
     
      
      
      
      
      
      
      
      
      
      try{  
          String sql2 = "SELECT TOP 1 netPay FROM Transactions where empID = ? ORDER BY dateTime DESC ;";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1,empID);
            
            ResultSet rs1 = ps2.executeQuery();
           
            
            
                while(rs1.next()){
                
                    empInfo.add(rs1.getString("netPay"));//8
                    
                    
                } 
                
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn :"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error :"+ez);
    }
      
      
      
      
      
      
      empInfo.stream().forEach((i) -> {
          System.out.println(i);
        });
      
    return empInfo;
          
}
    public String getJobID(String empID){
        return(setInfo(empID)).get(0);
    }
    public String getName(String empID){
        return(setInfo(empID)).get(1);
    }
    public String getNIC(String empID){
        return(setInfo(empID)).get(2);
    }
    public String getLastAccessedOn(String empID){
        return(setInfo(empID)).get(3);
    }
    public String getContactNo(String empID){
        return(setInfo(empID)).get(4);
    }
    public String getEmail(String empID){
        return(setInfo(empID)).get(5);
    }
    public String getLastPayment(String empID){
        return(setInfo(empID)).get(8);
    }
    public String getImage(String empID){
        return "empImg/"+empID+".jpg";
    }
    public String getBirthDay(String empID){
        return (setInfo(empID)).get(6);
    }
    int ie =0;
    public String getAddress(String empID){
         return (setInfo(empID)).get(7);
        
    }
    
    
    
    public String getJobName(String empID){
        
        Connection conn = new ConnectDB().connect();
        String sql1 = "select jobName from dbo.Job where jobID = ? ";
      // String sql2 = "SELECT TOP 1 netPay FROM Transactions where empID = ? ORDER BY dateTime DESC ;";
      try{  
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,getJobID(empID));
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ : "+getJobID(empID));
            ResultSet rs = ps1.executeQuery();
           
            
            
                while(rs.next()){
                
                    jobName = rs.getString("jobName");
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ : "+jobName);
                    
                }
                
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn :"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error :"+ez);
    }
      return jobName; 
    }
    
    
    
    public boolean CheckEmp(String empID){
        Connection conn = new ConnectDB().connect();
        String sql1 = "SELECT TOP 1 empID FROM dbo.EmpInfo WHERE empID = ?;";
      // String sql2 = "SELECT TOP 1 netPay FROM Transactions where empID = ? ORDER BY dateTime DESC ;";
      try{  
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,empID);
      
            ResultSet rs = ps1.executeQuery();
           
            
            
                if(rs.next()){
                    chk = true;
                }
                
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn :"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error :"+ez);
    }
        
        return chk;
        
    }
    
    
    
    
    protected void DelRow(String empID){
        Connection conn = new ConnectDB().connect();
        String sql1 = "DELETE FROM dbo.EmpInfo WHERE empID=?;";
      // String sql2 = "SELECT TOP 1 netPay FROM Transactions where empID = ? ORDER BY dateTime DESC ;";
      try{  
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,empID);
      
            ps1.executeUpdate();
                
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn :"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error :"+ez);
    }
    }

    
    
}
