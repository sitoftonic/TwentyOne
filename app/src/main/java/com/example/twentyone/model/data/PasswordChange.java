package com.example.twentyone.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PasswordChange {

    @SerializedName("currentPassword")
    @Expose
    private String currentPassword;

    @SerializedName("newPassword")
    @Expose
    private String newPassword;

    public PasswordChange(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
