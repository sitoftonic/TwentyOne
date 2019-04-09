package com.example.twentyone.restapi;

import android.util.Log;

import com.example.twentyone.model.data.PasswordChange;
import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;
import com.example.twentyone.model.data.User;
import com.example.twentyone.model.data.UserData;
import com.example.twentyone.model.data.UserToken;
import com.example.twentyone.restapi.callback.AccountAPICallBack;
import com.example.twentyone.restapi.callback.LoginAPICallBack;
import com.example.twentyone.restapi.callback.PointsAPICallBack;
import com.example.twentyone.restapi.callback.RegisterAPICallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIManager {
    //private static final String BASE_URL = "http://" + "your_ip:8080/";
    private static final String BASE_URL = "http://" + "android.byted.xyz/";
    private static RestAPIManager ourInstance;
    private Retrofit retrofit;
    private RestAPIService restApiService;
    private UserToken userToken;


    public static RestAPIManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new RestAPIManager();
        }
        return ourInstance;
    }

    private RestAPIManager() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restApiService = retrofit.create(RestAPIService.class);

    }

    public synchronized void getUserToken(String username, String password, final LoginAPICallBack restAPICallBack) {
        Log.d("LOLO", "GET USER TOKEN");
        UserData userData = new UserData(username, password);
        Call<UserToken> call = restApiService.requestToken(userData);

        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {

                if (response.isSuccessful()) {
                    userToken = response.body();
                    restAPICallBack.onLoginSuccess(userToken);
                } else {
                    restAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                restAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void postPoints(Points points, final PointsAPICallBack pointsAPICallBack) {
        final Points newUserPoints = points;
        Call<Points> call = restApiService.postPoints(newUserPoints, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<Points>() {
            @Override
            public void onResponse(Call<Points> call, Response<Points> response) {

                if (response.isSuccessful()) {
                    pointsAPICallBack.onPostPoints(response.body());
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void getPointsById( Integer id , final PointsAPICallBack pointsAPICallBack) {
        Call<Points> call = restApiService.getPointsById(id, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<Points>() {
            @Override
            public void onResponse(Call<Points> call, Response<Points> response) {

                if (response.isSuccessful()) {
                    pointsAPICallBack.onGetPoints(response.body());
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void register(String username, String email, String password, final RegisterAPICallBack registerAPICallback) {
        UserData userData = new UserData(username, email, password);
        Call<Void> call = restApiService.register(userData);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    registerAPICallback.onRegisterSuccess();
                } else {
                    registerAPICallback.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                registerAPICallback.onFailure(t);
            }
        });
    }

    public synchronized void getPointsWeek(final PointsAPICallBack pointsAPICallBack) {
        Log.d("LOLO", "week points GET request");

        Call<PointsWeek> call = restApiService.getPointsThisWeek("Bearer " + userToken.getIdToken());
        //Call<Points> call = restApiService.getPointsById(id, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<PointsWeek>() {
            @Override
            public void onResponse(Call<PointsWeek> call, Response<PointsWeek> response) {

                if (response.isSuccessful()) {
                    pointsAPICallBack.onGetPointsWeek(response.body());
                } else {
                    Log.d("LOLO", "[FAIL] - Connection stablished, but fail");
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<PointsWeek> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void getAllPoints(final PointsAPICallBack pointsAPICallBack) {
        Log.d("LRM", "all points GET request");

        Call<Points> call = restApiService.getAllPoints("Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<Points>() {
            @Override
            public void onResponse(Call<Points> call, Response<Points> response) {
                if (response.isSuccessful()) {
                    pointsAPICallBack.onGetPoints(response.body());
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void changePassword(String oldPassword, String finalPassword, final AccountAPICallBack accountAPICallBack) {
        Log.d("LRM", "all points GET request");

        Call<Void> call = restApiService.changePassword(new PasswordChange(oldPassword,finalPassword), "Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    accountAPICallBack.onChangePassword();
                } else {
                    accountAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                accountAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void onCheckUserExistence(String username, final AccountAPICallBack accountAPICallBack){
        Log.d("LOLO", "username check existence");
        String query = new StringBuilder().append("SELECT * FROM JHI_USER WHERE LOGIN = '" + username + "'").toString();

        Call<User> call = restApiService.checkUserExistence(query);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    accountAPICallBack.onCheckEmailExistence(user);
                } else {
                    accountAPICallBack.onUsernameFailed();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                accountAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void onCheckEmailExistence(String email, final AccountAPICallBack accountAPICallBack){
        Log.d("LOLO", "email check existence");
        String query = new StringBuilder().append("SELECT * FROM JHI_USER WHERE EMAIL = '" + email + "'").toString();

        Call<User> call = restApiService.checkUserExistence(query);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    accountAPICallBack.onUserIsAbleToBeCreated();
                } else {
                    accountAPICallBack.onEmailFailed();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                accountAPICallBack.onFailure(t);
            }
        });
    }
}
