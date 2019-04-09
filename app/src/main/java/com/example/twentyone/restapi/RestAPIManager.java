package com.example.twentyone.restapi;

import android.util.Log;

import com.example.twentyone.model.data.BloodPressure;
import com.example.twentyone.model.data.PasswordChange;
import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;
import com.example.twentyone.model.data.UserData;
import com.example.twentyone.model.data.UserToken;
import com.example.twentyone.restapi.callback.AccountAPICallBack;
import com.example.twentyone.restapi.callback.BloodAPICallBack;
import com.example.twentyone.restapi.callback.LoginAPICallBack;
import com.example.twentyone.restapi.callback.PointsAPICallBack;
import com.example.twentyone.restapi.callback.RegisterAPICallBack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    private List<Points> pointsList,pointsListByUser;

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
                    registerAPICallback.onSuccess();
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

    public synchronized void getAllPoints(final PointsAPICallBack pointsAPICallBack){
        pointsList = new LinkedList<>();
        getAllPoints(pointsAPICallBack,0);
    }

    private synchronized void getAllPoints(final PointsAPICallBack pointsAPICallBack, final int level) {
        Log.d("LRM", "all points GET request");
        //http://android.byted.xyz/api/points?page=1000&paged=true&sort.sorted=false&sort.unsorted=true
        Map<String, String> map = getParamsGetAllPoints(level);
        Call<Points[]> call =  restApiService.getAllPoints("Bearer " + userToken.getIdToken(),map);
        call.enqueue(new Callback<Points[]>() {
            @Override
            public void onResponse(Call<Points[]> call, Response<Points[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length>0){
                        pointsList.addAll(Arrays.asList(response.body()));
                        getAllPoints(pointsAPICallBack,level+1);
                    }
                    else{
                        pointsAPICallBack.onFinishedCallback(pointsList);
                    }

                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points[]> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void getAllPointsByUser(final PointsAPICallBack pointsAPICallBack){
        pointsListByUser = new LinkedList<>();
        getPointsByUser(pointsAPICallBack,0);
    }

    public synchronized void getPointsByUser(final PointsAPICallBack pointsAPICallBack, final int level) {
        Log.d("LRM", "all points GET request");
        //http://android.byted.xyz/api/points?page=1000&paged=true&sort.sorted=false&sort.unsorted=true
        Map<String, String> map = new HashMap<>();
        map.put("query","SELECT * FROM POINTS WHERE USER_ID = "+userToken.getIdToken());
        map.put("page",String.valueOf(level));

        Call<Points[]> call =  restApiService.getAllPoints("Bearer " + userToken.getIdToken(),map);
        call.enqueue(new Callback<Points[]>() {
            @Override
            public void onResponse(Call<Points[]> call, Response<Points[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length>0){
                        if(response.body()[response.body().length-1].getUser().getId()!=Integer.parseInt(userToken.getIdToken())){
                            for(Points p : response.body()){
                                if(p.getUser().getId()==Integer.parseInt(userToken.getIdToken())){
                                    pointsListByUser.add(p);
                                    continue;
                                }
                                break;
                            }
                            pointsAPICallBack.onFinishedCallback(pointsList);
                            return;
                        }
                        pointsListByUser.addAll(Arrays.asList(response.body()));
                        getAllPoints(pointsAPICallBack,level+1);
                    }
                    else{
                        pointsAPICallBack.onFinishedCallback(pointsList);
                    }

                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points[]> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }
        });
    }

    private Map<String, String> getParamsGetAllPoints(int level) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("page",String.valueOf(level));
        map.put("paged","true");
        map.put("sort.sorted","false");
        map.put("sort.unsorted","true");
        return map;
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

    public synchronized void getAllBloodPressure(final BloodAPICallBack bloodAPICallBack) {
        Log.d("LRM", "all points GET request");

        Call<BloodPressure> call = restApiService.getAllBloodPressure("Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<BloodPressure>() {
            @Override
            public void onResponse(Call<BloodPressure> call, Response<BloodPressure> response) {
                if (response.isSuccessful()) {
                    bloodAPICallBack.onGetAllBloodPressure();
                } else {
                    bloodAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<BloodPressure> call, Throwable t) {
                bloodAPICallBack.onFailure(t);
            }
        });
    }
}
