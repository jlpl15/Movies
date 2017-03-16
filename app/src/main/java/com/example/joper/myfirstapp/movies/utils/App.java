package com.example.joper.myfirstapp.movies.utils;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.joper.myfirstapp.movies.interfaces.Api;
import com.google.gson.Gson;
import com.squareup.otto.Bus;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joper on 23/01/17.
 */

public class App extends Application {

    private static Gson gson;
    private static RequestQueue requestQueue;
    private static Bus bus;
    private static Realm realm;
    private static Api api;


    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();

        gson = new Gson();
        requestQueue = Volley.newRequestQueue(this);
        bus = new Bus();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(JSONKeys.KEY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

    }

    public static Gson getGson(){
        return  gson;
    }

    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public static Bus getBus(){
        return bus;
    }

    public static Realm getRealm() {
        return realm;
    }

    public static Api getApi(){
        return api;
    }
}
