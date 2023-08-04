package sg.edu.rp.c346.id22012732.movieplaylist;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private int resource;
    private ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> movieList) {
        super(context, resource, movieList);
        this.context = context;
        this.resource = resource;
        this.movieList = songList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
            holder = new ViewHolder();
            holder.textViewTitle = view.findViewById(R.id.textViewTitle);
            holder.textViewGenre = view.findViewById(R.id.textViewGenre);
            holder.textViewYear = view.findViewById(R.id.textViewGenre);
            holder.textViewRating = view.findViewById(R.id.textViewRating);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Get the Song object at the current position
        Movie movie = movieList.get(position);

        // Set the song details to the TextViews
        holder.textViewTitle.setText("Title: " + movie.getTitle());
        holder.textViewGenre.setText("Singers: " + movie.getGenre());
        holder.textViewYear.setText("Year: " + movie.getYear());
        holder.textViewStars.setText("Stars: " + getStarRating(movie.getRating()));

        return view;
    }

    private String getRating(String rating) {
        StringBuilder starRating = new StringBuilder();
        if (currentItem.getRating() == 'G') {
            ivGender.setImageResource(R.drawable.rating_g);
        } else if (currentItem.getRating() == 'PG'){
            ivGender.setImageResource(R.drawable.rating_pg);
        }


    // ViewHolder pattern to improve ListView performance
    private static class ViewHolder {
        TextView textViewTitle;
        TextView textViewGenre;
        TextView textViewYear;
        TextView textViewRating;
    }
}