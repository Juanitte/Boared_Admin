package com.juanite.util;

import com.juanite.App;
import com.juanite.model.DTO.DeveloperDTO;
import com.juanite.model.DTO.GameDTO;
import com.juanite.model.DTO.ReviewDTO;
import com.juanite.model.DTO.UserDTO;
import com.juanite.model.domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.Set;

public class AppData {

    /**
     * admin , the Logged admin. Used for authentication purposes too.
     */
    private static Admin admin;

    /**
     * width , the window's actual Width.
     */
    private static double width = 600;

    /**
     * height , the window's actual Height.
     */
    private static double height = 800;

    /**
     * errorMsg , the error message to be displayed when switching to error screen.
     */
    private static String errorMsg;

    /**
     * confirmationType , an identification String that tells the confirmation window what to do.
     */
    private static String confirmationType;

    /**
     * previousScene , an identification String that tells the app which one was the last window visited.
     */
    private static String previousScene;

    /**
     * stage , the actual app's Stage.
     */
    private static Stage stage = App.getStage();

    /**
     * pa , an instance from the PasswordAuthentication class. Used to hash and authenticate passwords.
     */
    private static PasswordAuthentication pa = new PasswordAuthentication();

    /**
     * games , the List of Games to be displayed at the TableView.
     * game , the selected Game to work with.
     */
    private static ObservableList<GameDTO> games = FXCollections.observableArrayList();
    private static Game game;

    /**
     * developers , the List of Developers to be displayed at the TableView.
     * developer , the selected Developer to work with.
     */
    private static ObservableList<DeveloperDTO> developers = FXCollections.observableArrayList();
    private static Developer developer;

    /**
     * users , the List of Users to be displayed at the TableView.
     * user , the selected User to work with.
     */
    private static ObservableList<UserDTO> users = FXCollections.observableArrayList();
    private static User user;

    /**
     * reviews , the List of Reviews to be displayed at the TableView.
     * review , the selected Review to work with.
     */
    private static ObservableList<ReviewDTO> reviews = FXCollections.observableArrayList();
    private static Review review;

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        AppData.admin = admin;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        AppData.height = height;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        AppData.width = width;
    }

    public static String getErrorMsg() {
        return errorMsg;
    }

    public static void setErrorMsg(String errorMsg) {
        AppData.errorMsg = errorMsg;
    }

    public static String getPreviousScene() {
        return previousScene;
    }

    public static void setPreviousScene(String previousScene) {
        AppData.previousScene = previousScene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AppData.stage = stage;
    }

    public static PasswordAuthentication getPa() {
        return pa;
    }

    public static void setPa(PasswordAuthentication pa) {
        AppData.pa = pa;
    }

    public static ObservableList<GameDTO> getGames() {
        return games;
    }

    public static void setGames(ObservableList<GameDTO> games) {
        AppData.games = games;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        AppData.game = game;
    }

    public static String getConfirmationType() {
        return confirmationType;
    }

    public static void setConfirmationType(String confirmationType) {
        AppData.confirmationType = confirmationType;
    }

    public static Developer getDeveloper() {
        return developer;
    }

    public static void setDeveloper(Developer developer) {
        AppData.developer = developer;
    }

    public static ObservableList<DeveloperDTO> getDevelopers() {
        return developers;
    }

    public static void setDevelopers(ObservableList<DeveloperDTO> developers) {
        AppData.developers = developers;
    }

    public static ObservableList<UserDTO> getUsers() {
        return users;
    }

    public static void setUsers(ObservableList<UserDTO> users) {
        AppData.users = users;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        AppData.user = user;
    }

    public static ObservableList<ReviewDTO> getReviews() {
        return reviews;
    }

    public static void setReviews(ObservableList<ReviewDTO> reviews) {
        AppData.reviews = reviews;
    }

    public static Review getReview() {
        return review;
    }

    public static void setReview(Review review) {
        AppData.review = review;
    }
}
