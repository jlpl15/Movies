package com.example.joper.myfirstapp.movies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.joper.myfirstapp.movies.adapters.MoviesAdapter;
import com.example.joper.myfirstapp.movies.events.SuccessLoadMoviesEvent;
import com.example.joper.myfirstapp.movies.models.Movies;
import com.example.joper.myfirstapp.movies.models.User;
import com.example.joper.myfirstapp.movies.utils.App;
import com.example.joper.myfirstapp.movies.utils.Controller;
import com.example.joper.myfirstapp.movies.utils.SessionStateManager;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterItemCallBacks{

    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private ArrayList<Movies> moviesArrayList = new ArrayList<>();
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        /*progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Cargando todas las peliculas");
        progressDialog.show();*/

        //Controller.loadMovies();

        SessionStateManager ssm = new SessionStateManager(MainActivity.this);
        User user = ssm.getCurrentUser();

        Toast.makeText(MainActivity.this, user.getUserName(), Toast.LENGTH_LONG).show();

        List<Movies> realmResults = App.getRealm().where(Movies.class).findAll();
        moviesAdapter = new MoviesAdapter(realmResults, MainActivity.this);
        recyclerView.setAdapter(moviesAdapter);

    }



    @Override
    public void OnMoviesItemSelected(Movies movies) {
        Intent intent = new Intent(MainActivity.this, MoviesDetails.class);
        intent.putExtra("id",movies.getMovieId());
        startActivity(intent);
    }



}
