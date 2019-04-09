package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.User;

public interface AccountAPICallBack extends RestAPICallBack {
    void onChangePassword();
    void onCheckUserExistence(User user);
    void onCheckEmailExistence(User user);
    void onUsernameFailed();
    void onEmailFailed();
    void onUserIsAbleToBeCreated();
}
