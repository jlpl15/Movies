package com.example.joper.myfirstapp.movies.interfaces;

import com.example.joper.myfirstapp.movies.models.Movies;
import com.example.joper.myfirstapp.movies.utils.JSONKeys;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * Created by 01caffeinelabs on 3/15/17.
 */

public interface Api {

    @GET(JSONKeys.KEY_URL_ALL_MOVIES)
    Call<JsonObject> loadmovies();


    @POST(JSONKeys.KEY_URL_MOVIE_DETAILS)
    Call<JsonObject> movieDeails(@Body JsonObject jsonObject);
}
