package com.alansystems.footballstatistics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;


public class TeamListForStatistics {
    @JsonProperty("teams")
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

    @Override
    public String toString() {
        return "TeamListForStatistics{" +
                "teams=" + teams +
                '}';
    }
}
