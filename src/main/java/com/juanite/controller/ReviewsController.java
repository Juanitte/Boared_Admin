package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.GameDAO;
import com.juanite.model.DAO.ReviewDAO;
import com.juanite.model.DAO.UserDAO;
import com.juanite.model.DTO.ReviewDTO;
import com.juanite.model.DTO.UserDTO;
import com.juanite.model.domain.Review;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ReviewsController {

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
    public Button btn_remove;
    @FXML
    public TableView<ReviewDTO> tv_reviews;
    @FXML
    public TableColumn<ReviewDTO, String> tc_username;
    @FXML
    public TableColumn<ReviewDTO, String> tc_game;
    @FXML
    public Button btn_devs;
    @FXML
    public TableColumn<ReviewDTO, Double> tc_score;
    @FXML
    public Button btn_show;
    @FXML
    public Button btn_back;
    @FXML
    public Label lbl_reviews;


    @FXML
    public void initialize() throws Exception {
        img_resize.setOnMousePressed(this::resizeWindow);
        btn_profile.setText(AppData.getAdmin().getName());
        tc_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        tc_game.setCellValueFactory(new PropertyValueFactory<>("game"));
        tc_score.setCellValueFactory(new PropertyValueFactory<>("score"));
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
        AppData.setPreviousScene("reviews");
        AppData.getStage().setTitle("BOARED - Main");
        Utils.switchToScreen("main");
    }

    @FXML
    public void btnGamesValidate() throws IOException {
        AppData.setPreviousScene("reviews");
        AppData.getStage().setTitle("BOARED - Games");
        Utils.switchToScreen("games");
    }

    @FXML
    public void btnUsersValidate() throws IOException {
        AppData.setPreviousScene("reviews");
        AppData.getStage().setTitle("BOARED - Users");
        Utils.switchToScreen("users");
    }

    @FXML
    public void btnLogoutValidate() throws IOException {
        AppData.setPreviousScene("reviews");
        App.setRoot("login");
        AppData.getStage().setTitle("BOARED - Log in");
        AppData.getStage().setWidth(350);
        AppData.getStage().setHeight(400);
    }

    @FXML
    public void btnSearchValidate() throws Exception {
        if(!txtfld_search.getText().equals("")) {
            AppData.setPreviousScene("reviews");
            try (ReviewDAO rdao = new ReviewDAO()) {
                AppData.setReviews(rdao.findContainingNames(txtfld_search.getText()));
                tv_reviews.setItems(AppData.getReviews());
            }
        }
    }

    public void refresh() throws Exception {
        try (ReviewDAO rdao = new ReviewDAO()) {
            if (AppData.getReviews().isEmpty()) {
                AppData.getReviews().addAll(rdao.findByUser(AppData.getUser()));
            } else {
                AppData.getReviews().clear();
                AppData.getReviews().addAll(rdao.findByUser(AppData.getUser()));
            }tv_reviews.setItems(AppData.getReviews());
        }
    }

    @FXML
    public void btnDevsValidate() throws IOException {
        AppData.setPreviousScene("reviews");
        AppData.getStage().setTitle("BOARED - Devs");
        Utils.switchToScreen("devs");
    }

    @FXML
    public void btnShowValidate() {

    }

    @FXML
    public void btnRemoveValidate() throws Exception {
        if(tv_reviews.getSelectionModel().getSelectedItem() != null) {
            try (UserDAO udao = new UserDAO()) {
                try (GameDAO gdao = new GameDAO()) {
                    try (ReviewDAO rdao = new ReviewDAO()) {
                        AppData.setReview((rdao.find(udao.getId(udao.find(tv_reviews.getSelectionModel().getSelectedItem().getUsername())), gdao.getCode(gdao.find(tv_reviews.getSelectionModel().getSelectedItem().getGame())))));
                        if (AppData.getReview() != null) {
                            AppData.setPreviousScene("reviews");
                            AppData.setConfirmationType("delete");
                            AppData.getStage().setWidth(350);
                            AppData.getStage().setHeight(180);
                            App.setRoot("confirmation");
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void btnBackValidate() throws IOException {
        AppData.setPreviousScene("reviews");
        Utils.switchToScreen("users");
    }

    @FXML
    public void btnProfileValidate() throws IOException {
        AppData.setPreviousScene("reviews");
        AppData.getStage().setTitle("BOARED - " + AppData.getAdmin().getName());
        Utils.switchToScreen("profile");
    }
}