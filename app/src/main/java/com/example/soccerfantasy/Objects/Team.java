package com.example.soccerfantasy.Objects;

public class Team {

    public String leagueName;

    public String teamName;

    public String userId;

    private String points;

    public Team(){

    }
    
    public Team(String leagueName, String teamName, String userId) {

        this.leagueName = leagueName;

        this.teamName = teamName;

        this.userId = userId;

    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getUserId() {
        return userId;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPoints(){ return points; }

}

