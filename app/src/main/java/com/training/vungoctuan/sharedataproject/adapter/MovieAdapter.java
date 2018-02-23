package com.training.vungoctuan.sharedataproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.training.vungoctuan.sharedataproject.R;
import com.training.vungoctuan.sharedataproject.model.Movie;

import java.util.List;

/**
 * Created by vungoctuan on 2/23/18.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private Context mContext;

    public MovieAdapter(List<Movie> mMovies, Context mContext) {
        this.mMovies = mMovies;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView = inflater.inflate(R.layout.item_movie,parent,false);
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        TextView nameTextView = holder.nameTextView;
        TextView genreTextView = holder.genreTextView;
        TextView releaseDateTextView = holder.releaseDateTextView;
        nameTextView.setText(movie.getmName());
        genreTextView.setText(movie.getmGenre());
        releaseDateTextView.setText(movie.getmReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public Context getmContext() {
        return mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView, genreTextView, releaseDateTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.movie_name);
            genreTextView = itemView.findViewById(R.id.movie_genre);
            releaseDateTextView = itemView.findViewById(R.id.movie_release_date);
        }
    }
}
