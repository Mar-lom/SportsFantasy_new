package com.example.soccerfantasy.myTeam;

import static android.R.*;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccerfantasy.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class myTeamHome extends Fragment {

    View view;
    ListView list_view_teams;
    Team team;
    List<String> teams = new ArrayList<String>();
    String[] listOfTeams = {"MarcosT", "DiegosT"};

    ArrayAdapter ad;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_team_home, container, false);
        //array convert to list
        teams = new ArrayList<String>(Arrays.asList(listOfTeams));

        list_view_teams = (ListView) view.findViewById(R.id.list_view_teams);

         ad = new ArrayAdapter<>(getContext(), layout.simple_list_item_1, teams);

        list_view_teams.setAdapter(ad);


        return view;

    }
}