package com.example.soccerfantasy.myTeam;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.soccerfantasy.Objects.Players;
import com.example.soccerfantasy.R;

import java.util.ArrayList;

public class TeamRosterAdapter extends ArrayAdapter<Players> {

        public TeamRosterAdapter(Context context, ArrayList<Players> players) {

            super(context, 0, players);

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            Players player = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_team_roster_row, parent, false);

            }

            // Lookup view for data population

            TextView tvPlayerPosition = (TextView) convertView.findViewById(R.id.playerPosition);

            TextView tvPlayerName = (TextView) convertView.findViewById(R.id.playerName);


            //TextView tvPlayerPoints = (TextView) convertView.findViewById(R.id.playerPoints);

            //Button tvSwapPlayer = (Button) convertView.findViewById(R.id.changePlayer_btn); // come back

            // Populate the data into the template view using the data object

            tvPlayerName.setText(player.getName());

            tvPlayerPosition.setText(player.getPosition());

            //tvPlayerPoints.setText(String.valueOf(player.getPoints()));



            // Return the completed view to render on screen

            return convertView;

        }

    }


