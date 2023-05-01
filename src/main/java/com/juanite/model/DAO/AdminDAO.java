package com.juanite.model.DAO;

import com.juanite.model.DTO.AdminDTO;
import com.juanite.model.connections.ConnectionMySQL;
import com.juanite.model.domain.Admin;
import com.juanite.model.domain.CompanyEmails;
import com.juanite.util.AppData;
import com.juanite.util.PasswordAuthentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AdminDAO implements DAO {

    private final static String FINDALL = "SELECT * FROM admin";
    private final static String FINDALLDTO = "SELECT name, email FROM admin";
    private final static String FINDBYNAME = "SELECT * FROM admin WHERE name=?";
    private final static String FINDBYEMAIL = "SELECT * FROM admin WHERE email=?";
    private final static String FINDBYID = "SELECT * FROM admin WHERE id=?";
    private final static String FINDBYNAMEDTO = "SELECT name, email FROM admin WHERE name=?";
    private final static String FINDBYEMAILDTO = "SELECT name, email FROM admin WHERE email=?";
    private final static String FINDBYIDDTO = "SELECT name, email FROM admin WHERE id=?";
    private final static String INSERT = "INSERT INTO admin (email,name,password) VALUES (?,?,?)";
    private final static String UPDATE = "UPDATE admin SET email=?, name=?, password=? WHERE id=?";
    private final static String DELETE = "DELETE FROM admin WHERE id=?";
    private final static String GETID = "SELECT id FROM admin WHERE name=?";

    private Connection conn;

    public AdminDAO(Connection conn){
        this.conn = conn;
    }
    public AdminDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    /**
     * Method that finds all admins stored at the database.
     * @return a Set of all Admins stored at the database.
     */
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

    /**
     * Method that finds all admins stored at the database.
     * @return a Set of all AdminDTOs stored at the database.
     */
    public Set<AdminDTO> findAllDTO() throws SQLException {
        Set<AdminDTO> result = new HashSet<AdminDTO>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALLDTO)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    AdminDTO a = new AdminDTO();
                    a.setEmail(res.getString("email"));
                    a.setName(res.getString("name"));
                    result.add(a);
                }
            }
        }
        return result;
    }

    /**
     * Method that finds an admin stored at the database.
     * @param param , the email/username to find.
     * @return the Admin found/null if not found.
     */
    @Override
    public Admin find(String param) throws SQLException {
        if(!param.contains("@boared.com")) {
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYNAME)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        return new Admin(res.getString("email"), res.getString("name"), res.getString("password"));
                    }
                }
            }
        }else{
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYEMAIL)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        return new Admin(res.getString("email"), res.getString("name"), res.getString("password"));
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that finds an admin stored at the database.
     * @param param , the email/username to find.
     * @return the AdminDTO found/null if not found.
     */
    public AdminDTO findDTO(String param) throws SQLException {
        if(!param.contains("@boared.com")) {
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYNAMEDTO)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        return new AdminDTO(res.getString("email"), res.getString("name"));
                    }
                }
            }
        }else{
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYEMAILDTO)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        return new AdminDTO(res.getString("email"), res.getString("name"));
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that finds an admin stored at the database.
     * @param id , the id to find.
     * @return the Admin found/null if not found.
     */
    @Override
    public Admin find(int id) throws Exception {
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYID)) {
                pst.setInt(1, id);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        return new Admin(res.getString("email"), res.getString("name"), res.getString("password"));
                    }
                }
            }
        return null;
    }

    /**
     * Method that finds an admin stored at the database.
     * @param id , the id to find.
     * @return the AdminDTO found/null if not found.
     */
    public AdminDTO findDTO(int id) throws Exception {
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYIDDTO)) {
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    return new AdminDTO(res.getString("email"), res.getString("name"));
                }
            }
        }
        return null;
    }

    /**
     * Method that stores/updates an Admin at the database.
     * @param entity , the Admin to save.
     * @return the stored/updated Admin.
     */
    @Override
    public Admin save(Object entity) throws SQLException {
        Admin a = find(((Admin)entity).getName());
        if(a == null){
            try (PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                pst.setString(1, ((Admin) entity).getEmail());
                pst.setString(2, ((Admin) entity).getName());
                pst.setString(3, AppData.getPa().hash(((Admin) entity).getPassword()));
                pst.executeUpdate();
            }
        }else{
            try (PreparedStatement pst = this.conn.prepareStatement(UPDATE)) {
                pst.setString(1, ((Admin) entity).getEmail());
                pst.setString(2, ((Admin) entity).getName());
                pst.setString(3, AppData.getPa().hash(((Admin) entity).getPassword()));
                pst.setInt(4, getId((Admin) entity));
                pst.executeUpdate();
            }
        }
        return (Admin) entity;
    }

    /**
     * Method that removes an admin stored at the database.
     * @param entity , the Admin to remove.
     */
    @Override
    public void delete(Object entity) throws SQLException {
        Admin a = find(((Admin)entity).getName());

        if(a != null){
            try(PreparedStatement pst = this.conn.prepareStatement(DELETE)){
                pst.setInt(1, getId((Admin)entity));
                pst.executeUpdate();
            }
        }
    }

    @Override
    public void close() throws Exception {

    }

    /**
     * Method that gets the id from an Admin stored at the database.
     * @param admin , the Admin to find.
     * @return the id of that Admin if found/-1 if not found.
     */
    public int getId(Admin admin) throws SQLException {
        if(admin != null){
            if(!admin.getName().equals("")){
                try(PreparedStatement pst = this.conn.prepareStatement(GETID)){
                    pst.setString(1, admin.getName());
                    try(ResultSet res = pst.executeQuery()){
                        if(res.next()){
                            return res.getInt("id");
                        }
                    }
                }
            }
        }
        return -1;
    }
}
