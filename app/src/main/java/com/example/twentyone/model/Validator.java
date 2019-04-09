package com.example.twentyone.model;

public class Validator {

    // Instance
    private static Validator ourInstance;

    // Username limits
    private static final int USERNAME_MIN_LENGTH = 4;
    private static final int USERNAME_MAX_LENGTH = 12;

    // Password limits
    private static final int PASSWORD_MIN_LENGTH = 4;
    private static final int PASSWORD_MAX_LENGTH = 12;

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

        if (username == null){
            return EMPTY;
        }
        if (username.length() == 0) {
            return EMPTY;
        } else if (username.length() < USERNAME_MIN_LENGTH){
            return SHORT;
        } else if (username.length() > USERNAME_MAX_LENGTH){
            return LONG;
        } else {
            // TODO: username regex and return FORMAT
            return CORRECT;
        }

    }

    public int validateLoginPassword(final String password) {
        if (password == null){
            return EMPTY;
        }
        if (password.length() == 0) {
            return EMPTY;
        } else if (password.length() < PASSWORD_MIN_LENGTH){
            return SHORT;
        } else if (password.length() > PASSWORD_MAX_LENGTH){
            return LONG;
        } else {
            // TODO: password regex and return FORMAT
            return CORRECT;
        }
    }

    public int validateLogin(final String username, final String password) {

        int valid = 0;

        return valid;
    }

    public int validateRegister(final String user, final String email, final String pass, final String pass_repeat) {

        int valid = 0;




        return valid;
    }

    public int validateRegisterUsername(final String username) {

        if (username.length() == 0) {
            return EMPTY;
        } else if (username.length() < USERNAME_MIN_LENGTH){
            return SHORT;
        } else if (username.length() > USERNAME_MAX_LENGTH){
            return LONG;
        } else {
            // TODO: Username regex and return FORMAT
            return CORRECT;
        }
    }

    public int validateRegisterEmail(final String email) {

        if (email.length() == 0) {
            return 1;
        } else {
            return 0;
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
            // TODO: Password regex and return FORMAT
            return CORRECT;
        }
    }

}
