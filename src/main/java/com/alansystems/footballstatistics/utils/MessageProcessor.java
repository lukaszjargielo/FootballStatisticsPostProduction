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
    static Map<String, TeamStatistics> mapWithTeamStatistics = new HashMap<>();

    public static void processMessage(List<Message> messages) {
        for (Message message : messages) {
            if (message.getType().equals(MessageType.RESULT)) {
                EventResult eventResult = message.getResult();

                String homeTeam = eventResult.getHomeTeam();
                String awayTeam = eventResult.getAwayTeam();
                int homeScore = eventResult.getHomeScore();
                int awayScore = eventResult.getAwayScore();

                int homeTeamGainedPoints = 0;
                int awayTeamGainedPoints = 0;

                int numberOfPlayedEvents = 1;

                int sumOfGoalsInEvent = homeScore + awayScore;

                double homeTeamAverageAmountOfGoalsInAllEvents;
                homeTeamAverageAmountOfGoalsInAllEvents = sumOfGoalsInEvent / numberOfPlayedEvents;

                double awayTeamAverageAmountOfGoalsInAllEvents;
                awayTeamAverageAmountOfGoalsInAllEvents = sumOfGoalsInEvent / numberOfPlayedEvents;

                EventStatuses homeTeamLastEventStatus = null;
                EventStatuses homeTeamSecondLastEventStatus = null;
                EventStatuses homeTeamThirdLastEventStatus = null;

                EventStatuses awayTeamLastEventStatus = null;
                EventStatuses awayTeamSecondLastEventStatus = null;
                EventStatuses awayTeamThirdLastEventStatus = null;

                if (homeScore == awayScore) {
                    awayTeamGainedPoints++;
                    homeTeamGainedPoints++;

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

                if (!mapWithTeamStatistics.containsKey(homeTeam)) {
                    mapWithTeamStatistics.put(homeTeam, new TeamStatistics(homeTeam, homeTeamLastEventStatus, homeTeamSecondLastEventStatus, homeTeamThirdLastEventStatus, homeTeamAverageAmountOfGoalsInAllEvents, numberOfPlayedEvents, homeTeamGainedPoints, homeScore, awayScore));
                    printSimpleStatistics(mapWithTeamStatistics.get(homeTeam));
                } else {
                    TeamStatistics existingObject = mapWithTeamStatistics.get(homeTeam);

                    existingObject.setThirdLastMatchResult(existingObject.getSecondLastMatchResult());
                    existingObject.setSecondLastMatchResult(existingObject.getLastMatchResult());
                    existingObject.setLastMatchResult(homeTeamLastEventStatus);

                    existingObject.setNumberOfPlayedEvents(existingObject.getNumberOfPlayedEvents() + numberOfPlayedEvents);

                    existingObject.setSumOfGainedPoints(existingObject.getSumOfGainedPoints() + homeTeamGainedPoints);

                    existingObject.setSumOfGoalsScored(existingObject.getSumOfGoalsScored() + homeScore);

                    existingObject.setSumOfGoalsConceded(existingObject.getSumOfGoalsConceded() + awayScore);

                    existingObject.setAverageAmountOfGoalsInTheTeamEvents(((existingObject.getSumOfGoalsScored() * 1.0) + existingObject.getSumOfGoalsConceded()) / existingObject.getNumberOfPlayedEvents());

                    printSimpleStatistics(existingObject);
                }

                if (!mapWithTeamStatistics.containsKey(awayTeam)) {
                    mapWithTeamStatistics.put(awayTeam, new TeamStatistics(awayTeam, awayTeamLastEventStatus, awayTeamSecondLastEventStatus, awayTeamThirdLastEventStatus, awayTeamAverageAmountOfGoalsInAllEvents, numberOfPlayedEvents, awayTeamGainedPoints, awayScore, homeScore));
                    printSimpleStatistics(mapWithTeamStatistics.get(awayTeam));
                } else {
                    TeamStatistics existingObject = mapWithTeamStatistics.get(awayTeam);

                    existingObject.setThirdLastMatchResult(existingObject.getSecondLastMatchResult());
                    existingObject.setSecondLastMatchResult(existingObject.getLastMatchResult());
                    existingObject.setLastMatchResult(awayTeamLastEventStatus);

                    existingObject.setNumberOfPlayedEvents(existingObject.getNumberOfPlayedEvents() + numberOfPlayedEvents);

                    existingObject.setSumOfGainedPoints(existingObject.getSumOfGainedPoints() + awayTeamGainedPoints);

                    existingObject.setSumOfGoalsScored(existingObject.getSumOfGoalsScored() + awayScore);

                    existingObject.setSumOfGoalsConceded(existingObject.getSumOfGoalsConceded() + homeScore);

                    existingObject.setAverageAmountOfGoalsInTheTeamEvents(((existingObject.getSumOfGoalsScored() * 1.0) + existingObject.getSumOfGoalsConceded()) / existingObject.getNumberOfPlayedEvents());

                    printSimpleStatistics(existingObject);
                    ;
                }
            } else if (message.getType().equals(MessageType.GET_STATISTICS)) {
                TeamListForStatistics teamListForStatistics = message.getGetStatistics();
                List<String> teams = teamListForStatistics.getTeams();
                for (String team : teams) {
                    printAdvancedStatistics(mapWithTeamStatistics.get(team));
                }
            }
        }


    }
}