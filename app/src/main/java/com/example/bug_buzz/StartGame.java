package com.example.bug_buzz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class StartGame extends AppCompatActivity implements View.OnClickListener {

    String player_username;

    Button button7, button6, button5, button;

    private Questions question = new Questions();

    private String answer;
    private int questionLength = question.questions.length;

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        if(getIntent().hasExtra("player_username")){
            player_username = getIntent().getStringExtra("player_username");
            Toast.makeText(this, player_username, Toast.LENGTH_SHORT).show();
        }


        button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(this);
        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }



    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button7:
                if(button7.getText() == answer){
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion(random.nextInt(questionLength));
                }else{
                    GameOver();
                }

                break;

            case R.id.button6:
                if(button6.getText() == answer){
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion(random.nextInt(questionLength));
                }else{
                    GameOver();
                }

                break;

            case R.id.button5:
                if(button5.getText() == answer){
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion(random.nextInt(questionLength));
                }else{
                    GameOver();
                }

                break;

            case R.id.button:
                if(button.getText() == answer){
                    Toast.makeText(StartGame.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    NextQuestion(random.nextInt(questionLength));
                }else{
                    GameOver();
                }

                break;
        }
    }

    private void GameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StartGame.this);
        alertDialogBuilder
                .setMessage("Game Over")
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), StartGame.class));
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
        alertDialogBuilder.show();

    }

    private void NextQuestion(int num){
        button7.setText(question.getChoice1(num));
        button6.setText(question.getChoice2(num));
        button5.setText(question.getChoice3(num));
        button.setText(question.getChoice4(num));

        answer = question.getCorrectAnswer(num);
    }
}