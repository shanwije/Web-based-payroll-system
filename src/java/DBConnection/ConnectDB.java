package DBConnection;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shan Wijenayaka
 */
public class ConnectDB {
   
    private Connection DBConnection;
    public Connection connect(){
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Connection Success(1)");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("error check mssql driver for java : "+ex);
            //JOptionPane.showMessageDialog(null," );
            System.exit(0);
            
        }
        
        String connectionUrl = "jdbc:sqlserver://localhost:1433;database=Payroll;integratedSecurity=true; selectMethod=cursor;";
//responseBuffering=adaptive;       
// String name = "sa";
       // String password = "";
        
        try{
            DBConnection = (Connection) DriverManager.getConnection(connectionUrl);
            System.out.println("Database Connected(2)");
            
        }
        catch(SQLException se){
            System.out.println("line 46 Database application is not running or incorrect url/name/password for the database :"+se);
            //JOptionPane.showMessageDialog(null,"base application is not running or incorrect url/name/password for the database : "+se );
            System.exit(0);
        }
        return DBConnection;
    }

    PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

