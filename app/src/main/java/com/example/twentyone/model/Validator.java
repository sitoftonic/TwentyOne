package com.example.twentyone.model;

public class Validator {

    private static final Validator ourInstance = new Validator();

    public static Validator getInstance() {
        return ourInstance;
    }

    private Validator() {
    }

    public int validateLoginUsername(final String username) {

        if (username.length() == 0) {
            return 1;
        } else {
            return 0;
        }

    }

    public int validateLoginPassword(final String password) {
        if (password.length() == 0) {
            return 1;
        } else {
            return 0;
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
            return 1;
        } else {
            return 0;
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
            return 1;
        } else {
            return 0;
        }
    }

}
