package com.example.soccerfantasy.Objects;

import com.example.soccerfantasy.Objects.Team;

public class TeamRosterClass extends Team {

    String playerId;

    public TeamRosterClass(){

    }

    public TeamRosterClass(String leagueName, String teamName, String userId, String playerId) {
            super(leagueName,teamName,userId);
            this.playerId = playerId;

    }

    public String getPlayerId() {
        return playerId;
    }



}
