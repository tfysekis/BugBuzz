package com.example.bug_buzz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
    MyDBHelper Players;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        if (getIntent().hasExtra("player_username")) {
            currentPlayerUsername = getIntent().getStringExtra("player_username");
        }

     /*   if (savedInstanceState != null){
            counter = savedInstanceState.getInt("my_counter");
            Questions.questions = savedInstanceState.getStringArrayList("my_questions");
            Questions.correct = savedInstanceState.getStringArrayList("my_answers");
            //Questions.shuffleEverything();
            Toast.makeText(this, "SAVED INSTANCE STATE", Toast.LENGTH_SHORT).show();
        }*/

        //setting the action bar when player is in the game
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("You play as: " + currentPlayerUsername);
        }
        score = 0;
        counter = 0;
        Players = new MyDBHelper(StartGame.this);

        Questions.initializeQuestions();
        Questions.correctAnswers();
        Questions.shuffleEverything();
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
        Toast.makeText(this, "COUNTER = " + counter, Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.buttonA:
                if (buttonA.getText() == answer) {
                    score++;
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                } else {
                    WrongAnswer();
                }
                break;
            case R.id.buttonB:
                if (buttonB.getText() == answer) {
                    score++;
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                } else {
                    WrongAnswer();
                }
                break;

            case R.id.buttonC:
                if (buttonC.getText() == answer) {
                    score++;
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                } else {
                    WrongAnswer();
                }
                break;

            case R.id.buttonD:
                if (buttonD.getText() == answer) {
                    score++;
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                } else {
                    WrongAnswer();
                }
                break;

            case R.id.button_skip:
                Toast.makeText(StartGame.this, "You skipped the question", Toast.LENGTH_SHORT).show();
                NextQuestion();
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
            //StartGame.this.finish();
            Intent intent = new Intent(StartGame.this, MainMenu.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            this.finish();
            //startActivity(intent);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           // startActivity(intent);
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
