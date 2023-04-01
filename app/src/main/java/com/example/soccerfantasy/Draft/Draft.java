package com.example.soccerfantasy.Draft;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.soccerfantasy.Objects.Players;
import com.example.soccerfantasy.Objects.Team;
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


public class Draft extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseAuth auth;

    FirebaseUser user;



    //get the refference and query to the teamRoster
    CollectionReference teamNameRef;
    Query query;

    //current team variable
    String currentTeamName;
    TextView textView;

    //playerlist variabl
    ArrayList<Players> playersArrayList;
    ListView playerListView;

    //Collection Name
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_draft, container, false);


        //get user data
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        teamNameRef = db.collection("team");
        query = teamNameRef.whereEqualTo("userId", user.getEmail());

        getTeamName();

        //get Id to display player name
        textView = view.findViewById(R.id.teamNameUpNext);


        //List for the players
        playersArrayList = new ArrayList<>();

        //List View
        playerListView = view.findViewById(R.id.list_view_player_draft);

        //current team

        String currentUserTeam;


        getPlayersInDraft();

        //get player list info


        return view;


    }


    public void getTeamName() {

        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                //Log.d(TAG, "TEAM NAME IS " + " => " + document.getData());
                                Team team =  document.toObject(Team.class);
                                currentTeamName = team.teamName;

                                Log.d(TAG, currentTeamName);

                                textView.setText(currentTeamName);

                            }
                        } else {

                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    public void getPlayersInDraft(){
        db.collection("players")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " ?=> " + document.getData().get("id"));

                                Players player = document.toObject(Players.class);


                                playersArrayList.add(player);

                                DraftPlayerAdapter adapter = new DraftPlayerAdapter(getContext(), playersArrayList);

                                playerListView.setAdapter(adapter);

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }




}