package com.alansystems.footballstatistics.service;

import com.alansystems.footballstatistics.model.EventStatuses;
import com.alansystems.footballstatistics.model.TeamStatistics;
import com.alansystems.footballstatistics.model.TeamsForStatistics;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode

@Service
public class StatisticsService {

    static Map<String, TeamStatistics> mapWithTeamStatistics = new HashMap<>();

    public void handleGetStatisticsMessage(TeamsForStatistics teamsForStatistics) {
        List<String> teams = teamsForStatistics.getTeams();
        for (String team : teams) {
            printAdvancedStatistics(mapWithTeamStatistics.get(team));
        }
        System.out.println();
    }

    public void printAdvancedStatistics(TeamStatistics teamStatistics) {
        StringBuilder result = new StringBuilder();

        if (teamStatistics.getThirdLastEventStatus() != EventStatuses.NONE) {
            result.append(teamStatistics.getThirdLastEventStatus());
        }
        if (teamStatistics.getSecondLastEventStatus() != EventStatuses.NONE) {
            result.append(teamStatistics.getSecondLastEventStatus());
        }
        if (teamStatistics.getLastEventStatus() != EventStatuses.NONE) {
            result.append(teamStatistics.getLastEventStatus());
        }
        System.out.println(teamStatistics.getName() + " " + result + " "
                + teamStatistics.getAverageAmountOfGoalsInAllEvents() + " "
                + teamStatistics.getNumberOfPlayedEvents() + " "
                + teamStatistics.getSumOfGainedPoints() + " " + teamStatistics.getSumOfGoalsScored() + " "
                + teamStatistics.getSumOfGoalsConceded());
    }




}
