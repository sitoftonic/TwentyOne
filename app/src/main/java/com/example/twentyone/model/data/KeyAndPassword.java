package com.example.twentyone.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeyAndPassword {

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("newPassword")
    @Expose
    private String newPassword;

    public KeyAndPassword(String key, String newPassword) {
        this.key = key;
        this.newPassword = newPassword;
    }

    public String getKey() {
        return key;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
