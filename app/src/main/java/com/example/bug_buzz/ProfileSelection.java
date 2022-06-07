package com.example.bug_buzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProfileSelection extends AppCompatActivity implements RecyclerViewInterface{
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageView;
    TextView noData, selectAUser;

    MyDBHelper Players;
    protected ArrayList<String> player_id, player_username, player_highscore; //array lists used for saving each column of the database
    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;
    CustomAdapterInProfile customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);
        //getSupportActionBar().hide();
        //initializing objects with their ids
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageView = findViewById(R.id.image_view_no_users);
        noData = findViewById(R.id.no_users_text_view);
        selectAUser = findViewById(R.id.select_a_user_text_view);

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

        //setting the custom adapter for this activity's recycler view
        customAdapter = new CustomAdapterInProfile(ProfileSelection.this, player_username,this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileSelection.this));

    }

    @Override
    protected void onPause()
    {
        super.onPause();

    }
    @Override
    protected void onResume(){
        super.onResume();

    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
    void storeDataInArrays(){
        Cursor cursor = Players.readAllData();

        if(cursor.getCount() == 0){
            //in case the database is empty make the appropriate views visible
            selectAUser.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
            empty_imageView.setVisibility(View.VISIBLE);
        }else{
            //else store the data in array list
            player_id.clear();
            player_highscore.clear();
            player_username.clear();

            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount(); i++){
                player_id.add(cursor.getString(0));
                player_username.add(cursor.getString(1));
                player_highscore.add(cursor.getString(2));
                cursor.moveToNext();
            }
            //default views
            selectAUser.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
            empty_imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onUsernameClick(int position) {
        Intent intent = new Intent(this, StartGame.class);
        intent.putExtra("player_username", player_username.get(position));

        startActivity(intent);
    }
}