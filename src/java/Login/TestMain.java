package Login;

import Emp.Employee;
import Tables.TimeKeeperTable;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shan Wijenayaka
 */
public class TestMain {
    public static void main(String [] args){
        //new SetAllTransTable().printll();
        //System.out.println();
        //int c = new SetAllTransTable().getRowCount();
        //System.out.println(c);
        //System.out.println();
        
        Logger xx = new Logger("shan@gmail.com","1111");
        xx.addCurrentDate("4");
        xx.checkLogin();
       // System.out.println(xx);
        System.out.println(xx.checkLogin());
        Employee zz = new Employee();
        zz.setInfo("6");
        
    }
}
