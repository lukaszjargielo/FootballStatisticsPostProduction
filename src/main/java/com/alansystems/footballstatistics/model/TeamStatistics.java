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


    public static void printSimpleStatistics(TeamStatistics teamStatistics) {
        System.out.println(teamStatistics.getName() + " " + teamStatistics.getNumberOfPlayedEvents() + " "
                + teamStatistics.getSumOfGainedPoints() + " " + teamStatistics.getSumOfGoalsScored() + " "
                + teamStatistics.getSumOfGoalsConceded());
    }

    public static void printAdvancedStatistics(TeamStatistics teamStatistics) {
        StringBuilder result = new StringBuilder();

        if (teamStatistics.getThirdLastEventStatus() != EventStatuses.UNDEFINED) {
            result.append(teamStatistics.getThirdLastEventStatus());
        }
        if (teamStatistics.getSecondLastEventStatus() != EventStatuses.UNDEFINED) {
            result.append(teamStatistics.getSecondLastEventStatus());
        }
        if (teamStatistics.getLastEventStatus() != EventStatuses.UNDEFINED) {
            result.append(teamStatistics.getLastEventStatus());
        }
        System.out.println(teamStatistics.getName() + " " + result + " "
                + teamStatistics.getAverageAmountOfGoalsInAllEvents() + " "
                + teamStatistics.getNumberOfPlayedEvents() + " "
                + teamStatistics.getSumOfGainedPoints() + " " + teamStatistics.getSumOfGoalsScored() + " "
                + teamStatistics.getSumOfGoalsConceded());
    }
}
