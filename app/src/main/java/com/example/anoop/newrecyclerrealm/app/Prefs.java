package com.example.anoop.newrecyclerrealm.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by anoop on 08/11/2016.
 */

public class Prefs {
    private static final String PRE_LOAD = "preload";
    private static final String PREFS_NAME = "prefs";
    private static Prefs instance;
    private final SharedPreferences sharedPreferences;

    public Prefs(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, context.MODE_PRIVATE);
    }

    public static Prefs with(Context context){
        if(instance==null){
            instance=new Prefs(context);
        }
        return instance;
    }

    public void setPreLoad(boolean totalTime){
        sharedPreferences
                .edit()
                .putBoolean(PRE_LOAD, totalTime)
                .apply();
    }

    public boolean getPreLoad(){
        return sharedPreferences.getBoolean(PRE_LOAD, false);
    }

}
