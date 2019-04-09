package com.example.twentyone.restapi;


import com.example.twentyone.model.data.PasswordChange;
import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;
import com.example.twentyone.model.data.User;
import com.example.twentyone.model.data.UserData;
import com.example.twentyone.model.data.UserToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPIService {
    @POST("/api/points")
    Call<Points> postPoints(@Body Points points, @Header("Authorization") String token);
    @GET("/api/points/{id}")
    Call<Points> getPointsById(@Path("id") Integer id, @Header("Authorization") String token);
    @POST("/api/authenticate")
    Call<UserToken> requestToken(@Body UserData userData);
    @POST("/api/register")
    Call<Void> register(@Body UserData userData);
    @GET("/api/points-this-week")
    Call<PointsWeek> getPointsThisWeek(@Header("Authorization") String token);
    @GET("/api/points")
    Call<Points> getAllPoints(@Header("Authorization") String token);
    @POST("/api/account/change-password")
    Call<Void> changePassword(@Body PasswordChange passwordChange, @Header("Authorization") String token);
    @GET("/api/_search/users/{query}")
    Call<User> checkUserExistence(@Path("query") String query);
}
