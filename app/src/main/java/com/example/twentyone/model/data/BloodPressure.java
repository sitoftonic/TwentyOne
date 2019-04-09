package com.example.twentyone.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class BloodPressure {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("timestamp")
    @Expose
    private Timestamp timestamp;

    @SerializedName("systolic")
    @Expose
    private Integer systolic;

    @SerializedName("diastolic")
    @Expose
    private Integer diastolic;

    @SerializedName("user")
    @Expose
    private User user;

    public BloodPressure(int id, Timestamp timestamp, int systolic, int diastolic, User user) {
        this.id = id;
        this.timestamp = timestamp;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public User getUser() {
        return user;
    }
}
