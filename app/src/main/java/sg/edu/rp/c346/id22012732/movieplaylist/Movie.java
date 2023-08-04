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

    public class Movie {
        private int id;
        private String title;
        private String genre;
        private int year;
        private String rating;

        public Movie(String title, String genre, int year, String rating) {
            this.title = title;
            this.genre = genre;
            this.year = year;
            this.rating = rating;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getRating() {
            return rating;
        }

        public void setId(int id) {
            this.id = id;
        }


        public void setRating(String rating) {
            this.rating = rating;
        }
    }
}