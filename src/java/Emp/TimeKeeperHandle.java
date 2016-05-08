package Emp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Emp.TimeKeeper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shan Wijenayaka
 */
public class TimeKeeperHandle extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        TimeKeeper tk = new TimeKeeper();
        
        String empID = request.getParameter("empID");
        String hours = request.getParameter("hours");
        String uPrice = request.getParameter("uPrice");
        String taxes = request.getParameter("taxes");
        
        String grossSal = Double.toString(Double.parseDouble(hours)*Double.parseDouble(uPrice));
        String netSal = Double.toString(Double.parseDouble(grossSal)*(100-Double.parseDouble(taxes))/100);
        
        String[] arr = {empID,grossSal,taxes,netSal,hours,uPrice};
        tk.updateDB(arr);
    }

 
    

}
