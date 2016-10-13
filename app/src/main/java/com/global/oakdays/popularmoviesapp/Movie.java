package com.global.oakdays.popularmoviesapp;

import java.io.Serializable;

public class Movie implements Serializable {
    private String poster;
    private String title;
    private String synopsis;
    private String releaseDate;
    private int voteAverage;

    public Movie (String poster, String title, String synopsis, String releaseDate, int voteAverage) {
        this.poster = poster;
        this.title = title;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public String getPoster () {
        return poster;
    }

    public void setPoster (String poster) {
        this.poster = poster;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getSynopsis () {
        return synopsis;
    }

    public void setSynopsis (String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate () {
        return releaseDate;
    }

    public void setReleaseDate (String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getVoteAverage () {
        return voteAverage;
    }

    public void setVoteAverage (int voteCount) {
        this.voteAverage = voteAverage;
    }
}