package com.example.soccerfantasy;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.soccerfantasy.League.Players;
import com.example.soccerfantasy.adapter.PlayerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;


public class Draft extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //Collection Name
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_draft, container, false);

        ArrayList<Players> playersArrayList = new ArrayList<>();
        //Players players1 = new Players("Marco", true);
        //Players players2 = new Players("Diego", true);

        //playersArrayList.add(players2);

        //request players from db
        db.collection("players")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {

                                Players players1 = document.toObject(Players.class);

                                //String txt = players1.getName();

                                playersArrayList.add(players1);


                                RecyclerView rvPlayers = view.findViewById(R.id.rvPlayer);

                                rvPlayers.setLayoutManager(new LinearLayoutManager(view.getContext()));

                                rvPlayers.setHasFixedSize(true);

                                PlayerAdapter adapter = new PlayerAdapter(getContext(),playersArrayList);

                                rvPlayers.setAdapter(adapter);


                                adapter.notifyDataSetChanged();



                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });




//
//        adapter = new Adapter(getContext(), playerList);
//
//
        return view;


    }












        }







