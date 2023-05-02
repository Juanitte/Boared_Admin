package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.AdminDAO;
import com.juanite.model.domain.Admin;
import com.juanite.util.AppData;
import com.juanite.util.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignupController {

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
    public Button btn_signup;
    @FXML
    public Label lbl_slogan1;
    @FXML
    public Label lbl_slogan2;
    @FXML
    public Label lbl_slogan3;
    @FXML
    public Label lbl_name;
    @FXML
    public TextField txtfld_name;

    @FXML
    public void initialize(){
        img_resize.setOnMousePressed(this::resizeWindow);
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
        });
    }

    @FXML
    public void btnBackValidate() throws IOException {
        App.setRoot("login");
    }

    @FXML
    public void btnSignupValidate() throws Exception {
        try (AdminDAO adao = new AdminDAO()) {
            if(Validator.validateCompanyEmail(txtfld_email.getText())) {
                if(Validator.validateUsername(txtfld_name.getText())) {
                    if (Validator.validatePassword(txtfld_password.getText())) {
                        if(adao.find(txtfld_name.getText()) == null) {
                            if (adao.find(txtfld_email.getText()) == null) {
                                adao.save(new Admin(txtfld_email.getText(), txtfld_name.getText(), txtfld_password.getText()));
                                AppData.setPreviousScene("signup");
                                AppData.getStage().setWidth(350);
                                AppData.getStage().setHeight(400);
                                App.setRoot("login");
                            } else {
                                AppData.setErrorMsg("This email is already in use.");
                                switchToErrorScreen();
                            }
                        }else{
                            AppData.setErrorMsg("This name is already in use.");
                            switchToErrorScreen();
                        }
                    } else {
                        AppData.setErrorMsg("Password must include a lowercase letter,\nan uppercase letter and a number,\nand have 8 characters at least.");
                        switchToErrorScreen();
                    }
                }else{
                    AppData.setErrorMsg("Name must be between 3 and 25 characters long,\nincluding only letters.");
                    switchToErrorScreen();
                }
            }else{
                AppData.setErrorMsg("Email must be from our company (@boared.com).");
                switchToErrorScreen();
            }
        }
    }

    public void switchToErrorScreen() throws IOException {
        AppData.getStage().setWidth(350);
        AppData.getStage().setHeight(180);
        App.setRoot("error");
    }
}