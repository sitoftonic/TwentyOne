package com.example.twentyone.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.twentyone.R;
import com.example.twentyone.model.data.UserData;
import com.example.twentyone.model.data.UserToken;
import com.example.twentyone.restapi.RestAPIManager;
import com.example.twentyone.restapi.callback.LoginAPICallBack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoadActivity extends AppCompatActivity implements LoginAPICallBack {

    public static final String APP_IDENTIFIER = "21-POINTS";
    private static final int TIME_TO_WAIT = 1500;
    private Context context;
    private UserData savedData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        savedData = hasTokenSaved();
        if (savedData != null){
            // Try to login
            RestAPIManager.getInstance().getUserToken(savedData.getUsername(), savedData.getPassword(), this);

        }else{
            context = this;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intentToLaunchActivity = new Intent(context, LaunchActivity.class);
                    intentToLaunchActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("LOLO", "Launch main Activity");
                    startActivity(intentToLaunchActivity);
                }
            }, TIME_TO_WAIT);
        }
    }

    public UserData hasTokenSaved(){
        SharedPreferences preferences = getSharedPreferences(APP_IDENTIFIER, MODE_PRIVATE);
        String username = preferences.getString("username", null);
        String password = preferences.getString("password", null);
        if (username != null && password != null){
            return new UserData(username, password);
        }
        return null;
    }


    @Override
    public void onLoginSuccess(UserToken userToken) {
        switchToMainActivity();
    }

    @Override
    public void onFailure(Throwable t) {
        switchToLaunchActivity();
    }

    public void switchToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user", savedData.getUsername());
        Log.d("LOLO", "Launch main Activity");
        startActivity(intent);
    }

    public void switchToLaunchActivity() {
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d("LOLO", "Going to Launch Activity");
        startActivity(intent);
    }


    // I WANTED TO DO THE 100 COMMIT
}
