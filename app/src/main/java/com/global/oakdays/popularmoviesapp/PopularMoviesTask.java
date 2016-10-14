package com.global.oakdays.popularmoviesapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class PopularMoviesTask extends AsyncTask<String, Void, String> {

    private final String LOG_TAG = PopularMoviesTask.class.getSimpleName();

    private MovieAdapter mAdapter;

    PopularMoviesTask (MovieAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    protected String doInBackground (String... params) {

        mAdapter.movies.clear();

        String BASE_URL = "https://api.themoviedb.org/3/movie/" + params[0] + "?";
        String API_KEY_PARAM = "api_key";
        String LANGUAGE_PARAM = "language";

        String API_KEY = "";
        String LANGUAGE = "en-US";

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .appendQueryParameter(LANGUAGE_PARAM, LANGUAGE)
                .build();

        try {
            URL url = new URL(builtUri.toString());

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            String jsonStr = buffer.toString();
            Log.v(LOG_TAG, "Result: " + jsonStr);

            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute (String jsonStr) {
        getMoviesFromJson(jsonStr);
    }

    private void getMoviesFromJson (String jsonStr) {
        try {
            JSONObject moviesObject = new JSONObject(jsonStr);
            JSONArray moviesArray = moviesObject.getJSONArray("results");

            for (int i = 0; i < moviesArray.length(); i++) {
                JSONObject thisMovie = moviesArray.getJSONObject(i);
                Movie movie = new Movie(
                        thisMovie.getString("poster_path"),
                        thisMovie.getString("original_title"),
                        thisMovie.getString("overview"),
                        thisMovie.getString("release_date"),
                        thisMovie.getInt("vote_average"));

                mAdapter.movies.add(movie);
                mAdapter.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}