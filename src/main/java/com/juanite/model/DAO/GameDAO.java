package com.juanite.model.DAO;

import com.juanite.model.DTO.GameDTO;
import com.juanite.model.connections.ConnectionMySQL;
import com.juanite.model.domain.Game;
import com.juanite.model.domain.Tags;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class GameDAO implements DAO {

    private final static String FINDALL = "SELECT * FROM game";
    private final static String FINDBYTITLE = "SELECT * FROM game WHERE title=?";
    private final static String FINDBYCODE = "SELECT * FROM game WHERE code=?";
    private final static String INSERT = "INSERT INTO game (title,description,tags,release_date,price,logo,score,images,dev_id) VALUES (?,?,?,?,?)";
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

    @Override
    public Set<Game> findAll() throws Exception {
        Set<Game> result = new HashSet<Game>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Game g = new Game();
                    g.setTitle(res.getString("title"));
                    g.setDescription(res.getString("description"));
                    g.setTags(convertTags(res));
                    g.setReleaseDate(res.getDate("release_date"));
                    g.setPrice(res.getDouble("price"));
                    g.setLogo(res.getString("logo"));
                    g.setScore(res.getDouble("score"));
                    g.setImages(convertImages(res));

                    try (DeveloperDAO dDao = new DeveloperDAO()) {
                        g.setDeveloper(dDao.find(res.getInt("id")));
                    }
                    result.add(g);
                }
            }
        }
        return result;
    }

    public Set<GameDTO> findAllDTO() throws Exception {
        Set<GameDTO> result = new HashSet<GameDTO>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    GameDTO g = new GameDTO();
                    g.setTitle(res.getString("title"));
                    g.setTags(convertTags(res));
                    g.setPrice(res.getDouble("price"));
                    g.setLogo(res.getString("logo"));
                    result.add(g);
                }
            }
        }
        return result;
    }

    @Override
    public Game find(String param) throws SQLException {
        Game result = new Game();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYTITLE)) {
            pst.setString(1, param);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setTitle(res.getString("title"));
                    result.setDescription(res.getString("description"));
                    result.setTags(convertTags(res));
                    result.setReleaseDate(res.getDate("release_date"));
                    result.setPrice(res.getDouble("price"));
                    result.setLogo(res.getString("logo"));
                    result.setScore(res.getDouble("score"));
                    result.setImages(convertImages(res));
                }
            }
        }
        return result;
    }

    public GameDTO findDTO(String param) throws SQLException {
        GameDTO result = new GameDTO();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYTITLE)) {
            pst.setString(1, param);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setTitle(res.getString("title"));
                    result.setTags(convertTags(res));
                    result.setPrice(res.getDouble("price"));
                    result.setLogo(res.getString("logo"));
                }
            }
        }
        return result;
    }

    @Override
    public Game find(int id) throws Exception {
        Game result = new Game();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYCODE)) {
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setTitle(res.getString("title"));
                    result.setDescription(res.getString("description"));
                    result.setTags(convertTags(res));
                    result.setReleaseDate(res.getDate("release_date"));
                    result.setPrice(res.getDouble("price"));
                    result.setLogo(res.getString("logo"));
                    result.setScore(res.getDouble("score"));
                    result.setImages(convertImages(res));
                }
            }
        }
        return result;
    }

    public GameDTO findDTO(int id) throws Exception {
        GameDTO result = new GameDTO();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYCODE)) {
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setTitle(res.getString("title"));
                    result.setTags(convertTags(res));
                    result.setPrice(res.getDouble("price"));
                    result.setLogo(res.getString("logo"));
                }
            }
        }
        return result;
    }

    @Override
    public Game save(Object entity) throws Exception {
        Game g = find(((Game)entity).getTitle());

        if(g.getTitle().equals("")){
            try(PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                pst.setString(1, ((Game)entity).getTitle());
                pst.setString(2, ((Game)entity).getDescription());
                pst.setString(3, convertTags(((Game)entity).getTags()));
                pst.setDate(4, (java.sql.Date) ((Game)entity).getReleaseDate());
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
                pst.setDate(4, (java.sql.Date) ((Game)entity).getReleaseDate());
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

        return g;
    }

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

    public Set<Tags> convertTags(ResultSet res) throws SQLException {
        Set<Tags> tags = new HashSet<Tags>();
        Set<String> strings = Arrays.stream(res.getString("tags").split(",")).collect(Collectors.toSet());
        for(String tag : strings){
            tags.add(Tags.valueOf(tag));
        }
        return tags;
    }

    public String convertTags(Set<Tags> tags) {
        StringBuilder result = new StringBuilder();
        for(Tags tag : tags){
            result.append(tag.name()).append(",");
        }
        return result.toString();
    }

    public List<String> convertImages(ResultSet res) throws SQLException {
        List<String> strings = new ArrayList<String>();
        strings = Arrays.stream(res.getString("tags").split(",")).collect(Collectors.toList());
        return strings;
    }

    public String convertImages(List<String> images) {
        StringBuilder result = new StringBuilder();
        for(String image : images){
            result.append(image).append(",");
        }
        return result.toString();
    }
}
