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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterInProfile extends RecyclerView.Adapter<CustomAdapterInProfile.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    protected ArrayList<String> player_username;
    int position;

    public CustomAdapterInProfile(Context context, ArrayList<String> player_username, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        this.player_username = player_username;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //use my_row1.xml layout for the recycler view in the profile selection activity
        View view = inflater.inflate(R.layout.my_row1, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
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

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
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
                    switch (item.getItemId()) {
                        case R.id.delete_selection:
                            MyDBHelper db = new MyDBHelper(context);
                            if (db.deletePlayer(player_username.get(position))) {
                                Intent intent = new Intent(context, ProfileSelection.class);
                                context.startActivity(intent);
                            }
                        default:
                            return true;
                    }
                }
            });
            usernameClickListeners(menuOnLongClick);
            //usernameClickListeners(menuOnLongClick);
            player_username_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onUsernameClick(pos);
                        }
                    }
                }
            });
        }

        void usernameClickListeners(PopupMenu menu) {
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

