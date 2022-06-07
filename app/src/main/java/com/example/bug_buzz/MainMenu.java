package com.example.bug_buzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Button start_game,high_scores,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_menu);

        //initializing objects with their ids
        start_game = findViewById(R.id.start_game);
        high_scores = findViewById(R.id.high_scores);
        exit = findViewById(R.id.exit);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    //on click method for Start Game button
    @Override
    public void onBackPressed(){
        this.finishAffinity();
    }
    public void onStartGame(View view) {
        Intent intent = new Intent(this, ProfileSelection.class);
        startActivity(intent);
    }

    //on click method for Highscores button
    public void onHighScores(View view){
        Intent intent= new Intent(this, HighScores.class);
        startActivity(intent);
    }

    //on click method for exit button
    public void QuitApp(View view) {
        this.finishAffinity();
        System.exit(0);
    }
}