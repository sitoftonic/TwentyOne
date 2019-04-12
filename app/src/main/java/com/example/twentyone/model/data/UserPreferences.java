package com.example.twentyone.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPreferences {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("weeklyGoal")
    @Expose
    private Integer weeklyGoal;
    @SerializedName("weightUnits")
    @Expose
    private String weightUnits;
    @SerializedName("user")
    @Expose
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeeklyGoal() {
        return weeklyGoal;
    }

    public void setWeeklyGoal(Integer weeklyGoal) {
        this.weeklyGoal = weeklyGoal;
    }

    public String getWeightUnits() {
        return weightUnits;
    }

    public void setWeightUnits(String weightUnits) {
        this.weightUnits = weightUnits;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserPreferences{" +
                "id=" + id +
                ", weeklyGoal=" + weeklyGoal +
                ", weightUnits='" + weightUnits +
                "', user={" +
                "id=" + user.getId() +
                ", login='" + user.getLogin() + '\'' +
                '}' + '\'' +
                '}';
    }
}
