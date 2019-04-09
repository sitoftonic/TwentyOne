package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.User;

public interface AccountAPICallBack extends RestAPICallBack {
    void onChangePassword();
    void onCheckEmailExistence();
    void onUsernameFailed();
    void onEmailFailed();
    void onUserIsAbleToBeCreated();
    void onFailure();
    void onDeleteAccount();
}
