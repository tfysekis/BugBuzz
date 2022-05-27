package com.example.bug_buzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUser extends AppCompatActivity {
    EditText username_input;
    Button add_username_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        username_input = findViewById(R.id.enter_username);
        add_username_button = findViewById(R.id.add_username_button);

        add_username_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper Players = new MyDBHelper(AddUser.this);
                Players.addPlayer(username_input.getText().toString().trim());
            }
        });
    }
}