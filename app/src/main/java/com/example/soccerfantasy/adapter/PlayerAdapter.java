package com.example.soccerfantasy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerfantasy.League.Players;
import com.example.soccerfantasy.R;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<Players> playersList;
    private Context context;

    public PlayerAdapter(Context context, List<Players> players) {

    this.playersList = players;
    this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View playerView = inflater.inflate(R.layout.player_row,parent,false);

        ViewHolder viewHolder = new ViewHolder(playerView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.ViewHolder holder, int position) {

        Players players = playersList.get(position);


        //set item views based on view model
        TextView textview = holder.playerName;
        textview.setText(players.getName());

        Button button = holder.draftButton;
        button.setText(players.getDraft()? "Available" : "Not Available");

    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView playerName;
        public Button draftButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            playerName = itemView.findViewById(R.id.player_name);
            draftButton = itemView.findViewById(R.id.drafted_btn);
        }
    }
}



