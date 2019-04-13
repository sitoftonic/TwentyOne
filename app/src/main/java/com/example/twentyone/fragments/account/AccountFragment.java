package com.example.twentyone.fragments.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twentyone.R;
import com.example.twentyone.activities.LaunchActivity;
import com.example.twentyone.restapi.callback.AccountAPICallBack;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static com.example.twentyone.activities.LoadActivity.APP_IDENTIFIER;

public class AccountFragment extends Fragment implements AccountAPICallBack {

    private TextInputLayout password_input;
    private TextInputLayout password_new_input;
    private MaterialButton buttonLogout;

    public static final String TAG = AccountFragment.class.getSimpleName();

    private static final AccountFragment ourInstance = new AccountFragment();

    public static AccountFragment getInstance() {
        return ourInstance;
    }

    public AccountFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //String oldPassword = "admin";
        //String newPassword = "admin";

        //RestAPIManager.getInstance().changePassword(oldPassword, newPassword, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Get the button from the view
        buttonLogout = (MaterialButton)view.findViewById(R.id.bt_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogout();
            }
        });

        return view;
    }

    @Override
    public void onResetPasswordFinish() {

    }

    @Override
    public void onResetPasswordInit() {

    }

    @Override
    public void onChangePassword() {
        /* TODO action after changed password */
    }

    @Override
    public void onUsernameFailed() { }

    @Override
    public void onEmailFailed() { }

    @Override
    public void onPasswordDontMatch() {

    }

    @Override
    public void onEmailNotFound() {

    }

    @Override
    public void onBadResetKey() {

    }

    @Override
    public void onFailure() { }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(Throwable t) { }


    // Logout
    public void onLogout(){
        SharedPreferences preferences = getActivity().getSharedPreferences(APP_IDENTIFIER, MODE_PRIVATE);
        String username = preferences.getString("username", null);
        String password = preferences.getString("password", null);
        if (username != null && password != null){
            // Delete username and password (delete all data)
            preferences.edit().clear().apply();
        }

        // ntent to LaunchActivity
        Intent intent = new Intent(getActivity(), LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
