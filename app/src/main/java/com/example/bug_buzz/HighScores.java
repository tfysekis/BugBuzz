package com.example.bug_buzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class HighScores extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapterInScores customAdapter;
    protected ArrayList<String> player_id, player_username, player_highscore;

    ImageView emptyImageView;
    TextView noData;
    MyDBHelper Players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        Players = new MyDBHelper(HighScores.this);
        recyclerView = findViewById(R.id.recycler_view_in_scores);
        emptyImageView = findViewById(R.id.image_view_no_users_scores);
        noData = findViewById(R.id.no_users_text_view_scores);

        player_id = new ArrayList<>();
        player_username = new ArrayList<>();
        player_highscore = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapterInScores(HighScores.this, player_username, player_highscore);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HighScores.this));


    }

    void storeDataInArrays() {
        Cursor cursor = Players.readAllData();

        if (cursor.getCount() == 0) {
            noData.setVisibility(View.VISIBLE);
            emptyImageView.setVisibility(View.VISIBLE);
        } else {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                player_id.add(cursor.getString(0));
                player_username.add(cursor.getString(1));
                player_highscore.add(cursor.getString(2));
                cursor.moveToNext();

            }
            noData.setVisibility(View.GONE);
            emptyImageView.setVisibility(View.GONE);
        }
    }
}