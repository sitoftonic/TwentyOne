package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;

import java.util.List;

public interface PointsAPICallBack extends RestAPICallBack{
    void onPostPoints(Points points);
    void onGetPoints(Points points);
    void onGetPointsWeek(PointsWeek pointsWeek);
    void onFinishedCallback(List<Points> pointsList);
}
