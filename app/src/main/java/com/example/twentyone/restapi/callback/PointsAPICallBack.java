package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.Points;
import com.example.twentyone.model.data.PointsWeek;

public interface PointsAPICallBack extends RestAPICallBack{
    void onPostPoints(Points points);
    void onGetPoints(Points points);
    void onGetPointsWeek(PointsWeek pointsWeek);
}
