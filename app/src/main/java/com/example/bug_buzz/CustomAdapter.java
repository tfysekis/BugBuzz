package com.example.bug_buzz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    protected ArrayList player_id,player_username,player_highscore;
    int position;

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
        View view = inflater.inflate(R.layout.my_row1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //holder.player_id_txt.setText(String.valueOf(player_id.get(position)));
        holder.player_username_txt.setText(String.valueOf(player_username.get(position)));
        //holder.player_highscore_txt.setText(String.valueOf(player_highscore.get(position)));
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return player_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView player_id_txt, player_username_txt, player_highscore_txt;
        Button player_username_txt;
        PopupMenu menuOnLongClick;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //player_id_txt = itemView.findViewById(R.id.player_id_txt);
            player_username_txt = itemView.findViewById(R.id.player_username_txt);
            //player_highscore_txt = itemView.findViewById(R.id.player_highscore_txt);

            menuOnLongClick = new PopupMenu(context, player_username_txt);
            menuOnLongClick.inflate(R.menu.popup_menu);
            menuOnLongClick.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.delete_selection:
                            //todo should delete user from the db
                            MyDBHelper db = new MyDBHelper(context);
                            if (db.deletePlayer(player_username_txt.getText().toString())){
                                Toast.makeText(context,"SDSAD",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context,ProfileSelection.class);
                                context.startActivity(intent);
                            }
                        default:
                            return true;
                    }
                }
            });

            usernameClickListeners(menuOnLongClick);
        }
        void usernameClickListeners(PopupMenu menu){
            player_username_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //should redirect to the start of the game
                    Intent intent = new Intent(context, StartGame.class);
                    intent.putExtra("player_username", player_username_txt.getText().toString());
                    context.startActivity(intent);
                }
            });
            player_username_txt.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //should popup a delete selection
                        menu.show();
                        return true;
                }
            });
        }
    }
}
