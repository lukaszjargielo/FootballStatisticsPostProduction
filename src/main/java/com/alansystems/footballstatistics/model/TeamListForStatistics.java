package com.alansystems.footballstatistics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString

public class TeamListForStatistics {
    @JsonProperty("teams")
    private List<String> teams;

}
