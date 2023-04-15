package com.example.soccerfantasy.Objects;

public class League {

    private String leagueName;

    private String adminId;

    private String password;

    private int playerCount;

    private boolean draftDate;

    private boolean draftStatus;


    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public League() {

    }
    // created getter and setter methods
    // for all our variables.


    public boolean getDraftDate(){return draftDate;};

    public boolean getDraftStatus(){return draftStatus;};

    public void setDraftDate(boolean draftDate){this.draftDate = draftDate;};

    public void setDraftStatus(boolean draftStatus){this.draftStatus = draftStatus;};

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
