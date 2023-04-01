package com.example.soccerfantasy.Objects;

public class Players {

    public String id;

     public String first_name_new;

     public int points;

    public String position;

    public Players(){}

    public Players(String id, String firstName, int points,  String position){

        this.id = id;
        this.first_name_new = firstName;
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

    public String getName() {
        return first_name_new;
    }


    public void setPoints(int points) {
        this.points = points;
    }

    public void setName(String name) {
        this.first_name_new = name;
    }

    public void setPlayerId(String id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}



