package com.example.project4;

import android.content.Context;
import android.content.SharedPreferences;

public class OnBroardingSharePreferences {
    private static final String BOARDING_SHARE_PREFERENCES = "BOARDING_SHARE_PREFERENCES";
    private Context mContext;

    OnBroardingSharePreferences(Context mContext){
        this.mContext = mContext;
    }
    public void putBooleanValue(String key, boolean value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(BOARDING_SHARE_PREFERENCES,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public  boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(BOARDING_SHARE_PREFERENCES,0);
        return sharedPreferences.getBoolean(key,false);
    }
}
