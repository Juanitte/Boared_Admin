package com.juanite.controller;

import com.juanite.App;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ErrorController {

    private static double xOffset = 0;
    private static double yOffset = 0;

    @FXML
    public Label lbl_titlebar;
    @FXML
    public ToolBar tb_1;
    @FXML
    public ToolBar tb_2;
    @FXML
    public ImageView img_icon;
    @FXML
    public ImageView img_resize;
    @FXML
    public Label lbl_errorMsg;
    @FXML
    public Button btn_ok;

    @FXML
    public void initialize(){
        lbl_errorMsg.setText(AppData.getErrorMsg());
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
    public void btnOkValidate() throws IOException {
        if(AppData.getPreviousScene().equals("login")){
            AppData.getStage().setWidth(350);
            AppData.getStage().setHeight(400);
            App.setRoot(AppData.getPreviousScene());
        }else {
            Utils.switchToPreviousScreen();
        }
    }
}
