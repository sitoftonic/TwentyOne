package com.example.twentyone.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.twentyone.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean bool = sharedPreferences.getBoolean("pref_key_haptic", false);
    }

    public void switchToSignin(View view) {
        Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void switchToRegister(View view) {
        Intent intent = new Intent(LaunchActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
