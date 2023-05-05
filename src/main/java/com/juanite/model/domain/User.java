package com.juanite.model.domain;

import com.juanite.model.DAO.DeveloperDAO;
import com.juanite.model.DAO.UserDAO;
import com.juanite.model.domain.interfaces.iUser;
import com.juanite.util.AppData;

import java.sql.Date;
import java.util.*;

public class User extends Entity implements iUser {
    private String username;
    private String password;
    private String surname;
    private String email;
    private String town;
    private String address;
    private String phoneNumber;
    private String avatar;
    private boolean banned;
    private Set<User> friends;
    private Map<User,Boolean> pendingFriends;
    //  User    = The other user involved in the request.
    //  Boolean = (True = You sent the request) || (False = You got the request and have to accept/decline it)

    public User() {
        this.username = "";
        this.password = "";
        this.email = "";
        this.name = "";
        this.surname = "";
        this.birthDate = null;
        this.country = Countries.NONE;
        this.town = "";
        this.address = "";
        this.phoneNumber = "";
        this.avatar = "basic_avatar";
        this.banned = false;
        this.games = new HashSet<Game>();
        this.friends = new HashSet<User>();
        this.pendingFriends = new HashMap<User,Boolean>();
    }
    public User(String username, String password, String email, String name, String surname, Date birthDate, Countries country, String town, String address, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.email = email;
        this.town = town;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.country = country;
        this.birthDate = birthDate;
        this.avatar = "basic_avatar";
        this.banned = false;
        this.games = new HashSet<Game>();
        this.friends = new HashSet<User>();
        this.pendingFriends = new HashMap<User,Boolean>();
    }
    public User(String username, String password, String email, String name, String surname, Date birthDate, Countries country, String town, String address, String phoneNumber, String avatar, boolean banned, Set<Game> games, Set<User> friends, Map<User,Boolean> pendingFriends) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.email = email;
        this.town = town;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.friends = friends;
        this.name = name;
        this.avatar = avatar;
        this.banned = banned;
        this.games = games;
        this.country = country;
        this.birthDate = birthDate;
        this.pendingFriends = pendingFriends;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getBirthDate() {
        return this.birthDate;
    }

    @Override
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public Set<Game> getGames() {
        return this.games;
    }

    @Override
    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public Countries getCountry() {
        return this.country;
    }

    @Override
    public void setCountry(Countries country) {
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Map<User,Boolean> getPendingFriends() {
        return pendingFriends;
    }

    public void setPendingFriends(Map<User,Boolean> pendingFriends) {
        this.pendingFriends = pendingFriends;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Method that stores a new User at the database.
     * @return the stored User.
     */
    @Override
    public User create() throws Exception {
        try (UserDAO udao = new UserDAO()) {
            udao.save(this);
        }
        return this;
    }

    /**
     * Method that updates a User stored at the database.
     * @param user , the new User.
     * @return the updated User.
     */
    @Override
    public User update(User user) throws Exception {
        try (UserDAO udao = new UserDAO()) {
            if(udao.find(user.getUsername()) != null){
                udao.save(user);
            }else{
                udao.save(user, this.getUsername());
            }
        }
        return user;
    }

    /**
     * Method that removes a User from the database.
     */
    @Override
    public void remove() throws Exception {
        try (UserDAO udao = new UserDAO()) {
                udao.delete(this);
                AppData.setUser(null);
        }
    }

    /**
     * Method that adds a Game to this User's Games List.
     * @param game , the Game to be added.
     * @return true if it was successful or false if it wasn't.
     */
    @Override
    public boolean addGame(Game game) {
        return this.games.add(game);
    }

    /**
     * Method that removes a Game from this User's Games List.
     * @param game , the Game to be removed.
     * @return true if it was successful or false if it wasn't.
     */
    @Override
    public boolean removeGame(Game game) {
        return this.games.remove(game);
    }

    /**
     * Method that adds a User to this User's friends List.
     * @param user , the User to be added.
     * @return true if it was successful or false if it wasn't.
     */
    @Override
    public boolean addFriend(User user) {
        return this.friends.add(user);
    }

    /**
     * Method that removes a User from this User's friends List.
     * @param user , the User to be removed.
     * @return true if it was successful or false if it wasn't.
     */
    @Override
    public boolean removeFriend(User user) {
        return this.friends.remove(user);
    }

    /**
     * Method that adds a request to this User's friend requests List.
     * @param user , the User to be added.
     * @param isSender , a boolean true if YOU are the sender or false if YOU are the receiver.
     * @return true if it was successful or false if it wasn't.
     */
    @Override
    public boolean addFriendRequest(User user, boolean isSender) {
        return Boolean.TRUE.equals(this.pendingFriends.put(user, isSender));
    }

    /**
     * Method that removes a request from this User's friend requests List.
     * @param user , the User to remove.
     * @return true if it was successful or false if it wasn't.
     */
    @Override
    public boolean removeFriendRequest(User user) {
        return this.pendingFriends.remove(user);
    }
}