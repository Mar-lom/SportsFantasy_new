package com.example.soccerfantasy.myTeam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.soccerfantasy.Draft.Draft;
import com.example.soccerfantasy.Objects.Team;
import com.example.soccerfantasy.R;

import java.util.ArrayList;
import java.util.List;


public class
myTeamHome extends Fragment implements View.OnClickListener  {

    View view;

    ListView list_view_teams;

    Team team;
    List<String> teams = new ArrayList<String>();
    String[] listOfTeams = {"MarcosT", "DiegosT"};





    ArrayAdapter ad;

    Button enter_draft, view_teamRoster_btn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //import button
        view = inflater.inflate(R.layout.fragment_my_team_home, container, false);

        enter_draft = (Button) view.findViewById(R.id.enter_draft_btn);
        view_teamRoster_btn = (Button) view.findViewById(R.id.ViewTeamRosterBtn);

        view_teamRoster_btn.setOnClickListener(this);
        enter_draft.setOnClickListener(this);


        // add current match up


        //array convert to list

        //teams = new ArrayList<String>(Arrays.asList(listOfTeams));

        //list_view_teams = (ListView) view.findViewById(R.id.list_view_teams);

        //ad = new ArrayAdapter<>(getContext(), layout.simple_list_item_1, teams);

        //list_view_teams.setAdapter(ad);




        return view;

    }



    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        if (view.getId() == R.id.enter_draft_btn) {
            replaceFragment(new Draft());
        }
        else if (view.getId() == R.id.ViewTeamRosterBtn){
                replaceFragment(new TeamRoster());
        }


    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}