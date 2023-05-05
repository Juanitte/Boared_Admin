package com.juanite.util;

import com.juanite.App;
import com.juanite.model.DAO.GameDAO;
import com.juanite.model.DAO.UserDAO;
import com.juanite.model.DTO.ReviewDTO;
import com.juanite.model.domain.Review;
import com.juanite.model.domain.Tags;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    /**
     * Method that converts a String to a sql.Date.
     * @param date , the String to convert.
     * @return the sql.Date resulting from the conversion.
     */
    public static Date convertDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return new java.sql.Date(format.parse(date).getTime());
    }

    /**
     * Method that converts a sql.Date to a String.
     * @param date , the sql.Date to convert.
     * @return the String resulting from the conversion.
     */
    public static String convertDate(Date date) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(date);
    }

    /**
     * Method that converts a String to a double.
     * @param number , the String to convert.
     * @return the double resulting from the conversion.
     */
    public static double convertDouble(String number) {
         return Double.parseDouble(number);
    }

    /**
     * Method that converts a double to a String.
     * @param number , the double to convert.
     * @return the String resulting from the conversion.
     */
    public static String convertDouble(double number) {
        return Double.toString(number);
    }

    /**
     * Method that makes the conversion from a String to a Set, separating it by the commas.
     * @param string , the String to work with.
     * @return a Set of Tags built using the string provided.
     */
    public static Set<Tags> convertTags(String string) throws SQLException {
        Set<Tags> tags = new HashSet<Tags>();
        Set<String> strings = Arrays.stream(string.split(",")).collect(Collectors.toSet());
        for(String tag : strings){
            tags.add(Tags.valueOf(tag));
        }
        return tags;
    }

    /**
     * Method that makes the conversion from a Set to a String, separating it by commas.
     * @param tags , the Set of Tags to work with.
     * @return a String built using the Set of Tags provided.
     */
    public static String convertTags(Set<Tags> tags) {
        StringBuilder result = new StringBuilder();
        for(Tags tag : tags){
            result.append(tag.name()).append(",");
        }
        return result.toString();
    }

    /**
     * Method that makes the conversion from a String to a Set, separating it by the commas.
     * @param string , the String to work with.
     * @return a List of String built using the string provided.
     */
    public static List<String> convertImages(String string) throws SQLException {
        List<String> strings = new ArrayList<String>();
        strings = Arrays.stream(string.split(",")).collect(Collectors.toList());
        return strings;
    }

    /**
     * Method that makes the conversion from a List to a String, separating it by commas.
     * @param images , the Set of String to work with.
     * @return a String built using the List of String provided.
     */
    public static String convertImages(List<String> images) {
        StringBuilder result = new StringBuilder();
        for(String image : images){
            result.append(image).append(",");
        }
        return result.toString();
    }

    /**
     * Method that switch to an error screen that shows the given message.
     * @param errorMsg , message to show on error screen
     */
    public static void switchToErrorScreen(String errorMsg) throws IOException {
        AppData.setErrorMsg(errorMsg);
        AppData.getStage().setWidth(350);
        AppData.getStage().setHeight(180);
        App.setRoot("error");
    }

    /**
     * Method that switch to the last visited screen.
     */
    public static void switchToPreviousScreen() throws IOException {
        boolean maximize = AppData.getStage().isMaximized();
        App.setRoot(AppData.getPreviousScene());
        if (maximize) {
            AppData.getStage().setMaximized(true);
        } else {
            AppData.getStage().setWidth(AppData.getWidth());
            AppData.getStage().setHeight(AppData.getHeight());
        }
    }

    /**
     * Method that switch to the given screen.
     * @param screen , the name of the target screen.
     */
    public static void switchToScreen(String screen) throws IOException {
        boolean maximize = AppData.getStage().isMaximized();
        App.setRoot(screen);
        if (maximize) {
            AppData.getStage().setMaximized(true);
        } else {
            AppData.getStage().setWidth(AppData.getWidth());
            AppData.getStage().setHeight(AppData.getHeight());
        }
    }

    /**
     * Method that insert a "\n" every "x" characters into a given String.
     * @param str , the String to modify.
     * @param x , number of characters until "\n" insert.
     * @return the modified String.
     */
    public static String insertNewlines(String str, int x) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i += x) {
            String part = str.substring(i, Math.min(i + x, str.length()));
            sb.append(part);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Method that removes the "\n" contained by a given String.
     * @param str , the String to modify.
     * @return the modified String.
     */
    public static String removeNewlines(String str) {
        return str.replaceAll("\\n", "");
    }

}
