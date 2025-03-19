package com.alansystems.footballstatistics.utils;

import com.alansystems.footballstatistics.model.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alansystems.footballstatistics.model.TeamStatistics.printAdvancedStatistics;
import static com.alansystems.footballstatistics.model.TeamStatistics.printSimpleStatistics;

@Component
public class MessageProcessor {

    private MessageProcessor() {}

    static Map<String, TeamStatistics> mapWithTeamStatistics = new HashMap<>();

    public static void processMessage(List<Message> messages) {
        for (Message message : messages) {
            if (message.getType().equals(MessageType.RESULT)) {
                handleResultMessage(message.getResult());
            } else if (message.getType().equals(MessageType.GET_STATISTICS)) {
                handleGetStatisticsMessage(message.getGetStatistics());
            }
        }
    }

    private static void handleResultMessage(EventResult eventResult) {
        String homeTeam = eventResult.getHomeTeam();
        String awayTeam = eventResult.getAwayTeam();
        int homeScore = eventResult.getHomeScore();
        int awayScore = eventResult.getAwayScore();

        TeamStatistics homeTeamObj = processTeam(homeTeam, homeScore, awayScore, true);
        TeamStatistics awayTeamObj = processTeam(awayTeam, awayScore, homeScore, false);

        updatePointsAndStatus(homeTeamObj, awayTeamObj, homeScore, awayScore);

        updateAverageGoals(homeTeamObj);
        updateAverageGoals(awayTeamObj);

        printSimpleStatistics(homeTeamObj);
        printSimpleStatistics(awayTeamObj);
        System.out.println();
    }

    private static void handleGetStatisticsMessage(TeamListForStatistics teamListForStatistics) {
        List<String> teams = teamListForStatistics.getTeams();
        for (String team : teams) {
            printAdvancedStatistics(mapWithTeamStatistics.get(team));
        }
        System.out.println();
    }

    private static TeamStatistics processTeam(String teamName, int goalsScored, int goalsConceded, boolean isHomeTeam) {
        TeamStatistics teamObj;

        if (!mapWithTeamStatistics.containsKey(teamName)) {
            teamObj = new TeamStatistics(teamName,
                    null,
                    null,
                    null,
                    0.0,
                    1,
                    0,
                    goalsScored,
                    goalsConceded);
            mapWithTeamStatistics.put(teamName, teamObj);
        } else {
            teamObj = mapWithTeamStatistics.get(teamName);
            updateTeamStatistics(teamObj, goalsScored, goalsConceded);
        }
        return teamObj;
    }

    private static void updatePointsAndStatus(TeamStatistics homeTeamObj, TeamStatistics awayTeamObj, int homeScore,
                                              int awayScore) {
        int homeTeamGainedPoints = 0;
        int awayTeamGainedPoints = 0;

        EventStatuses homeTeamLastEventStatus = null;
        EventStatuses awayTeamLastEventStatus = null;

        if (homeScore == awayScore) {
            homeTeamGainedPoints++;
            awayTeamGainedPoints++;
            homeTeamLastEventStatus = EventStatuses.D;
            awayTeamLastEventStatus = EventStatuses.D;
        } else if (homeScore > awayScore) {
            homeTeamGainedPoints += 3;
            homeTeamLastEventStatus = EventStatuses.W;
            awayTeamLastEventStatus = EventStatuses.L;
        } else {
            awayTeamGainedPoints += 3;
            homeTeamLastEventStatus = EventStatuses.L;
            awayTeamLastEventStatus = EventStatuses.W;
        }

        homeTeamObj.setLastMatchResult(homeTeamLastEventStatus);
        awayTeamObj.setLastMatchResult(awayTeamLastEventStatus);

        homeTeamObj.setSumOfGainedPoints(homeTeamObj.getSumOfGainedPoints() + homeTeamGainedPoints);
        awayTeamObj.setSumOfGainedPoints(awayTeamObj.getSumOfGainedPoints() + awayTeamGainedPoints);
    }

    private static void updateAverageGoals(TeamStatistics teamObj) {
        double averageGoals = Math.round(((teamObj.getSumOfGoalsScored() + teamObj.getSumOfGoalsConceded()) * 1.0 / teamObj.getNumberOfPlayedEvents()) * 100.0) / 100.0;
        teamObj.setAverageAmountOfGoalsInTheTeamEvents(averageGoals);
    }

    private static void updateTeamStatistics(TeamStatistics teamObj, int goalsScored, int goalsConceded) {
        teamObj.setThirdLastMatchResult(teamObj.getSecondLastMatchResult());
        teamObj.setSecondLastMatchResult(teamObj.getLastMatchResult());
        teamObj.setNumberOfPlayedEvents(teamObj.getNumberOfPlayedEvents() + 1);
        teamObj.setSumOfGoalsScored(teamObj.getSumOfGoalsScored() + goalsScored);
        teamObj.setSumOfGoalsConceded(teamObj.getSumOfGoalsConceded() + goalsConceded);
    }

}
