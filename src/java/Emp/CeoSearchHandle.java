/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emp;

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
public class CeoSearchHandle extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String empID =(String) request.getParameter("id");
        if(new Employee().CheckEmp(empID)){
            request.setAttribute("searchID", empID);
        request.getRequestDispatcher("ceo.jsp").forward(request, response);
        }
        else{
            request.setAttribute("errSearch", "Please enter a remaining Employee ID");
            request.getRequestDispatcher("ceo.jsp").forward(request, response);
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
}
