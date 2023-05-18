package com.juanite.utils;

import com.juanite.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ValidatorTest {

    @Test
    @DisplayName("Validate Username")
    void testUsername(){
        Assertions.assertTrue(Validator.validateUsername("Juan"));
        assertFalse(Validator.validateUsername("a"));
        assertFalse(Validator.validateUsername("Juannananananananananmananannan"));
        assertTrue(Validator.validateUsername("Manolo23"));
        assertFalse(Validator.validateUsername("as-rt.;"));
    }

    @Test
    @DisplayName("Validate Password")
    void testPassword(){
        assertTrue(Validator.validatePassword("Juanite1234"));
        assertFalse(Validator.validatePassword("a12A"));
        assertFalse(Validator.validatePassword("juanite1234"));
        assertTrue(Validator.validatePassword("Manolo23"));
        assertFalse(Validator.validatePassword("Cada23(=)asdasdasdasdasdasd"));
    }

    @Test
    @DisplayName("Validate Email")
    void testEmail(){
        assertTrue(Validator.validateEmail("juanite@gmail.com"));
        assertFalse(Validator.validateEmail("juan@asd"));
        assertFalse(Validator.validateEmail("juan"));
        assertTrue(Validator.validateEmail("juanite_28@hotmail.com"));
        assertFalse(Validator.validateEmail("juanite_gmail.com"));
    }

    @Test
    @DisplayName("Validate Company Email")
    void testCompanyEmail(){
        assertTrue(Validator.validateCompanyEmail("juanite@boared.com"));
        assertFalse(Validator.validateCompanyEmail("juan@boared"));
        assertFalse(Validator.validateCompanyEmail("juan"));
        assertTrue(Validator.validateCompanyEmail("erich@boared.com"));
        assertFalse(Validator.validateCompanyEmail("juanite@gmail.com"));
    }

    @Test
    @DisplayName("Validate Name")
    void testName(){
        assertTrue(Validator.validateName("Juan"));
        assertFalse(Validator.validateName("juan1"));
        assertFalse(Validator.validateName("ju_an"));
        assertTrue(Validator.validateName("Juan Francisco"));
        assertFalse(Validator.validateName("Juan FranciscoJuan FranciscoJuan FranciscoJuan FranciscoJuan Francisco"));
    }

    @Test
    @DisplayName("Validate Date")
    void testDate(){
        assertTrue(Validator.validateDate("2023-05-18"));
        assertFalse(Validator.validateDate("18-05-2023"));
        assertFalse(Validator.validateDate("2023/05/18"));
        assertTrue(Validator.validateDate("1994-11-22"));
        assertFalse(Validator.validateDate("2023-15-05"));
        assertFalse(Validator.validateDate("2023-11-45"));
    }

    @Test
    @DisplayName("Validate Price")
    void testPrice(){
        assertTrue(Validator.validatePrice("19.99"));
        assertFalse(Validator.validatePrice(".99"));
        assertFalse(Validator.validatePrice("19.999"));
        assertTrue(Validator.validatePrice("89"));
        assertFalse(Validator.validatePrice("89999.88"));
    }

    @Test
    @DisplayName("Validate Phone Number")
    void testPhoneNumber(){
        assertTrue(Validator.validatePhoneNumber("+34123456789"));
        assertFalse(Validator.validatePhoneNumber("123456789"));
        assertFalse(Validator.validatePhoneNumber("+3463"));
        assertTrue(Validator.validatePhoneNumber("+3463456"));
        assertFalse(Validator.validatePhoneNumber("+34654789a"));
    }
}