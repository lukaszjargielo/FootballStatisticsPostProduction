package com.alansystems.footballstatistics.model;

import java.util.List;

public class TeamListForStatistics {
    private List<String> teams;

    public TeamListForStatistics(List<String> teams) {
        this.teams = teams;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }
}
