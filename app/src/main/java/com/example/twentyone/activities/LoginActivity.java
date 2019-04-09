package com.example.twentyone.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.twentyone.AnimationManager;
import com.example.twentyone.R;
import com.example.twentyone.dialogs.ResetPassDialog;
import com.example.twentyone.model.Validator;
import com.example.twentyone.model.data.UserToken;
import com.example.twentyone.restapi.RestAPIManager;
import com.example.twentyone.restapi.callback.LoginAPICallBack;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity implements LoginAPICallBack {

    private Toolbar toolbar;

    private TextInputLayout username_input;
    private TextInputEditText username_text;
    private final String USERNAME_KEY = "username";

    private TextInputLayout password_input;
    private TextInputEditText password_text;
    private final String PASSWORD_KEY = "password";

    private SwitchCompat remember;
    private final String REMEMBER_KEY = "remember";

    private MaterialButton signin_button;
    private MaterialButton forgot_button;

    private ProgressDialog progressDialog;

    private AnimationManager animationManager = AnimationManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        initToolbar();
        initView();
        initProgressDialog();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        username_text.setText(savedInstanceState.getString(USERNAME_KEY));
        password_text.setText(savedInstanceState.getString(PASSWORD_KEY));
        remember.setChecked(savedInstanceState.getBoolean(REMEMBER_KEY));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(USERNAME_KEY, username_text.getText().toString());
        outState.putString(PASSWORD_KEY, password_text.getText().toString());
        outState.putBoolean(REMEMBER_KEY, remember.isChecked());
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Processing...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
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

        password_input = findViewById(R.id.password_text_input);
        password_text = findViewById(R.id.password_edit_text);

        remember = findViewById(R.id.remember_switch);

        signin_button = findViewById(R.id.signin_button);
        signin_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });

        forgot_button = findViewById(R.id.forgot_button);
        forgot_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResetDialog();
            }
        });
    }

    private void validateFields() {

        // Store values at the time of the login attempt.
        String username = username_text.getText().toString();
        String password = password_text.getText().toString();

        boolean correct = false;

        correct = isUsernameValid(username) && isPasswordValid(password);

        if (correct) {
            RestAPIManager.getInstance().getUserToken(username, password, this);
            progressDialog.show();
        }
        /*
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            password_input.setError(getString(R.string.login_password_error_empty));
            cancel = true;
        }else if (!isPasswordValid(password)){
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            username_input.setError(getString(R.string.login_username_error_empty));
            cancel = true;
        } else if (!isUsernameValid(username)) {
            cancel = true;
        }
        */



        /*
        int err = 0;

        switch (validator.validateLoginUsername(username_text.getText().toString())) {
            case 0:
                username_input.setError("");
                break;
            case 1:
                username_input.setError(getString(R.string.login_username_error_empty));
                username_input.startAnimation(animationManager.shakeError());
                err = 1;
                break;
        }

        switch (validator.validateLoginPassword(password_text.getText().toString())) {
            case 0:
                password_input.setError("");
                break;
            case 1:
                password_input.setError(getString(R.string.login_password_error_empty));
                password_input.startAnimation(animationManager.shakeError());
                err = 2;
                break;
        }

        if (err == 0) {
            switchToMainActivity();
        } else {
            Snackbar.make(findViewById(R.id.activity_login) , R.string.login_toast_error, Snackbar.LENGTH_SHORT).show();
            animationManager.hapticError(this);
        }
        */
    }

    private boolean isUsernameValid(String username) {
        Log.d("LOLO", "Validating username");

        int valid = Validator.getInstance().validateLoginUsername(username);

        switch (valid){
            case Validator.EMPTY:
                username_input.setError(getString(R.string.login_username_error_empty));
                return false;
            case Validator.SHORT:
                username_input.setError(getString(R.string.login_username_error_minChar));
                return false;
            case Validator.LONG:
                username_input.setError(getString(R.string.login_username_error_maxChar));
                return false;
            case Validator.FORMAT:
                username_input.setError(getString(R.string.login_username_error_format));
                return false;
            default:
                // It's OK
                username_input.setError(null);
                return true;
        }
    }

    private boolean isPasswordValid(String password) {
        Log.d("LOLO", "Validating password");

        int valid = Validator.getInstance().validateLoginPassword(password);

        switch (valid){
            case Validator.EMPTY:
                password_input.setError(getString(R.string.login_password_error_empty));
                return false;
            case Validator.SHORT:
                password_input.setError(getString(R.string.login_password_error_minChar));
                return false;
            case Validator.LONG:
                password_input.setError(getString(R.string.login_password_error_maxChar));
                return false;
            case Validator.FORMAT:
                password_input.setError(getString(R.string.login_password_error_format));
                return false;
            default:
                // It's OK
                password_input.setError(null);
                return true;
        }

    }

    public void switchToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user", username_text.getText().toString());
        Log.d("LOLO", "Launch main Activity");
        startActivity(intent);
    }

    public void showResetDialog() {
        new ResetPassDialog().show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onLoginSuccess(UserToken userToken) {
        Log.d("LOLO", "Login success");
        progressDialog.dismiss();
        switchToMainActivity();
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d("LOLO", "Login failed");
        Log.d("LOLO", t.getMessage());
        progressDialog.dismiss();
        Snackbar.make(findViewById(R.id.activity_login) , R.string.login_toast_error, Snackbar.LENGTH_SHORT).show();
        animationManager.hapticError(this);
    }
}
