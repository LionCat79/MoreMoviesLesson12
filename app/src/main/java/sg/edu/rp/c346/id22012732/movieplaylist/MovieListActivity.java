package sg.edu.rp.c346.id22012732.movieplaylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Movie> movieList;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        listView = findViewById(R.id.listView);
        movieList = new ArrayList<>();
        dbHelper = new DBHelper(this);

        // Retrieve the movies from the database
        movieList = dbHelper.getAllMovies();

        // Create the CustomAdapter to adapt the movie information to the ListView
        CustomAdapter adapter = new CustomAdapter(this, R.layout.row, movieList);

        // Set the CustomAdapter as the adapter for the ListView
        listView.setAdapter(adapter);

        // Set the OnItemClickListener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked movie
                Movie clickedMovie = movieList.get(position);

                // Pass the clicked movie properties as extras in the Intent
                Intent intent = new Intent(MovieListActivity.this, MovieDetailsActivity.class);
                intent.putExtra("id", clickedMovie.getId()); // Pass the ID as well
                intent.putExtra("title", clickedMovie.getTitle());
                intent.putExtra("genre", clickedMovie.getGenre());
                intent.putExtra("year", clickedMovie.getYear());
                intent.putExtra("rating", clickedMovie.getRating());
                startActivity(intent);
            }
        });
    }

}