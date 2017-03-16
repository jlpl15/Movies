package com.example.joper.myfirstapp.movies.events;

import com.example.joper.myfirstapp.movies.models.Movies;

import java.util.ArrayList;

/**
 * Created by joper on 25/01/17.
 */

public class SuccessMoviesDetails {

    private String message = "";
    private Movies movies;

    public SuccessMoviesDetails(String message, Movies movies) {
        this.message = message;
        this.movies = movies;
    }

    public String getMessage() {
        return message;
    }

    public Movies getMovie() {
        return movies;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
