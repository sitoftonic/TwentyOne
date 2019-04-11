package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;

import java.util.List;

public interface PointsAPICallBack extends RestAPICallBack{
    void onPostPoints();
    void onGetPoints(Points points);
    void onGetPointsWeek(PointsWeek pointsWeek);
    void onBadRequest();
    void onFinishedCallback(List<Points> pointsList);
}
