package com.example.joper.myfirstapp.movies.models;

import com.example.joper.myfirstapp.movies.utils.JSONKeys;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by joper on 23/01/17.
 */

public class Movies extends RealmObject implements Serializable {
    @PrimaryKey
    @SerializedName(JSONKeys.KEY_ID)
    int movieId;
    @SerializedName(JSONKeys.KEY_NAME)
    String movieName;
    @SerializedName(JSONKeys.KEY_DESCRIPTION)
    String movieDescription;
    @SerializedName(JSONKeys.KEY_DIRECTOR)
    String movieDirector;
    @SerializedName(JSONKeys.KEY_CATEGORY)
    String movieCategory;
    @SerializedName(JSONKeys.KEY_IMAGE)
    String movieImage;

    public Movies() {
        this.movieId = 0;
        this.movieName = "";
        this.movieDescription = "";
        this.movieDirector = "";
        this.movieCategory = "";
        this.movieImage = "";
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }


}
