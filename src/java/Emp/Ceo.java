/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emp;

import java.util.ArrayList;

/**
 *
 * @author Shan Wijenayaka
 */
public class Ceo extends Employee {
    
   public ArrayList<String> searchEmp(String EmpID){
       ArrayList<String> arr = new ArrayList<>();
       Employee em = new Employee();
       //0
       arr.add(em.getImage(EmpID));
       //1
       arr.add(em.getName(EmpID));
       //2
       arr.add(em.getNIC(EmpID));
       //3
       arr.add(em.getJobID(EmpID));
       //4
       arr.add(em.getEmail(EmpID));
       //5
       arr.add(em.getContactNo(EmpID));
       //6
       arr.add(em.getLastPayment(EmpID));
       //7
       arr.add(em.getLastAccessedOn(EmpID));
       
       return arr;
   }
}
