package sg.edu.rp.c346.id22012732.movieplaylist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText movieTitleEditText;
    EditText genreEditText;
    EditText releaseYearText;
    RadioGroup ratings;
    Button insertButton;
    Button displayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieTitleEditText = findViewById(R.id.editTextTitle);
        genreEditText = findViewById(R.id.editTextGenre);
        releaseYearText = findViewById(R.id.editTextYear);
        ratings = findViewById(R.id.rating);
        insertButton = findViewById(R.id.btnUpdate);
        displayButton = findViewById(R.id.btnDelete);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = ratings.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedRating = selectedRadioButton.getText().toString(); // Change variable name to selectedRating

                // Get the entered movie title and genre
                String movieTitle = movieTitleEditText.getText().toString();
                String genre = genreEditText.getText().toString();
                int year = Integer.parseInt(releaseYearText.getText().toString());

                // Insert the movie record into the database
                insertMovieRecord(movieTitle, genre, year, selectedRating);

                // Clear the input fields after insertion
                movieTitleEditText.setText("");
                genreEditText.setText("");
                releaseYearText.setText("");
            }
        });

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the DisplayActivity to show the records
                Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertSongRecord(String movieTitle, String genre, int year, String rating) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_TITLE, movieTitle);
        values.put(DBHelper.COLUMN_SINGERS, genre);
        values.put(DBHelper.COLUMN_YEAR, year);
        values.put(DBHelper.COLUMN_STARS, rating);

        long result = db.insert(DBHelper.TABLE_MOVIE, null, values);
        db.close();
    }
}