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

                TeamStatistics homeTeamObj = null;
                TeamStatistics awayTeamObj = null;

                int homeTeamNumberOfPlayedEvents = 1;
                int awayTeamNumberOfPlayedEvents = 1;

                int homeTeamSumOfGoalsScored = homeScore;
                int awayTeamSumOfGoalsScored = awayScore;

                int homeTeamSumOfGoalsConceded = awayScore;
                int awayTeamSumOfGoalsConceded = homeScore;

                EventStatuses homeTeamLastEventStatus = null;
                EventStatuses homeTeamSecondLastEventStatus = null;
                EventStatuses homeTeamThirdLastEventStatus = null;

                EventStatuses awayTeamLastEventStatus = null;
                EventStatuses awayTeamSecondLastEventStatus = null;
                EventStatuses awayTeamThirdLastEventStatus = null;

                if (mapWithTeamStatistics.containsKey(homeTeam)) {
                    homeTeamObj = mapWithTeamStatistics.get(homeTeam);
                    homeTeamNumberOfPlayedEvents = homeTeamObj.getNumberOfPlayedEvents() + homeTeamNumberOfPlayedEvents;
                    homeTeamSumOfGoalsScored = homeTeamObj.getSumOfGoalsScored() + homeTeamSumOfGoalsScored;
                    homeTeamSumOfGoalsConceded = homeTeamObj.getSumOfGoalsConceded() + homeTeamSumOfGoalsConceded;
                    homeTeamThirdLastEventStatus = homeTeamObj.getSecondLastMatchResult();
                    homeTeamSecondLastEventStatus = homeTeamObj.getLastMatchResult();
                }

                if (mapWithTeamStatistics.containsKey(awayTeam)) {
                    awayTeamObj = mapWithTeamStatistics.get(awayTeam);
                    awayTeamNumberOfPlayedEvents = awayTeamObj.getNumberOfPlayedEvents() + awayTeamNumberOfPlayedEvents;
                    awayTeamSumOfGoalsScored = awayTeamObj.getSumOfGoalsScored() + awayTeamSumOfGoalsScored;
                    awayTeamSumOfGoalsConceded = awayTeamObj.getSumOfGoalsConceded() + awayTeamSumOfGoalsConceded;
                    awayTeamThirdLastEventStatus = awayTeamObj.getSecondLastMatchResult();
                    awayTeamSecondLastEventStatus = awayTeamObj.getLastMatchResult();
                }

                int homeTeamGainedPoints = 0;
                int awayTeamGainedPoints = 0;

                double homeTeamAverageAmountOfGoalsInAllEvents;
                homeTeamAverageAmountOfGoalsInAllEvents = Math.round((homeTeamSumOfGoalsScored + homeTeamSumOfGoalsConceded) * 1.0 / homeTeamNumberOfPlayedEvents * 100.0) / 100.0;

                double awayTeamAverageAmountOfGoalsInAllEvents;
                awayTeamAverageAmountOfGoalsInAllEvents = Math.round((awayTeamSumOfGoalsScored + awayTeamSumOfGoalsConceded) * 1.0 / awayTeamNumberOfPlayedEvents * 100.0) / 100.0;

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
                    mapWithTeamStatistics.put(homeTeam, new TeamStatistics(homeTeam, homeTeamLastEventStatus, homeTeamSecondLastEventStatus, homeTeamThirdLastEventStatus, homeTeamAverageAmountOfGoalsInAllEvents, homeTeamNumberOfPlayedEvents, homeTeamGainedPoints, homeScore, awayScore));
                    printSimpleStatistics(mapWithTeamStatistics.get(homeTeam));
                } else {
                    TeamStatistics existingObject = mapWithTeamStatistics.get(homeTeam);

                    existingObject.setThirdLastMatchResult(existingObject.getSecondLastMatchResult());
                    existingObject.setSecondLastMatchResult(existingObject.getLastMatchResult());
                    existingObject.setLastMatchResult(homeTeamLastEventStatus);

                    existingObject.setNumberOfPlayedEvents(existingObject.getNumberOfPlayedEvents() + homeTeamNumberOfPlayedEvents);

                    existingObject.setSumOfGainedPoints(existingObject.getSumOfGainedPoints() + homeTeamGainedPoints);

                    existingObject.setSumOfGoalsScored(existingObject.getSumOfGoalsScored() + homeScore);

                    existingObject.setSumOfGoalsConceded(existingObject.getSumOfGoalsConceded() + awayScore);

                    existingObject.setAverageAmountOfGoalsInTheTeamEvents(((existingObject.getSumOfGoalsScored() * 1.0) + existingObject.getSumOfGoalsConceded()) / existingObject.getNumberOfPlayedEvents());

                    printSimpleStatistics(existingObject);
                }

                if (!mapWithTeamStatistics.containsKey(awayTeam)) {
                    mapWithTeamStatistics.put(awayTeam, new TeamStatistics(awayTeam, awayTeamLastEventStatus, awayTeamSecondLastEventStatus, awayTeamThirdLastEventStatus, awayTeamAverageAmountOfGoalsInAllEvents, awayTeamNumberOfPlayedEvents, awayTeamGainedPoints, awayScore, homeScore));
                    printSimpleStatistics(mapWithTeamStatistics.get(awayTeam));
                } else {
                    TeamStatistics existingObject = mapWithTeamStatistics.get(awayTeam);

                    existingObject.setThirdLastMatchResult(existingObject.getSecondLastMatchResult());
                    existingObject.setSecondLastMatchResult(existingObject.getLastMatchResult());
                    existingObject.setLastMatchResult(awayTeamLastEventStatus);

                    existingObject.setNumberOfPlayedEvents(existingObject.getNumberOfPlayedEvents() + awayTeamNumberOfPlayedEvents);

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