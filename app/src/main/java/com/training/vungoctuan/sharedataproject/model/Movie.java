package com.training.vungoctuan.sharedataproject.model;

/**
 * Created by vungoctuan on 2/23/18.
 */
public class Movie {
    private String mName;
    private String mGenre;
    private String mReleaseDate;

    public Movie(String mName, String mGenre, String mReleaseDate) {
        this.mName = mName;
        this.mGenre = mGenre;
        this.mReleaseDate = mReleaseDate;
    }

    public String getmName() {
        return mName;
    }

    public String getmGenre() {
        return mGenre;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }
}
