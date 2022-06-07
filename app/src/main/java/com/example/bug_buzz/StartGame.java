package com.example.bug_buzz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Currency;
import java.util.Random;
import java.util.Spliterator;

public class StartGame extends AppCompatActivity implements View.OnClickListener {

    private String currentPlayerUsername;
    private int score;
    private Button buttonA, buttonB, buttonC, buttonD, button_skip;
    private TextView myTextView;
    private String answer;
    private int questionLength;
    private int counter;
    private int skipCounter;
    MyDBHelper Players;
    int myColor = Color.parseColor("#00ffff");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        if (getIntent().hasExtra("player_username")) {
            currentPlayerUsername = getIntent().getStringExtra("player_username");
        }
        //setting the action bar when player is in the game
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("You play as: " + currentPlayerUsername);
        }
        score = 0;
        counter = 0;
        skipCounter = 0;
        Players = new MyDBHelper(StartGame.this);

        Questions.initializeQuestions();
        Questions.correctAnswers();
        questionLength = Questions.questions.size();

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        button_skip = (Button) findViewById(R.id.button_skip);
        myTextView = (TextView) findViewById(R.id.myTextView);
        NextQuestion();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonA:
                if (buttonA.getText() == answer) {
                    buttonA.setBackgroundColor(Color.GREEN);
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            buttonA.setBackgroundColor(myColor);
                        }
                    }, 1000);
                    score++;
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                } else {
                    buttonA.setBackgroundColor(Color.RED);
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            buttonA.setBackgroundColor(myColor);
                        }
                    }, 1000);
                    WrongAnswer();
                }
                break;
            case R.id.buttonB:
                if (buttonB.getText() == answer) {
                    buttonB.setBackgroundColor(Color.GREEN);
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            buttonB.setBackgroundColor(myColor);
                        }
                    }, 1000);
                    score++;
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                } else {
                    buttonB.setBackgroundColor(Color.RED);
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            buttonB.setBackgroundColor(myColor);
                        }
                    }, 1000);
                    WrongAnswer();
                }
                break;

            case R.id.buttonC:
                if (buttonC.getText() == answer) {
                    buttonC.setBackgroundColor(Color.GREEN);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            buttonC.setBackgroundColor(myColor);
                        }
                    }, 1000);
                    score++;
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                } else {
                    buttonC.setBackgroundColor(Color.RED);
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            buttonC.setBackgroundColor(myColor);
                        }
                    }, 1000);
                    WrongAnswer();
                }
                break;

            case R.id.buttonD:
                if (buttonD.getText() == answer) {
                    buttonD.setBackgroundColor(Color.GREEN);
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            buttonD.setBackgroundColor(myColor);
                        }
                    }, 1000);
                    score++;
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                } else {
                    buttonD.setBackgroundColor(Color.RED);
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            buttonD.setBackgroundColor(myColor);
                        }
                    }, 1000);
                    WrongAnswer();
                }
                break;

            case R.id.button_skip:
                if(skipCounter == 3){
                    Toast.makeText(StartGame.this, "You cant skip any more questions!", Toast.LENGTH_SHORT).show();
                }else{
                    skipCounter++;
                    Toast.makeText(StartGame.this, "You skipped the question", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }
        }
    }

    /**
     * This function is called when the player's
     * choice is wrong.
     */

    private void WrongAnswer() {
        Toast.makeText(StartGame.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
        NextQuestion();
    }

    /**
     * This function sets new texts for buttons and
     * textView everytime the player makes a choice or skips
     * the question.
     */

    private void NextQuestion() {
        if (counter >= Questions.questions.size()) {
            Players.updatePlayerScore(currentPlayerUsername, String.valueOf(score));
            counter = 0;
            Toast.makeText(StartGame.this, "Score = " + score, Toast.LENGTH_SHORT).show();
            finish();
        }else{
            myTextView.setText(Questions.questions.get(counter));
            buttonA.setText(Questions.choices[counter][0]);
            buttonB.setText(Questions.choices[counter][1]);
            buttonC.setText(Questions.choices[counter][2]);
            buttonD.setText(Questions.choices[counter][3]);
            answer = Questions.correct.get(counter);
            counter++;
        }
    }
}
