package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.GameDAO;
import com.juanite.model.DAO.ReviewDAO;
import com.juanite.model.DAO.UserDAO;
import com.juanite.model.DTO.GameDTO;
import com.juanite.model.DTO.UserDTO;
import com.juanite.model.domain.User;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class UsersController {

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
    public TextField txtfld_search;
    @FXML
    public Button btn_search;
    @FXML
    public Button btn_logout;
    @FXML
    public Button btn_add;
    @FXML
    public Button btn_edit;
    @FXML
    public Button btn_remove;
    @FXML
    public TableView<UserDTO> tv_users;
    @FXML
    public TableColumn<UserDTO, String> tc_username;
    @FXML
    public TableColumn<UserDTO, String> tc_country;
    @FXML
    public Button btn_devs;
    @FXML
    public TableColumn<UserDTO, Boolean> tc_banned;
    @FXML
    public Button btn_ban;
    @FXML
    public Button btn_reviews;


    @FXML
    public void initialize() throws Exception {
        img_resize.setOnMousePressed(this::resizeWindow);
        btn_profile.setText(AppData.getAdmin().getName());
        tc_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        tc_country.setCellValueFactory(new PropertyValueFactory<>("country"));
        tc_banned.setCellValueFactory(new PropertyValueFactory<>("banned"));
        refresh();
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
    public void btnAddValidate() throws IOException {
        AppData.setPreviousScene("users");
        Utils.switchToScreen("adduser");
    }

    @FXML
    public void btnSearchValidate() throws Exception {
        if(!txtfld_search.getText().equals("")) {
            AppData.setPreviousScene("users");
            try (UserDAO udao = new UserDAO()) {
                AppData.setUsers(udao.findContainingNames(txtfld_search.getText()));
                tv_users.setItems(AppData.getUsers());
            }
        }
    }

    @FXML
    public void btnEditValidate() throws Exception {
        if(tv_users.getSelectionModel().getSelectedItem() != null) {
            AppData.setPreviousScene("users");
            try (UserDAO udao = new UserDAO()) {
                AppData.setUser(udao.find(tv_users.getSelectionModel().getSelectedItem().getUsername()));
                if(AppData.getUser() != null) {
                    Utils.switchToScreen("edituser");
                }
            }
        }
    }

    public void refresh() throws Exception {
        try (UserDAO udao = new UserDAO()) {
            if (AppData.getUsers().isEmpty()) {
                AppData.getUsers().addAll(udao.findAllDTO());
            } else {
                AppData.getUsers().clear();
                AppData.getUsers().addAll(udao.findAllDTO());
            }
            tv_users.setItems(AppData.getUsers());
        }
    }

    @FXML
    public void btnDevsValidate() throws IOException {
        AppData.setPreviousScene("users");
        AppData.getStage().setTitle("BOARED - Devs");
        Utils.switchToScreen("devs");
    }

    @FXML
    public void btnRemoveValidate() throws Exception {
        try (UserDAO udao = new UserDAO()) {
            AppData.setUser(udao.find(tv_users.getSelectionModel().getSelectedItem().getUsername()));
            if (AppData.getUser() != null) {
                AppData.setPreviousScene("users");
                AppData.setConfirmationType("delete");
                AppData.getStage().setWidth(350);
                AppData.getStage().setHeight(180);
                App.setRoot("confirmation");
            }
        }
    }

    @FXML
    public void btnBanValidate() throws Exception {
        try (UserDAO udao = new UserDAO()) {
            AppData.setUser(udao.find(tv_users.getSelectionModel().getSelectedItem().getUsername()));
            if(AppData.getUser() != null) {
                udao.updateBanStatus(AppData.getUser());
                AppData.setUser(null);
                refresh();
            }
        }
    }

    @FXML
    public void btnReviewsValidate() throws Exception {
        AppData.setPreviousScene("users");
        Utils.switchToScreen("reviews");
    }
}