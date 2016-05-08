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

/**
 *
 * @author Shan Wijenayaka
 */
public class TimeKeeperTable extends EmpTable {
    public String TransID;
    private ArrayList <String> allRows = new ArrayList<>();
    
    
    public ArrayList <String> setRows(){
        //String [] colm = new String[7];
        //String empID = empID;
        int rowCount = 0;
        
        int c = getRowCount();
        String transID;
        String empID;
        String grossPay;
        String taxes;
        String netPay;
        String myDate;
        String workingHours;
        String unitPrice;
        
        
            try {
                Connection conn = new ConnectDB().connect();
                String sql = "select transID, empID, grossPay, taxes, netPay, dateTime, workingHours, unitPrice from dbo.Transactions ";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                //ps.setString(1, transID);
                
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    transID = rs.getString("transID");
                    empID = rs.getString("empID");
                    ///name = rs.getString("name");
                    //id = rs.getString("id");
                    //workingHours = rs.getString("workingHours");
                    //unitPrice = rs.getString("unitPrice");
                    grossPay = rs.getString("grossPay");
                    taxes = rs.getString("taxes");
                    netPay = rs.getString("netPay");
                    myDate = rs.getString("dateTime");
                    
                    //System.out.println(name+" "+id+" "+netPay);
                    System.out.println(rowCount);
                    myRow = "<tr>"
                            + "<td>" +transID+"</td>"
                            + "<td>" +empID+"</td>"
                            + "<td>" +myDate+"</td>"
                            //+ "<td>" +workingHours+"</td>"
                            //+ "<td>" +unitPrice+"</td>"
                            + "<td>" +grossPay+"</td>"
                            + "<td>" +taxes+"</td>"
                            + "<td>" +netPay+"</td>"
                            +"</tr>";
                    
                    System.out.println(myRow);
                    
                    allRows.add(myRow)  ;
                    
                    
                }
                
                
                
            } catch (SQLException ex) {
                System.out.println( ex);
            }
        
        
        
        return allRows;
    }
   
}
