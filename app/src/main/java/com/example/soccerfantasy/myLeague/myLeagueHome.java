package com.example.soccerfantasy.myLeague;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.soccerfantasy.CreateLeagueF;
import com.example.soccerfantasy.JoinLeague;
import com.example.soccerfantasy.Login;
import com.example.soccerfantasy.R;
import com.example.soccerfantasy.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class myLeagueHome extends Fragment implements View.OnClickListener {

    View view;

    TextView userTextView;

    Button joinLeagueFragment, createleagueFragment;

    FirebaseAuth auth;

    FirebaseUser user;

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

        userTextView = view.findViewById(R.id.myLeagues_id);
        //login info
        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();

        if (user == null){
            userTextView.setText("Who Are You?");
        }
        else {
            userTextView.setText(user.getEmail());
        }


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