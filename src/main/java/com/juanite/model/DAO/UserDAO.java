package com.juanite.model.DAO;

import com.juanite.model.DTO.GameDTO;
import com.juanite.model.DTO.UserDTO;
import com.juanite.model.connections.ConnectionMySQL;
import com.juanite.model.domain.Countries;
import com.juanite.model.domain.User;
import com.juanite.util.AppData;
import com.juanite.util.PasswordAuthentication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDAO implements DAO {

    private final static String FINDALL = "SELECT * FROM user";
    private final static String FINDALLDTO = "SELECT username, country, avatar, banned FROM user";
    private final static String FINDBYUSERNAME = "SELECT * FROM user WHERE username=?";
    private final static String FINDBYEMAIL = "SELECT * FROM user WHERE email=?";
    private final static String FINDBYID = "SELECT * FROM user WHERE id=?";
    private final static String FINDBYUSERNAMEDTO = "SELECT username, country, avatar, banned FROM user WHERE username=?";
    private final static String FINDBYEMAILDTO = "SELECT username, country, avatar, banned FROM user WHERE email=?";
    private final static String FINDBYIDDTO = "SELECT username, country, avatar, banned FROM user WHERE id=?";
    private final static String FINDCONTAININGNAMESDTO = "SELECT username, country, avatar, banned FROM user WHERE username LIKE ?";
    private final static String INSERT = "INSERT INTO user (username,password,name,surname,email,country,town,address,birth_date,phone_number,avatar, banned) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String UPDATE = "UPDATE user SET username=?, password=?, name=?, surname=?, email=?, country=?, town=?, address=?, birth_date=?, phone_number=?, avatar=?, banned=? WHERE id=?";
    private final static String UPDATEBANSTATUS = "UPDATE user SET banned=? WHERE id=?";
    private final static String DELETE = "DELETE FROM user WHERE id=?";
    private final static String GETID = "SELECT id FROM user WHERE email=?";

    private Connection conn;

    public UserDAO(Connection conn){
        this.conn = conn;
    }
    public UserDAO(){
        this.conn = ConnectionMySQL.getConnect();
    }

    /**
     * Method that finds all users stored at the database.
     * @return a Set of all Users stored at the database.
     */
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
                    u.setBanned(res.getBoolean("banned"));
                    result.add(u);
                }
            }
        }
        return result;
    }

    /**
     * Method that finds all users stored at the database.
     * @return a Set of all UserDTOs stored at the database.
     */
    @Override
    public Set<UserDTO> findAllDTO() throws SQLException {
        Set<UserDTO> result = new HashSet<UserDTO>();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDALLDTO)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    UserDTO u = new UserDTO(res.getString("username"), Countries.valueOf(res.getString("country")), res.getString("avatar"), res.getBoolean("banned"));
                    result.add(u);
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
    public ObservableList<UserDTO> findContainingNames(String searchInput) throws Exception {
        ObservableList<UserDTO> result = FXCollections.observableArrayList();
        try(PreparedStatement pst = this.conn.prepareStatement(FINDCONTAININGNAMESDTO)) {
            pst.setString(1, "%" + searchInput + "%");
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    UserDTO u = new UserDTO();
                    u.setUsername(res.getString("username"));
                    u.setCountry(Countries.valueOf(res.getString("country")));
                    u.setAvatar(res.getString("avatar"));
                    u.setBanned(res.getBoolean("banned"));
                    result.add(u);
                }
            }
        }
        return result;
    }

    /**
     * Method that finds a user stored at the database.
     * @param param , the email/username to find.
     * @return the User found/null if not found.
     */
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
                        result.setBanned(res.getBoolean("banned"));
                        return result;
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
                        result.setBanned(res.getBoolean("banned"));
                        return result;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that finds a user stored at the database.
     * @param param , the email/username to find.
     * @return the UserDTO found/null if not found.
     */
    @Override
    public UserDTO findDTO(String param) throws SQLException {
        if(!param.contains("@")) {
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYUSERNAMEDTO)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        return new UserDTO(res.getString("username"), Countries.valueOf(res.getString("country")), res.getString("avatar"), res.getBoolean("banned"));
                    }
                }
            }
        }else{
            try (PreparedStatement pst = this.conn.prepareStatement(FINDBYEMAILDTO)) {
                pst.setString(1, param);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        return new UserDTO(res.getString("username"), Countries.valueOf(res.getString("country")), res.getString("avatar"), res.getBoolean("banned"));
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that finds a user stored at the database.
     * @param id , the id to find.
     * @return the User found/null if not found.
     */
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
                    result.setBanned(res.getBoolean("banned"));
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Method that finds a user stored at the database.
     * @param id , the id to find.
     * @return the UserDTO found/null if not found.
     */
    @Override
    public UserDTO findDTO(int id) throws SQLException {
        try (PreparedStatement pst = this.conn.prepareStatement(FINDBYIDDTO)) {
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    return new UserDTO(res.getString("username"), Countries.valueOf(res.getString("country")), res.getString("avatar"), res.getBoolean("banned"));
                }
            }
        }
        return null;
    }

    /**
     * Method that stores/updates a User at the database.
     * @param entity , the User to save.
     * @return the stored/updated User.
     */
    @Override
    public User save(Object entity) throws SQLException {
        User u = find(((User)entity).getUsername());

        if(u == null){
            try(PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                pst.setString(1, ((User)entity).getUsername());
                pst.setString(2, AppData.getPa().hash(((User)entity).getPassword()));
                pst.setString(3, ((User)entity).getName());
                pst.setString(4, ((User)entity).getSurname());
                pst.setString(5, ((User)entity).getEmail());
                pst.setString(6, ((User)entity).getCountry().name());
                pst.setString(7, ((User)entity).getTown());
                pst.setString(8, ((User)entity).getAddress());
                pst.setDate(9, ((User)entity).getBirthDate());
                pst.setString(10, ((User)entity).getPhoneNumber());
                pst.setString(11, ((User)entity).getAvatar());
                pst.setBoolean(12, ((User) entity).isBanned());
                pst.executeUpdate();
            }
        }else{
            try(PreparedStatement pst = this.conn.prepareStatement(UPDATE)){
                pst.setString(1, ((User)entity).getUsername());
                pst.setString(2, AppData.getPa().hash(((User)entity).getPassword()));
                pst.setString(3, ((User)entity).getName());
                pst.setString(4, ((User)entity).getSurname());
                pst.setString(5, ((User)entity).getEmail());
                pst.setString(6, ((User)entity).getCountry().name());
                pst.setString(7, ((User)entity).getTown());
                pst.setString(8, ((User)entity).getAddress());
                pst.setDate(9, ((User)entity).getBirthDate());
                pst.setString(10, ((User)entity).getPhoneNumber());
                pst.setString(11, ((User)entity).getAvatar());
                pst.setBoolean(12, ((User) entity).isBanned());
                pst.setInt(13, getId((User)entity));
                pst.executeUpdate();
            }
        }

        return (User)entity;
    }

    /**
     * Method that stores/updates a User at the database.
     * @param entity , the User to save.
     * @return the stored/updated User.
     */
    public User save(Object entity, String oldUsername) throws SQLException {
            try(PreparedStatement pst = this.conn.prepareStatement(UPDATE)){
                pst.setString(1, ((User)entity).getUsername());
                pst.setString(2, AppData.getPa().hash(((User)entity).getPassword()));
                pst.setString(3, ((User)entity).getName());
                pst.setString(4, ((User)entity).getSurname());
                pst.setString(5, ((User)entity).getEmail());
                pst.setString(6, ((User)entity).getCountry().name());
                pst.setString(7, ((User)entity).getTown());
                pst.setString(8, ((User)entity).getAddress());
                pst.setDate(9, ((User)entity).getBirthDate());
                pst.setString(10, ((User)entity).getPhoneNumber());
                pst.setString(11, ((User)entity).getAvatar());
                pst.setBoolean(12, ((User) entity).isBanned());
                pst.setInt(13, getId(find(oldUsername)));
                pst.executeUpdate();
            }

        return (User)entity;
    }

    /**
     * Method that removes a user stored at the database.
     * @param entity , the User to remove.
     */
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

    /**
     * Method that gets the id from a User stored at the database.
     * @param user , the User to find.
     * @return the id of that User if found/-1 if not found.
     */
    public int getId(User user) throws SQLException {
        if(user != null){
            if(!user.getUsername().equals("")){
                try(PreparedStatement pst = this.conn.prepareStatement(GETID)){
                    pst.setString(1, user.getEmail());
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

    /**
     * Method that updates the ban status from a user.
     * @param user , the user to ban/unban.
     */
    public void updateBanStatus(User user) throws SQLException {
        if(user != null){
            try (PreparedStatement pst = this.conn.prepareStatement(UPDATEBANSTATUS)) {
                pst.setBoolean(1, !user.isBanned());
                pst.setInt(2, getId(find(user.getUsername())));
                pst.executeUpdate();
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
