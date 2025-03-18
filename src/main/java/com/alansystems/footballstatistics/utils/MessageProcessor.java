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

                TeamStatistics homeTeamObj;
                TeamStatistics awayTeamObj;

                if (!mapWithTeamStatistics.containsKey(homeTeam)) {
                    mapWithTeamStatistics.put(homeTeam,
                            new TeamStatistics(homeTeam,
                                    null,
                                    null,
                                    null,
                                    0.0,
                                    1,
                                    0,
                                    homeScore,
                                    awayScore));
                    homeTeamObj = mapWithTeamStatistics.get(homeTeam);
                } else {
                    homeTeamObj = mapWithTeamStatistics.get(homeTeam);

                    homeTeamObj.setThirdLastMatchResult(homeTeamObj.getSecondLastMatchResult());
                    homeTeamObj.setSecondLastMatchResult(homeTeamObj.getLastMatchResult());

                    homeTeamObj.setNumberOfPlayedEvents(homeTeamObj.getNumberOfPlayedEvents() + 1);

                    homeTeamObj.setSumOfGoalsScored(homeTeamObj.getSumOfGoalsScored() + homeScore);
                    homeTeamObj.setSumOfGoalsConceded(homeTeamObj.getSumOfGoalsConceded() + awayScore);
                }

                if (!mapWithTeamStatistics.containsKey(awayTeam)) {
                    mapWithTeamStatistics.put(awayTeam,
                            new TeamStatistics(awayTeam,
                                    null,
                                    null,
                                    null,
                                    0.0,
                                    1,
                                    0,
                                    awayScore,
                                    homeScore));
                    awayTeamObj = mapWithTeamStatistics.get(awayTeam);
                } else {
                    awayTeamObj = mapWithTeamStatistics.get(awayTeam);

                    awayTeamObj.setThirdLastMatchResult(awayTeamObj.getSecondLastMatchResult());
                    awayTeamObj.setSecondLastMatchResult(awayTeamObj.getLastMatchResult());

                    awayTeamObj.setNumberOfPlayedEvents(awayTeamObj.getNumberOfPlayedEvents() + 1);

                    awayTeamObj.setSumOfGoalsScored(awayTeamObj.getSumOfGoalsScored() + awayScore);
                    awayTeamObj.setSumOfGoalsConceded(awayTeamObj.getSumOfGoalsConceded() + homeScore);
                }

                int homeTeamGainedPoints = 0;
                int awayTeamGainedPoints = 0;

                EventStatuses homeTeamLastEventStatus = null;
                EventStatuses awayTeamLastEventStatus = null;

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

                homeTeamObj.setLastMatchResult(homeTeamLastEventStatus);
                awayTeamObj.setLastMatchResult(awayTeamLastEventStatus);

                homeTeamObj.setSumOfGainedPoints(homeTeamObj.getSumOfGainedPoints() + homeTeamGainedPoints);
                awayTeamObj.setSumOfGainedPoints(awayTeamObj.getSumOfGainedPoints() + awayTeamGainedPoints);

                double homeTeamAverageAmountOfGoalsInAllEvents;
                homeTeamAverageAmountOfGoalsInAllEvents = Math.round(((homeTeamObj.getSumOfGoalsScored() + homeTeamObj.getSumOfGoalsConceded()) * 1.0 / homeTeamObj.getNumberOfPlayedEvents()) * 100.0) / 100.0;
                homeTeamObj.setAverageAmountOfGoalsInTheTeamEvents(homeTeamAverageAmountOfGoalsInAllEvents);

                double awayTeamAverageAmountOfGoalsInAllEvents;
                awayTeamAverageAmountOfGoalsInAllEvents = Math.round(((awayTeamObj.getSumOfGoalsScored() + awayTeamObj.getSumOfGoalsConceded()) * 1.0 / awayTeamObj.getNumberOfPlayedEvents()) * 100.0) / 100.0;
                awayTeamObj.setAverageAmountOfGoalsInTheTeamEvents(awayTeamAverageAmountOfGoalsInAllEvents);

                printSimpleStatistics(homeTeamObj);
                printSimpleStatistics(awayTeamObj);
                System.out.println();

            } else if (message.getType().equals(MessageType.GET_STATISTICS)) {
                TeamListForStatistics teamListForStatistics = message.getGetStatistics();
                List<String> teams = teamListForStatistics.getTeams();
                for (String team : teams) {
                    printAdvancedStatistics(mapWithTeamStatistics.get(team));
                }
                System.out.println();
            }
        }
    }
}