package com.example.twentyone.restapi.BloodWheightPointApiSons;

import android.util.Log;

import com.example.twentyone.model.data.BloodPressure;
import com.example.twentyone.model.data.Preferences;
import com.example.twentyone.model.data.UserPreferences;
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

public class PreferencesManager extends BloodWeightPointDataApiManager {
    public PreferencesManager(UserToken userToken, RestAPIService restApiService) {
        super(userToken, restApiService);
    }

    /*
    protected synchronized void getAll(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack, final int level){
        Log.d("LRM", "all points GET request");

        Map<String,String> data = new HashMap<>();
        data.put("page",String.valueOf(level));
        Call<UserPreferences[]> call = restApiService.getAllPreferences("Bearer " + userToken.getIdToken(),data);
        call.enqueue(new Callback<UserPreferences[]>() {
            @Override
            public void onResponse(Call<UserPreferences[]> call, Response<UserPreferences[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length>0){
                        genList.addAll(Arrays.asList(response.body()));
                        getAll(bwpgpsdapiCallBack,level+1);
                    }
                    else{
                        Preferences[] preferences = new Preferences[1];
                        bwpgpsdapiCallBack.onFinishedCallback(preferences);
                    }
                } else {
                    bwpgpsdapiCallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<UserPreferences[]> call, Throwable t) {
                bwpgpsdapiCallBack.onFailure(t);
            }
        });

    }

    protected synchronized void getAllByUser(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack, final int level){
        Log.d("LRM", "all points GET request");
        //http://android.byted.xyz/api/points?page=1000&paged=true&sort.sorted=false&sort.unsorted=true
        Map<String, String> map = new HashMap<>();
        map.put("query","SELECT * FROM PREFERENCES WHERE USER_ID = "+userToken.getIdToken());
        map.put("page",String.valueOf(level));

        Call<UserPreferences[]> call =  restApiService.getAllPreferencesByUser("Bearer " + userToken.getIdToken(),map);
        call.enqueue(new Callback<UserPreferences[]>() {
            @Override
            public void onResponse(Call<UserPreferences[]> call, Response<UserPreferences[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length>0){
                        if(response.body()[response.body().length-1].getUser().getId()!=Integer.parseInt(userToken.getIdToken())){
                            for(UserPreferences up : response.body()){
                                if(up.getUser().getId()==Integer.parseInt(userToken.getIdToken())){
                                    getGenListByUser.add(up);
                                    continue;
                                }
                                break;
                            }
                            Preferences[] preferences = new Preferences[1];
                            bwpgpsdapiCallBack.onFinishedCallback(preferences);
                            return;
                        }
                        getGenListByUser.addAll(Arrays.asList(response.body()));
                        getAllByUser(bwpgpsdapiCallBack,level+1);
                    }
                    else{
                        Preferences[] preferences = new Preferences[1];
                        bwpgpsdapiCallBack.onFinishedCallback(preferences);
                    }

                } else {
                    bwpgpsdapiCallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<UserPreferences[]> call, Throwable t) {
                bwpgpsdapiCallBack.onFailure(t);
            }
        });
    }

    protected synchronized void getAllByUser(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack, final int level, String search){
        Log.d("LRM", "all points GET request");
        //http://android.byted.xyz/api/points?page=1000&paged=true&sort.sorted=false&sort.unsorted=true
        Map<String, String> map = new HashMap<>();
        map.put("query","SELECT * FROM BLOOD_PRESSURE WHERE USER_ID = "+userToken.getIdToken()+ "AND (WEEKLY_GOAL like '%"
                +search+"%' OR WEIGHT_UNITS like '%"+search+"%')");
        map.put("page",String.valueOf(level));

        Call<UserPreferences[]> call =  restApiService.getAllPreferencesByUser("Bearer " + userToken.getIdToken(),map);
        call.enqueue(new Callback<UserPreferences[]>() {
            @Override
            public void onResponse(Call<UserPreferences[]> call, Response<UserPreferences[]> response) {
                if (response.isSuccessful()) {
                    if(response.body().length>0){
                        if(response.body()[response.body().length-1].getUser().getId()!=Integer.parseInt(userToken.getIdToken())){
                            for(UserPreferences up : response.body()){
                                if(up.getUser().getId()==Integer.parseInt(userToken.getIdToken())){
                                    getGenListByUser.add(up);
                                    continue;
                                }
                                break;
                            }
                            Preferences[] preferences = new Preferences[1];
                            bwpgpsdapiCallBack.onFinishedCallback(preferences);
                            return;
                        }
                        getGenListByUser.addAll(Arrays.asList(response.body()));
                        getAllByUser(bwpgpsdapiCallBack,level+1);
                    }
                    else{
                        Preferences[] preferences = new Preferences[1];
                        bwpgpsdapiCallBack.onFinishedCallback(preferences);
                    }

                } else {
                    bwpgpsdapiCallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<UserPreferences[]> call, Throwable t) {
                bwpgpsdapiCallBack.onFailure(t);
            }
        });
    }
    */
}
