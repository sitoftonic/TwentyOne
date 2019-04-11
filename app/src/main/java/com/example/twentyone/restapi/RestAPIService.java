package com.example.twentyone.restapi;


import com.example.twentyone.model.data.BloodPressure;
import com.example.twentyone.model.data.KeyAndPassword;
import com.example.twentyone.model.data.PasswordChange;
import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;
import com.example.twentyone.model.data.User;
import com.example.twentyone.model.data.UserData;
import com.example.twentyone.model.data.UserToken;
import com.example.twentyone.model.data.Weight;

import java.util.ArrayList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

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
    Call<Points[]> getAllPoints(@Header("Authorization") String token, @QueryMap Map<String,String> params);
    @POST("/api/account/change-password")
    Call<Void> changePassword(@Body PasswordChange passwordChange, @Header("Authorization") String token);
    @GET("/api/_search/users/{query}")
    Call<ArrayList<User>> checkUserExistence(@Path("query") String query, @Header("Authorization") String token);
    @GET("/api/users/{login}")
    Call<User> getUser(@Path("login") String login);
    Call<User> checkUserExistence(@Path("query") String query);
    @GET("/api/_search/points")
    Call<Points[]> getPointsByUser(@Header("Authorization") String token, @QueryMap Map<String,String> params);
    //@GET("/api/_search/points")
    //Call<Points[]> getPointsByUser(@Header("Authorization") String token, @QueryMap Map<String,String> params);
    @DELETE("/api/users/{login}")
    Call<Void> deleteUser(@Path("login") String login, @Header("Authorization") String token);
    @GET("/api/blood_pressures")
    Call<BloodPressure[]> getAllBloodPressure(@Header("Authorization") String token,@QueryMap Map<String,String> params);
    @GET("/api/_search/blood_pressures")
    Call<BloodPressure[]> getAllBloodPressureByUser(@Header("Authorization") String token,@QueryMap Map<String,String> params);
    @GET("/api/_search/weights")
    Call<Weight[]> getAllWeight(@Header("Authorization") String token, @QueryMap Map<String,String> params);
    @GET("/api/weights")
    Call<Weight[]> getAllWeightByUser(@Header("Authorization") String token,@QueryMap Map<String,String> params);
    @POST("/api/account/reset-password/init")
    Call<Void> resetPasswordInit(@Body String mail, @Header("Authorization") String token);
    @POST("/api/account/reset-password/finish")
    Call<Void> resetPasswordFinish(@Body KeyAndPassword keyAndPassword, @Header("Authorization") String token);
}
