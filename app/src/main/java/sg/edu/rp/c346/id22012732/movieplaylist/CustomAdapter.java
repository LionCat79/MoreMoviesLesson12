package sg.edu.rp.c346.id22012732.movieplaylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private int resource;
    private ArrayList<Movie> movieList;
    private Map<String, Integer> ratingImages;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> movieList) {
        super(context, resource, movieList);
        this.context = context;
        this.resource = resource;
        this.movieList = movieList;

        // Initialize a mapping of rating to image resource
        ratingImages = new HashMap<>();
        ratingImages.put("G", R.drawable.rating_g);
        ratingImages.put("PG", R.drawable.rating_pg);
        ratingImages.put("PG13", R.drawable.rating_pg13);
        ratingImages.put("NC16", R.drawable.rating_nc16);
        ratingImages.put("M18", R.drawable.rating_m18);
        ratingImages.put("R21", R.drawable.rating_r21);
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
            holder.textViewYear = view.findViewById(R.id.textViewYear);
            holder.imageViewRating = view.findViewById(R.id.imageViewRating); // Add ImageView reference
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Get the Movie object at the current position
        Movie movie = movieList.get(position);

        // Set the movie details to the TextViews
        holder.textViewTitle.setText("Title: " + movie.getTitle());
        holder.textViewGenre.setText("Genre: " + movie.getGenre());
        holder.textViewYear.setText("Year: " + movie.getYear());

        // Set the rating image based on the rating value
        if (ratingImages.containsKey(movie.getRating())) {
            int ratingImageResource = ratingImages.get(movie.getRating());
            holder.imageViewRating.setImageResource(ratingImageResource);
        }

        return view;
    }

    // ViewHolder pattern to improve ListView performance
    private static class ViewHolder {
        TextView textViewTitle;
        TextView textViewGenre;
        TextView textViewYear;
        ImageView imageViewRating;
    }
}