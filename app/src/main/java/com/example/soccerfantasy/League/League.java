package com.example.soccerfantasy.League;

public class League {

    private String leagueName;

    private String adminId;

    private String password;

    private int playerCount;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public League() {

    }
    // created getter and setter methods
    // for all our variables.

    public String getAdminId() {
        return adminId;
    }

    public String getLeagueName() {return leagueName;}

    public String getPassword(){return password;}

    public  int getPlayerCount(){return playerCount;}


    public void setAdminId(String admin_id) {

        this.adminId =  admin_id;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setLeaguePassword(String password_league) {
        this.password = password_league;
    }

    public void setPlayerCount(int player_count) {
        this.playerCount = playerCount;
    }


}
