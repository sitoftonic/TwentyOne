package com.example.twentyone.model.data;

import android.annotation.SuppressLint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Points {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("exercise")
    @Expose
    private Integer exercise;
    @SerializedName("meals")
    @Expose
    private Integer meals;
    @SerializedName("alcohol")
    @Expose
    private Integer alcohol;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("user")
    @Expose
    private User user;

    public Points(String date, Integer exercise, Integer meals, Integer alcohol, String notes) {
        this.date = changeDateFormat(date);
        this.exercise = exercise;
        this.meals = meals;
        this.alcohol = alcohol;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getExercise() {
        return exercise;
    }

    public void setExercise(Integer exercise) {
        this.exercise = exercise;
    }

    public Integer getMeals() {
        return meals;
    }

    public void setMeals(Integer meals) {
        this.meals = meals;
    }

    public Integer getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Integer alcohol) {
        this.alcohol = alcohol;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String changeDateFormat(String oldDateString) {
        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy-MM-dd";

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            return sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return oldDateString;
    }

    @Override
    public String toString() {
        return "Points{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", exercise=" + exercise +
                ", meals=" + meals +
                ", alcohol=" + alcohol +
                ", notes=" + notes +
                ", user=" + user +
                '}';
    }
}
