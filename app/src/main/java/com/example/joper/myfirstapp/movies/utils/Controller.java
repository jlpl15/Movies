package com.example.joper.myfirstapp.movies.utils;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.joper.myfirstapp.movies.events.SuccessLoadMoviesEvent;
import com.example.joper.myfirstapp.movies.events.SuccessMoviesDetails;
import com.example.joper.myfirstapp.movies.interfaces.Api;
import com.example.joper.myfirstapp.movies.models.Movies;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joper on 24/01/17.
 */

public class Controller {

    public static void loadMoviesRetrofit(){

         Call<JsonObject> call = App.getApi().loadmovies();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.e("request", response.body().toString());
                JsonElement jsonarray1 = response.body().get(JSONKeys.KEY_MOVIE);
                JsonArray jsonArray = jsonarray1.getAsJsonArray();
                for (int i = 0; i <jsonArray.size() ; i++) {
                    JsonObject json = (JsonObject) jsonArray.get(i);
                    Movies movie = App.getGson().fromJson(json.toString(), Movies.class);
                    App.getRealm().beginTransaction();
                    App.getRealm().copyToRealmOrUpdate(movie);
                    App.getRealm().commitTransaction();
                    //  arrayList.add(movie);
                    Log.e("Agregando pelicula", movie.getMovieName());
                }
                App.getBus().post(new SuccessLoadMoviesEvent("Peliculas agregadas"));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
    public static void loadMovies(){
        final ArrayList<Movies> arrayList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,
                JSONKeys.KEY_URL_ALL_MOVIES, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(JSONKeys.KEY_MOVIE);
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject jsonOnject = jsonArray.getJSONObject(i);
                        Movies movie = App.getGson().fromJson(jsonOnject.toString(), Movies.class);
                        App.getRealm().beginTransaction();
                        App.getRealm().copyToRealmOrUpdate(movie);
                        App.getRealm().commitTransaction();
                      //  arrayList.add(movie);
                        Log.e("Agregando pelicula", movie.getMovieName());
                    }
                    App.getBus().post(new SuccessLoadMoviesEvent("Peliculas agregadas"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error en la peticion", error.toString());
            }
        });

        App.getRequestQueue().add(jsonObjectRequest);
    }
    
    public static void loadMovieDetailsRetrofit(JsonObject jsonObject){
        Call<JsonObject> call = App.getApi().movieDeails(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.e("Details response",response.body().toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("error response",t.toString());
            }
        });
    }

    public static void loadMoviesDetails(int id){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectMovie = new JSONObject();
        try {
            jsonObjectMovie.put(JSONKeys.KEY_ID, id);
            jsonObject.put(JSONKeys.KEY_MOVIE, jsonObjectMovie);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    JSONKeys.KEY_URL_MOVIE_DETAILS, jsonObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Response Detail", response.toString());
                    try {
                        JSONObject  jsonObjectMovie = response.getJSONObject(JSONKeys.KEY_MOVIE);
                        Movies movie = App.getGson().fromJson(jsonObjectMovie.toString(), Movies.class);
                        App.getBus().post(new SuccessMoviesDetails("Movie Details", movie));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volleyerror MovieDetail", error.toString());

                }
            });
            App.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
