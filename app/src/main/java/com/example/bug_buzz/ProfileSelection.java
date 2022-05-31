package com.example.bug_buzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProfileSelection extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    //Button username;

    MyDBHelper Players;
    ArrayList<String> player_id, player_username, player_highscore;

    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        //username = findViewById(R.id.player_username_txt);
        //on click listener for add button
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileSelection.this, AddUser.class);
                startActivity(intent);
            }
        });
        Players = new MyDBHelper(ProfileSelection.this);
        player_id = new ArrayList<>();
        player_username = new ArrayList<>();
        player_highscore = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ProfileSelection.this, player_id,player_username,player_highscore);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileSelection.this));
    }

    void storeDataInArrays(){
        Cursor cursor = Players.readAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount(); i++){
                player_id.add(cursor.getString(0));
                player_username.add(cursor.getString(1));
                player_highscore.add(cursor.getString(2));
                cursor.moveToNext();

            }
        }

    }
}