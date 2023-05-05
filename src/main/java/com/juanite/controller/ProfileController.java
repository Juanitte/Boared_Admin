package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.AdminDAO;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import com.juanite.util.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ProfileController {

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
    public Button btn_games;
    @FXML
    public Button btn_users;
    @FXML
    public Button btn_profile;
    @FXML
    public Button btn_logout;
    @FXML
    public Label lbl_resetPassword;
    @FXML
    public Label lbl_emailContent;
    @FXML
    public Label lbl_usernameContent;
    @FXML
    public Label lbl_username;
    @FXML
    public Label lbl_email;
    @FXML
    public Label lbl_password;
    @FXML
    public PasswordField txtfld_password;
    @FXML
    public Button btn_devs;
    @FXML
    public Button btn_resetPassword;
    @FXML
    public Label lbl_profile;
    @FXML
    public Label lbl_profileName;

    @FXML
    public void initialize(){
        img_resize.setOnMousePressed(this::resizeWindow);
        btn_profile.setText(AppData.getAdmin().getName());
        lbl_emailContent.setText(AppData.getAdmin().getEmail());
        lbl_usernameContent.setText(AppData.getAdmin().getName());
        lbl_profileName.setText(AppData.getAdmin().getName());
    }

    @FXML
    public void btnCloseValidate(){
        AppData.getStage().close();
    }
    @FXML
    public void btnMinimizeValidate(){
        AppData.getStage().setIconified(true);
    }
    @FXML
    public void btnMaximizeValidate(){
        AppData.getStage().setMaximized(!AppData.getStage().isMaximized());
    }
    @FXML
    public void tbClickValidate(MouseEvent event) {
        xOffset = AppData.getStage().getX() - event.getScreenX();
        yOffset = AppData.getStage().getY() - event.getScreenY();
    }

    @FXML
    public void tbDragValidate(MouseEvent event) {
        AppData.getStage().setX(event.getScreenX() + xOffset);
        AppData.getStage().setY(event.getScreenY() + yOffset);
    }

    @FXML
    public void resizeWindow(MouseEvent event) {
        double offsetX = event.getSceneX();
        double offsetY = event.getSceneY();
        double width = AppData.getStage().getWidth();
        double height = AppData.getStage().getHeight();

        img_resize.setOnMouseDragged(e -> {
            double newWidth = width + (e.getSceneX() - offsetX);
            double newHeight = height + (e.getSceneY() - offsetY);
            AppData.getStage().setWidth(newWidth);
            AppData.getStage().setHeight(newHeight);
            AppData.setWidth(newWidth);
            AppData.setHeight(newHeight);
        });
    }

    @FXML
    public void lblTitleValidate() throws IOException {
        AppData.setPreviousScene("profile");
        AppData.getStage().setTitle("BOARED - Main");
        Utils.switchToScreen("main");
    }

    @FXML
    public void btnGamesValidate() throws IOException {
        AppData.setPreviousScene("profile");
        AppData.getStage().setTitle("BOARED - Games");
        Utils.switchToScreen("games");
    }

    @FXML
    public void btnUsersValidate() throws IOException {
        AppData.setPreviousScene("profile");
        AppData.getStage().setTitle("BOARED - Users");
        Utils.switchToScreen("users");
    }

    @FXML
    public void btnLogoutValidate() throws IOException {
        AppData.setPreviousScene("profile");
        App.setRoot("login");
        AppData.getStage().setTitle("BOARED - Log in");
        AppData.getStage().setWidth(350);
        AppData.getStage().setHeight(400);
    }

    @FXML
    public void btnDevsValidate() throws IOException {
        AppData.setPreviousScene("profile");
        AppData.getStage().setTitle("BOARED - Devs");
        Utils.switchToScreen("devs");
    }

    @FXML
    public void btnProfileValidate() throws IOException {
        AppData.setPreviousScene("profile");
        AppData.getStage().setTitle("BOARED - " + AppData.getAdmin().getName());
        Utils.switchToScreen("profile");
    }

    @FXML
    public void lblResetPasswordValidate() {
        AppData.setPreviousScene("profile");
        if(lbl_password.isVisible()){
            lbl_password.setVisible(false);
            txtfld_password.setVisible(false);
            btn_resetPassword.setVisible(false);
            lbl_resetPassword.setText("RESET PASSWORD");
        }else{
            lbl_password.setText("OLD PASSWORD :");
            txtfld_password.setText("");
            btn_resetPassword.setText("SUBMIT");
            lbl_password.setVisible(true);
            txtfld_password.setVisible(true);
            btn_resetPassword.setVisible(true);
            lbl_resetPassword.setText("CLOSE");
        }
    }

    @FXML
    public void btnResetPasswordValidate() throws Exception {
        AppData.setPreviousScene("profile");
        if(btn_resetPassword.getText().equals("SUBMIT")) {
            if(Validator.validatePassword(txtfld_password.getText())) {
                if (AppData.getPa().authenticate(txtfld_password.getText(), AppData.getAdmin().getPassword())) {
                    lbl_password.setText("NEW PASSWORD :");
                    txtfld_password.setText("");
                    btn_resetPassword.setText("RESET PASSWORD");
                }else{
                    Utils.switchToErrorScreen("Wrong password.");
                }
            }else{
                Utils.switchToErrorScreen("Invalid password.");
            }
        }else{
            if(Validator.validatePassword(txtfld_password.getText())) {
                AppData.getAdmin().setPassword(txtfld_password.getText());
                try (AdminDAO adao = new AdminDAO()) {
                    adao.save(AppData.getAdmin());
                }
                lbl_password.setVisible(false);
                txtfld_password.setVisible(false);
                btn_resetPassword.setVisible(false);
            }else{
                Utils.switchToErrorScreen("Invalid password.");
            }
        }
    }
}