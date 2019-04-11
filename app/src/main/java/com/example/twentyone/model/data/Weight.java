package com.example.twentyone.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Weight {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("timestamp")
    @Expose
    private Timestamp timestamp;

    @SerializedName("weight")
    @Expose
    private Integer weight;

    @SerializedName("user")
    @Expose
    private User user;

    public Weight(int id, Timestamp timestamp, int weight, User user) {
        this.id = id;
        this.timestamp = timestamp;
        this.weight = weight;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Integer getWeight() {
        return weight;
    }

    public User getUser() {
        return user;
    }
}
