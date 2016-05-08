/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import DBConnection.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shan Wijenayaka
 */
public class EmpTable {
    private int rowCount;
    String myRow;
    String [] allRows = new String[getRowCount()] ;
    
    
    public int getRowCount(){
    Connection conn = new ConnectDB().connect();
        String sql = "select * from dbo.Transactions";
       
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            

                while (rs.next()) {
                    ++rowCount;
                    // Get data from the current row and use it
                }

                if (rowCount == 0) {
                    System.out.println("No records found");
                }
                
        } catch (SQLException ex) {
            System.out.println( ex);
        }
        return rowCount;
}
    
    public ArrayList<String> setRows(String empID){
        //String [] colm = new String[7];
        //String empID = empID;
        ArrayList<String> az = new ArrayList<>();
        
        int c = getRowCount();
        String transID;
        String grossPay;
        String taxes;
        String netPay;
        String myDate;
        Connection conn = new ConnectDB().connect();
        
        
        
       //for(int i = 0;i<=c;i++){
        
            try {
                String sql = "select transID, grossPay, taxes, netPay, dateTime from dbo.Transactions where empID = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, empID);
                
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    transID = rs.getString("transID");
                    ///name = rs.getString("name");
                    //id = rs.getString("id");
                    grossPay = rs.getString("grossPay");
                    taxes = rs.getString("taxes");
                    netPay = rs.getString("netPay");
                    myDate = rs.getString("dateTime");
                    
                    //System.out.println(name+" "+id+" "+netPay);
                    System.out.println(rowCount);
                    myRow = "<tr>"
                            + "<td>" +myDate+"</td>"
                            + "<td>" +transID+"</td>"
                            + "<td>" +grossPay+"</td>"
                            + "<td>" +taxes+"</td>"
                            + "<td>" +netPay+"</td>"
                            
                            +"</tr>";
                    
                    System.out.println(myRow);
                    az.add(myRow);
                }
                
                
                
            } catch (SQLException ex) {
                System.out.println( ex);
            }
        
        
        
        return az;
    }
   
     
}
