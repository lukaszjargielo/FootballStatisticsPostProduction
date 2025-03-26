package com.alansystems.footballstatistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString

public class TeamStatistics {

    private String name;
    private EventStatuses lastEventStatus;
    private EventStatuses secondLastEventStatus;
    private EventStatuses thirdLastEventStatus;
    private double averageAmountOfGoalsInAllEvents;
    private int numberOfPlayedEvents;
    private int sumOfGainedPoints;
    private int sumOfGoalsScored;
    private int sumOfGoalsConceded;

}
