package com.juanite.util;

import com.juanite.model.domain.CompanyEmails;

public class Validator {

    /**
     * Method that validates a username.
     * @param username , the string to validate.
     * @return true if it matches the regex or false if it doesn't.
     */
    public static boolean validateUsername(String username){
        return username.matches("^[a-zA-Z0-9]{3,25}$");
    }

    /**
     * Method that validates a password.
     * @param password , the string to validate.
     * @return true if it matches the regex or false if it doesn't.
     */
    public static boolean validatePassword(String password) {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{8,50}$");
    }

    /**
     * Method that validates an email.
     * @param email , the string to validate.
     * @return true if it matches the regex or false if it doesn't.
     */
    public static boolean validateEmail(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    /**
     * Method that validates a company email.
     * @param email , the string to validate.
     * @return true if it matches the regex or false if it doesn't.
     */
    public static boolean validateCompanyEmail(String email){
        if(email.matches("^[a-zA-Z0-9_]{3,25}@boared\\.com$")){
            for(CompanyEmails ce : CompanyEmails.values()){
                if(email.equals(ce.name() + "@boared.com")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that validates a name.
     * @param name , the string to validate.
     * @return true if it matches the regex or false if it doesn't.
     */
    public static boolean validateName(String name){
        return name.matches("^(?=.*[a-zA-ZñÑáéíóúÁÉÍÓÚ])[a-zA-ZñÑáéíóúÁÉÍÓÚ ]{3,25}$");
    }

    /**
     * Method that validates a date.
     * @param date , the string to validate.
     * @return true if it matches the regex or false if it doesn't.
     */
    public static boolean validateDate(String date){
        return date.matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
    }

    /**
     * Method that validates a price.
     * @param price , the string to validate.
     * @return true if it matches the regex or false if it doesn't.
     */
    public static boolean validatePrice(String price){
        return price.matches("^\\d{1,4}(\\.\\d{2})?$");
    }

}
