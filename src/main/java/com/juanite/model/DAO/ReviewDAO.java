package com.juanite.model.DAO;

import com.juanite.model.connections.ConnectionMySQL;
import com.juanite.model.domain.Review;
import com.juanite.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class ReviewDAO implements AutoCloseable{

    private final static String FINDALL = "SELECT * FROM user_games";
    private final static String FINDBYUSER = "SELECT * FROM user_games WHERE user_id=?";
    private final static String FINDBYGAME = "SELECT * FROM user_games WHERE game_code=?";
    private final static String FIND = "SELECT * FROM user_games WHERE user_id=? AND game_code=?";
    private final static String UPDATE = "UPDATE user_games SET review=?, score=? WHERE user_id=? AND game_code=?";
    private final static String DELETE = "UPDATE user_games SET review=?, score=? WHERE user_id=? AND game_code=?";

    private Connection conn;

    public ReviewDAO(Connection conn){
        this.conn = conn;
    }
    public ReviewDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    /**
     * Method that finds all reviews stored at the database.
     * @return a Set of all Reviews stored at the database.
     */
    public Set<Review> findAll() throws Exception {
        Set<Review> result = new HashSet<Review>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Review r = new Review();
                    try (UserDAO udao = new UserDAO()) {
                        r.setUser(udao.find(res.getInt("user_id")));
                    }
                    try (GameDAO gdao = new GameDAO()) {
                        r.setGame(gdao.find(res.getInt("game_code")));
                    }
                    r.setReview(res.getString("review"));
                    r.setScore(res.getDouble("score"));
                    result.add(r);
                }
            }
        }
        return result;
    }

    /**
     * Method that finds a review stored at the database.
     * @param userId , the user_id to find.
     * @param gameCode , the game_code to find.
     * @return the Review found/null if not found.
     */
    public Review find(int userId, int gameCode) throws Exception {
        Review r = new Review();
        try (PreparedStatement pst = this.conn.prepareStatement(FIND)) {
            pst.setInt(1, userId);
            pst.setInt(2, gameCode);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    try (UserDAO udao = new UserDAO()) {
                        r.setUser(udao.find(res.getInt("user_id")));
                    }
                    try (GameDAO gdao = new GameDAO()) {
                        r.setGame(gdao.find(res.getInt("game_code")));
                    }
                    r.setReview(res.getString("review"));
                    r.setScore(res.getDouble("score"));
                }
            }
        }
        return r;
    }

    /**
     * Method that finds all reviews from a user stored at the database.
     * @param userId , the user_id to find.
     * @return the Set of Review found/null if not found.
     */
    public Set<Review> findByUser(int userId) throws Exception {
        Set<Review> result = new HashSet<Review>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDBYUSER)) {
            pst.setInt(1, userId);
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Review r = new Review();
                    try (UserDAO udao = new UserDAO()) {
                        r.setUser(udao.find(res.getInt("user_id")));
                    }
                    try (GameDAO gdao = new GameDAO()) {
                        r.setGame(gdao.find(res.getInt("game_code")));
                    }
                    r.setReview(res.getString("review"));
                    r.setScore(res.getDouble("score"));
                    result.add(r);
                }
            }
        }
        return result;
    }

    /**
     * Method that finds a review stored at the database.
     * @param gameCode , the game_code to find.
     * @return the Review found/null if not found.
     */
    public Set<Review> findByGame(int gameCode) throws Exception {
        Set<Review> result = new HashSet<Review>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDBYGAME)) {
            pst.setInt(1, gameCode);
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Review r = new Review();
                    try (UserDAO udao = new UserDAO()) {
                        r.setUser(udao.find(res.getInt("user_id")));
                    }
                    try (GameDAO gdao = new GameDAO()) {
                        r.setGame(gdao.find(res.getInt("game_code")));
                    }
                    r.setReview(res.getString("review"));
                    r.setScore(res.getDouble("score"));
                    result.add(r);
                }
            }
        }
        return result;
    }

    /**
     * Method that stores/updates a Review at the database.
     * @param entity , the Review to save.
     * @return the stored/updated Review.
     */
    public Review save(Object entity) throws Exception {
        try (UserDAO udao = new UserDAO()) {
            try(GameDAO gdao = new GameDAO()) {
                Review r = find(udao.getId(((Review)entity).getUser()), gdao.getCode(((Review)entity).getGame()));
                try (PreparedStatement pst = this.conn.prepareStatement(UPDATE)) {
                    pst.setString(1, ((Review) entity).getReview());
                    pst.setDouble(2, ((Review) entity).getScore());
                    pst.setInt(3, udao.getId(((Review) entity).getUser()));
                    pst.setInt(4, gdao.getCode(((Review) entity).getGame()));
                    pst.executeUpdate();
                }
            }
        }
        return (Review)entity;
    }

    /**
     * Method that removes a review stored at the database.
     * @param entity , the Review to remove.
     */
    public void delete(Object entity) throws Exception {
        try (UserDAO udao = new UserDAO()) {
            try (GameDAO gdao = new GameDAO()) {
                Review r = find(udao.getId(((Review)entity).getUser()), gdao.getCode(((Review)entity).getGame()));

                if (r.getUser() != null && r.getGame() != null) {
                    try (PreparedStatement pst = this.conn.prepareStatement(DELETE)) {
                        pst.setString(1, "");
                        pst.setDouble(2, -1);
                        pst.setInt(3, udao.getId(((Review) entity).getUser()));
                        pst.setInt(4, gdao.getCode(((Review) entity).getGame()));
                        pst.executeUpdate();
                    }
                }
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
