package com.alansystems.footballstatistics.model;


public class TeamStatistics implements TeamData{
    private EventStatuses lastEventStatuses;
    private EventStatuses secondLastEventStatuses;
    private EventStatuses thirdLastEventStatuses;
    private double averageAmountOfGoalsInAllEvents; // sum of scored and conceded
    private int numberOfPlayedEvents;
    private int sumOfGainedPoints;
    private int sumOfGoalsScored;
    private int sumOfGoalsConceded;

    public TeamStatistics(EventStatuses lastEventStatuses, EventStatuses secondLastEventStatuses, EventStatuses thirdLastEventStatuses, double averageAmountOfGoalsInTheTeamEvents, int numberOfPlayedEvents, int sumOfGainedPoints, int sumOfGoalsScored, int sumOfGoalsConceded) {
        this.lastEventStatuses = lastEventStatuses;
        this.secondLastEventStatuses = secondLastEventStatuses;
        this.thirdLastEventStatuses = thirdLastEventStatuses;
        this.averageAmountOfGoalsInAllEvents = averageAmountOfGoalsInTheTeamEvents;
        this.numberOfPlayedEvents = numberOfPlayedEvents;
        this.sumOfGainedPoints = sumOfGainedPoints;
        this.sumOfGoalsScored = sumOfGoalsScored;
        this.sumOfGoalsConceded = sumOfGoalsConceded;
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
}
