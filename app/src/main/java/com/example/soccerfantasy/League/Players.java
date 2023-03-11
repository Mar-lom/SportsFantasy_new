package com.example.soccerfantasy.League;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Players {

     public String name;
     public boolean draft;


    public Players(){}


    public Players(String name, boolean draft){

        this.name = name;
        this.draft = draft;

    }


    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public boolean getDraft(){
        return draft;
    }

    public void setDraft(boolean newDraft){
        this.draft = newDraft;
    }





    //private static int lastContactId = 0;

//    public static ArrayList<Players> createPlayerList(int numPlayers){
//        ArrayList<Players> players = new ArrayList<Players>();
//
//        for (int i = 1; i <= numPlayers; i++) {
//            players.add(new Players("Player " + ++lastContactId, i <= numPlayers / 2));
//        }
//
//        return players;
//    }
}
