package com.example.bug_buzz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList player_id,player_username,player_highscore;

    public CustomAdapter(Context context,
                         ArrayList player_id,
                         ArrayList player_username,
                         ArrayList player_highscore) {

        this.context = context;
        this.player_id = player_id;
        this.player_username = player_username;
        this.player_highscore = player_highscore;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.player_id_txt.setText(String.valueOf(player_id.get(position)));
        holder.player_username_txt.setText(String.valueOf(player_username.get(position)));
        holder.player_highscore_txt.setText(String.valueOf(player_highscore.get(position)));

    }

    @Override
    public int getItemCount() {
        return player_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView player_id_txt, player_username_txt, player_highscore_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            player_id_txt = itemView.findViewById(R.id.player_id_txt);
            player_username_txt = itemView.findViewById(R.id.player_username_txt);
            player_highscore_txt = itemView.findViewById(R.id.player_highscore_txt);
        }
    }
}
