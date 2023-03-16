package com.example.soccerfantasy.myLeague;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccerfantasy.CreateLeagueF;
import com.example.soccerfantasy.JoinLeague;
import com.example.soccerfantasy.League.League;
import com.example.soccerfantasy.League.Players;
import com.example.soccerfantasy.Login;
import com.example.soccerfantasy.R;
import com.example.soccerfantasy.adapter.PlayerAdapter;
import com.example.soccerfantasy.databinding.ActivityMainBinding;
import com.example.soccerfantasy.myTeam.Team;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class myLeagueHome extends Fragment implements View.OnClickListener {
    View view;
    TextView userTextView;
    Button joinLeagueFragment, createleagueFragment;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<League> leagueArrayList;

    ListView leagueLV;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //populate the buttons
        view = inflater.inflate(R.layout.fragment_my_league_home, container, false);

        joinLeagueFragment = (Button) view.findViewById(R.id.joinLeagueFragment_btn);
        createleagueFragment = (Button) view.findViewById(R.id.createleagueFragment_btn);
        joinLeagueFragment.setOnClickListener(this);
        createleagueFragment.setOnClickListener(this);

        // user ID and league info
        userTextView = view.findViewById(R.id.myLeagues_title);
        //login info
        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();

        if (user == null){
            userTextView.setText("Who Are You?");
        }
        else {
            userTextView.setText(user.getEmail());
        }


        ListView leagueLV = (ListView) view.findViewById(R.id.list_view_leagues);

        leagueArrayList = new ArrayList<>();

        db.collection("league")
                .whereEqualTo("league","FirstLeague")
                .get()
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


        return view;

    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.joinLeagueFragment_btn:
                fragment = new JoinLeague();
                replaceFragment(fragment);
                break;

            case R.id.createleagueFragment_btn:
                fragment = new CreateLeagueF();
                replaceFragment(fragment);
                break;
        }

    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}


//if (!queryDocumentSnapshots.isEmpty()){
//
//        List<DocumentSnapshot> leagueList = queryDocumentSnapshots.getDocuments();
//
//        for (DocumentSnapshot d : leagueList ){
//        League league = d.toObject(League.class);
//        leagueArrayList.add(league);
//        }
//
//        LeagueAdapter adapter = new LeagueAdapter(getContext(), leagueArrayList);
//
//        leagueLV.setAdapter(adapter);