package com.juanite.model.DAO;

import com.juanite.model.DTO.DeveloperDTO;
import com.juanite.model.connections.ConnectionMySQL;
import com.juanite.model.domain.Countries;
import com.juanite.model.domain.Developer;
import com.juanite.model.domain.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DeveloperDAO implements DAO {

    private final static String FINDALL = "SELECT * FROM developer";
    private final static String FINDBYNAME = "SELECT * FROM developer WHERE name=?";
    private final static String FINDBYID = "SELECT * FROM developer WHERE id=?";
    private final static String INSERT = "INSERT INTO developer (name,description,country,birth_date,logo) VALUES (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE developer SET name=?, description=?, country=?, birth_date=?, logo=? WHERE id=?";
    private final static String DELETE = "DELETE FROM developer WHERE id=?";
    private final static String GETID = "SELECT id FROM developer WHERE name=?";

    private Connection conn;

    public DeveloperDAO(Connection conn){
        this.conn = conn;
    }
    public DeveloperDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    @Override
    public Set<Developer> findAll() throws SQLException {
        Set<Developer> result = new HashSet<Developer>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Developer d = new Developer();
                    d.setName(res.getString("name"));
                    d.setDescription(res.getString("description"));
                    d.setCountry(Countries.valueOf(res.getString("country")));
                    d.setBirthDate(res.getDate("birth_date"));
                    d.setLogo(res.getString("logo"));
                    result.add(d);
                }
            }
        }
        return result;
    }

    public Set<DeveloperDTO> findAllDTO() throws SQLException {
        Set<DeveloperDTO> result = new HashSet<DeveloperDTO>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    DeveloperDTO d = new DeveloperDTO();
                    d.setName(res.getString("name"));
                    result.add(d);
                }
            }
        }
        return result;
    }

    @Override
    public Developer find(String param) throws SQLException {
        Developer result = new Developer();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYNAME)) {
            pst.setString(1, param);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setName(res.getString("name"));
                    result.setDescription(res.getString("description"));
                    result.setCountry(Countries.valueOf(res.getString("country")));
                    result.setBirthDate(res.getDate("birth_date"));
                    result.setLogo(res.getString("logo"));
                }
            }
        }
        return result;
    }

    public DeveloperDTO findDTO(String param) throws SQLException {
        DeveloperDTO result = new DeveloperDTO();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYNAME)) {
            pst.setString(1, param);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setName(res.getString("name"));
                }
            }
        }
        return result;
    }
    @Override
    public Developer find(int id) throws SQLException {
        Developer result = new Developer();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDBYID)){
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()){
                if(res.next()){
                    result.setName(res.getString("name"));
                    result.setDescription(res.getString("description"));
                    result.setCountry(Countries.valueOf(res.getString("country")));
                    result.setBirthDate(res.getDate("birth_date"));
                    result.setLogo(res.getString("logo"));
                }
            }
        }

        return result;
    }

    public DeveloperDTO findDTO(int id) throws SQLException {
        DeveloperDTO result = new DeveloperDTO();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDBYID)){
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()){
                if(res.next()){
                    result.setName(res.getString("name"));
                }
            }
        }

        return result;
    }

    @Override
    public Developer save(Object entity) throws SQLException {
        Developer d = find(((Developer)entity).getName());

        if(d.getName().equals("")){
            try(PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                pst.setString(1, ((Developer)entity).getName());
                pst.setString(2, ((Developer)entity).getDescription());
                pst.setString(3, ((Developer)entity).getCountry().name());
                pst.setDate(4, (Date) ((Developer)entity).getBirthDate());
                pst.setString(5, ((Developer)entity).getLogo());
                pst.executeUpdate();
            }
        }else{
            try(PreparedStatement pst = this.conn.prepareStatement(UPDATE)){
                pst.setString(1, ((Developer)entity).getName());
                pst.setString(2, ((Developer)entity).getDescription());
                pst.setString(3, ((Developer)entity).getCountry().name());
                pst.setDate(4, (Date) ((Developer)entity).getBirthDate());
                pst.setString(5, ((Developer)entity).getLogo());
                pst.setInt(6, getId((Developer) entity));
                pst.executeUpdate();
            }
        }

        return (Developer) entity;
    }

    @Override
    public void delete(Object entity) throws SQLException {
        Developer d = find(((Developer)entity).getName());

        if(!d.getName().equals("")){
            try(PreparedStatement pst = this.conn.prepareStatement(DELETE)){
                pst.setInt(1, getId((Developer) entity));
                pst.executeUpdate();
            }
        }
    }

    public int getId(Developer developer) throws SQLException {
        if(developer != null){
            if(!developer.getName().equals("")){
                try(PreparedStatement pst = this.conn.prepareStatement(GETID)){
                    pst.setString(1, developer.getName());
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
