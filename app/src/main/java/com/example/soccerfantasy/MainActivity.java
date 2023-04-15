package com.example.soccerfantasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.soccerfantasy.Login.Login;
import com.example.soccerfantasy.databinding.ActivityMainBinding;
import com.example.soccerfantasy.myLeague.myLeagueHome;
import com.example.soccerfantasy.myTeam.myTeamHome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseAuth auth;

    TextView textView;
    FirebaseUser user;

    SharedPreferences sharedpreferences; // Instead of alwasy calling to the database.. switch to sharded preferences.
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeScreen());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeScreen());
                    break;
                case R.id.myLeague:
                    replaceFragment(new myLeagueHome());
                    break;
                case R.id.myTeam:
                    replaceFragment(new myTeamHome());
                    break;
            }
            return true;
        });

        sharedpreferences = getSharedPreferences("UserAccountId", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        //login info
        auth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.user_details);

         // local storage
        user = auth.getCurrentUser();

        //basic authentication
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
            String accountName = user.getEmail();
            //save for later -- not implemented yet.
            editor.putString("UserAccountId", user.getEmail());
            editor.commit();

        }

    }

    //fragment manager to manage the fragments
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


    public void onLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();

    }



}