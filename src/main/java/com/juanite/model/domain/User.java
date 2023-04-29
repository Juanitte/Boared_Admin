package com.juanite.model.domain;

import com.juanite.model.domain.interfaces.iUser;

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
        this.birthDate = "";
        this.country = Countries.NONE;
        this.town = "";
        this.address = "";
        this.phoneNumber = "";
        this.avatar = "basic_avatar.png";
        this.games = new HashSet<Game>();
        this.friends = new HashSet<User>();
        this.pendingFriends = new HashMap<User,Boolean>();
    }
    public User(String username, String password, String email, String name, String surname, String birthDate, Countries country, String town, String address, String phoneNumber) {
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
        this.avatar = "basic_avatar.png";
        this.games = new HashSet<Game>();
        this.friends = new HashSet<User>();
        this.pendingFriends = new HashMap<User,Boolean>();
    }
    public User(String username, String password, String email, String name, String surname, String birthDate, Countries country, String town, String address, String phoneNumber, String avatar, Set<Game> games, Set<User> friends, Map<User,Boolean> pendingFriends) {
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
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public void setBirthDate(String birthDate) {
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


    @Override
    public User create() {


        return this;
    }

    @Override
    public User update(String username, String password, String name, String surname, String email, String birthDate, Countries country, String town, String address, String phoneNumber, String avatar) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.country = country;
        this.town = town;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        return this;
    }

    @Override
    public User remove() {
        return this;
    }

    @Override
    public boolean addGame(Game game) {
        return this.games.add(game);
    }


    @Override
    public boolean removeGame(Game game) {
        return this.games.remove(game);
    }

    @Override
    public boolean addFriend(User user) {
        return this.friends.add(user);
    }

    @Override
    public boolean removeFriend(User user) {
        return this.friends.remove(user);
    }

    @Override
    public boolean addFriendRequest(User user, boolean isSender) {
        return Boolean.TRUE.equals(this.pendingFriends.put(user, isSender));
    }

    @Override
    public boolean removeFriendRequest(User user) {
        return this.pendingFriends.remove(user);
    }
}