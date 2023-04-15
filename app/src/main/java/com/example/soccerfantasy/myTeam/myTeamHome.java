package com.example.soccerfantasy.myTeam;

import static android.content.ContentValues.TAG;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.soccerfantasy.Draft.Draft;
import com.example.soccerfantasy.Objects.Players;
import com.example.soccerfantasy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.time.Clock;
// get the league info
// if


public class
myTeamHome extends Fragment implements View.OnClickListener {


    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int matchDay;
    View view;


    Button enter_draft, view_teamRoster_btn;

    ArrayList<Players> playersInLeague;
    ArrayList<Players> playerIdsFromRoster;

    ArrayList<Players> arrayOfPlayersStarting;


    int homeTeamTotal;

    int awayTeamTotal;

   //DEMO only
    TextView homeScoreView, awayScoreView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //import button
        view = inflater.inflate(R.layout.fragment_my_team_home, container, false);

        enter_draft = (Button) view.findViewById(R.id.enter_draft_btn);
        view_teamRoster_btn = (Button) view.findViewById(R.id.ViewTeamRosterBtn);

        view_teamRoster_btn.setOnClickListener(this);
        enter_draft.setOnClickListener(this);

        //store the player in league
        playersInLeague = new ArrayList<>();

        //store the player IDs on roster
        playerIdsFromRoster = new ArrayList<>();

        //store players that are in aleague and on rotser
        arrayOfPlayersStarting = new ArrayList<>();


        //get matchup
        weekMatchups();
        //matchup view
        TextView gameWeekView = view.findViewById(R.id.gameWeekId);

        gameWeekView.setText(String.valueOf(matchDay));

        //get players from league
        getPlayersFromLeague();


        //for DEMO ONLY draft disapear ??
        SharedPreferences preferences = this.getActivity().getSharedPreferences("draftClick", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

//        if (preferences.getBoolean("draftClick",true)) {
//            enter_draft.setVisibility(View.VISIBLE);
//        }else
//        {
//            enter_draft.setVisibility(View.INVISIBLE);
//        }


        // add current match up


        //getAwayTeamView

        //which week are we in match to date.

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
            //DEMO ONLY
            SharedPreferences preferences = getContext().getSharedPreferences("draftClick", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("draftClick", false);
            editor.commit();

        } else if (view.getId() == R.id.ViewTeamRosterBtn) {
            replaceFragment(new TeamRoster());
        }


    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void weekMatchups() {


        Date d1 = new Date();

        Calendar cl = Calendar.getInstance();
        cl.setTime(d1);

        int yearWeek = cl.get(Calendar.WEEK_OF_YEAR);

        switch (yearWeek) {
            case 1:
                System.out.println("today is Match Day 16");
                matchDay = 16;
                break;
            case 2:
                System.out.println("today is Match Day 17");
                matchDay = 17;
                break;
            case 3:
                System.out.println("today is Match Day 18");
                matchDay = 18;
                break;
            case 4:
                System.out.println("today is Match Day 19");
                matchDay = 19;
                break;
            case 5:
                System.out.println("today is Match Day 20");
                matchDay = 20;
                break;
            case 6:
                System.out.println("today is Match Day 21");
                matchDay = 21;
                break;
            case 7:
                System.out.println("today is Match Day 22");
                matchDay = 22;
                break;
            case 8:
                System.out.println("today is Match Day 23");
                matchDay = 23;
                break;
            case 9:
                System.out.println("today is Match Day 24");
                matchDay = 24;
                break;
            case 10:
                System.out.println("today is Match Day 25");
                matchDay = 25;
                break;
            case 11:
                System.out.println("today is Match Day 26");
                matchDay = 26;
                break;
            case 12:
                System.out.println("today is Match Day 27");
                matchDay = 27;
                break;
            case 13:
                System.out.println("today is Match Day 28");
                matchDay = 28;
                break;
            case 14:
                System.out.println("today is Match Day 29");
                //awayPoints
                //homePoints
                //gameWeek(29)
                matchDay = 29;
                break;
            case 15:
                System.out.println("today is Match Day 30");
                matchDay = 30;
                break;
            case 16:
                System.out.println("today is Match Day 31");
                matchDay = 31;
            case 17:
                System.out.println("today is Match Day 32");
                matchDay = 32;
            case 18:
                System.out.println("today is Match Day 33");
                matchDay = 33;
            case 19:
                System.out.println("today is Match Day 34");
                matchDay = 34;
            case 20:
                System.out.println("today is Match Day 35");
                matchDay = 35;


        }


    }


    public int gameWeek(int matchDayNew) {

        //database connection
        //get the refference and query to the teamRoster
        CollectionReference gameMatchRef = db.collection("gameMatches");
        Query query = gameMatchRef.whereEqualTo("matchWeek", matchDayNew);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {


                            //if player is on team add points to team total.

                            //import team totals
                            //if away is more then winner.
                            //if (matchDayNew == matchDay) {



                                //if players on on league calculate add there points up and add to total.
                                //get players on your team.

                                //add up all the points from your players  and post them.
                                // call roster
                                //get home team score.
                                //get away team score.

                            //}

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



        return matchDay;
    }




    // while match week is 29
    // call points
    // else change.
    // get the points for


    public void getPlayersFromLeague(){

        //get refference to players table
        CollectionReference players = db.collection("players");
        players.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Players players = document.toObject(Players.class);
                                playersInLeague.add(players);
                                //Log.d(TAG, "Player Name = " + players.getFirst_name());
                            }
                        } else {

                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        getPlayersFromRoster();

                    }

                });
    };

    public void getPlayersFromRoster(){

        CollectionReference teamRosterRef = db.collection("teamRoster");

        Query query = teamRosterRef.whereEqualTo("teamName", "Marleys Team" ); //hardcoded only for marcos team
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {

                        Players player =  document.toObject(Players.class);

                        playerIdsFromRoster.add(player);

                        Log.d(TAG, player.getPlayerId() + " Is on Your team " + player.getPoints());
                    }

                    getPlayersNames();

                } else
                {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }
    public void getPlayersNames() {

        for (Players PR : playerIdsFromRoster) {

            int playersOnRosterID = parseInt(PR.getPlayerId());

            for (Players PL : playersInLeague){

                int playersOnLeagueID = parseInt(PL.getPlayerId());

                if (playersOnRosterID == playersOnLeagueID){

                    arrayOfPlayersStarting.add(new Players(PL.getPlayerId(), PL.getFirst_name(), PL.getPoints(), PL.getPosition()));

                    Log.d(TAG, "Player on your Team!  " + PL.getLast_name_name() + PL.getPoints());

                }
            }

        }

        calculateTeamPoints();

        homeScore();
    }

    public int calculateTeamPoints(){

        //get each player and add put score.
        int pointsTotal = 0;

        //Players player = new Players();

        for (Players p : arrayOfPlayersStarting) {

            pointsTotal =  pointsTotal + p.getPoints();

        }

        Log.d(TAG, "Points Points Points Points "  + pointsTotal);

        //calculate your team and other teams


        return pointsTotal;

        }


        public void homeScore(){

            //getHomeTeamView
            homeScoreView = view.findViewById(R.id.homeScoreId);
            awayScoreView = view.findViewById(R.id.awayScoreId);
            homeScoreView.setText(String.valueOf(calculateTeamPoints()));

                awayScoreView.setText(String.valueOf(calculateTeamPoints() - 3));


        }





    }


