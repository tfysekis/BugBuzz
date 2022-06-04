package com.example.bug_buzz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class StartGame extends AppCompatActivity implements View.OnClickListener {

    private String player_username;
    private Button buttonA, buttonB, buttonC, buttonD, button_skip;
    private TextView myTextView;
    private String answer;
    private int questionLength;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        if(getIntent().hasExtra("player_username")){
            player_username = getIntent().getStringExtra("player_username");
            Toast.makeText(this, player_username, Toast.LENGTH_SHORT).show();
        }
        Questions.initializeQuestions();
        Questions.correctAnswers();
        Questions.shuffleEverything();
        questionLength = Questions.questions.size();

        buttonA = (Button)findViewById(R.id.buttonA);
        buttonB = (Button)findViewById(R.id.buttonB);
        buttonC = (Button)findViewById(R.id.buttonC);
        buttonD = (Button)findViewById(R.id.buttonD);
        button_skip = (Button)findViewById(R.id.button_skip);
        myTextView = (TextView)findViewById(R.id.myTextView);
        NextQuestion();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonA:
                if(buttonA.getText() == answer){
                    //TODO: Score ++ to database
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    WrongAnswer();
                }
                break;
            case R.id.buttonB:
                if(buttonB.getText() == answer){
                    //TODO: Score ++ to database
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    WrongAnswer();
                }
                break;

            case R.id.buttonC:
                if(buttonC.getText() == answer){
                    //TODO: Score ++ to database
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    WrongAnswer();
                }
                break;

            case R.id.buttonD:
                if(buttonD.getText() == answer){
                    //TODO: Score ++ to database
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion();
                }else{
                    WrongAnswer();
                }
                break;

            case R.id.button_skip:
                Toast.makeText(StartGame.this, "You skipped the question", Toast.LENGTH_SHORT).show();
                NextQuestion();
        }
    }

    /**
     * This class is activated when the player's
     * choice is wrong.
     */

    private void WrongAnswer(){
        Toast.makeText(StartGame.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
        //TODO: Maybe Score-- to database
        NextQuestion();
    }

    /**
     * This class is setting new texts for buttons and
     * textView everytime the player make a choice or skip
     * the question.
     */

    private void NextQuestion(){
        if (counter == Questions.questions.size()){
            Intent intent = new Intent(this, MainMenu.class);
            Toast.makeText(StartGame.this, "End Game", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        myTextView.setText(Questions.questions.get(counter));
        buttonA.setText(Questions.choices[counter][0]);
        buttonB.setText(Questions.choices[counter][1]);
        buttonC.setText(Questions.choices[counter][2]);
        buttonD.setText(Questions.choices[counter][3]);
        answer = Questions.correct.get(counter);
        counter++;
    }
}