package com.example.twentyone.restapi;

import android.util.Log;

import com.example.twentyone.model.data.BloodPressure;
import com.example.twentyone.model.data.KeyAndPassword;
import com.example.twentyone.model.data.PasswordChange;
import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;
import com.example.twentyone.model.data.UserData;
import com.example.twentyone.model.data.UserPreferences;
import com.example.twentyone.model.data.UserToken;
import com.example.twentyone.model.data.Weight;
import com.example.twentyone.restapi.callback.AccountAPICallBack;
import com.example.twentyone.restapi.callback.BloodAPICallBack;
import com.example.twentyone.restapi.callback.BloodWeightPointsGPSDAPICallBack;
import com.example.twentyone.restapi.callback.LoginAPICallBack;
import com.example.twentyone.restapi.callback.PointsAPICallBack;
import com.example.twentyone.restapi.callback.PreferencesAPICallBack;
import com.example.twentyone.restapi.callback.WeightAPICallBack;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIManager {
    //private static final String BASE_URL = "http://" + "your_ip:8080/";
    private static final String BASE_URL = "http://" + "android.byted.xyz/";
    private static final String ERROR_KEY_USERNAME = "userexists";
    private static final String ERROR_ID_EXISTS = "idexists";
    private static final String ERROR_TITLE_PASSWORD = "Incorrect password";
    private static final String ERROR_EMAIL_NOT_FOUND = "Email address not registered";
    private static final String ERROR_RESET_KEY = "No user was found for this reset key";
    private static final String ERROR_KEY_EMAIL = "emailexists";

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

    public synchronized void getUserToken(String username, String password, final LoginAPICallBack loginAPICallBack) {
        Log.d("LOLO", "GET USER TOKEN");
        UserData userData = new UserData(username, password);
        Call<UserToken> call = restApiService.requestToken(userData);

        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {

                if (response.isSuccessful()) {
                    userToken = response.body();
                    loginAPICallBack.onLoginSuccess(userToken);
                } else {
                    loginAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                loginAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void postPoints(final Points points, final PointsAPICallBack pointsAPICallBack) {

        Call<Points> call = restApiService.postPoints(points, "Bearer " + userToken.getIdToken());

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

    public synchronized void postBloodPressure(final BloodPressure bloodPressure, final BloodAPICallBack bloodAPICallBack) {

        Call<BloodPressure> call = restApiService.postBloodPressure(bloodPressure, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<BloodPressure>() {
            @Override
            public void onResponse(Call<BloodPressure> call, Response<BloodPressure> response) {

                if (response.isSuccessful()) {
                    bloodAPICallBack.onPostBloodPressure(response.body());
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

    public synchronized void postWeights(final Weight weight, final WeightAPICallBack weightAPICallBack) {

        Call<Weight> call = restApiService.postWeights(weight, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<Weight>() {
            @Override
            public void onResponse(Call<Weight> call, Response<Weight> response) {

                if (response.isSuccessful()) {
                    weightAPICallBack.onPostWeights(response.body());
                } else {
                    weightAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Weight> call, Throwable t) {
                weightAPICallBack.onFailure(t);
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

    public synchronized void register(String username, String email, String password, final AccountAPICallBack accountAPICallBack) {
        UserData userData = new UserData(username, email, password);
        Call<Void> call = restApiService.register(userData);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    accountAPICallBack.onSuccess();
                } else {
                    try {

                        String errorKey = getErrorKey(response);

                        if (errorKey.equals(ERROR_KEY_USERNAME)){
                            accountAPICallBack.onUsernameFailed();
                        }else{
                            accountAPICallBack.onEmailFailed();
                        }

                    } catch (IOException e) {
                        accountAPICallBack.onFailure();
                    }
                    Log.d("LOLO", "hola2");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                accountAPICallBack.onFailure(t);
            }


            private String getErrorKey(Response<Void> response) throws IOException {
                String content = response.errorBody().string();
                return new JsonParser().parse(content).getAsJsonObject().get("errorKey").getAsString();
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


    public synchronized void changePassword(String oldPassword, String finalPassword, final AccountAPICallBack accountAPICallBack) {
        Log.d("LRM", "change password request");

        Call<Void> call = restApiService.changePassword(new PasswordChange(oldPassword,finalPassword), "Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    accountAPICallBack.onChangePassword();
                } else {

                    try {
                        String errorTitle = getErrorTitle(response);

                        if (errorTitle.equals(ERROR_TITLE_PASSWORD)){
                            accountAPICallBack.onPasswordDontMatch();
                        }else{
                            accountAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                        }

                    } catch (IOException e) {
                        accountAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                    }

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                accountAPICallBack.onFailure(t);
            }

            private String getErrorTitle(Response<Void> response) throws IOException {
                String content = response.body().toString();
                return new JsonParser().parse(content).getAsJsonObject().get("title").getAsString();
            }
        });
    }

    public synchronized void resetPasswordInit(String mail, final AccountAPICallBack accountAPICallBack) {
        Log.d("LRM", "change password request");

        Call<Void> call = restApiService.resetPasswordInit(mail, "Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    accountAPICallBack.onResetPasswordInit();
                } else {
                    try {
                        String errorTitle = getErrorTitle(response);

                        if (errorTitle.equals(ERROR_EMAIL_NOT_FOUND)){
                            accountAPICallBack.onEmailNotFound();
                        }else{
                            accountAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                        }

                    } catch (IOException e) {
                        accountAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                accountAPICallBack.onFailure(t);
            }

            private String getErrorTitle(Response<Void> response) throws IOException {
                String content = response.body().toString();
                return new JsonParser().parse(content).getAsJsonObject().get("title").getAsString();
            }
        });
    }

    public synchronized void resetPasswordFinish(String key, String newPassword, final AccountAPICallBack accountAPICallBack) {
        Log.d("LRM", "change password request");

        Call<Void> call = restApiService.resetPasswordFinish(new KeyAndPassword(key,newPassword), "Bearer " + userToken.getIdToken());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    accountAPICallBack.onResetPasswordFinish();
                } else {
                    try {
                        String errorTitle = getErrorTitle(response);

                        if (errorTitle.equals(ERROR_RESET_KEY)){
                            accountAPICallBack.onBadResetKey();
                        }else{
                            accountAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                        }

                    } catch (IOException e) {
                        accountAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                accountAPICallBack.onFailure(t);
            }

            private String getErrorTitle(Response<Void> response) throws IOException {
                String content = response.body().toString();
                return new JsonParser().parse(content).getAsJsonObject().get("title").getAsString();
            }
        });
    }


    public synchronized void postUserPreferences(final UserPreferences userPreferences, final PreferencesAPICallBack preferencesAPICallBack) {

        Call<UserPreferences> call = restApiService.getUserPreferences(userPreferences, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<UserPreferences>() {
            @Override
            public void onResponse(Call<UserPreferences> call, Response<UserPreferences> response) {

                if (response.isSuccessful()) {
                    preferencesAPICallBack.onGetPreferences(response.body());
                } else {
                    preferencesAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<UserPreferences> call, Throwable t) {
                preferencesAPICallBack.onFailure(t);
            }
        });

    }


    public synchronized void getAllPointsByUser(final PointsAPICallBack pointsAPICallBack, final int level, final ArrayList<Points> pointsList){
        Log.d("LRM", "all points GET request");

        Map<String,String> data = new HashMap<>();
        data.put("page",String.valueOf(level));
        Call<Points[]> call = restApiService.getAllPoints("Bearer " + userToken.getIdToken(),data);
        call.enqueue(new Callback<Points[]>() {
            @Override
            public void onResponse(Call<Points[]> call, Response<Points[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length > 0){
                        pointsList.addAll(Arrays.asList(response.body()));
                        getAllPointsByUser(pointsAPICallBack,level+1,pointsList);
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

    public synchronized void getAllPointsByUserSearch(final PointsAPICallBack pointsAPICallBack, final int level, final String search, final ArrayList<Points> pointsList){
        Log.d("LRM", "all points search GET request");

        Map<String,String> data = new HashMap<>();
        data.put("page",String.valueOf(level));
        Call<Points[]> call = restApiService.getAllPoints("Bearer " + userToken.getIdToken(),data);
        call.enqueue(new Callback<Points[]>() {
            @Override
            public void onResponse(Call<Points[]> call, Response<Points[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length > 0){
                        pointsList.addAll(Arrays.asList(response.body()));
                        getAllPointsByUserSearch(pointsAPICallBack,level+1,search,pointsList);
                    }
                    else{
                        ArrayList<Points> finalPoints = getPointsBySearch(pointsList);
                        pointsAPICallBack.onFinishedCallback(finalPoints);
                    }
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points[]> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }

            private ArrayList<Points> getPointsBySearch(ArrayList<Points> points) {
                ArrayList<Points> finalPoints = new ArrayList<>();
                for (Points p : points) {
                    if (p.hasWord(search)) {
                        finalPoints.add(p);
                    }
                }
                return finalPoints;
            }
        });

    }

    public synchronized void getTotalPointsWeek(final PointsAPICallBack pointsAPICallBack, final int level, final int total){
        Log.d("LRM", "all points GET request");

        Map<String,String> data = new HashMap<>();
        data.put("page",String.valueOf(level));
        Call<Points[]> call = restApiService.getAllPoints("Bearer " + userToken.getIdToken(),data);
        call.enqueue(new Callback<Points[]>() {
            @Override
            public void onResponse(Call<Points[]> call, Response<Points[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length > 0){
                        ArrayList<Points> pointsArrayList = new ArrayList<>(Arrays.asList(response.body()));
                        int suma = sumaWeek(pointsArrayList,total);
                        getTotalPointsWeek(pointsAPICallBack,level+1,suma);
                    }
                    else{
                        pointsAPICallBack.onFinishedGraphCallback(total);
                    }
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points[]> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }

            private int sumaWeek(ArrayList<Points> points, int suma) {
                for (Points p : points) {
                    try {
                        if (isDateInCurrentWeek(new SimpleDateFormat("dd/MM/yyyy",Locale.FRANCE).parse(p.getDate()))) {
                            suma++;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                return suma;
            }

            private boolean isDateInCurrentWeek(Date date) {
                Calendar currentCalendar = Calendar.getInstance();
                int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
                int year = currentCalendar.get(Calendar.YEAR);
                Calendar targetCalendar = Calendar.getInstance();
                targetCalendar.setTime(date);
                int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
                int targetYear = targetCalendar.get(Calendar.YEAR);
                return week == targetWeek && year == targetYear;
            }
        });

    }


    public synchronized void getTotalPointsMonth(final PointsAPICallBack pointsAPICallBack, final int level, final int total){
        Log.d("LRM", "all points GET request");

        Map<String,String> data = new HashMap<>();
        data.put("page",String.valueOf(level));
        Call<Points[]> call = restApiService.getAllPoints("Bearer " + userToken.getIdToken(),data);
        call.enqueue(new Callback<Points[]>() {
            @Override
            public void onResponse(Call<Points[]> call, Response<Points[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length > 0){
                        ArrayList<Points> pointsArrayList = new ArrayList<>(Arrays.asList(response.body()));
                        int suma = sumaMonth(pointsArrayList,total);
                        getTotalPointsMonth(pointsAPICallBack,level+1,suma);
                    }
                    else{
                        pointsAPICallBack.onFinishedGraphCallback(total);
                    }
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points[]> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }

            private int sumaMonth(ArrayList<Points> points, int suma) {
                for (Points p : points) {
                    try {
                        if (isDateInCurrentMonth(new SimpleDateFormat("dd/MM/yyyy",Locale.FRANCE).parse(p.getDate()))) {
                            suma++;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                return suma;
            }

            private boolean isDateInCurrentMonth(Date date) {
                Calendar currentCalendar = Calendar.getInstance();
                int month = currentCalendar.get(Calendar.MONTH);
                int year = currentCalendar.get(Calendar.YEAR);
                Calendar targetCalendar = Calendar.getInstance();
                targetCalendar.setTime(date);
                int targetMonth = targetCalendar.get(Calendar.MONTH);
                int targetYear = targetCalendar.get(Calendar.YEAR);
                return month == targetMonth && year == targetYear;
            }
        });

    }

}
