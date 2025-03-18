package com.alansystems.footballstatistics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventResult {
    @JsonProperty("home_team")
    private final String homeTeam;

    @JsonProperty("away_team")
    private final String awayTeam;

    @JsonProperty("home_score")
    private final Integer homeScore;

    @JsonProperty("away_score")
    private final Integer awayScore;

    public EventResult(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    @Override
    public String toString() {
        return "EventResult{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore +
                '}';
    }
}
