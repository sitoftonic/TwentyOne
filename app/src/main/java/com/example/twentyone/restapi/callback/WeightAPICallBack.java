package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.Weight;

import java.util.ArrayList;

public interface WeightAPICallBack extends RestAPICallBack {
    void onPostWeights(Weight weight);
    void onFinishedCallback(ArrayList<Weight> weights);
    void onFinished7LastDays(ArrayList<Integer> values);
}
