package com.juanite.test;

import com.juanite.model.DAO.AdminDAO;
import com.juanite.model.domain.Admin;
import com.juanite.model.domain.RepoAdmin;

public class Test {
    public static void main(String[] args) throws Exception {
        //Admin admin = new Admin();
        Admin admin = new Admin("juanite@boared.com", "Juanite", "Juanite1234");
        if(admin.save() != null){
            System.out.println(admin.getName());
        }else{
            System.out.println("Error");
        }
        /*if(admin.remove() != null){
            System.out.println(admin.getName());
        }else{
            System.out.println("Error");
        }*/
        /*if(admin.create("juanite@boared.com", "Juanite", "1234") != null){
            System.out.println(admin.getName());
        }else{
            System.out.println("Error");
        }*/
    }
}
