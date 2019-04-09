package com.example.twentyone.restapi.BloodWheightPointApiSons;

import android.util.Log;

import com.example.twentyone.model.data.BloodPressure;
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

public class BloodManager extends BloodWeightPointDataApiManager {
    public BloodManager(UserToken userToken, RestAPIService restApiService) {
        super(userToken, restApiService);
    }

    protected synchronized void getAll(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack, final int level){
        Log.d("LRM", "all points GET request");

        Map<String,String> data = new HashMap<>();
        data.put("page",String.valueOf(level));
        Call<BloodPressure[]> call = restApiService.getAllBloodPressure("Bearer " + userToken.getIdToken(),data);
        call.enqueue(new Callback<BloodPressure[]>() {
            @Override
            public void onResponse(Call<BloodPressure[]> call, Response<BloodPressure[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length>0){
                        genList.addAll(Arrays.asList(response.body()));
                        getAll(bwpgpsdapiCallBack,level+1);
                    }
                    else{
                        bwpgpsdapiCallBack.onFinishedCallback(genList);
                    }
                } else {
                    bwpgpsdapiCallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<BloodPressure[]> call, Throwable t) {
                bwpgpsdapiCallBack.onFailure(t);
            }
        });

    }

    protected synchronized void getAllByUser(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack, final int level){
        Log.d("LRM", "all points GET request");
        //http://android.byted.xyz/api/points?page=1000&paged=true&sort.sorted=false&sort.unsorted=true
        Map<String, String> map = new HashMap<>();
        map.put("query","SELECT * FROM BLOOD_PRESSURE WHERE USER_ID = "+userToken.getIdToken());
        map.put("page",String.valueOf(level));

        Call<BloodPressure[]> call =  restApiService.getAllBloodPressureByUser("Bearer " + userToken.getIdToken(),map);
        call.enqueue(new Callback<BloodPressure[]>() {
            @Override
            public void onResponse(Call<BloodPressure[]> call, Response<BloodPressure[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length>0){
                        if(response.body()[response.body().length-1].getUser().getId()!=Integer.parseInt(userToken.getIdToken())){
                            for(BloodPressure b : response.body()){
                                if(b.getUser().getId()==Integer.parseInt(userToken.getIdToken())){
                                    getGenListByUser.add(b);
                                    continue;
                                }
                                break;
                            }
                            bwpgpsdapiCallBack.onFinishedCallback(getGenListByUser);
                            return;
                        }
                        getGenListByUser.addAll(Arrays.asList(response.body()));
                        getAllByUser(bwpgpsdapiCallBack,level+1);
                    }
                    else{
                        bwpgpsdapiCallBack.onFinishedCallback(getGenListByUser);
                    }

                } else {
                    bwpgpsdapiCallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<BloodPressure[]> call, Throwable t) {
                bwpgpsdapiCallBack.onFailure(t);
            }
        });
    }
}
