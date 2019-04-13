package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.BloodPressure;
import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;
import com.example.twentyone.model.data.Weight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface BloodWeightPointsGPSDAPICallBack extends RestAPICallBack {
    //void onPostPoints(Object tval);
    //void onGetPoints(Object tval);
    //void onFinishedCallback(List<Object> tlist);
    void onFinishedPointsCallback(ArrayList<Points> tlist);
    void onFinishedBloodCallback(ArrayList<BloodPressure> tlist);
    void onFinishedWieghtCallback(ArrayList<Weight> tlist);
}
