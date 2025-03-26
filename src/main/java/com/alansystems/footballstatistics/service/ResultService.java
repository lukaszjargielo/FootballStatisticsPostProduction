package com.alansystems.footballstatistics.service;

import com.alansystems.footballstatistics.model.EventResult;
import com.alansystems.footballstatistics.model.EventStatuses;
import com.alansystems.footballstatistics.model.TeamStatistics;
import org.springframework.stereotype.Service;

import static com.alansystems.footballstatistics.service.StatisticsService.mapWithTeamStatistics;

@Service
public class ResultService {

    public void handleResultMessage(EventResult eventResult) {
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

    public TeamStatistics processTeam(String teamName, int goalsScored, int goalsConceded) {
        TeamStatistics teamObj;

        if (!mapWithTeamStatistics.containsKey(teamName)) {
            teamObj = new TeamStatistics(teamName,
                    EventStatuses.NONE,
                    EventStatuses.NONE,
                    EventStatuses.NONE,
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

    public void updateTeamStatistics(TeamStatistics teamObj, int goalsScored, int goalsConceded) {
        teamObj.setThirdLastEventStatus(teamObj.getSecondLastEventStatus());
        teamObj.setSecondLastEventStatus(teamObj.getLastEventStatus());
        teamObj.setNumberOfPlayedEvents(teamObj.getNumberOfPlayedEvents() + 1);
        teamObj.setSumOfGoalsScored(teamObj.getSumOfGoalsScored() + goalsScored);
        teamObj.setSumOfGoalsConceded(teamObj.getSumOfGoalsConceded() + goalsConceded);
    }

    public void updatePointsAndEventStatus(TeamStatistics homeTeamObj, TeamStatistics awayTeamObj, int homeScore,
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

    public void updateAverageGoals(TeamStatistics teamObj) {
        double averageGoals = Math.round(((teamObj.getSumOfGoalsScored() + teamObj.getSumOfGoalsConceded()) * 1.0 / teamObj.getNumberOfPlayedEvents()) * 100.0) / 100.0;
        teamObj.setAverageAmountOfGoalsInAllEvents(averageGoals);
    }

    public void printSimpleStatistics(TeamStatistics teamStatistics) {
        System.out.println(teamStatistics.getName() + " " + teamStatistics.getNumberOfPlayedEvents() + " "
                + teamStatistics.getSumOfGainedPoints() + " " + teamStatistics.getSumOfGoalsScored() + " "
                + teamStatistics.getSumOfGoalsConceded());
    }
}
