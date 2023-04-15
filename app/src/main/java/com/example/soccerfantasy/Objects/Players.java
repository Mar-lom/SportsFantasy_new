package com.example.soccerfantasy.Objects;

public class Players {

    public String id;

     private String first_name;

     public String last_name;

     public String assists;

     public String goals;
     public int points;

     public String red;

     public String yellow;

    public String double_yellow;

    public String position;

    public Players(){}

    public Players(String id, String firstName, int points,  String position){

        this.id = id;
        this.first_name = firstName;
        this.points = points;
        this.position = position;

    }

    public String getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }

    public String getPlayerId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }


    public void setPoints(int points) {
        this.points = points;
    }

    public void setName(String name) {
        this.first_name = name;
    }

    public void setPlayerId(String id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getAssists() {
        return assists;
    }

    public String getDouble_yellow() {
        return double_yellow;
    }

    public String getLast_name_name() {
        return first_name;
    }

    public String getGoals() {
        return goals;
    }

    public String getRed() {
        return red;
    }

    public String getYellow() {
        return yellow;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public void setDouble_yellow(String double_yellow) {
        this.double_yellow = double_yellow;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }

    public void setYellow(String yellow) {
        this.yellow = yellow;
    }

    public void setRed(String red) {
        this.red = red;
    }


}



