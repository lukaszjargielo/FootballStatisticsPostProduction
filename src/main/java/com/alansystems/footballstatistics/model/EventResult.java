package com.alansystems.footballstatistics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class EventResult {

    private  String homeTeam;
    private  String awayTeam;
    private  Integer homeScore;
    private  Integer awayScore;

}
