package com.example.bug_buzz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class CustomAdapterInScores extends RecyclerView.Adapter<CustomAdapterInScores.MyViewHolder>{
    Context context;
    ArrayList player_username, player_score;
    int position;

    public CustomAdapterInScores(Context context, ArrayList player_username, ArrayList player_score) {
        this.context = context;
        this.player_username = player_username;
        this.player_score = player_score;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.username.setText(String.valueOf(player_username.get(position)));
        holder.score.setText(String.valueOf(player_score.get(position)));
        holder.person.setVisibility(View.VISIBLE);

        this.position = position;

    }

    @Override
    public int getItemCount() {
        return player_username.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, score;
        ImageView person;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.player_username_txt);
            score = itemView.findViewById(R.id.player_highscore_txt);
            person = itemView.findViewById(R.id.image_view_person);
        }
    }
}
