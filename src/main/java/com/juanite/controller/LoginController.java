package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.AdminDAO;
import com.juanite.model.domain.Admin;
import com.juanite.util.AppData;
import com.juanite.util.Validator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.util.Optional;

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
    public Label lbl_email;
    @FXML
    public Label lbl_password;
    @FXML
    public TextField txtfld_email;
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
    public void btnLoginValidate() throws Exception {
        AppData.setPreviousScene("login");
        try (AdminDAO adao = new AdminDAO()) {
            if(!Validator.validateCompanyEmail(txtfld_email.getText()) || !Validator.validateUsername(txtfld_email.getText())) {
                if(!Validator.validatePassword(txtfld_password.getText())) {
                    Admin admin = adao.find(txtfld_email.getText());
                    if(txtfld_password.getText().equals(admin.getPassword())) {
                        AppData.setAdmin(admin);
                        if (AppData.getAdmin() != null) {
                            Stage stage = App.getStage();
                            stage.setWidth(800);
                            stage.setHeight(600);
                            App.setRoot("main");
                            stage.setTitle("BOARED - Main");
                        }
                    }
                }else{

                }
            }else{

            }
        }

    }
}