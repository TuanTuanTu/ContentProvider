package com.training.vungoctuan.sharedataproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.training.vungoctuan.sharedataproject.MovieContract;

/**
 * Created by vungoctuan on 2/23/18.
 */
public class MovieDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movieList.db";

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        addGenreTable(db);
        addMovieTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Inserts the genre table into the database.
     *
     * @param db The SQLiteDatabase the table is being inserted into.
     */
    private void addGenreTable(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + MovieContract.GenreEntry.TABLE_NAME + " (" +
                MovieContract.GenreEntry._ID + " INTEGER PRIMARY KEY, " +
                MovieContract.GenreEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL);"
        );
    }

    private void addMovieTable(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY, " +
                MovieContract.MovieEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_GENRE + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + MovieContract.MovieEntry.COLUMN_GENRE + ") " +
                "REFERENCES " + MovieContract.GenreEntry.TABLE_NAME + " (" + MovieContract.GenreEntry._ID + "));"
        );

    }
}
