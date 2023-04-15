package com.example.soccerfantasy.myLeague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccerfantasy.R;
import com.example.soccerfantasy.Objects.Team;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TeamsInLeagueAdapter extends ArrayAdapter<Team> {
    public TeamsInLeagueAdapter(@NonNull Context context, ArrayList<Team> arrayListOfTeamsInLeague) {
        super(context,0, arrayListOfTeamsInLeague);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.team_list_row, parent, false);
        }
        //initialize the UI componenets
        Team team = getItem(position);

        TextView teamName = convertView.findViewById(R.id.layout_team_name);
        TextView points = convertView.findViewById(R.id.pointsId);

        //get Data
        teamName.setText(team.getTeamName());

        points.setText(team.getPoints());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "Item clicked is : " + team.getTeamName(), Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;

       //return super.getView(position, convertView, parent);



    }
}
