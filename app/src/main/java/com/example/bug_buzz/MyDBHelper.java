package com.example.bug_buzz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "Main.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Players";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "player_username";
    private static final String COLUMN_HIGHSCORE = "player_highscore";


    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    /**
     * Create the database table with 3 columns:
     * 1)id (primary key)
     * 2)player username
     * 3)current highscore on the game
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USERNAME + " TEXT, " +
                        COLUMN_HIGHSCORE + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Function that reads all the data from the database
     * @return a cursor object with all the columns of the database in it
     */
    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /**
     * function that adds a new player in the database
     * @param username add a new player in the database with the given username
     */
    void addPlayer(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //no need to add a new id as it is autoincrement
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_HIGHSCORE, 0);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed to add player", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * function used to delete a player from the database
     * @param username delete a player with the given username
     * @return true if the player has successfully deleted
     */
    boolean deletePlayer(String username){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, COLUMN_USERNAME + "=?",new String[]{username}) > 0;
    }

    /**
     * Function that updates the current score of the player if he/she made a new highscore
     * @param username current player's username
     * @param newScore player's new score
     */
    void updatePlayerScore(String username, String newScore){
        SQLiteDatabase db = this.getWritableDatabase();
        //sql query to take the current score of the player with the given username
        String query = "SELECT " + COLUMN_HIGHSCORE + " FROM " + TABLE_NAME + " where instr(" + COLUMN_USERNAME + ", ?)";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        String currentPlayerScore = "";
        if (db != null){
            cursor.moveToFirst();
            currentPlayerScore = cursor.getString(0);
            ContentValues cv = new ContentValues();
            //if the player's new score is bigger than the current one then update it
            if(Integer.parseInt(newScore) > Integer.parseInt(currentPlayerScore)){
                cv.put(COLUMN_HIGHSCORE, newScore);
                db.update(TABLE_NAME,cv, COLUMN_USERNAME + "=?", new String[]{username});
            }
        }

    }



}
