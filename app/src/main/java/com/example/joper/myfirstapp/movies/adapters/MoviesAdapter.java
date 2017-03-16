package com.example.joper.myfirstapp.movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.joper.myfirstapp.movies.R;
import com.example.joper.myfirstapp.movies.models.Movies;
import com.example.joper.myfirstapp.movies.utils.JSONKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joper on 24/01/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    public interface MoviesAdapterItemCallBacks{
        public void OnMoviesItemSelected(Movies movies);
    }

    private MoviesAdapterItemCallBacks moviesAdapterItemCallBacks;
    private List<Movies> items;
    private Context context;

    public MoviesAdapter(List<Movies> items, Context context){
        this.items = items;
        this.context = context;
        this.moviesAdapterItemCallBacks = (MoviesAdapterItemCallBacks)context;
    }

    @Override
    public MoviesAdapter.MoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MoviesAdapterViewHolder holder, final int position) {
        holder.textViewMovieName.setText(items.get(position).getMovieName());

        Glide.with(context)
                .load(JSONKeys.KEY_BASE_URL+ items.get(position).getMovieImage())
                .into(holder.imageViewMovieImage);

        holder.clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesAdapterItemCallBacks.OnMoviesItemSelected(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MoviesAdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewMovieImage;
        TextView textViewMovieName;
        View clickView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewMovieImage = (ImageView) itemView.findViewById(R.id.imageView_movie_image);
            textViewMovieName = (TextView) itemView.findViewById(R.id.textView_movie_name);
            clickView = itemView.findViewById(R.id.click_view_movie);
        }
    }
}
