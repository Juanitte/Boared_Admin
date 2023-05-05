package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.DeveloperDAO;
import com.juanite.model.DAO.UserDAO;
import com.juanite.model.DTO.DeveloperDTO;
import com.juanite.model.DTO.UserDTO;
import com.juanite.model.domain.Countries;
import com.juanite.model.domain.Developer;
import com.juanite.model.domain.User;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import com.juanite.util.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.text.ParseException;

public class EditUserController {

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
    public Button btn_submit;
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
        txtfld_username.setText(AppData.getUser().getUsername());
        txtfld_email.setText(AppData.getUser().getEmail());
        txtfld_name.setText(AppData.getUser().getName());
        txtfld_surname.setText(AppData.getUser().getSurname());
        dp_birthDate.setValue(AppData.getUser().getBirthDate().toLocalDate());
        cb_country.setValue(AppData.getUser().getCountry());
        txtfld_town.setText(AppData.getUser().getTown());
        txtfld_address.setText(AppData.getUser().getAddress());
        txtfld_phoneNumber.setText(AppData.getUser().getPhoneNumber());
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
    public void btnSubmitValidate() throws Exception {
        AppData.setPreviousScene("users");
                if(Validator.validateDate(dp_birthDate.getValue().toString())){
                    if(Validator.validateUsername(txtfld_username.getText())) {
                        if(Validator.validateEmail(txtfld_email.getText())) {
                            if(Validator.validateName(txtfld_name.getText())) {
                                if(Validator.validateName(txtfld_surname.getText())) {
                                    if(Validator.validatePhoneNumber(txtfld_phoneNumber.getText())) {
                                        editUser();
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
                        Utils.switchToErrorScreen("Invalid Username.");
                    }
                }else{
                    Utils.switchToErrorScreen("Invalid Date.");
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
        Utils.switchToScreen("users");
    }

    @FXML
    public void btnProfileValidate() throws IOException {
        AppData.setPreviousScene("users");
        AppData.getStage().setTitle("BOARED - " + AppData.getAdmin().getName());
        Utils.switchToScreen("profile");
    }

    public void editUser() throws Exception {
        try (UserDAO udao = new UserDAO()) {
            User user = new User();
            if (txtfld_username.getText().equals("") || udao.usernameAlreadyExists(txtfld_username.getText())) {
                user.setUsername(AppData.getUser().getUsername());
            } else {
                user.setUsername(txtfld_username.getText());
            }
            user.setPassword(AppData.getUser().getPassword());
            if (txtfld_name.getText().equals("")) {
                user.setName(AppData.getUser().getName());
            } else {
                user.setName(txtfld_name.getText());
            }
            if (txtfld_surname.getText().equals("")) {
                user.setSurname(AppData.getUser().getSurname());
            } else {
                user.setSurname(txtfld_surname.getText());
            }
            if (txtfld_email.getText().equals("") || udao.emailAlreadyExists(txtfld_email.getText())) {
                user.setEmail(AppData.getUser().getEmail());
            } else {
                user.setEmail(txtfld_email.getText());
            }
            if (dp_birthDate.getValue() == null) {
                user.setBirthDate(AppData.getUser().getBirthDate());
            } else {
                user.setBirthDate(Utils.convertDate(dp_birthDate.getValue().toString()));
            }
            if (cb_country.getValue() == null) {
                user.setCountry(AppData.getUser().getCountry());
            } else {
                user.setCountry(cb_country.getValue());
            }
            if (txtfld_town.getText().equals("")) {
                user.setTown(AppData.getUser().getTown());
            } else {
                user.setTown(txtfld_town.getText());
            }
            if (txtfld_address.getText().equals("")) {
                user.setAddress(AppData.getUser().getAddress());
            } else {
                user.setAddress(txtfld_address.getText());
            }
            if (txtfld_phoneNumber.getText().equals("")) {
                user.setPhoneNumber(AppData.getUser().getPhoneNumber());
            } else {
                user.setPhoneNumber(txtfld_phoneNumber.getText());
            }
            if (txtfld_username.getText().length() <= 50 && txtfld_email.getText().length() <= 50 &&
                    txtfld_name.getText().length() <= 50 && txtfld_surname.getText().length() <= 50 &&
                    txtfld_town.getText().length() <= 100 && txtfld_address.getText().length() <= 100 &&
                    txtfld_phoneNumber.getText().length() <= 50) {
                AppData.getUser().update(user);
                ObservableList<UserDTO> users = FXCollections.observableArrayList();
                users.addAll(udao.findAllDTO());
                AppData.setUsers(users);
                btnUsersValidate();
            } else {
                Utils.switchToErrorScreen("Too much text.");
            }
        }
    }
}