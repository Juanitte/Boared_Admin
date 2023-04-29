package com.juanite.model.DAO;

import com.juanite.model.DTO.UserDTO;
import com.juanite.model.connections.ConnectionMySQL;
import com.juanite.model.domain.Countries;
import com.juanite.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDAO implements DAO {

    private final static String FINDALL = "SELECT * FROM user";
    private final static String FINDBYUSERNAME = "SELECT * FROM user WHERE username=?";
    private final static String FINDBYEMAIL = "SELECT * FROM user WHERE email=?";
    private final static String FINDBYID = "SELECT * FROM user WHERE id=?";
    private final static String INSERT = "INSERT INTO user (username,password,name,surname,email,country,town,address,birth_date,phone_number,avatar) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private final static String UPDATE = "UPDATE user SET username=?, password=?, name=?, surname=?, email=?, country=?, town=?, address=?, birth_date=?, phone_number=?, avatar=? WHERE id=?";
    private final static String DELETE = "DELETE FROM user WHERE id=?";
    private final static String GETID = "SELECT id FROM user WHERE email=?";

    private Connection conn;

    public UserDAO(Connection conn){
        this.conn = conn;
    }
    public UserDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    @Override
    public Set<User> findAll() throws SQLException {
        Set<User> result = new HashSet<User>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    User u = new User();
                    u.setUsername(res.getString("username"));
                    u.setPassword(res.getString("password"));
                    u.setName(res.getString("name"));
                    u.setSurname(res.getString("surname"));
                    u.setEmail(res.getString("email"));
                    u.setCountry(Countries.valueOf(res.getString("country")));
                    u.setTown(res.getString("town"));
                    u.setAddress(res.getString("address"));
                    u.setBirthDate(res.getDate("birth_date"));
                    u.setPhoneNumber(res.getString("phone_number"));
                    u.setAvatar(res.getString("avatar"));
                    result.add(u);
                }
            }
        }
        return result;
    }

    @Override
    public Set<UserDTO> findAllDTO() throws SQLException {
        Set<UserDTO> result = new HashSet<UserDTO>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    UserDTO u = new UserDTO();
                    u.setUsername(res.getString("username"));
                    u.setCountry(Countries.valueOf(res.getString("country")));
                    u.setAvatar(res.getString("avatar"));
                    result.add(u);
                }
            }
        }
        return result;
    }

    @Override
    public User find(String param) throws SQLException {
        User result = new User();
        if(!param.contains("@")) {
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYUSERNAME)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        result.setUsername(res.getString("username"));
                        result.setPassword(res.getString("password"));
                        result.setName(res.getString("name"));
                        result.setSurname(res.getString("surname"));
                        result.setEmail(res.getString("email"));
                        result.setCountry(Countries.valueOf(res.getString("country")));
                        result.setTown(res.getString("town"));
                        result.setAddress(res.getString("address"));
                        result.setBirthDate(res.getDate("birth_date"));
                        result.setPhoneNumber(res.getString("phone_number"));
                        result.setAvatar(res.getString("avatar"));
                    }
                }
            }
        }else{
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYEMAIL)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        result.setUsername(res.getString("username"));
                        result.setPassword(res.getString("password"));
                        result.setName(res.getString("name"));
                        result.setSurname(res.getString("surname"));
                        result.setEmail(res.getString("email"));
                        result.setCountry(Countries.valueOf(res.getString("country")));
                        result.setTown(res.getString("town"));
                        result.setAddress(res.getString("address"));
                        result.setBirthDate(res.getDate("birth_date"));
                        result.setPhoneNumber(res.getString("phone_number"));
                        result.setAvatar(res.getString("avatar"));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public UserDTO findDTO(String param) throws SQLException {
        UserDTO result = new UserDTO();
        if(!param.contains("@")) {
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYUSERNAME)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        result.setUsername(res.getString("username"));
                        result.setCountry(Countries.valueOf(res.getString("country")));
                        result.setAvatar(res.getString("avatar"));
                    }
                }
            }
        }else{
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYEMAIL)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        result.setUsername(res.getString("username"));
                        result.setCountry(Countries.valueOf(res.getString("country")));
                        result.setAvatar(res.getString("avatar"));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public User find(int id) throws SQLException {
        User result = new User();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYID)) {
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setUsername(res.getString("username"));
                    result.setPassword(res.getString("password"));
                    result.setName(res.getString("name"));
                    result.setSurname(res.getString("surname"));
                    result.setEmail(res.getString("email"));
                    result.setCountry(Countries.valueOf(res.getString("country")));
                    result.setTown(res.getString("town"));
                    result.setAddress(res.getString("address"));
                    result.setBirthDate(res.getDate("birth_date"));
                    result.setPhoneNumber(res.getString("phone_number"));
                    result.setAvatar(res.getString("avatar"));
                }
            }
        }
        return result;
    }

    @Override
    public UserDTO findDTO(int id) throws SQLException {
        UserDTO result = new UserDTO();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYID)) {
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setUsername(res.getString("username"));
                    result.setCountry(Countries.valueOf(res.getString("country")));
                    result.setAvatar(res.getString("avatar"));
                }
            }
        }
        return result;
    }

    @Override
    public User save(Object entity) throws SQLException {
        User u = find(((User)entity).getUsername());

        if(u.getUsername().equals("")){
            try(PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                pst.setString(1, ((User)entity).getUsername());
                pst.setString(2, ((User)entity).getPassword());
                pst.setString(3, ((User)entity).getName());
                pst.setString(4, ((User)entity).getSurname());
                pst.setString(5, ((User)entity).getEmail());
                pst.setString(6, ((User)entity).getCountry().name());
                pst.setString(7, ((User)entity).getTown());
                pst.setString(8, ((User)entity).getAddress());
                pst.setDate(9, (java.sql.Date)(((User)entity).getBirthDate()));
                pst.setString(10, ((User)entity).getPhoneNumber());
                pst.setString(11, ((User)entity).getAvatar());
                pst.executeUpdate();
            }
        }else{
            try(PreparedStatement pst = this.conn.prepareStatement(UPDATE)){
                pst.setString(1, ((User)entity).getUsername());
                pst.setString(2, ((User)entity).getPassword());
                pst.setString(3, ((User)entity).getName());
                pst.setString(4, ((User)entity).getSurname());
                pst.setString(5, ((User)entity).getEmail());
                pst.setString(6, ((User)entity).getCountry().name());
                pst.setString(7, ((User)entity).getTown());
                pst.setString(8, ((User)entity).getAddress());
                pst.setDate(9, (java.sql.Date)(((User)entity).getBirthDate()));
                pst.setString(10, ((User)entity).getPhoneNumber());
                pst.setString(11, ((User)entity).getAvatar());
                pst.setInt(12, getId((User)entity));
                pst.executeUpdate();
            }
        }

        return (User)entity;
    }

    @Override
    public void delete(Object entity) throws SQLException {
        User u = find(((User)entity).getUsername());

        if(!u.getUsername().equals("")){
            try(PreparedStatement pst = this.conn.prepareStatement(DELETE)){
                pst.setInt(1, getId((User)entity));
                pst.executeUpdate();
            }
        }
    }

    public int getId(User user) throws SQLException {
        if(user != null){
            if(!user.getUsername().equals("")){
                try(PreparedStatement pst = this.conn.prepareStatement(GETID)){
                    pst.setString(1, user.getUsername());
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

    @Override
    public void close() throws Exception {

    }
}
