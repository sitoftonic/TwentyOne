package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.UserToken;

public interface LoginAPICallBack extends RestAPICallBack{
    void onLoginSuccess(UserToken userToken);
}
