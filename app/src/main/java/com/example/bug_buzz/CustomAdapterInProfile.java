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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterInProfile extends RecyclerView.Adapter<CustomAdapterInProfile.MyViewHolder> {
    Context context;
    protected ArrayList player_username;
    int position;

    public CustomAdapterInProfile(Context context, ArrayList player_username) {
        this.context = context;
        this.player_username = player_username;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //use my_row1.xml layout for the recycler view in the profile selection activity
        View view = inflater.inflate(R.layout.my_row1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.player_username_button.setText(String.valueOf(player_username.get(position)));
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return player_username.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button player_username_button;
        PopupMenu menuOnLongClick;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing objects with their ids
            player_username_button = itemView.findViewById(R.id.player_username_button);

            //initialize the popup menu
            menuOnLongClick = new PopupMenu(context, player_username_button);
            menuOnLongClick.inflate(R.menu.popup_menu);
            menuOnLongClick.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    //when the delete selection has been pressed delete the current player from the database
                    switch (item.getItemId()){
                        case R.id.delete_selection:
                            MyDBHelper db = new MyDBHelper(context);
                            if (db.deletePlayer(player_username_button.getText().toString())){
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
            //if user clicks on the username button the game starts
            player_username_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //should redirect to the start of the game
                    Intent intent = new Intent(context, StartGame.class);
                    intent.putExtra("player_username", player_username_button.getText().toString());
                    context.startActivity(intent);
                }
            });
            //if user clicks for a long time on the username button a pop up delete selection appears
            player_username_button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                        menu.show();
                        return true;
                }
            });
        }
    }
}
