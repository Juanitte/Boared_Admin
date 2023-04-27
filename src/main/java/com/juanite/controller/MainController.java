package com.juanite.controller;

import com.juanite.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private static double xOffset = 0;
    private static double yOffset = 0;
    private static double width = 800;
    private static double height = 600;

    public static double getWidth(){
        return MainController.width;
    }
    public static void setWidth(double width){
        MainController.width = width;
    }
    public static double getHeight(){
        return MainController.height;
    }
    public static void setHeight(double height){
        MainController.height = height;
    }

    @FXML
    public Label lbl_titlebar;
    @FXML
    public Button btn_minimize;
    @FXML
    public Button btn_maximize;
    @FXML
    public Button btn_close;
    @FXML
    public ToolBar tb_1;
    @FXML
    public ToolBar tb_2;
    @FXML
    public ImageView img_icon;
    @FXML
    public ImageView img_resize;
    @FXML
    public Button btn_games;
    @FXML
    public Button btn_users;
    @FXML
    public Button btn_profile;
    @FXML
    public ImageView img_iconBigInv;
    @FXML
    public ImageView img_iconBig;
    @FXML
    public Label lbl_slogan1;
    @FXML
    public Label lbl_slogan2;
    @FXML
    public Label lbl_slogan3;
    @FXML
    public Separator separator_1;
    @FXML
    public Label lbl_news;
    @FXML
    public Separator separator_2;
    @FXML
    public Label lbl_newsContent1;
    @FXML
    public Button btn_logout;

    @FXML
    public void initialize(){
        img_resize.setOnMousePressed(this::resizeWindow);
        width = App.getStage().getWidth();
        height = App.getStage().getHeight();
    }

    @FXML
    public void btnCloseValidate(){
        Stage stage = App.getStage();
        stage.close();
    }
    @FXML
    public void btnMinimizeValidate(){
        Stage stage = App.getStage();
        stage.setIconified(true);
    }
    @FXML
    public void btnMaximizeValidate(){
        Stage stage = App.getStage();
        stage.setMaximized(!stage.isMaximized());
    }
    @FXML
    public void tbClickValidate(MouseEvent event) {
        Stage stage = App.getStage();
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }

    @FXML
    public void tbDragValidate(MouseEvent event) {
        Stage stage = App.getStage();
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }

    @FXML
    public void resizeWindow(MouseEvent event) {
        Stage stage = App.getStage();
        double offsetX = event.getSceneX();
        double offsetY = event.getSceneY();
        double width = stage.getWidth();
        double height = stage.getHeight();

        img_resize.setOnMouseDragged(e -> {
            double newWidth = width + (e.getSceneX() - offsetX);
            double newHeight = height + (e.getSceneY() - offsetY);
            stage.setWidth(newWidth);
            stage.setHeight(newHeight);
            MainController.width = newWidth;
            MainController.height = newHeight;
        });
    }

    @FXML
    public void lblTitleValidate() throws IOException {
        Stage stage = App.getStage();
        boolean maximize = stage.isMaximized();
        App.setRoot("main");
        stage.setTitle("BOARED - Main");
        if(maximize){
            stage.setMaximized(true);
        }else {
            stage.setWidth(MainController.width);
            stage.setHeight(MainController.height);
        }
    }

    @FXML
    public void btnGamesValidate() throws IOException {
        Stage stage = App.getStage();
        boolean maximize = stage.isMaximized();
        App.setRoot("games");
        stage.setTitle("BOARED - Games");
        if(maximize){
            stage.setMaximized(true);
        }else {
            stage.setWidth(MainController.width);
            stage.setHeight(MainController.height);
        }
    }

    @FXML
    public void btnUsersValidate() throws IOException {
        Stage stage = App.getStage();
        boolean maximize = stage.isMaximized();
        App.setRoot("users");
        stage.setTitle("BOARED - Users");
        if(maximize){
            stage.setMaximized(true);
        }else {
            stage.setWidth(MainController.getWidth());
            stage.setHeight(MainController.getHeight());
        }
    }

    @FXML
    public void btnLogoutValidate() throws IOException {
        Stage stage = App.getStage();
        App.setRoot("login");
        stage.setTitle("BOARED - Log in");
        stage.setWidth(350);
        stage.setHeight(400);
    }
}