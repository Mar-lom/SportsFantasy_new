package com.example.soccerfantasy.myLeague;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.soccerfantasy.Draft.Draft;
import com.example.soccerfantasy.Objects.Players;
import com.example.soccerfantasy.R;
import com.example.soccerfantasy.Objects.Team;
import com.example.soccerfantasy.myLeague.LeagueSettings.LeagueSettings;
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


public class myLeagueHome extends Fragment implements View.OnClickListener {
    View view;
    TextView userTextView;
    Button joinLeagueFragment, createleagueFragment, manageLeagueFragment;

    //Authentication
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<Team> listOfTeamInLeague; //Store list of teams in the entire League
    ListView leagueLV; // list view

    CollectionReference leagueRef = db.collection("league"); // ref the league
    Query queryForLeagueAdmin;

    String leagueAdmin; //store league Admin

    Button leagueManageButton; //manager League button


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //populate the buttons
        view = inflater.inflate(R.layout.fragment_my_league_home, container, false);

        joinLeagueFragment = (Button) view.findViewById(R.id.joinLeagueFragment_btn);
        createleagueFragment = (Button) view.findViewById(R.id.createleagueFragment_btn);
        manageLeagueFragment = (Button) view.findViewById(R.id.manageMyLeagueBtn);

        joinLeagueFragment.setOnClickListener(this);
        createleagueFragment.setOnClickListener(this);
        manageLeagueFragment.setOnClickListener(this);

        // user ID and league info
        userTextView = view.findViewById(R.id.myLeague);

        //login info
       auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();

        if (user == null){
            userTextView.setText("Who Are You?");
        }
        else {
            userTextView.setText(user.getEmail());
        }

        //Query for league Admin Check
        //if this isnt the admin then the manage league button wont show!
        queryForLeagueAdmin = leagueRef.whereEqualTo("adminId", user.getEmail() );
        getAdminOfLeague();


        leagueAdmin = "Manage League";// button text
        // if league admin == league admin show the button

        leagueManageButton = view.findViewById(R.id.manageMyLeagueBtn);
        leagueManageButton.setText(leagueAdmin);
        leagueManageButton.setVisibility(View.INVISIBLE);

        listOfTeamInLeague = new ArrayList<>(); //list that stores the teams in the league -- the databse call stores it here.


        //FOR DEMO ONLY
        String[] leaguesIn = {"The A League"}; //get data for spinner
        //FOR DEMO ONLY

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_leagues_list); // the spinner display


        //Creating the ArrayAdapter instance -- this code is for future use, need to implement database functionality.
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,leaguesIn);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("joinedLeague", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        //FOR DEMO SETUP// ///
        //editor.putBoolean("joinedLeague", false);
        //editor.commit();


        if (preferences.getBoolean("joinedLeague",false)) {

            Log.d(TAG, "JoinedLeague?: ");

            db.collection("team")
                    //.whereEqualTo("leagueName", "FirstLeague" )
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot document : task.getResult()) {
                                    //Setting the ArrayAdapter data on the Spinner //MOVED FOR DEMO ONLY
                                    spinner.setAdapter(spinnerAdapter);
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    Team team = document.toObject(Team.class);
                                    listOfTeamInLeague.add(team);
                                    leagueLV = (ListView) view.findViewById(R.id.list_view_leagues);
                                    leagueLV.setVisibility(View.VISIBLE);
                                    TeamsInLeagueAdapter adapter = new TeamsInLeagueAdapter(getContext(), listOfTeamInLeague);
                                    leagueLV.setAdapter(adapter);
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

        }
        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.joinLeagueFragment_btn:
                //for demo porposes only
                //add the league to the spinner
                //make league table visable

                fragment = new JoinLeague();
                replaceFragment(fragment);
                break;

            case R.id.createleagueFragment_btn:
                fragment = new CreateLeagueF();
                replaceFragment(fragment);
                break;

            case R.id.manageMyLeagueBtn:
                fragment = new LeagueSettings();
                replaceFragment(fragment);
                break;

        }
    }

    public void onResume() {
        super.onResume();
        Log.d("OnResume","onResume");

    }

    public void onPause() {
        super.onPause();
        Log.d("OnPause","onPause");

    }



    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void getSpinnerData(){

    }

// if this user is the admin of the league then the button will appear.
    public void getAdminOfLeague(){

        queryForLeagueAdmin.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());

                                leagueAdmin = document.getData().get("adminId").toString();

                                if (leagueAdmin.equals(user.getEmail())){
                                    leagueManageButton.setVisibility(View.VISIBLE);
                                }   else {
                                    leagueManageButton.setVisibility(View.INVISIBLE);
                                }

                                //leagueManageButton.setText(leagueAdmin);

                                Log.d(TAG, "=>" + leagueAdmin);

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }
                });
    };

    }





