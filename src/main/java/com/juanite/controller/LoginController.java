package com.juanite.controller;

import com.juanite.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private static double xOffset = 0;
    private static double yOffset = 0;

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
    public Label lbl_username;
    @FXML
    public Label lbl_password;
    @FXML
    public TextField txtfld_username;
    @FXML
    public PasswordField txtfld_password;
    @FXML
    public Button btn_login;
    @FXML
    public Button btn_signup;
    @FXML
    public Label lbl_slogan1;
    @FXML
    public Label lbl_slogan2;
    @FXML
    public Label lbl_slogan3;

    @FXML
    public void initialize(){
        img_resize.setOnMousePressed(this::resizeWindow);
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
        });
    }

    @FXML
    public void btnLoginValidate() throws IOException {
        Stage stage = App.getStage();
        stage.setWidth(800);
        stage.setHeight(600);
        App.setRoot("main");
        stage.setTitle("BOARED - Main");
    }
}