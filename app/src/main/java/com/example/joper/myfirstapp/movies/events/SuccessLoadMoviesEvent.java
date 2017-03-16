package com.example.joper.myfirstapp.movies.events;

import com.example.joper.myfirstapp.movies.models.Movies;

import java.util.ArrayList;

/**
 * Created by joper on 24/01/17.
 */

public class SuccessLoadMoviesEvent {
    private String message = "";
    // private ArrayList<Movies> arrayList = new ArrayList<>();

    public SuccessLoadMoviesEvent(String message) {
        this.message = message;
       // this.arrayList = arrayList;
    }

    public String getMessage() {
        return message;
    }

   /*public ArrayList<Movies> getArrayList() {
        return arrayList;
    }*/

    public void setMessage(String message) {
        this.message = message;
    }
}
