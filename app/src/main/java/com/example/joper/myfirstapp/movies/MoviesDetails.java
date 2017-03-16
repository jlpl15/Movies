package com.example.joper.myfirstapp.movies;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.joper.myfirstapp.movies.events.SuccessMoviesDetails;
import com.example.joper.myfirstapp.movies.models.Movies;
import com.example.joper.myfirstapp.movies.utils.App;
import com.example.joper.myfirstapp.movies.utils.Controller;
import com.example.joper.myfirstapp.movies.utils.JSONKeys;
import com.google.gson.JsonObject;
import com.squareup.otto.Subscribe;

public class MoviesDetails extends AppCompatActivity {

    private ImageView detailsMovieImage;
    private TextView detailsMovieName;
    private TextView detailsMovieDescription;
    private TextView detailsMovieDirector;
    private TextView detailsMovieCategory;
    private int id;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

//        id = getIntent().getIntExtra("id", -1);
//        JsonObject jsonObject = new JsonObject();
//        JsonObject jsonObjectMovie = new JsonObject();
//        jsonObjectMovie.addProperty("id",id);
//        jsonObject.add("movie",jsonObjectMovie);
//        Controller.loadMovieDetailsRetrofit(jsonObject);

        Movies movie = App.getRealm().where(Movies.class).equalTo("movieId", id).findFirst();

        detailsMovieImage = (ImageView) findViewById(R.id.imageView_moviedetails_image);
        detailsMovieName = (TextView) findViewById(R.id.textView_moviedetails_name);
        detailsMovieDescription = (TextView) findViewById(R.id.textView_moviedetails_description);
        detailsMovieDirector = (TextView) findViewById(R.id.textView_moviedetails_director);
        detailsMovieCategory = (TextView) findViewById(R.id.textView_moviedetails_category);



        Glide.with(this)
                .load(JSONKeys.KEY_BASE_URL + movie.getMovieImage())
                .into((detailsMovieImage));
        detailsMovieName .setText("Nombre de la pelicula: \n" + movie.getMovieName());
        detailsMovieDescription.setText("Descripción: \n" + movie.getMovieDescription());
        detailsMovieDirector.setText("Director: \n" + movie.getMovieDirector());
        detailsMovieCategory.setText("Categoria: \n" + movie.getMovieCategory());


      /*  Controller.loadMoviesDetails(id);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Cargando datos de la pelicula");
        progressDialog.show();
        */

    }

    @Override
    protected void onResume() {
        App.getBus().register(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        App.getBus().unregister(this);
        super.onPause();
    }

    @Subscribe
    public void onSuccessDetailsMovie(SuccessMoviesDetails successMoviesDetails){
        progressDialog.dismiss();
       /* Movies movie = successMoviesDetails.getMovie();
        Glide.with(this)
                .load(JSONKeys.KEY_URL_SERVER + movie.getMovieImage())
                .into((detailsMovieImage));
        detailsMovieName .setText("Nombre de la pelicula: \n" + movie.getMovieName());
        detailsMovieDescription.setText("Descripción: \n" + movie.getMovieDescription());
        detailsMovieDirector.setText("Director: \n" + movie.getMovieDirector());
        detailsMovieCategory.setText("Categoria: \n" + movie.getMovieCategory());
        */
    }
}
