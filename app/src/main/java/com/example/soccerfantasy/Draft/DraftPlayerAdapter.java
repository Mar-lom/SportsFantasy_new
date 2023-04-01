package com.example.soccerfantasy.Draft;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccerfantasy.Objects.Players;
import com.example.soccerfantasy.Objects.Team;
import com.example.soccerfantasy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DraftPlayerAdapter extends ArrayAdapter<Players> {
    public DraftPlayerAdapter(@NonNull Context context, ArrayList<Players> arrayPlayerList) {
        super(context,0, arrayPlayerList);


    }
    //get the refference and query to the teamRoster

    CollectionReference teamNameRef;
    Query query;

    String currentTeamName;

    //player id
    String id;

    //firebase connections
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    FirebaseUser user;

    Map<String, Object>  draftedPlayer;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_row, parent, false);
        }
        //GET TEAM NAME
        teamNameRef = db.collection("team");
        query = teamNameRef.whereEqualTo("userId", user.getEmail());

        getTeamName();

        Players player = getItem(position); // lookinto this

        //initialize the UI componenets
        TextView playerName = convertView.findViewById(R.id.playerPosition);
        TextView playerPosition = convertView.findViewById(R.id.playerName);
        TextView playerDraftStatus = convertView.findViewById(R.id.playersAvailablity);

        //Setting data.
        playerName.setText(player.getName());
        playerPosition.setText(player.getPosition());

        playerDraftStatus.setText(true ? "Available" : "Taken"); // check

        //click player
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getContext(), "Player Choosen  is : " + player.getName() + " " + player.getPlayerId() + "Picked"  , Toast.LENGTH_SHORT).show();

                //add player to team roster;
                draftedPlayer = new HashMap<>();
                draftedPlayer.put("teamName", currentTeamName);
                draftedPlayer.put("id", player.getPlayerId());

                CallTeamRoster();

                //make them disapear on click


                //Players playerDrafted = new Players(player.getPlayerId(), player.getName(),player.getPoints(),player.getPosition());
                //db.collection("player").document("LA").set(playerDrafted);
                //draftedPlayer.put("userId", user);

                // on click use a function to send data to team roster.
                //add player to teamroster
                //add data to list

                //Map<String, Object> selectedPlayer = new HashMap<>();
                //selectedPlayer.put("playerId", player.getPlayerId());
                //selectedPlayer.put("teamId",   );


                //db.collection("teamRoster").document("");


                //remove player from thelist / make player invisable
                // next persons turn.

            }
        });


        return convertView;

        //return super.getView(position, convertView, parent);


    }

    public void CallTeamRoster(){
        db.collection("teamRoster")
                .add(draftedPlayer)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

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

                            }
                        } else {

                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }



//change back to string


}
