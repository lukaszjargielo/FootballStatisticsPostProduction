package com.alansystems.footballstatistics.utils;

import com.alansystems.footballstatistics.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageProcessor {
    Map<String, TeamInformation> mapWithTeamInformation = new HashMap<>();
    Map<String, TeamStatistics> mapWithTeamStatistics = new HashMap<>();

    public void processMessage(List<Message> messages) {
        for (Message message : messages) {
            if (message.getType().equals(MessageType.RESULT)) {
                EventResult eventResult = message.getResult();
                String homeTeam = eventResult.getHomeTeam();
                String awayTeam = eventResult.getAwayTeam();
                int homeScore = eventResult.getHomeScore();
                int awayScore = eventResult.getAwayScore();

                int gainedPointsHomeTeam = 0;
                int gainedPointsAwayTeam = 0;
                EventStatuses lastEventStatusHomeTeam;
                EventStatuses lastEventStatusAwayTeam;

                if (homeScore == awayScore) {
                    gainedPointsAwayTeam++;
                    gainedPointsHomeTeam++;
                    lastEventStatusHomeTeam = EventStatuses.D;
                    lastEventStatusAwayTeam = EventStatuses.D;
                } else if (homeScore > awayScore) {
                    gainedPointsHomeTeam += 3;
                    lastEventStatusHomeTeam = EventStatuses.W;
                    lastEventStatusAwayTeam = EventStatuses.L;
                } else {
                    gainedPointsAwayTeam += 3;
                    lastEventStatusAwayTeam = EventStatuses.W;
                    lastEventStatusHomeTeam = EventStatuses.L;
                }

                if (!mapWithTeamInformation.containsKey(homeTeam)) {
                    mapWithTeamInformation.put(homeTeam, new TeamInformation(homeTeam, 1, gainedPointsHomeTeam, homeScore, awayScore);
                } else {
                    TeamInformation existingObject = mapWithTeamInformation.get(homeTeam);
                    existingObject.setNumberOfPlayedEvents(existingObject.getNumberOfPlayedEvents() + 1);
                    existingObject.setSumOfGainedPoints(existingObject.getSumOfGainedPoints() + gainedPointsHomeTeam);
                    existingObject.setSumOfGoalsScored(existingObject.getSumOfGoalsScored() + homeScore);
                    existingObject.setSumOfGoalsConceded(existingObject.getSumOfGoalsConceded() + awayScore);
                }

                if (!mapWithTeamInformation.containsKey(awayTeam)) {
                    mapWithTeamInformation.put(awayTeam, new TeamInformation(awayTeam, 1, gainedPointsAwayTeam, awayScore, homeScore);
                } else {
                    TeamInformation existingObject = mapWithTeamInformation.get(awayTeam);
                    existingObject.setNumberOfPlayedEvents(existingObject.getNumberOfPlayedEvents() + 1);
                    existingObject.setSumOfGainedPoints(existingObject.getSumOfGainedPoints() + gainedPointsAwayTeam);
                    existingObject.setSumOfGoalsScored(existingObject.getSumOfGoalsScored() + awayScore);
                    existingObject.setSumOfGoalsConceded(existingObject.getSumOfGoalsConceded() + homeScore);
                }
            } else if (message.getType().equals(MessageType.GET_STATISTICS)) {
                TeamListForStatistics teamListForStatistics = message.getGetStatistics();
                List<String> teams = teamListForStatistics.getTeams();
            }
        }


    }
}