package com.juanite.test;

import com.juanite.model.domain.Tags;
import com.juanite.util.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestUtils {

    @Test
    @DisplayName("Convert Date")
    void testConvertDate() throws ParseException {
        assertEquals(new Date(20230518), Utils.convertDate("2023-05-18"));
        assertEquals(new Date(19941122), Utils.convertDate("2023-05-18"));
    }

    @Test
    @DisplayName("Convert Double")
    void testConvertDouble() {
        //From String
        assertEquals(19.99, Utils.convertDouble("19.99"));

        //To String
        assertEquals("19.99", Utils.convertDouble(19.99));
    }

    @Test
    @DisplayName("Convert Tags")
    void testConvertTags() {
        Set<Tags> tags = new HashSet<Tags>();
        tags.add(Tags.ADVENTURE);
        tags.add(Tags.INDIE);
        tags.add(Tags.ROGUELIKE);

        //From String
        assertEquals(tags, Utils.convertTags("ADVENTURE,INDIE,ROGUELIKE"));

        //To String
        assertEquals("ADVENTURE,INDIE,ROGUELIKE", Utils.convertTags(tags));
    }

    @Test
    @DisplayName("Convert Images")
    void testConvertImages() {
        List<String> images = new ArrayList<String>();
        images.add("img1");
        images.add("img2");
        images.add("img3");

        //From String
        assertEquals(images, Utils.convertImages("img1,img2,img3"));

        //To String
        assertEquals("img1,img2,img3", Utils.convertImages(images));
    }

}
