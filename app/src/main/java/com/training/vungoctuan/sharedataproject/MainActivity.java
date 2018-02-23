package com.training.vungoctuan.sharedataproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.training.vungoctuan.sharedataproject.adapter.MovieAdapter;
import com.training.vungoctuan.sharedataproject.model.Movie;

import java.util.ArrayList;
import java.util.List;

import static com.training.vungoctuan.sharedataproject.MovieContract.MovieEntry;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnInsert;
    private RecyclerView rvMovie;
    private List<Movie> mMovies;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btnInsert);
        rvMovie = findViewById(R.id.rvMovie);
        mMovies = new ArrayList<>();
        insertDemoData();
        requestMovies();
        initListMovies();
    }

    private void insertDemoData() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Test
                ContentValues mValues = new ContentValues();
                mValues.put(MovieEntry.COLUMN_NAME, "Scale People");
                mValues.put(MovieEntry.COLUMN_GENRE, "Tuan Tuan Tu");
                mValues.put(MovieEntry.COLUMN_RELEASE_DATE, "20/03/1995");
                getContentResolver().insert(MovieEntry.CONTENT_URI, mValues);
                requestMovies();
                mAdapter.notifyItemInserted(mMovies.size() - 1);
            }
        });
    }

    //Init RecyclerView & Adapter
    private void initListMovies() {
        mAdapter = new MovieAdapter(mMovies, this);
        rvMovie.setAdapter(mAdapter);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
    }

    //Request query to get all movie from SQLite
    private void requestMovies() {
        mMovies.clear();
        String[] projection = new String[]{
            MovieEntry.COLUMN_NAME,
            MovieEntry.COLUMN_GENRE,
            MovieEntry.COLUMN_RELEASE_DATE};
        Cursor cursor = getContentResolver().query(
            MovieEntry.CONTENT_URI
            , projection
            , null
            , null
            , null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            Movie mMovie = new Movie(
                cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_GENRE)),
                cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_RELEASE_DATE))
            );
            mMovies.add(mMovie);
            cursor.moveToNext();
        }
        cursor.close();
    }
}
