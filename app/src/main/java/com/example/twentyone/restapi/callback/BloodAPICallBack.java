package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.BloodPressure;
import com.example.twentyone.model.data.Points;

import java.util.List;

public interface BloodAPICallBack extends RestAPICallBack {
    void onGetAllBloodPressure();
    void onPostBloodPressure(BloodPressure bloodPressure);
    void onFinishedCallback(List<BloodPressure> bloodList);
}
