package com.example.twentyone.model;

import java.util.regex.Pattern;

public class Validator {

    // Instance
    private static Validator ourInstance;

    // Username limits
    private static final int USERNAME_MIN_LENGTH = 4;
    private static final int USERNAME_MAX_LENGTH = 12;

    // Password limits
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 12;

    // General validation return values
    public static final int CORRECT = 0;
    public static final int EMPTY = 1;
    public static final int SHORT = 2;
    public static final int LONG = 3;
    public static final int FORMAT = 4;


    public static Validator getInstance() {
        if (ourInstance == null){
            ourInstance = new Validator();
        }
        return ourInstance;
    }

    private Validator() {}

    public int validateLoginUsername(final String username) {

        return validateRegisterUsername(username);

    }

    public int validateLoginPassword(final String password) {
        return validateRegisterPassword(password);
    }

    public int validateLogin(final String username, final String password) {

        int valid = 0;

        return valid;
    }

    public int validateRegister(final String user, final String email, final String pass, final String pass_repeat) {

        int valid = 0;




        return valid;
    }

    private int calculateStrength(String password){
       if(password.length()<0 || isOnlyLLetters(password) || isOnlyMins(password)){
           return 0;
       }
       else {
           int mLetter = oneMLetter(password);
           int number = oneNumber(password);
           int special = oneSpecial(password);
           int data = mLetter + number + special;
           if(data == 1){
                return 1;
           }
           else if(mLetter <2 && number <2&& special <2){
               return data;
           }
           else if(onlyOneIsOne(mLetter,number,special)){
               return 4;
           }
           else if(onlyTwoIsOne(mLetter,number,special)){
               return 5;
           }
           return 6;
       }
    }

    private boolean onlyTwoIsOne(int mLetter, int number, int special) {
        if(mLetter==1&&number>1&&special>1){
            return true;
        }
        if(mLetter>1&&number==1&&special>1){
            return true;
        }
        return mLetter > 1 && number > 1 && special == 1;
    }

    private boolean onlyOneIsOne(int mLetter, int number, int special) {
        if(mLetter==1&&number==1&&special>1){
            return true;
        }
        if(mLetter==1&&number>1&&special==1){
            return true;
        }
        return mLetter > 1 && number == 1 && special == 1;
    }

    private int oneSpecial(String password) {
        int amunt = 0;
        for(char c : password.toCharArray()){
            boolean special = Pattern.matches(".*[^A-Za-z0-9].*",""+c);
            if(special){
                amunt++;
            }
        }
        return amunt;
    }

    private int oneNumber(String password) {
        int amunt=0;
        for(char c : password.toCharArray()){
            boolean number = Pattern.matches(".*\\d.*", c+"");
            if(number){
                amunt++;
            }

        }
        return amunt;
    }

    private int oneMLetter(String password) {
        int amunt = 0;
        for(char c : password.toCharArray()){
            if(c>='A'&&c<='Z'){
                amunt++;
            }
        }
        return amunt;
    }


    private boolean isOnlyMins(String password) {
        for(char c : password.toCharArray()){
            if(c>'z'||c<'a'){
                return false;
            }
        }
        return true;
    }

    private boolean isOnlyLLetters(String password) {
        boolean number = Pattern.matches(".*\\d.*", password);
        boolean special = Pattern.matches(".*[^A-Za-z0-9].*",password);
        //boolean special = hasSpecial(password);
        boolean min = !password.equals(password.toUpperCase());
        boolean may = !password.equals(password.toLowerCase());
        return !number && !special && min && !may;
    }

    public int validateRegisterUsername(final String username) {

        if (username.length() == 0) {
            return EMPTY;
        } else if (username.length() < USERNAME_MIN_LENGTH){
            return SHORT;
        } else if (username.length() > USERNAME_MAX_LENGTH){
            return LONG;
        } else {
            //nums y letrass no especiales (solo guion y barra baja y punto)
            boolean hasLetter = Pattern.matches(".*\\w.*",username);
            boolean hasNonLetterCharacters = Pattern.matches(".*[^A-Za-z0-9].*",username);
            if(hasLetter&&!hasNonLetterCharacters) return CORRECT;

            return FORMAT;
        }
    }

    public int validateRegisterEmail(final String email) {

        if (email.length() == 0) {
            return EMPTY;
        }
        else {
            if(Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
                    ,email)){
                return CORRECT;
            }
            return FORMAT;
        }
    }

    public int validateRegisterPassword(final String password) {

        if (password.length() == 0) {
            return EMPTY;
        } else if (password.length() < PASSWORD_MIN_LENGTH){
            return SHORT;
        } else if (password.length() > PASSWORD_MAX_LENGTH){
            return LONG;
        } else {
            //min una may un num u special character
            boolean number = Pattern.matches(".*\\d.*", password);
            boolean special = Pattern.matches(".*[^A-Za-z0-9].*",password);
            //boolean special = hasSpecial(password);
            boolean min = !password.equals(password.toUpperCase());
            boolean may = !password.equals(password.toLowerCase());
            if(number&&special&&min&&may) return CORRECT;
            return FORMAT;
        }
    }

    private boolean hasSpecial(String password) {
        for(char val : password.toCharArray()){
            if(!(val>='0'&&val<='0')&&!(val>='A'&&val<='Z')&&!(val>='a'&&val<='z')){
                return true;
            }
        }
        return false;
    }


}
