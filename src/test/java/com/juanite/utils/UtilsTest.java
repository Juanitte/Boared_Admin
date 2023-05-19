package com.juanite.utils;

import com.juanite.model.domain.Tags;
import com.juanite.util.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    @Test
    @DisplayName("Convert Date")
    void testConvertDate() throws ParseException {
        assertEquals("2023-05-18", Utils.convertDate("2023-05-18").toString());
        assertEquals("1994-11-22", Utils.convertDate("1994-11-22").toString());
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
        assertEquals("INDIE,ADVENTURE,ROGUELIKE,", Utils.convertTags(tags));
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
        assertEquals("img1,img2,img3,", Utils.convertImages(images));
    }
}