package com.example.soccerfantasy.myLeague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccerfantasy.League.League;
import com.example.soccerfantasy.R;

import java.util.ArrayList;

public class LeagueAdapter extends ArrayAdapter<League> {
    public LeagueAdapter(@NonNull Context context, ArrayList<League> arrayListLeague) {
        super(context,0, arrayListLeague);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.team_list_row, parent, false);
        }
        //initialize the UI componenets
        League league = getItem(position);
        TextView leagueName = convertView.findViewById(R.id.layout_team_name);

        //get Data
        leagueName.setText(league.getLeagueName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "Item clicked is : " + league.getLeagueName(), Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;

       //return super.getView(position, convertView, parent);



    }
}
