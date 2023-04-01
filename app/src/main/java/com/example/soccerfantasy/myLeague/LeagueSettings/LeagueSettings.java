package com.example.soccerfantasy.myLeague.LeagueSettings;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.soccerfantasy.Draft.Draft;
import com.example.soccerfantasy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LeagueSettings extends Fragment {


    //firebase Auth
    FirebaseAuth auth;
    FirebaseUser user;

    //Btn to start Draft
    Button draftButton;

    //btn for my team draft start;
    Button myTeamDraftButton, generateMatchups;

    //fire base refference
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //collection refference
    CollectionReference teamRef = db.collection("team");
    Query queryTeamRef;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_league_settings, container, false);


        //login info
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        // query for team ref
        queryTeamRef = teamRef.whereEqualTo("leagueName", "FirstLeague");
        getTeamsInLeague();


        generateMatchups = view.findViewById(R.id.generateMatchBtn);

        generateMatchups.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("GENERATING", "MATCHUP");


                String[] playersInLeague = {"Marco", "John", "Daniel", "Marley"};

                //add each

               // ArrayList<String> Matchups = new ArrayList<>();

               // Collections.addAll(Matchups, playersInLeague);

               // Collections.shuffle(Matchups);



               // Log.d("The Log Order" , String.valueOf(Matchups));

            }
        });




        return view;
    }

    public void StartDraft(View view){

        myTeamDraftButton.setVisibility(view.VISIBLE);



    }


    public void getTeamsInLeague(){

        queryTeamRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }
                });

    }





}