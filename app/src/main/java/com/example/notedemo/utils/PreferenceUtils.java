package com.example.notedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    private SharedPreferences sharedPreferences;
    private static final String USERNAME_KEY = "username key";
    private static PreferenceUtils instance;


    public static PreferenceUtils getInstance(Context context){
        if( instance == null){
            instance = new PreferenceUtils(context);
        }
        return  instance;
    }

    private PreferenceUtils(Context context) {

      sharedPreferences  =   context.getSharedPreferences("NoteDemo", Context.MODE_PRIVATE);

    }

    public void addOrUpdateUsername(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY, username);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(USERNAME_KEY, "");
    }
    public void deleteUsername(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USERNAME_KEY);
        editor.apply();
    }

}
