package com.example.twentyone.restapi.callback;

import com.example.twentyone.model.data.UserPreferences;

public interface PreferencesAPICallBack extends RestAPICallBack {
    void onPostPreferences(UserPreferences userPreferences);
    void onGetPreferences(UserPreferences userPreferences);

    void onFailure(Throwable t);
}
