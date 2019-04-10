package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.User;

public interface AccountAPICallBack extends RestAPICallBack {
    void onChangePassword();
    void onUsernameFailed();
    void onEmailFailed();
    void onFailure();
    void onSuccess();
}
