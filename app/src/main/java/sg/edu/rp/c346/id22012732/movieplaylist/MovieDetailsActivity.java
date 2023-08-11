package sg.edu.rp.c346.id22012732.movieplaylist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

    public class MovieDetailsActivity extends AppCompatActivity {

        private EditText titleEditText;
        private EditText genreEditText;
        private EditText yearEditText;
        private Button updateButton;
        private Button deleteButton;
        private Button cancelButton;
        private Movie movie;
        private DBHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_movie_details);

            titleEditText = findViewById(R.id.editTextTitle);
            genreEditText = findViewById(R.id.editTextGenre);
            yearEditText = findViewById(R.id.editTextYear);
            updateButton = findViewById(R.id.btnUpdate);
            deleteButton = findViewById(R.id.btnDelete);
            cancelButton = findViewById(R.id.btnCancel);
            dbHelper = new DBHelper(this);

            // Get the clicked song from the intent
            String title = getIntent().getStringExtra("title");
            String genre = getIntent().getStringExtra("genre");
            int year = getIntent().getIntExtra("year", 0);
            int stars = getIntent().getIntExtra("stars", 0);

            // Display the song details in the EditText fields
            titleEditText.setText(title);
            genreEditText.setText(genre);
            yearEditText.setText(String.valueOf(year));


            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the clicked song from the intent
                    String title = getIntent().getStringExtra("title");
                    String genre = getIntent().getStringExtra("genre");
                    int year = getIntent().getIntExtra("year", 0);
                    String rating = getIntent().getStringExtra("rating");

                    // Initialize the song object with the retrieved data
                    movie = new Movie(title, genre, year, rating);

                    // Update the song details based on the edited values
                    movie.setTitle(titleEditText.getText().toString());
                    movie.setGenre(genreEditText.getText().toString());
                    movie.setYear(Integer.parseInt(yearEditText.getText().toString()));

                    // Update the song record in the database
                    boolean isUpdated = dbHelper.updateMovie(movie);

                    if (isUpdated) {
                        Toast.makeText(MovieDetailsActivity.this, "Movie updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MovieDetailsActivity.this, "Failed to update movie", Toast.LENGTH_SHORT).show();
                    }

                    // Finish the activity and go back to the previous activity
                    finish();
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the clicked song details from the intent
                    String title = getIntent().getStringExtra("title");
                    String genre = getIntent().getStringExtra("genre");
                    int year = getIntent().getIntExtra("year", 0);
                    String rating = getIntent().getStringExtra("rating");
                    int id = getIntent().getIntExtra("id", 0);


                    movie = new Movie(title, genre, year, rating);
                    movie.setId(id);

                    // Delete the song record from the database
                    boolean isDeleted = dbHelper.deleteMovie(movie);
                    Log.d("MovieDetailsActivity", "Song deleted: " + isDeleted);
                    if (isDeleted) {
                        Toast.makeText(MovieDetailsActivity.this, "Movie deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MovieDetailsActivity.this, "Failed to delete movie", Toast.LENGTH_SHORT).show();
                    }

                    // Finish the activity and go back to the previous activity
                    finish();
                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finish the activity and go back to the previous activity (SongListActivity)
                    finish();
                }
            });
        }
    }