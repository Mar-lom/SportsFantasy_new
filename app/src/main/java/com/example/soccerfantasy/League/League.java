package com.example.soccerfantasy.League;

public class League {



    private String leagueName;

    private String admin_id;

    private String password_league;

    private String player_count;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public League() {

    }
    // created getter and setter methods
    // for all our variables.

    public String getAdminId() {
        return admin_id;
    }

    public void setAdminId(String admin_id) {

        this.admin_id =  admin_id;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setLeaguePassword(String password_league) {
        this.password_league = password_league;
    }

    public void setPlayerCount(String player_count) {
        this.player_count = player_count;
    }


}
