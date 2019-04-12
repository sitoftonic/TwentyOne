package com.example.twentyone.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Preferences {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("weeklyGoal")
    @Expose
    private int weeklyGoal;

    @SerializedName("weightUnits")
    @Expose
    private String weightUnits;

    public Preferences(int id,int weeklyGoal,String weightUnits , User user) {
        this.id = id;
        this.weeklyGoal = weeklyGoal;
        this.weightUnits = weightUnits;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public int getWeeklyGoal() {
        return weeklyGoal;
    }

    public String getWeightUnits() {
        return weightUnits;
    }

    public User getUser() {
        return user;
    }
}
