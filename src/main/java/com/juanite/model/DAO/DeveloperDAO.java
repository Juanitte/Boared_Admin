package com.juanite.model.DAO;

import com.juanite.model.DTO.DeveloperDTO;
import com.juanite.model.DTO.GameDTO;
import com.juanite.model.connections.ConnectionMySQL;
import com.juanite.model.domain.Countries;
import com.juanite.model.domain.Developer;
import com.juanite.model.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DeveloperDAO implements DAO {

    private final static String FINDALL = "SELECT * FROM developer";
    private final static String FINDALLDTO = "SELECT name FROM developer";
    private final static String FINDBYNAME = "SELECT * FROM developer WHERE name=?";
    private final static String FINDBYID = "SELECT * FROM developer WHERE id=?";
    private final static String FINDBYNAMEDTO = "SELECT name FROM developer WHERE name=?";
    private final static String FINDBYIDDTO = "SELECT name FROM developer WHERE id=?";
    private final static String FINDCONTAININGNAMESDTO = "SELECT name FROM developer WHERE name LIKE ?";
    private final static String INSERT = "INSERT INTO developer (name,description,country,birth_date,logo) VALUES (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE developer SET name=?, description=?, country=?, birth_date=?, logo=? WHERE id=?";
    private final static String DELETE = "DELETE FROM developer WHERE id=?";
    private final static String GETID = "SELECT id FROM developer WHERE name=?";
    private final static String GETDEVGAMES = "SELECT code FROM games WHERE dev_id=?";

    private Connection conn;

    public DeveloperDAO(Connection conn){
        this.conn = conn;
    }
    public DeveloperDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    /**
     * Method that finds all developers stored at the database.
     * @return a Set of all Developers stored at the database.
     */
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

    /**
     * Method that finds all developers stored at the database.
     * @return a Set of all DeveloperDTOs stored at the database.
     */
    public Set<DeveloperDTO> findAllDTO() throws SQLException {
        Set<DeveloperDTO> result = new HashSet<DeveloperDTO>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALLDTO)) {
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

    /**
     * Method that finds all coincidences in dev names stored at the database.
     * @param searchInput , a String provided by the user at the searchbar.
     * @return an ObservableList of DeveloperDTOs containing all games whose name contains the provided String.
     */
    public ObservableList<DeveloperDTO> findContainingNames(String searchInput) throws Exception {
        ObservableList<DeveloperDTO> result = FXCollections.observableArrayList();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDCONTAININGNAMESDTO)) {
            pst.setString(1, "%" + searchInput + "%");
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

    /**
     * Method that finds a developer stored at the database.
     * @param param , the name to find.
     * @return the Developer found/null if not found.
     */
    @Override
    public Developer find(String param) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYNAME)) {
            pst.setString(1, param);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    return new Developer(res.getString("name"), res.getString("description"), Countries.valueOf(res.getString("country")), res.getDate("birth_date"), res.getString("logo"));
                }
            }
        }
        return null;
    }

    /**
     * Method that finds a developer stored at the database.
     * @param param , the name to find.
     * @return the DeveloperDTO found/null if not found.
     */
    public DeveloperDTO findDTO(String param) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYNAMEDTO)) {
            pst.setString(1, param);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    return new DeveloperDTO(res.getString("name"));
                }
            }
        }
        return null;
    }

    /**
     * Method that finds a developer stored at the database.
     * @param id , the id to find.
     * @return the Developer found/null if not found.
     */
    @Override
    public Developer find(int id) throws SQLException {
        try(PreparedStatement pst = this.conn.prepareStatement(FINDBYID)){
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()){
                if(res.next()){
                    return new Developer(res.getString("name"), res.getString("description"), Countries.valueOf(res.getString("country")), res.getDate("birth_date"), res.getString("logo"));
                }
            }
        }

        return null;
    }

    /**
     * Method that finds a developer stored at the database.
     * @param id , the id to find.
     * @return the DeveloperDTO found/null if not found.
     */
    public DeveloperDTO findDTO(int id) throws SQLException {
        try(PreparedStatement pst = this.conn.prepareStatement(FINDBYIDDTO)){
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()){
                if(res.next()){
                    return new DeveloperDTO(res.getString("name"));
                }
            }
        }

        return null;
    }

    /**
     * Method that stores/updates a Developer at the database.
     * @param entity , the Developer to save.
     * @return the stored/updated Developer.
     */
    @Override
    public Developer save(Object entity) throws SQLException {
        Developer d = find(((Developer)entity).getName());

        if(d == null){
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

    /**
     * Method that removes a developer stored at the database.
     * @param entity , the Developer to remove.
     */
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

    /**
     * Method that gets the id from a Developer stored at the database.
     * @param developer , the Developer to find.
     * @return the id of that Developer if found/-1 if not found.
     */
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

    public boolean hasGames(Developer developer) throws SQLException {

        if(!developer.getName().equals("")){
            try(PreparedStatement pst = this.conn.prepareStatement(GETDEVGAMES)){
                pst.setInt(1, getId(developer));
                try(ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}
