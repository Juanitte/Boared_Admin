package com.juanite.model.DAO;

import com.juanite.model.connections.ConnectionMySQL;
import com.juanite.model.domain.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AdminDAO implements DAO {

    private final static String FINDALL = "SELECT * FROM admin";
    private final static String FINDBYNAME = "SELECT * FROM admin WHERE name=?";
    private final static String FINDBYEMAIL = "SELECT * FROM admin WHERE email=?";
    private final static String INSERT = "INSERT INTO admin (email,name,password) VALUES (?,?,?)";
    private final static String UPDATE = "UPDATE admin SET email=?, name=?, password=? WHERE email=?";
    private final static String DELETE = "DELETE FROM admin WHERE email=?";

    private Connection conn;

    public AdminDAO(Connection conn){
        this.conn = conn;
    }
    public AdminDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    @Override
    public Set<Admin> findAll() throws SQLException {
        Set<Admin> result = new HashSet<Admin>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Admin a = new Admin();
                    a.setEmail(res.getString("email"));
                    a.setName(res.getString("name"));
                    a.setPassword(res.getString("password"));
                    result.add(a);
                }
            }
        }
        return result;
    }

    @Override
    public Admin find(String param) throws SQLException {
        Admin result = new Admin();
        if(!param.contains("@boared.com")) {
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYNAME)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        result.setEmail(res.getString("email"));
                        result.setName(res.getString("name"));
                        result.setPassword(res.getString("password"));
                    }
                }
            }
        }else{
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYEMAIL)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        result.setEmail(res.getString("email"));
                        result.setName(res.getString("name"));
                        result.setPassword(res.getString("password"));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Admin save(Object entity) throws SQLException {
        Admin a = find(((Admin)entity).getName());

        if(a.getEmail().equals("")){
            try(PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                pst.setString(1, ((Admin)entity).getEmail());
                pst.setString(2, ((Admin)entity).getName());
                pst.setString(3, ((Admin)entity).getPassword());
                pst.executeUpdate();
            }
        }else{
            try(PreparedStatement pst = this.conn.prepareStatement(UPDATE)){
                pst.setString(1, ((Admin)entity).getEmail());
                pst.setString(2, ((Admin)entity).getName());
                pst.setString(3, ((Admin)entity).getPassword());
                pst.setString(4, ((Admin)entity).getEmail());
                pst.executeUpdate();
            }
        }

        return (Admin)entity;
    }

    @Override
    public void delete(Object entity) throws SQLException {
        Admin a = find(((Admin)entity).getName());

        if(!a.getEmail().equals("")){
            try(PreparedStatement pst = this.conn.prepareStatement(DELETE)){
                pst.setString(1, ((Admin)entity).getEmail());
                pst.executeUpdate();
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
