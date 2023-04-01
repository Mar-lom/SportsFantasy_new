package com.example.soccerfantasy.myLeague;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.soccerfantasy.Objects.League;
import com.example.soccerfantasy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class CreateLeagueF extends Fragment {

    private EditText leagueNameEdit , passwordEdit, playerCountEdit ;

    private Button sendToDb;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //DatabaseReference db;

    League league;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  =  inflater.inflate(R.layout.fragment_create_league, container, false);


        //CollectionReference usersCollectionRef = db.collection("users");

        leagueNameEdit = view.findViewById(R.id.league_name);
        passwordEdit = view.findViewById(R.id.league_password);
        playerCountEdit = view.findViewById(R.id.league_player_count);

        //leagueNameEdit = view.findViewById(R.id.league_name);


        sendToDb = view.findViewById(R.id.btn_create_league);


        league = new League();

        sendToDb.setOnClickListener((new View.OnClickListener() {
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
                String count = playerCountEdit.getText().toString();

                //league.setLeagueName(leagueName);
                //league.setLeaguePassword(password);
                //league.setPlayerCount(count);

                //saveToDatabase(leagueName,password,count,id);

                Map<String, Object> leagues = new HashMap<>();
                leagues.put("league_name", leagueName);
                leagues.put("admin_id", uid);
                leagues.put("password", password);
                leagues.put("player_count", count);


                db.collection("league").document(leagueName)
                        .set(leagues)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Context context = getContext();
                                Log.d(TAG, "DocumentSnapshot successfully written!");

                                Toast.makeText(context, "League Created: Visit My League to access settings", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });


            }
        }));


        return view;
    }

    private void saveToDatabase(String name, String password, String count , String id){

    }




}