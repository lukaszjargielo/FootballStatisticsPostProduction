package com.alansystems.footballstatistics.utils;

import com.alansystems.footballstatistics.model.*;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alansystems.footballstatistics.model.TeamStatistics.printAdvancedStatistics;
import static com.alansystems.footballstatistics.model.TeamStatistics.printSimpleStatistics;
@EqualsAndHashCode

@Component
public class MessageProcessor {

    Map<String, TeamStatistics> mapWithTeamStatistics = new HashMap<>();

    public void processMessage(List<Message> messages) {
        for (Message message : messages) {
            if (message.getType() == (MessageType.RESULT)) {
                handleResultMessage(message.getResult());
            } else if (message.getType() == (MessageType.GET_STATISTICS)) {
                handleGetStatisticsMessage(message.getGetStatistics());
            }
        }
    }

    private void handleResultMessage(EventResult eventResult) {
        String homeTeam = eventResult.getHomeTeam();
        String awayTeam = eventResult.getAwayTeam();
        int homeScore = eventResult.getHomeScore();
        int awayScore = eventResult.getAwayScore();

        TeamStatistics homeTeamObj = processTeam(homeTeam, homeScore, awayScore);
        TeamStatistics awayTeamObj = processTeam(awayTeam, awayScore, homeScore);

        updatePointsAndEventStatus(homeTeamObj, awayTeamObj, homeScore, awayScore);

        updateAverageGoals(homeTeamObj);
        updateAverageGoals(awayTeamObj);

        printSimpleStatistics(homeTeamObj);
        printSimpleStatistics(awayTeamObj);
        System.out.println();
    }

    private void handleGetStatisticsMessage(TeamsForStatistics teamsForStatistics) {
        List<String> teams = teamsForStatistics.getTeams();
        for (String team : teams) {
            printAdvancedStatistics(mapWithTeamStatistics.get(team));
        }
        System.out.println();
    }

    private TeamStatistics processTeam(String teamName, int goalsScored, int goalsConceded) {
        TeamStatistics teamObj;

        if (!mapWithTeamStatistics.containsKey(teamName)) {
            teamObj = new TeamStatistics(teamName,
                    EventStatuses.UNDEFINED,
                    EventStatuses.UNDEFINED,
                    EventStatuses.UNDEFINED,
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

    private void updatePointsAndEventStatus(TeamStatistics homeTeamObj, TeamStatistics awayTeamObj, int homeScore,
                                            int awayScore) {
        int homeTeamGainedPoints = 0;
        int awayTeamGainedPoints = 0;

        EventStatuses homeTeamLastEventStatus;
        EventStatuses awayTeamLastEventStatus;

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

            homeTeamObj.setLastEventStatus(homeTeamLastEventStatus);
            awayTeamObj.setLastEventStatus(awayTeamLastEventStatus);

            homeTeamObj.setSumOfGainedPoints(homeTeamObj.getSumOfGainedPoints() + homeTeamGainedPoints);
            awayTeamObj.setSumOfGainedPoints(awayTeamObj.getSumOfGainedPoints() + awayTeamGainedPoints);

    }

    protected void updateAverageGoals(TeamStatistics teamObj) {
        double averageGoals = Math.round(((teamObj.getSumOfGoalsScored() + teamObj.getSumOfGoalsConceded()) * 1.0 / teamObj.getNumberOfPlayedEvents()) * 100.0) / 100.0;
        teamObj.setAverageAmountOfGoalsInAllEvents(averageGoals);
    }

    private void updateTeamStatistics(TeamStatistics teamObj, int goalsScored, int goalsConceded) {
        teamObj.setThirdLastEventStatus(teamObj.getSecondLastEventStatus());
        teamObj.setSecondLastEventStatus(teamObj.getLastEventStatus());
        teamObj.setNumberOfPlayedEvents(teamObj.getNumberOfPlayedEvents() + 1);
        teamObj.setSumOfGoalsScored(teamObj.getSumOfGoalsScored() + goalsScored);
        teamObj.setSumOfGoalsConceded(teamObj.getSumOfGoalsConceded() + goalsConceded);
    }
}

