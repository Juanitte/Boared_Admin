package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.DeveloperDAO;
import com.juanite.model.DAO.GameDAO;
import com.juanite.model.DTO.DeveloperDTO;
import com.juanite.model.DTO.GameDTO;
import com.juanite.model.domain.Game;
import com.juanite.model.domain.Tags;
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
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EditGameController {

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
    public TextField txtfld_price;
    @FXML
    public TextField txtfld_logo;
    @FXML
    public TextField txtfld_images;
    @FXML
    public Button btn_devs;
    @FXML
    public DatePicker dp_releaseDate;
    @FXML
    public ComboBox<DeveloperDTO> cb_developer;


    @FXML
    public void initialize() throws Exception {
        img_resize.setOnMousePressed(this::resizeWindow);
        btn_profile.setText(AppData.getAdmin().getName());
        txtfld_title.setText(AppData.getGame().getTitle());
        txtfld_description.setText(AppData.getGame().getDescription());
        txtfld_tags.setText(Utils.convertTags(AppData.getGame().getTags()));
        dp_releaseDate.setValue(AppData.getGame().getReleaseDate().toLocalDate());
        txtfld_price.setText(Utils.convertDouble(AppData.getGame().getPrice()));
        txtfld_logo.setText(AppData.getGame().getLogo());
        txtfld_images.setText(Utils.convertImages(AppData.getGame().getImages()));
        try (DeveloperDAO ddao = new DeveloperDAO()) {
            if(AppData.getDevelopers().isEmpty()) {
                AppData.getDevelopers().addAll(ddao.findAllDTO());
            }else{
                AppData.getDevelopers().clear();
                AppData.getDevelopers().addAll(ddao.findAllDTO());
            }
            cb_developer.setItems(AppData.getDevelopers());
            cb_developer.setValue(ddao.findDTO(AppData.getGame().getDeveloper().getName()));
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
        AppData.setPreviousScene("games");
        AppData.getStage().setTitle("BOARED - Main");
        Utils.switchToScreen("main");
    }

    @FXML
    public void btnGamesValidate() throws IOException {
        AppData.setPreviousScene("games");
        AppData.getStage().setTitle("BOARED - Games");
        Utils.switchToScreen("games");
    }

    @FXML
    public void btnUsersValidate() throws IOException {
        AppData.setPreviousScene("games");
        AppData.getStage().setTitle("BOARED - Users");
        Utils.switchToScreen("users");
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
    public void btnEditValidate() throws Exception {
        AppData.setPreviousScene("games");
            if(validTags(txtfld_tags.getText())){
                if(Validator.validateDate(dp_releaseDate.getValue().toString())){
                    try (DeveloperDAO ddao = new DeveloperDAO()) {
                        if(ddao.find(cb_developer.getValue().getName()) != null) {
                            Game game = new Game(txtfld_title.getText(), txtfld_description.getText(), Utils.convertTags(txtfld_tags.getText()), Utils.convertDate(dp_releaseDate.getValue().toString()), Utils.convertDouble(txtfld_price.getText()), txtfld_logo.getText(), Utils.convertImages(txtfld_images.getText()), ddao.find(cb_developer.getValue().getName()));
                            if (txtfld_title.getText().equals("")) {
                                game.setTitle(AppData.getGame().getTitle());
                            }
                            if (txtfld_description.getText().equals("")) {
                                game.setDescription(AppData.getGame().getDescription());
                            }
                            if (txtfld_tags.getText().equals("")) {
                                game.setTags(AppData.getGame().getTags());
                            }
                            if (dp_releaseDate.getValue() == null) {
                                game.setReleaseDate(AppData.getGame().getReleaseDate());
                            }
                            if (!Validator.validatePrice(txtfld_price.getText())) {
                                game.setPrice(AppData.getGame().getPrice());
                            }
                            if (txtfld_logo.getText().equals("")) {
                                game.setLogo(AppData.getGame().getLogo());
                            }
                            if (txtfld_images.getText().equals("")) {
                                game.setImages(AppData.getGame().getImages());
                            }
                            if (cb_developer.getValue() == null) {
                                game.setDeveloper(AppData.getGame().getDeveloper());
                            }
                            if (txtfld_title.getText().length() <= 100 && txtfld_description.getText().length() <= 500 && txtfld_tags.getText().length() <= 250 && txtfld_logo.getText().length() <= 50 && txtfld_images.getText().length() <= 250) {
                                AppData.getGame().update(game);
                                ObservableList<GameDTO> games = FXCollections.observableArrayList();
                                try (GameDAO gdao = new GameDAO()) {
                                    games.addAll(gdao.findAllDTO());
                                    AppData.setGames(games);
                                    btnGamesValidate();
                                }
                            } else {
                                Utils.switchToErrorScreen("Too much text.");
                            }
                        }else{
                            Utils.switchToErrorScreen("Invalid developer.");
                        }
                    }
                }else{
                    Utils.switchToErrorScreen("Invalid Date.");
                }
            }else{
                Utils.switchToErrorScreen("Invalid Tag/s.");
            }

    }

    @FXML
    public void btnCancelValidate() throws IOException {
        btnGamesValidate();
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

    @FXML
    public void btnDevsValidate() throws IOException {
        AppData.setPreviousScene("games");
        AppData.getStage().setTitle("BOARED - Devs");
        Utils.switchToScreen("devs");
    }

    @FXML
    public void btnProfileValidate() throws IOException {
        AppData.setPreviousScene("games");
        AppData.getStage().setTitle("BOARED - " + AppData.getAdmin().getName());
        Utils.switchToScreen("profile");
    }
}