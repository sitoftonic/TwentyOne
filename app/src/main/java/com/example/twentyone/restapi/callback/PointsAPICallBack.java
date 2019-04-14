package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;

import java.util.ArrayList;
import java.util.List;

public interface PointsAPICallBack extends RestAPICallBack{
    void onPostPoints(Points points);
    void onGetPoints(Points points);
    void onGetPointsWeek(PointsWeek pointsWeek);
    void onBadRequest();
    void onFinishedCallback(ArrayList<Points> pointsList);
    void onFinishedGraphCallback(int value);
    void onFinished7LastMonths(ArrayList<Integer> values);
    void onFinished7LastDays(ArrayList<Integer> values);
}
