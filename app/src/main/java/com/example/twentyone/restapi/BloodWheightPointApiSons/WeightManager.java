package com.example.twentyone.restapi.BloodWheightPointApiSons;

import android.util.Log;

import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.UserToken;
import com.example.twentyone.restapi.BloodWeightPointDataApiManager;
import com.example.twentyone.restapi.RestAPIService;
import com.example.twentyone.restapi.callback.BloodWeightPointsGPSDAPICallBack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeightManager extends BloodWeightPointDataApiManager {
    public WeightManager(UserToken userToken, RestAPIService restApiService) {
        super(userToken, restApiService);
    }

    //TODO implement.
}
