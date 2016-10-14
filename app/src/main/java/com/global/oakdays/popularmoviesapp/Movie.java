package com.global.oakdays.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

class Movie implements Parcelable {
    private String poster;
    private String title;
    private String synopsis;
    private String releaseDate;
    private int voteAverage;

    Movie (String poster, String title, String synopsis, String releaseDate, int voteAverage) {
        this.poster = poster;
        this.title = title;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    private Movie (Parcel in) {
        poster = in.readString();
        title = in.readString();
        synopsis = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel (Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray (int size) {
            return new Movie[size];
        }
    };

    String getPoster () {
        return poster;
    }

    public void setPoster (String poster) {
        this.poster = poster;
    }

    String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    String getSynopsis () {
        return synopsis;
    }

    public void setSynopsis (String synopsis) {
        this.synopsis = synopsis;
    }

    String getReleaseDate () {
        return releaseDate;
    }

    public void setReleaseDate (String releaseDate) {
        this.releaseDate = releaseDate;
    }

    int getVoteAverage () {
        return voteAverage;
    }

    public void setVoteAverage (int voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel parcel, int i) {
        parcel.writeString(poster);
        parcel.writeString(title);
        parcel.writeString(synopsis);
        parcel.writeString(releaseDate);
        parcel.writeInt(voteAverage);
    }
}