package com.example.twentyone.activities;

import android.os.Bundle;

import android.view.View;


import com.example.twentyone.R;
import com.example.twentyone.fragments.settings.SettingsFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getWindow().setStatusBarColor(getResources().getColor(R.color.statusBar));
        getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_acid));

        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onBackPressed();
                }
            });

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
