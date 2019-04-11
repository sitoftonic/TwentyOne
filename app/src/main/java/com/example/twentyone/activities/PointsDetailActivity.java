package com.example.twentyone.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.twentyone.R;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initToolbar(getIntent().getExtras());
        initView();


        //TextView text = findViewById(R.id.demo);
        //text.setText(getIntent().getExtras().getString("note_id", "Default"));

    }


    private void initToolbar(Bundle bundle) {

        toolbar = findViewById(R.id.my_toolbar);

        toolbar.setTitle(bundle.getString("note_id", "Default"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {


    }
}
