package com.example.twentyone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.twentyone.AnimationManager;
import com.example.twentyone.R;
import com.example.twentyone.model.Validator;
import com.example.twentyone.restapi.callback.RegisterAPICallBack;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RegisterActivity extends AppCompatActivity implements RegisterAPICallBack {

    private Toolbar toolbar;

    private TextInputLayout username_input;
    private TextInputEditText username_text;
    private final String USERNAME_KEY = "username";

    private TextInputLayout email_input;
    private TextInputEditText email_text;
    private final String EMAIL_KEY = "email";

    private TextInputLayout password_input;
    private TextInputEditText password_text;
    private final String PASSWORD_KEY = "password";

    private TextInputLayout password_repeat_input;
    private TextInputEditText password_repeat_text;
    private final String PASSWORD_REPEAT_KEY = "password";

    private MaterialButton register_button;

    private AnimationManager animationManager = AnimationManager.getInstance();
    private Validator validator = Validator.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        initToolbar();
        initView();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        username_text.setText(savedInstanceState.getString(USERNAME_KEY));
        email_text.setText(savedInstanceState.getString(EMAIL_KEY));
        password_text.setText(savedInstanceState.getString(PASSWORD_KEY));
        password_repeat_text.setText(savedInstanceState.getString(PASSWORD_REPEAT_KEY));
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(USERNAME_KEY, username_text.getText().toString());
        outState.putString(EMAIL_KEY, email_text.getText().toString());
        outState.putString(PASSWORD_KEY, password_text.getText().toString());
        outState.putString(PASSWORD_REPEAT_KEY, password_repeat_text.getText().toString());
    }


    private void initToolbar() {

        toolbar = findViewById(R.id.my_toolbar);
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

        username_input = findViewById(R.id.username_text_input);
        username_text = findViewById(R.id.username_edit_text);

        email_input = findViewById(R.id.email_text_input);
        email_text = findViewById(R.id.email_edit_text);

        password_input = findViewById(R.id.password_text_input);
        password_text = findViewById(R.id.password_edit_text);

        password_repeat_input = findViewById(R.id.password_repeat_text_input);
        password_repeat_text = findViewById(R.id.password_repeat_edit_text);

        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
    }


    private void validateFields() {

        int err = 0;

        switch (validator.validateRegisterUsername(username_text.getText().toString())) {
            case 0:
                username_input.setError("");
                break;

            case 1:
                username_input.setError(getString(R.string.register_username_error_empty));
                username_input.startAnimation(animationManager.shakeError());
                err = 1;
                break;
        }

        switch (validator.validateRegisterUsername(email_text.getText().toString())) {
            case 0:
                email_input.setError("");
                break;

            case 1:
                email_input.setError(getString(R.string.register_email_error_empty));
                email_input.startAnimation(animationManager.shakeError());
                err = 2;
                break;
        }

        switch (validator.validateRegisterPassword(password_text.getText().toString())) {
            case 0:
                password_input.setError("");
                if (!password_repeat_text.getText().toString().equals(password_text.getText().toString())) {
                    password_repeat_input.setError(getString(R.string.register_password_error_match));
                    password_repeat_input.startAnimation(animationManager.shakeError());
                    err = 4;
                } else {
                    password_repeat_input.setError("");
                }
                break;
            case 1:
                password_input.setError(getString(R.string.register_password_error_empty));
                password_input.startAnimation(animationManager.shakeError());
                err = 3;
                break;
        }

        if (err == 0) {
            Snackbar.make(findViewById(R.id.activity_register), R.string.register_toast_success, Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(findViewById(R.id.activity_register), R.string.register_toast_error, Snackbar.LENGTH_SHORT).show();
            animationManager.hapticError(this);
        }
    }


    public void switchToMainActivity() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user", username_text.getText().toString());
        Log.d("LOLO", "Launch main Activity");
        startActivity(intent);
    }

    @Override
    public void onSuccess() {
        Log.d("LOLO", "Register success");
        switchToMainActivity();
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d("LOLO", "Register failed");
        // TODO: Something to notificate user that register failed
    }
}
