package com.example.twentyone.restapi;

import com.example.twentyone.model.data.UserToken;
import com.example.twentyone.restapi.callback.BloodWeightPointsGPSDAPICallBack;

import java.util.LinkedList;
import java.util.List;

public class BloodWeightPointDataApiManager {


    protected UserToken userToken;
    protected RestAPIService restApiService;
    protected List<Object> genList,getGenListByUser;


    public BloodWeightPointDataApiManager(UserToken userToken, RestAPIService restApiService){
        this.userToken = userToken;
        this.restApiService = restApiService;
    }
    /*

    public synchronized void getAll(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack){
        genList = new LinkedList<>();
        getAll(bwpgpsdapiCallBack,0);
    }

    protected synchronized void getAll(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack,final int level){
    }

    public synchronized void getAllByUser(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack){
        getGenListByUser = new LinkedList<>();
        getAllByUser(bwpgpsdapiCallBack,0);
    }
    protected synchronized void getAllByUser(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack, final int level) {
    }

    public synchronized void getAllByUser(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack,String search){
        getGenListByUser = new LinkedList<>();
        getAllByUser(bwpgpsdapiCallBack,0,search);
    }
    protected synchronized void getAllByUser(final BloodWeightPointsGPSDAPICallBack bwpgpsdapiCallBack, final int level,final String search){

    }
    */
}
