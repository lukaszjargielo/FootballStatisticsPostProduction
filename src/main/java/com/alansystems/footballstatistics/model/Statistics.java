package com.alansystems.footballstatistics.model;

public class Statistics {
    private MatchResult lastMatchResult;
    private MatchResult secondLastMatchResult;
    private MatchResult thirdLastMatchResult;
    private double averageAmountOfGoalsInAllEvents; // sum of scored and conceded
    private int numberOfPlayedEvents;
    private int sumOfGainedPoints;
    private int sumOfGoalsScored;
    private int sumOfGoalsConceded;

    public Statistics(MatchResult lastMatchResult, MatchResult secondLastMatchResult, MatchResult thirdLastMatchResult, double averageAmountOfGoalsInTheTeamEvents, int numberOfPlayedEvents, int sumOfGainedPoints, int sumOfGoalsScored, int sumOfGoalsConceded) {
        this.lastMatchResult = lastMatchResult;
        this.secondLastMatchResult = secondLastMatchResult;
        this.thirdLastMatchResult = thirdLastMatchResult;
        this.averageAmountOfGoalsInAllEvents = averageAmountOfGoalsInTheTeamEvents;
        this.numberOfPlayedEvents = numberOfPlayedEvents;
        this.sumOfGainedPoints = sumOfGainedPoints;
        this.sumOfGoalsScored = sumOfGoalsScored;
        this.sumOfGoalsConceded = sumOfGoalsConceded;
    }

    public MatchResult getLastMatchResult() {
        return lastMatchResult;
    }

    public void setLastMatchResult(MatchResult lastMatchResult) {
        this.lastMatchResult = lastMatchResult;
    }

    public MatchResult getSecondLastMatchResult() {
        return secondLastMatchResult;
    }

    public void setSecondLastMatchResult(MatchResult secondLastMatchResult) {
        this.secondLastMatchResult = secondLastMatchResult;
    }

    public MatchResult getThirdLastMatchResult() {
        return thirdLastMatchResult;
    }

    public void setThirdLastMatchResult(MatchResult thirdLastMatchResult) {
        this.thirdLastMatchResult = thirdLastMatchResult;
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
