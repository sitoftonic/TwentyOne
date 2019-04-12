package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.Weight;

public interface WeightAPICallBack extends RestAPICallBack {
    void onPostWeights(Weight weight);
}
