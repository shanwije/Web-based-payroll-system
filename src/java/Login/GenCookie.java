/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import DBConnection.ConnectDB;
import java.awt.HeadlessException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.Cookie;

/**
 *
 * @author Shan Wijenayaka
 */
public class GenCookie {
    public String genCookieID(){
        Random rnd = new Random(123);
        BigInteger tenPow30 = new BigInteger("10").pow(30);
        BigInteger min = new BigInteger("10").pow(29);
        BigInteger r;
        do {
                r = new BigInteger(100, rnd).remainder(tenPow30);
        } while (r.compareTo(min) < 0);
        
        String rdm = r.toString();
        
        return rdm;
        
    }
    
    public void cookieIDToDB(String cid,String empID){
        Connection conn = new ConnectDB().connect();
        String sql1 = "INSERT INTO dbo.CookieID VALUES (?,?)";
       
        try{
            
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,cid);
            ps1.setString(2,empID);
            
            System.out.println("$$$$$$$$$$$  Cookie id Successfull add to the database");
            ps1.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("sql exception in submit btn :"+ex);
           // Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(HeadlessException ez){
        System.out.println("Error :"+ez);
    }
        
    }
    
    public ArrayList<String> getCookieID(){
        ArrayList<String> cList = new ArrayList<>();
        String CID;
        
        
        try {
                Connection conn = new ConnectDB().connect();
                String sql = "select CID from dbo.CookieID";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                //ps.setString(1, empID);
                
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    CID = rs.getString("CID");
                    
                        cList.add(CID);
                     
                }
                
            } catch (SQLException ex) {
                System.out.println( ex);
            }
        return cList;
     }
    
    public String getEmpID(String C){
        String empID = "no";
        try {
                Connection conn = new ConnectDB().connect();
                String sql = "select empID from dbo.CookieID where CID=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, C);
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    empID = rs.getString("empID");
                 }
                
                
                
            } catch (SQLException ex) {
                System.out.println( ex);
            }
        return empID;
    }
    public void removeCookie(String empID){
        try {
                Connection conn = new ConnectDB().connect();
                String sql = "DELETE FROM dbo.CookieID WHERE empID=?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, empID);
                
                ps.executeUpdate();
                
                System.out.println("Cookie ID succcessfully deleted from table");
                
                
                
            } catch (SQLException ex) {
                System.out.println( ex);
            }
    }
}
