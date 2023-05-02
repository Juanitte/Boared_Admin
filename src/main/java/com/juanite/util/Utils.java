package com.juanite.util;

import com.juanite.model.domain.Tags;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
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
}
