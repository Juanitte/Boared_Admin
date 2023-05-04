package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.DeveloperDAO;
import com.juanite.model.DAO.GameDAO;
import com.juanite.model.DTO.DeveloperDTO;
import com.juanite.model.DTO.GameDTO;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class DevsController {

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
    public TableView<DeveloperDTO> tv_devs;
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
    public TableColumn<DeveloperDTO, String> tc_name;
    @FXML
    public Button btn_devs;


    @FXML
    public void initialize() throws Exception {
        img_resize.setOnMousePressed(this::resizeWindow);
        btn_profile.setText(AppData.getAdmin().getName());
        tc_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        try (DeveloperDAO ddao = new DeveloperDAO()) {
            if(AppData.getDevelopers().isEmpty()) {
                AppData.getDevelopers().addAll(ddao.findAllDTO());
            }else{
                AppData.getDevelopers().clear();
                AppData.getDevelopers().addAll(ddao.findAllDTO());
            }
            refresh();
        }
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
    public void btnAddValidate() throws IOException {
        AppData.setPreviousScene("devs");
        Utils.switchToScreen("adddev");

    }

    @FXML
    public void btnSearchValidate() throws Exception {
        if(!txtfld_search.getText().equals("")) {
            AppData.setPreviousScene("devs");
            try (DeveloperDAO ddao = new DeveloperDAO()) {
                AppData.setDevelopers(ddao.findContainingNames(txtfld_search.getText()));
                tv_devs.setItems(AppData.getDevelopers());
            }
        }
    }

    @FXML
    public void btnEditValidate() throws Exception {
        if(tv_devs.getSelectionModel().getSelectedItem() != null) {
            AppData.setPreviousScene("devs");
            try (DeveloperDAO ddao = new DeveloperDAO()) {
                AppData.setDeveloper(ddao.find(tv_devs.getSelectionModel().getSelectedItem().getName()));
                Utils.switchToScreen("editdev");
            }
        }
    }

    public void refresh(){
        tv_devs.setItems(AppData.getDevelopers());
    }

    @FXML
    public void btnDevsValidate() throws IOException {
        AppData.setPreviousScene("devs");
        AppData.getStage().setTitle("BOARED - Devs");
        Utils.switchToScreen("devs");
    }

    @FXML
    public void btnRemoveValidate() throws Exception {
        try (DeveloperDAO ddao = new DeveloperDAO()) {
            AppData.setDeveloper(ddao.find(tv_devs.getSelectionModel().getSelectedItem().getName()));
            if(AppData.getDeveloper() != null) {
                if (!ddao.hasGames(AppData.getDeveloper())) {
                    AppData.setPreviousScene("devs");
                    AppData.setConfirmationType("delete");
                    AppData.getStage().setWidth(350);
                    AppData.getStage().setHeight(180);
                    App.setRoot("confirmation");
                } else {
                    Utils.switchToErrorScreen("Developer must have no games\nbefore removing.");
                }
            }
        }
    }

    @FXML
    public void btnProfileValidate() throws IOException {
        AppData.setPreviousScene("devs");
        AppData.getStage().setTitle("BOARED - " + AppData.getAdmin().getName());
        Utils.switchToScreen("profile");
    }
}