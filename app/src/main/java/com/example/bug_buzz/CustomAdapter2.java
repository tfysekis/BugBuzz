package com.example.bug_buzz;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter2 extends CustomAdapter{
    Context context;
    public CustomAdapter2(Context context, ArrayList player_id, ArrayList player_username, ArrayList player_highscore) {
        super(context, player_id, player_username, player_highscore);
    }
    public void MyViewHolder(@NonNull ViewGroup parent, int viewType){

    }
}
