package com.example.soccerfantasy.myTeam;

import static android.content.ContentValues.TAG;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;


public class TeamRoster extends Fragment {

    View view;

    FirebaseAuth auth;

    FirebaseUser user;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //get the refference and query to the teamRoster
    CollectionReference teamRosterRef = db.collection("teamRoster");
    Query query = teamRosterRef.whereEqualTo("teamName", "Marleys Team" );

    //get refference to players table
    CollectionReference players = db.collection("players");

    //store players Ids from the roster.
    ArrayList<Players> playerIdsFromRoster;

    //store players from the entire league;
    ArrayList<Players> playersInLeague;

    // Construct the data source
    ArrayList<Players> arrayOfPlayersStarting;
    TeamRosterAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_team_roster, container, false);

        //store the player IDs
        playerIdsFromRoster = new ArrayList<>();

        //store the player in the league
        playersInLeague = new ArrayList<>();

        // Construct the data source
        arrayOfPlayersStarting = new ArrayList<>();

        getPlayersFromLeague();

        return view;

    }

//adds the players to array list

    public void print() {

        for (Players PR : playerIdsFromRoster) {

            int playersOnRosterID = parseInt(PR.getPlayerId());

                for (Players PL : playersInLeague){

                    int playersOnLeagueID = parseInt(PL.getPlayerId());

                    //Log.d(TAG, playersOnRosterID + " Is on Your team");

                    //Log.d(TAG, playersOnLeagueID + " Is in your league");

                            if (playersOnRosterID == playersOnLeagueID){

                                Log.d(TAG, playersOnRosterID + " Is on Your team");

                                arrayOfPlayersStarting.add(new Players(PL.getPlayerId(), PL.getFirst_name(), PL.getPoints(), PL.getPosition()));

                            }
                 }

        }

        loadPlayersToList();
    }







    //gets all the players from the player table.
public void getPlayersFromRoster(){

    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {

                    Players player =  document.toObject(Players.class);

                    playerIdsFromRoster.add(player);

                    for (Players i : playerIdsFromRoster) {

                        //Log.d(TAG, "Player Id = " + i.getPlayerId());
                    }

                }

                print();

            } else
            {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }

        }


    });
}


public void getPlayersFromLeague(){

    players.get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            Players players = document.toObject(Players.class);

                            playersInLeague.add(players);
                            //Log.d(TAG, players.getFirst_name() + " " + players.getPlayerId());
                        }

                        getPlayersFromRoster();

                        //loadPlayersToList();

                    } else {

                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
};



    public void loadPlayersToList(){

        //arrayOfPlayersStarting.add(new Players("12","Marissa",0, "0"));

        // Create the adapter to convert the array to views
        adapter = new TeamRosterAdapter(getContext(), arrayOfPlayersStarting);

        // Attach the adapter to a ListView

        ListView listView = (ListView) view.findViewById(R.id.list_view_teams_starters);

        listView.setAdapter(adapter);


    }


}
