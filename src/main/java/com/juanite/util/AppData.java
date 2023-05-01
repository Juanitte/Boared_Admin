package com.juanite.util;

import com.juanite.model.domain.Admin;

public class AppData {

    private static Admin admin;
    private static double width = 600;
    private static double height = 800;
    private static String errorMsg;
    private static String previousScene;

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        AppData.admin = admin;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        AppData.height = height;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        AppData.width = width;
    }

    public static String getErrorMsg() {
        return errorMsg;
    }

    public static void setErrorMsg(String errorMsg) {
        AppData.errorMsg = errorMsg;
    }

    public static String getPreviousScene() {
        return previousScene;
    }

    public static void setPreviousScene(String previousScene) {
        AppData.previousScene = previousScene;
    }
}
