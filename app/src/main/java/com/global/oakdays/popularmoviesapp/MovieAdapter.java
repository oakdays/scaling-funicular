package com.global.oakdays.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    ArrayList<Movie> movies;
    private Context context;

    MovieAdapter (Context context) {
        this.context = context;
        this.movies = new ArrayList<>();
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (MovieAdapter.ViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount () {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView moviePoster;

        ViewHolder (View v) {
            super(v);
            moviePoster = (ImageView) v.findViewById(R.id.movie_poster_pre);
        }

        void bind (final Movie item) {
            Picasso
                    .with(context)
                    .load(MainActivity.MOVIE_POSTER_BASE_URL + item.getPoster())
                    .into(moviePoster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick (View view) {
            Movie movie = movies.get(getLayoutPosition());

            Intent intent = new Intent(context, MovieActivity.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", movie);
            intent.putExtras(bundle);

            context.startActivity(intent);
        }
    }
}