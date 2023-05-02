package com.juanite.model.DAO;

import com.juanite.model.DTO.GameDTO;
import com.juanite.model.connections.ConnectionMySQL;
import com.juanite.model.domain.Developer;
import com.juanite.model.domain.Game;
import com.juanite.model.domain.Tags;
import com.juanite.model.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class GameDAO implements DAO {

    private final static String FINDALL = "SELECT * FROM game";
    private final static String FINDALLDTO = "SELECT title, tags, price, logo, dev_id FROM game";
    private final static String FINDBYTITLE = "SELECT * FROM game WHERE title=?";
    private final static String FINDBYCODE = "SELECT * FROM game WHERE code=?";
    private final static String FINDBYTITLEDTO = "SELECT title, tags, price, logo, dev_id FROM game WHERE title=?";
    private final static String FINDBYCODEDTO = "SELECT title, tags, price, logo, dev_id FROM game WHERE code=?";
    private final static String FINDCONTAININGTITLESDTO = "SELECT title, tags, price, logo, dev_id FROM game WHERE title LIKE ?";
    private final static String INSERT = "INSERT INTO game (title,description,tags,release_date,price,logo,score,images,dev_id) VALUES (?,?,?,?,?,?,?,?,?)";
    private final static String UPDATE = "UPDATE game SET title=?, description=?, tags=?, release_date=?, price=?, logo=?, score=?, images=?, dev_id=? WHERE code=?";
    private final static String DELETE = "DELETE FROM game WHERE code=?";
    private final static String GETCODE = "SELECT code FROM game WHERE title=?";

    private Connection conn;

    public GameDAO(Connection conn){
        this.conn = conn;
    }
    public GameDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    /**
     * Method that finds all games stored at the database.
     * @return a Set of all Games stored at the database.
     */
    @Override
    public Set<Game> findAll() throws Exception {
        Set<Game> result = new HashSet<Game>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    try (DeveloperDAO dDao = new DeveloperDAO()) {
                        Game g = new Game();
                        g.setTitle(res.getString("title"));
                        g.setDescription(res.getString("description"));
                        g.setTags(convertTags(res));
                        g.setReleaseDate(res.getDate("release_date"));
                        g.setPrice(res.getDouble("price"));
                        g.setLogo(res.getString("logo"));
                        g.setScore(res.getDouble("score"));
                        g.setImages(convertImages(res));
                        g.setDeveloper(dDao.find(res.getInt("id")));
                        result.add(g);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Method that finds all games stored at the database.
     * @return a Set of all GameDTOs stored at the database.
     */
    public Set<GameDTO> findAllDTO() throws Exception {
        Set<GameDTO> result = new HashSet<GameDTO>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALLDTO)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    try (DeveloperDAO ddao = new DeveloperDAO()) {
                        GameDTO g = new GameDTO();
                        g.setTitle(res.getString("title"));
                        g.setTags(convertTags(res));
                        g.setPrice(res.getDouble("price"));
                        g.setLogo(res.getString("logo"));
                        g.setDeveloper(ddao.findDTO(res.getInt("dev_id")).toString());
                        result.add(g);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Method that finds all coincidences in game titles stored at the database.
     * @param searchInput , a String provided by the user at the searchbar.
     * @return an ObservableList of GameDTOs containing all games whose title contains the provided String.
     */
    public ObservableList<GameDTO> findContainingTitles(String searchInput) throws Exception {
        ObservableList<GameDTO> result = FXCollections.observableArrayList();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDCONTAININGTITLESDTO)) {
            pst.setString(1, "%" + searchInput + "%");
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    try (DeveloperDAO ddao = new DeveloperDAO()) {
                        GameDTO g = new GameDTO();
                        g.setTitle(res.getString("title"));
                        g.setTags(convertTags(res));
                        g.setPrice(res.getDouble("price"));
                        g.setLogo(res.getString("logo"));
                        g.setDeveloper(ddao.findDTO(res.getInt("dev_id")).toString());
                        result.add(g);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Method that finds a game stored at the database.
     * @param param , the title to find.
     * @return the Game found/null if not found.
     */
    @Override
    public Game find(String param) throws Exception {
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYTITLE)) {
            pst.setString(1, param);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    try (DeveloperDAO ddao = new DeveloperDAO()) {
                        return new Game(res.getString("title"), res.getString("description"),
                                        convertTags(res), res.getDate("release_date"),
                                        res.getDouble("price"), res.getString("logo"),
                                        res.getDouble("score"), convertImages(res),
                                        ddao.find(res.getInt("dev_id")), new HashSet<User>());
                    }

                }
            }
        }
        return null;
    }

    /**
     * Method that finds a game stored at the database.
     * @param param , the title to find.
     * @return the GameDTO found/null if not found.
     */
    public GameDTO findDTO(String param) throws Exception {
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYTITLEDTO)) {
            pst.setString(1, param);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    try (DeveloperDAO ddao = new DeveloperDAO()) {
                        return new GameDTO(res.getString("title"), res.getString("logo"), convertTags(res), res.getDouble("price"), ddao.findDTO(res.getInt("dev_id")).toString());
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that finds a game stored at the database.
     * @param id , the id to find.
     * @return the Game found/null if not found.
     */
    @Override
    public Game find(int id) throws Exception {
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYCODE)) {
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    try (DeveloperDAO ddao = new DeveloperDAO()) {
                        return new Game(res.getString("title"), res.getString("description"),
                                convertTags(res), res.getDate("release_date"),
                                res.getDouble("price"), res.getString("logo"),
                                res.getDouble("score"), convertImages(res),
                                ddao.find(res.getInt("dev_id")), new HashSet<User>());
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that finds a game stored at the database.
     * @param id , the id to find.
     * @return the GameDTO found/null if not found.
     */
    public GameDTO findDTO(int id) throws Exception {
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYCODEDTO)) {
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    try (DeveloperDAO ddao = new DeveloperDAO()) {
                        return new GameDTO(res.getString("title"), res.getString("logo"), convertTags(res), res.getDouble("price"), ddao.findDTO(res.getInt("dev_id")).toString());
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that stores/updates a Game at the database.
     * @param entity , the Game to save.
     * @return the stored/updated Game.
     */
    @Override
    public Game save(Object entity) throws Exception {
        Game g = find(((Game)entity).getTitle());

        if(g == null){
            try(PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                pst.setString(1, ((Game)entity).getTitle());
                pst.setString(2, ((Game)entity).getDescription());
                pst.setString(3, convertTags(((Game)entity).getTags()));
                pst.setDate(4, ((Game)entity).getReleaseDate());
                pst.setDouble(5, ((Game)entity).getPrice());
                pst.setString(6, ((Game)entity).getLogo());
                pst.setDouble(7, ((Game)entity).getScore());
                pst.setString(8, convertImages(((Game)entity).getImages()));
                try (DeveloperDAO dDao = new DeveloperDAO()) {
                    pst.setInt(9, dDao.getId(((Game)entity).getDeveloper()));
                }
                pst.executeUpdate();
            }
        }else{
            try(PreparedStatement pst = this.conn.prepareStatement(UPDATE)){
                pst.setString(1, ((Game)entity).getTitle());
                pst.setString(2, ((Game)entity).getDescription());
                pst.setString(3, convertTags(((Game)entity).getTags()));
                pst.setDate(4, ((Game)entity).getReleaseDate());
                pst.setDouble(5, ((Game)entity).getPrice());
                pst.setString(6, ((Game)entity).getLogo());
                pst.setDouble(7, ((Game)entity).getScore());
                pst.setString(8, convertImages(((Game)entity).getImages()));
                try (DeveloperDAO dDao = new DeveloperDAO()) {
                    pst.setInt(9, dDao.getId(((Game)entity).getDeveloper()));
                }
                pst.setInt(10, getCode(((Game)entity)));
                pst.executeUpdate();
            }
        }

        return (Game) entity;
    }

    /**
     * Method that stores/updates a Game at the database.
     * @param entity , the Game to save.
     * @param oldTitle , the title to find at the database.
     * @return the updated Game.
     */
    public Game save(Object entity, String oldTitle) throws Exception {
            try(PreparedStatement pst = this.conn.prepareStatement(UPDATE)){
                pst.setString(1, ((Game)entity).getTitle());
                pst.setString(2, ((Game)entity).getDescription());
                pst.setString(3, convertTags(((Game)entity).getTags()));
                pst.setDate(4, ((Game)entity).getReleaseDate());
                pst.setDouble(5, ((Game)entity).getPrice());
                pst.setString(6, ((Game)entity).getLogo());
                pst.setDouble(7, ((Game)entity).getScore());
                pst.setString(8, convertImages(((Game)entity).getImages()));
                try (DeveloperDAO dDao = new DeveloperDAO()) {
                    pst.setInt(9, dDao.getId(((Game)entity).getDeveloper()));
                }
                pst.setInt(10, getCode(find(oldTitle)));
                pst.executeUpdate();
            }
        return (Game) entity;
    }

    /**
     * Method that removes a game stored at the database.
     * @param entity , the Game to remove.
     */
    @Override
    public void delete(Object entity) throws Exception {
        Game g = find(((Game)entity).getTitle());

        if(!g.getTitle().equals("")){
            try(PreparedStatement pst = this.conn.prepareStatement(DELETE)){
                pst.setInt(1, getCode((Game)entity));
                pst.executeUpdate();
            }
        }
    }

    @Override
    public void close() throws Exception {

    }

    /**
     * Method that gets the code from a Game stored at the database.
     * @param game , the Game to find.
     * @return the code of that Game if found/-1 if not found.
     */
    public int getCode(Game game) throws SQLException {
        if(game != null){
            if(!game.getTitle().equals("")){
                try(PreparedStatement pst = this.conn.prepareStatement(GETCODE)){
                    pst.setString(1, game.getTitle());
                    try(ResultSet res = pst.executeQuery()){
                        if(res.next()){
                            return res.getInt("code");
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Method that makes the conversion from a String to a Set, separating it by the commas.
     * @param res , the ResultSet to work with.
     * @return a Set of Tags built using the string provided.
     */
    public Set<Tags> convertTags(ResultSet res) throws SQLException {
        Set<Tags> tags = new HashSet<Tags>();
        Set<String> strings = Arrays.stream(res.getString("tags").split(",")).collect(Collectors.toSet());
        for(String tag : strings){
            tags.add(Tags.valueOf(tag));
        }
        return tags;
    }

    /**
     * Method that makes the conversion from a Set to a String, separating it by commas.
     * @param tags , the Set of Tags to work with.
     * @return a String built using the Set of Tags provided.
     */
    public String convertTags(Set<Tags> tags) {
        StringBuilder result = new StringBuilder();
        for(Tags tag : tags){
            result.append(tag.name()).append(",");
        }
        return result.toString();
    }

    /**
     * Method that makes the conversion from a String to a List, separating it by the commas.
     * @param res , the ResultSet to work with.
     * @return a List of String built using the string provided.
     */
    public List<String> convertImages(ResultSet res) throws SQLException {
        List<String> strings = new ArrayList<String>();
        strings = Arrays.stream(res.getString("images").split(",")).collect(Collectors.toList());
        return strings;
    }

    /**
     * Method that makes the conversion from a List to a String, separating it by commas.
     * @param images , the Set of String to work with.
     * @return a String built using the List of String provided.
     */
    public String convertImages(List<String> images) {
        StringBuilder result = new StringBuilder();
        for(String image : images){
            result.append(image).append(",");
        }
        return result.toString();
    }
}
