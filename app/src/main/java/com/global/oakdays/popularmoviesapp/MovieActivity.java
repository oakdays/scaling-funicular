package com.global.oakdays.popularmoviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView tvPoster = (ImageView) findViewById(R.id.movie_poster);
        TextView tvTitle = (TextView) findViewById(R.id.movie_title);
        TextView tvReleaseDate = (TextView) findViewById(R.id.movie_release_date);
        TextView tvOverview = (TextView) findViewById(R.id.movie_overview);
        TextView tvRating = (TextView) findViewById(R.id.movie_vote_count);

        Movie thisMovie = getIntent().getExtras().getParcelable("movie");

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");

        Date date = null;
        try {
            date = inputFormat.parse(thisMovie.getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String outputDateStr = outputFormat.format(date);

        Picasso.with(this).load(MainActivity.MOVIE_POSTER_BASE_URL + thisMovie.getPoster()).into(tvPoster);
        tvTitle.setText(thisMovie.getTitle());
        tvReleaseDate.setText(outputDateStr);
        tvOverview.setText(thisMovie.getSynopsis());
        tvRating.setText("Rating: " + thisMovie.getVoteAverage() + "/10");
    }
}
