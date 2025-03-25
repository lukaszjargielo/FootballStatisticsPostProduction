package com.alansystems.footballstatistics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString

public class EventResult {
    @JsonProperty("home_team")
    private final String homeTeam;

    @JsonProperty("away_team")
    private final String awayTeam;

    @JsonProperty("home_score")
    private final Integer homeScore;

    @JsonProperty("away_score")
    private final Integer awayScore;

}
