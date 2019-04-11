package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;

import java.util.List;

public interface BloodWeightPointsGPSDAPICallBack extends RestAPICallBack {
    void onPostPoints(Object tval);
    void onGetPoints(Object tval);
    void onFinishedCallback(List<Object> tlist);
}
