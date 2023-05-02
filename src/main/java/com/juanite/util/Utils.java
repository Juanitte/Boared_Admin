package com.juanite.util;

import com.juanite.model.domain.Tags;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    public static Date convertDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return new java.sql.Date(format.parse(date).getTime());
    }

    public static String convertDate(Date date) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(date);
    }

    public static double convertDouble(String number) {
         return Double.parseDouble(number);
    }

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
}
