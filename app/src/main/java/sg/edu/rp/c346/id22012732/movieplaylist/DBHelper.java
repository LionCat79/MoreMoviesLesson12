package sg.edu.rp.c346.id22012732.movieplaylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movie.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_MOVIE = "movie";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_RATING + " TEXT)";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }

    public long insertMovie(String title, String genre, int year, String rating) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_MOVIE, null, values);
        db.close();
        return result;
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOVIE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
            int genreIndex = cursor.getColumnIndex(COLUMN_GENRE);
            int yearIndex = cursor.getColumnIndex(COLUMN_YEAR);
            int ratingIndex = cursor.getColumnIndex(COLUMN_RATING);

            do {
                String title = cursor.getString(titleIndex);
                String genre = cursor.getString(genreIndex);
                int year = cursor.getInt(yearIndex);
                String rating = cursor.getString(ratingIndex);
                Movie movie = new Movie(title, genre, year, rating);
                movieList.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movieList;
    }

    public boolean updateMovie(Movie movie) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_YEAR, movie.getYear());
        values.put(COLUMN_RATING, movie.getRating());

        int rowsAffected = db.update(TABLE_MOVIE, values, COLUMN_ID + "=?", new String[]{String.valueOf(movie.getId())});
        db.close();
        return rowsAffected > 0;
    }

    public boolean deleteMovie(Movie movie) {
        SQLiteDatabase db = getWritableDatabase();
        String[] whereArgs = {String.valueOf(movie.getId())};
        int rowsAffected = db.delete(TABLE_MOVIE, COLUMN_ID + "=?", whereArgs);
        Log.d("DBHelper", "Rows affected: " + rowsAffected + ", ID: " + movie.getId());
        db.close();
        return rowsAffected > 0;
    }
}