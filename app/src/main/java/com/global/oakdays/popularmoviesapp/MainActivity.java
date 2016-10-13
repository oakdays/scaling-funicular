package com.global.oakdays.popularmoviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static String MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500/";
    public static String FILTER = "popular";

    MovieAdapter movieAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        movieAdapter = new MovieAdapter(this);

        RecyclerView rvMovies = (RecyclerView) findViewById(R.id.list_movies);
        rvMovies.setLayoutManager(gridLayoutManager);
        rvMovies.setAdapter(movieAdapter);

        PopularMoviesTask popularMoviesTask = new PopularMoviesTask(movieAdapter);
        popularMoviesTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
            FILTER = "popular";
        } else if (id == R.id.action_top_rated) {
            FILTER = "top_rated";
        }

        PopularMoviesTask popularMoviesTask = new PopularMoviesTask(movieAdapter);
        popularMoviesTask.execute();

        return super.onOptionsItemSelected(item);
    }
}