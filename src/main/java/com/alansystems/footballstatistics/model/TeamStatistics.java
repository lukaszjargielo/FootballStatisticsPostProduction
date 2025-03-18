package com.alansystems.footballstatistics.model;

public class TeamStatistics {
    private String name;
    private EventStatuses lastEventStatuses;
    private EventStatuses secondLastEventStatuses;
    private EventStatuses thirdLastEventStatuses;
    private double averageAmountOfGoalsInAllEvents; // sum of scored and conceded
    private int numberOfPlayedEvents;
    private int sumOfGainedPoints;
    private int sumOfGoalsScored;
    private int sumOfGoalsConceded;

    public TeamStatistics(String name, EventStatuses lastEventStatuses, EventStatuses secondLastEventStatuses, EventStatuses thirdLastEventStatuses, double averageAmountOfGoalsInTheTeamEvents, int numberOfPlayedEvents, int sumOfGainedPoints, int sumOfGoalsScored, int sumOfGoalsConceded) {
        this.name = name;
        this.lastEventStatuses = lastEventStatuses;
        this.secondLastEventStatuses = secondLastEventStatuses;
        this.thirdLastEventStatuses = thirdLastEventStatuses;
        this.averageAmountOfGoalsInAllEvents = averageAmountOfGoalsInTheTeamEvents;
        this.numberOfPlayedEvents = numberOfPlayedEvents;
        this.sumOfGainedPoints = sumOfGainedPoints;
        this.sumOfGoalsScored = sumOfGoalsScored;
        this.sumOfGoalsConceded = sumOfGoalsConceded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventStatuses getLastMatchResult() {
        return lastEventStatuses;
    }

    public void setLastMatchResult(EventStatuses lastEventStatuses) {
        this.lastEventStatuses = lastEventStatuses;
    }

    public EventStatuses getSecondLastMatchResult() {
        return secondLastEventStatuses;
    }

    public void setSecondLastMatchResult(EventStatuses secondLastEventStatuses) {
        this.secondLastEventStatuses = secondLastEventStatuses;
    }

    public EventStatuses getThirdLastMatchResult() {
        return thirdLastEventStatuses;
    }

    public void setThirdLastMatchResult(EventStatuses thirdLastEventStatuses) {
        this.thirdLastEventStatuses = thirdLastEventStatuses;
    }

    public double getAverageAmountOfGoalsInTheTeamEvents() {
        return averageAmountOfGoalsInAllEvents;
    }

    public void setAverageAmountOfGoalsInTheTeamEvents(double averageAmountOfGoalsInTheTeamEvents) {
        this.averageAmountOfGoalsInAllEvents = averageAmountOfGoalsInTheTeamEvents;
    }

    public int getNumberOfPlayedEvents() {
        return numberOfPlayedEvents;
    }

    public void setNumberOfPlayedEvents(int numberOfPlayedEvents) {
        this.numberOfPlayedEvents = numberOfPlayedEvents;
    }

    public int getSumOfGainedPoints() {
        return sumOfGainedPoints;
    }

    public void setSumOfGainedPoints(int sumOfGainedPoints) {
        this.sumOfGainedPoints = sumOfGainedPoints;
    }

    public int getSumOfGoalsScored() {
        return sumOfGoalsScored;
    }

    public void setSumOfGoalsScored(int sumOfGoalsScored) {
        this.sumOfGoalsScored = sumOfGoalsScored;
    }

    public int getSumOfGoalsConceded() {
        return sumOfGoalsConceded;
    }

    public void setSumOfGoalsConceded(int sumOfGoalsConceded) {
        this.sumOfGoalsConceded = sumOfGoalsConceded;
    }

    @Override
    public String toString() {
        return "TeamStatistics{" +
                "name='" + name + '\'' +
                ", lastEventStatuses=" + lastEventStatuses +
                ", secondLastEventStatuses=" + secondLastEventStatuses +
                ", thirdLastEventStatuses=" + thirdLastEventStatuses +
                ", averageAmountOfGoalsInAllEvents=" + averageAmountOfGoalsInAllEvents +
                ", numberOfPlayedEvents=" + numberOfPlayedEvents +
                ", sumOfGainedPoints=" + sumOfGainedPoints +
                ", sumOfGoalsScored=" + sumOfGoalsScored +
                ", sumOfGoalsConceded=" + sumOfGoalsConceded +
                '}';
    }

    public static void printSimpleStatistics(TeamStatistics teamStatistics) {
        System.out.println(teamStatistics.getName() + " " + teamStatistics.getNumberOfPlayedEvents() + " "
                + teamStatistics.getSumOfGainedPoints() + " " + teamStatistics.getSumOfGoalsScored() + " "
                + teamStatistics.getSumOfGoalsConceded());
    }

    public static void printAdvancedStatistics(TeamStatistics teamStatistics) {
        StringBuilder result = new StringBuilder();

        if (teamStatistics.getThirdLastMatchResult() != null) {
            result.append(teamStatistics.getThirdLastMatchResult());
        }
        if (teamStatistics.getSecondLastMatchResult() != null) {
            result.append(teamStatistics.getSecondLastMatchResult());
        }
        if (teamStatistics.getLastMatchResult() != null) {
            result.append(teamStatistics.getLastMatchResult());
        }
        System.out.println(teamStatistics.getName() + " " + result + " "
                + teamStatistics.getAverageAmountOfGoalsInTheTeamEvents() + " "
                + teamStatistics.getNumberOfPlayedEvents() + " "
                + teamStatistics.getSumOfGainedPoints() + " " + teamStatistics.getSumOfGoalsScored() + " "
                + teamStatistics.getSumOfGoalsConceded());
    }

}
