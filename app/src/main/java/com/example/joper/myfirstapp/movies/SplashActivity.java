package com.example.joper.myfirstapp.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.joper.myfirstapp.movies.events.SuccessLoadMoviesEvent;
import com.example.joper.myfirstapp.movies.utils.App;
import com.example.joper.myfirstapp.movies.utils.Controller;
import com.squareup.otto.Subscribe;

import java.util.ResourceBundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Controller.loadMoviesRetrofit();
    }

    @Override
    protected void onPause() {
        App.getBus().unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        App.getBus().register(this);
        super.onResume();
    }

    @Subscribe
    public void onSuccessLoadMovies (SuccessLoadMoviesEvent successLoadMoviesEvent){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
