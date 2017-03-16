package com.example.joper.myfirstapp.movies.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.joper.myfirstapp.movies.models.User;

/**
 * Created by joper on 26/01/17.
 */

public class SessionStateManager {

    private SharedPreferences sharedPreferences;

    public SessionStateManager (Context context){
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

    }

    public void saveSession(User user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_name", user.getUserName());
        editor.putString("user_lastName", user.getUserLastName());
        editor.putString("user_city", user.getUserCity());
        editor.putInt("user_age", user.getUserAge());

        editor.apply();
    }

    public User getCurrentUser(){
        String name = sharedPreferences.getString("user_name", "");
        String lastname = sharedPreferences.getString("user_lastName", "");
        String city = sharedPreferences.getString("user_city", "");
        int age = sharedPreferences.getInt("user_age", 0);

        User user = new User();
        user.setUserName(name);
        user.setUserLastName(lastname);
        user.setUserCity(city);
        user.setUserAge(age);
        return user;
    }

    public void logOut(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
