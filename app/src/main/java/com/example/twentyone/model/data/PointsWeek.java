package com.example.twentyone.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointsWeek {
    // Attributes
    @SerializedName("points")
    @Expose
    private int points;
    @SerializedName("week")
    @Expose
    private String week;

    // Constructor
    public PointsWeek(int points, String week) {
        this.points = points;
        this.week = week;
    }

    // Getters & Setters
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
