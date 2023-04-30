package com.juanite.test;

import com.juanite.model.DAO.AdminDAO;
import com.juanite.model.domain.Admin;

public class Test {
    public static void main(String[] args) throws Exception {
        Admin admin = new Admin("juanite@boared.com", "Juanite", "1234");
        try (AdminDAO adao = new AdminDAO()) {
            adao.save(admin);
        }
    }
}
