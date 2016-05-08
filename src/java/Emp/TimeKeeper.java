package Emp;


import DBConnection.ConnectDB;
import Tables.TimeKeeperTable;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shan Wijenayaka
 */
public class TimeKeeper extends Employee{
    TimeKeeperTable tk = new TimeKeeperTable();
    String[] info;
    
    
    protected void updateDB(String[] info){
        this.info = info;
        Connection conn = new ConnectDB().connect();
        String sql1 = "INSERT INTO dbo.Transactions VALUES (? ,? ,? ,? ,getdate() ,? ,? )";
       
        try{
            
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,info[0]);
            
            ps1.setString(2, info[1]);
            ps1.setString(3, info[2]);
            ps1.setString(4, info[3]);
            ps1.setString(5, info[4]);
            ps1.setString(6, info[5]);
            
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
