package com.example.bug_buzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends AppCompatActivity {
    EditText username_input;
    Button add_username_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //initializing objects with their ids
        username_input = findViewById(R.id.enter_username);
        add_username_button = findViewById(R.id.add_username_button);

        add_username_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper Players = new MyDBHelper(AddUser.this);
                String givenUsername = username_input.getText().toString().trim();
                //check if username already exists in the database
                if(usernameExists(Players, givenUsername)){
                    //if username exists then repeat with another username
                    Toast.makeText(AddUser.this, "user already exists", Toast.LENGTH_SHORT).show();
                }else{
                    //if username doesn't exist in the database add the given username in the database
                    Players.addPlayer(givenUsername);
                    //back in the profile selection
                    Intent intent = new Intent(AddUser.this, ProfileSelection.class);
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        AddUser.this.finish();

    }

    /**
     *
     * @param database current database
     * @param username given username
     * @return true if username already exists in the database
     */
    boolean usernameExists(MyDBHelper database, String username){
        Cursor cursor = database.readAllData();

        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            String usernameInDB = cursor.getString(1).trim();
            if(username.equals(usernameInDB)){
                System.out.println("username already exists");
                return true;
            }
            cursor.moveToNext();
        }
        return false;
    }
}