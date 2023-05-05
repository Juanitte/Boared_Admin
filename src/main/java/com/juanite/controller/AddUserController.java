package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.DeveloperDAO;
import com.juanite.model.DAO.UserDAO;
import com.juanite.model.domain.Countries;
import com.juanite.model.domain.Developer;
import com.juanite.model.domain.User;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import com.juanite.util.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AddUserController {

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
    public Button btn_add;
    @FXML
    public Button btn_cancel;
    @FXML
    public Button btn_devs;
    @FXML
    public Label lbl_name;
    @FXML
    public TextField txtfld_name;
    @FXML
    public Label lbl_birthDate;
    @FXML
    public Label lbl_country;
    @FXML
    public ChoiceBox<Countries> cb_country;
    @FXML
    public Label lbl_username;
    @FXML
    public TextField txtfld_username;
    @FXML
    public Label lbl_password;
    @FXML
    public PasswordField txtfld_password;
    @FXML
    public Label lbl_surname;
    @FXML
    public TextField txtfld_surname;
    @FXML
    public Label lbl_email;
    @FXML
    public TextField txtfld_email;
    @FXML
    public Label lbl_town;
    @FXML
    public TextField txtfld_town;
    @FXML
    public Label lbl_address;
    @FXML
    public TextField txtfld_address;
    @FXML
    public Label lbl_phoneNumber;
    @FXML
    public TextField txtfld_phoneNumber;
    @FXML
    public DatePicker dp_birthDate;


    @FXML
    public void initialize() {
        img_resize.setOnMousePressed(this::resizeWindow);
        btn_profile.setText(AppData.getAdmin().getName());
        cb_country.getItems().addAll(Countries.values());
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
        AppData.setPreviousScene("users");
        AppData.getStage().setTitle("BOARED - Main");
        Utils.switchToScreen("main");
    }

    @FXML
    public void btnGamesValidate() throws IOException {
        AppData.setPreviousScene("users");
        AppData.getStage().setTitle("BOARED - Games");
        Utils.switchToScreen("games");
    }

    @FXML
    public void btnUsersValidate() throws IOException {
        AppData.setPreviousScene("users");
        AppData.getStage().setTitle("BOARED - Users");
        Utils.switchToScreen("users");
    }

    @FXML
    public void btnLogoutValidate() throws IOException {
        AppData.setPreviousScene("users");
        App.setRoot("login");
        AppData.getStage().setTitle("BOARED - Log in");
        AppData.getStage().setWidth(350);
        AppData.getStage().setHeight(400);
    }

    @FXML
    public void btnAddValidate() throws Exception {
        AppData.setPreviousScene("users");
        if(!txtfld_username.getText().equals("") && !txtfld_password.getText().equals("")
        && !txtfld_email.getText().equals("") && !txtfld_name.getText().equals("")
        && !txtfld_surname.getText().equals("") && dp_birthDate.getValue() != null
        && cb_country.getValue() != null && !txtfld_town.getText().equals("")
        && !txtfld_address.getText().equals("") && !txtfld_phoneNumber.getText().equals("")) {
                if(Validator.validateDate(dp_birthDate.getValue().toString())){
                    if(Validator.validatePassword(txtfld_password.getText())) {
                        if(Validator.validateEmail(txtfld_email.getText())) {
                            if(Validator.validateName(txtfld_name.getText())) {
                                if(Validator.validateName(txtfld_surname.getText())) {
                                    if(Validator.validatePhoneNumber(txtfld_phoneNumber.getText())) {
                                        try (UserDAO udao = new UserDAO()) {
                                            if (!udao.emailAlreadyExists(txtfld_email.getText())) {
                                                if (!udao.usernameAlreadyExists(txtfld_username.getText())) {
                                                    if (txtfld_username.getText().length() <= 50 && txtfld_password.getText().length() <= 25
                                                            && txtfld_email.getText().length() <= 50 && txtfld_name.getText().length() <= 50
                                                            && txtfld_surname.getText().length() <= 50 && txtfld_town.getText().length() <= 100
                                                            && txtfld_address.getText().length() <= 100 && txtfld_phoneNumber.getText().length() <= 50) {
                                                        AppData.setUser(new User(txtfld_username.getText(), txtfld_password.getText(), txtfld_email.getText(),
                                                                txtfld_name.getText(), txtfld_surname.getText(), Utils.convertDate(dp_birthDate.getValue().toString()),
                                                                cb_country.getValue(), txtfld_town.getText(), txtfld_address.getText(), txtfld_phoneNumber.getText()));
                                                        AppData.getUser().create();
                                                        btnUsersValidate();
                                                    } else {
                                                        Utils.switchToErrorScreen("Too much text.");
                                                    }
                                                }else{
                                                    Utils.switchToErrorScreen("Username already in use.");
                                                }
                                            }else{
                                                Utils.switchToErrorScreen("Email already in use.");
                                            }
                                        }
                                    }else{
                                        Utils.switchToErrorScreen("Invalid phone number.");
                                    }
                                }else{
                                    Utils.switchToErrorScreen("Invalid surname.");
                                }
                            }else{
                                Utils.switchToErrorScreen("Invalid name.");
                            }
                        }else{
                            Utils.switchToErrorScreen("Invalid email.");
                        }
                    }else{
                        Utils.switchToErrorScreen("Password must include a lowercase letter,\nan uppercase letter and a number,\nand have 8 characters at least.");
                    }
                }else{
                    Utils.switchToErrorScreen("Invalid Date.");
                }
        }else{
            Utils.switchToErrorScreen("All fields required.");
        }
    }

    @FXML
    public void btnCancelValidate() throws IOException {
        btnUsersValidate();
    }

    @FXML
    public void btnDevsValidate() throws IOException {
        AppData.setPreviousScene("users");
        AppData.getStage().setTitle("BOARED - Devs");
        Utils.switchToScreen("devs");
    }

    @FXML
    public void btnProfileValidate() throws IOException {
        AppData.setPreviousScene("users");
        AppData.getStage().setTitle("BOARED - " + AppData.getAdmin().getName());
        Utils.switchToScreen("profile");
    }
}