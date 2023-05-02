package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.DeveloperDAO;
import com.juanite.model.DAO.GameDAO;
import com.juanite.model.DTO.GameDTO;
import com.juanite.model.domain.Game;
import com.juanite.model.domain.Tags;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import com.juanite.util.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AddGameController {

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
    public Label lbl_title;
    @FXML
    public Label lbl_description;
    @FXML
    public Button btn_cancel;
    @FXML
    public Label lbl_tags;
    @FXML
    public Label lbl_releaseDate;
    @FXML
    public Label lbl_price;
    @FXML
    public Label lbl_logo;
    @FXML
    public Label lbl_images;
    @FXML
    public Label lbl_developer;
    @FXML
    public TextField txtfld_title;
    @FXML
    public TextArea txtfld_description;
    @FXML
    public TextField txtfld_tags;
    @FXML
    public TextField txtfld_releaseDate;
    @FXML
    public TextField txtfld_price;
    @FXML
    public TextField txtfld_logo;
    @FXML
    public TextField txtfld_images;
    @FXML
    public TextField txtfld_developer;


    @FXML
    public void initialize() {
        img_resize.setOnMousePressed(this::resizeWindow);
        btn_profile.setText(AppData.getAdmin().getName());
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
        AppData.setPreviousScene("games");
        boolean maximize = AppData.getStage().isMaximized();
        App.setRoot("main");
        AppData.getStage().setTitle("BOARED - Main");
        if(maximize){
            AppData.getStage().setMaximized(true);
        }else {
            AppData.getStage().setWidth(AppData.getWidth());
            AppData.getStage().setHeight(AppData.getHeight());
        }
    }

    @FXML
    public void btnGamesValidate() throws IOException {
        AppData.setPreviousScene("games");
        boolean maximize = AppData.getStage().isMaximized();
        App.setRoot("games");
        AppData.getStage().setTitle("BOARED - Games");
        if(maximize){
            AppData.getStage().setMaximized(true);
        }else {
            AppData.getStage().setWidth(AppData.getWidth());
            AppData.getStage().setHeight(AppData.getHeight());
        }
    }

    @FXML
    public void btnUsersValidate() throws IOException {
        AppData.setPreviousScene("games");
        boolean maximize = AppData.getStage().isMaximized();
        App.setRoot("users");
        AppData.getStage().setTitle("BOARED - Users");
        if(maximize){
            AppData.getStage().setMaximized(true);
        }else {
            AppData.getStage().setWidth(AppData.getWidth());
            AppData.getStage().setHeight(AppData.getHeight());
        }
    }

    @FXML
    public void btnLogoutValidate() throws IOException {
        AppData.setPreviousScene("games");
        App.setRoot("login");
        AppData.getStage().setTitle("BOARED - Log in");
        AppData.getStage().setWidth(350);
        AppData.getStage().setHeight(400);
    }

    @FXML
    public void btnAddValidate() throws Exception {
        AppData.setPreviousScene("games");
        if(!txtfld_title.getText().equals("") && !txtfld_description.getText().equals("") &&
        !txtfld_tags.getText().equals("") && !txtfld_releaseDate.getText().equals("") &&
        !txtfld_price.getText().equals("") && !txtfld_images.getText().equals("") &&
        !txtfld_developer.getText().equals("")){
            if(validTags(txtfld_tags.getText())){
                if(Validator.validateDate(txtfld_releaseDate.getText())){
                    try (DeveloperDAO ddao = new DeveloperDAO()) {
                        if(ddao.find(txtfld_developer.getText()) != null){
                            Game game = new Game(txtfld_title.getText(), txtfld_description.getText(), Utils.convertTags(txtfld_tags.getText()), Utils.convertDate(txtfld_releaseDate.getText()), Utils.convertDouble(txtfld_price.getText()), txtfld_logo.getText(), ddao.find(txtfld_developer.getText()));
                            game.create();
                            btnGamesValidate();
                        }
                    }
                }else{
                    AppData.setErrorMsg("Invalid Date.");
                    switchToErrorScreen();
                }
            }else{
                AppData.setErrorMsg("Invalid Tag/s.");
                switchToErrorScreen();
            }
        }else{
            AppData.setErrorMsg("All fields required.");
            switchToErrorScreen();
        }
    }

    @FXML
    public void btnCancelValidate() throws IOException {
        btnGamesValidate();
    }

    public void switchToErrorScreen() throws IOException {
        AppData.getStage().setWidth(350);
        AppData.getStage().setHeight(180);
        App.setRoot("error");
    }

    public boolean validTags(String tags){
        boolean isValid = false;
        Set<String> strings = Arrays.stream(tags.split(",")).collect(Collectors.toSet());
        for(String tag : strings){
            boolean isDone = false;
            for(Tags t : Tags.values()){
                if(!isDone) {
                    if (tag.equals(t.name())) {
                        isValid = true;
                        isDone = true;
                    }
                }
            }
            if(!isDone){
                return false;
            }
        }
        return isValid;
    }
}