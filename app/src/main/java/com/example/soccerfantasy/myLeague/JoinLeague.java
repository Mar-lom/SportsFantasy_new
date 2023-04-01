package com.example.soccerfantasy.myLeague;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.soccerfantasy.Objects.League;
import com.example.soccerfantasy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class JoinLeague extends Fragment {


    private EditText leagueNameEdit , passwordEdit, playerCountEdit ;
    private Button joinLeague;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //DatabaseReference db;

    League league;




    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_join_league, container, false);

        leagueNameEdit = view.findViewById(R.id.league_name);
        passwordEdit = view.findViewById(R.id.league_password);
        joinLeague = view.findViewById(R.id.btn_join_league);

        league = new League();

        joinLeague.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uid = null;

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    for (UserInfo profile : user.getProviderData()) {
                        // Id of the provider (ex: google.com)
                        String providerId = profile.getProviderId();

                        // UID specific to the provider
                        uid = profile.getUid();

                        // Name, email address, and profile photo Url
                        String name = profile.getDisplayName();
                        String email = profile.getEmail();
                    }
                }

                String leagueName = leagueNameEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                //get league info
                //DocumentReference docRef = db.collection("league").document(leagueName);

                db.collection("league")
                .whereEqualTo("league", leagueName)
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


            }
        }));



        return view;
    }
}