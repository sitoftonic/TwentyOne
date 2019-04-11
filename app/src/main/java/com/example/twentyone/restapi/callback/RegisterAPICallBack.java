package com.example.twentyone.restapi.callback;

public interface RegisterAPICallBack extends RestAPICallBack{
    void onRegisterSuccess();
    void onRegisterFailed();
}
