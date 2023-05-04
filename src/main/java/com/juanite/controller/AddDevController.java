package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.DeveloperDAO;
import com.juanite.model.domain.Countries;
import com.juanite.model.domain.Developer;
import com.juanite.model.domain.Game;
import com.juanite.model.domain.Tags;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import com.juanite.util.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class AddDevController {

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
    public Label lbl_description;
    @FXML
    public Button btn_cancel;
    @FXML
    public Label lbl_logo;
    @FXML
    public TextArea txtfld_description;
    @FXML
    public TextField txtfld_logo;
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
        AppData.setPreviousScene("devs");
        AppData.getStage().setTitle("BOARED - Main");
        Utils.switchToScreen("main");
    }

    @FXML
    public void btnGamesValidate() throws IOException {
        AppData.setPreviousScene("devs");
        AppData.getStage().setTitle("BOARED - Games");
        Utils.switchToScreen("games");
    }

    @FXML
    public void btnUsersValidate() throws IOException {
        AppData.setPreviousScene("devs");
        AppData.getStage().setTitle("BOARED - Users");
        Utils.switchToScreen("users");
    }

    @FXML
    public void btnLogoutValidate() throws IOException {
        AppData.setPreviousScene("devs");
        App.setRoot("login");
        AppData.getStage().setTitle("BOARED - Log in");
        AppData.getStage().setWidth(350);
        AppData.getStage().setHeight(400);
    }

    @FXML
    public void btnAddValidate() throws Exception {
        AppData.setPreviousScene("devs");
        if(!txtfld_name.getText().equals("") && !txtfld_description.getText().equals("") &&
        dp_birthDate.getValue() != null && cb_country.getValue() != null && !txtfld_logo.getText().equals("")){
                if(Validator.validateDate(dp_birthDate.getValue().toString())){
                        try (DeveloperDAO ddao = new DeveloperDAO()) {
                            if(ddao.find(txtfld_name.getText()) == null) {
                                if(txtfld_name.getText().length() <= 50 && txtfld_description.getText().length() <= 500 && txtfld_logo.getText().length() <= 50) {
                                    AppData.setDeveloper(new Developer(txtfld_name.getText(), txtfld_description.getText(), cb_country.getValue(), Utils.convertDate(dp_birthDate.getValue().toString()), txtfld_logo.getText()));
                                    AppData.getDeveloper().create();
                                    btnDevsValidate();
                                }else{
                                    Utils.switchToErrorScreen("Too much text.");
                                }
                            }else{
                                Utils.switchToErrorScreen("Developer already exists.");
                            }
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
        btnDevsValidate();
    }

    @FXML
    public void btnDevsValidate() throws IOException {
        AppData.setPreviousScene("devs");
        AppData.getStage().setTitle("BOARED - Devs");
        Utils.switchToScreen("devs");
    }

    @FXML
    public void btnProfileValidate() throws IOException {
        AppData.setPreviousScene("devs");
        AppData.getStage().setTitle("BOARED - " + AppData.getAdmin().getName());
        Utils.switchToScreen("profile");
    }
}