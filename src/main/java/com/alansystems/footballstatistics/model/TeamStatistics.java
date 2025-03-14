package com.alansystems.footballstatistics.model;

public class TeamStatistics {
    private char lastMatchResult;
    private char secondLastMatchResult;
    private char thirdLastMatchResult;
    private double averageAmountOfGoalsInTheTeamEvents; // sum of scored and conceded
    private int numberOfPlayedEvents;
    private int sumOfGainedPoints;
    private int sumOfGoalsScored;
    private int sumOfGoalsConceded;

    public TeamStatistics(char lastMatchResult, char secondLastMatchResult, char thirdLastMatchResult, double averageAmountOfGoalsInTheTeamEvents, int numberOfPlayedEvents, int sumOfGainedPoints, int sumOfGoalsScored, int sumOfGoalsConceded) {
        this.lastMatchResult = lastMatchResult;
        this.secondLastMatchResult = secondLastMatchResult;
        this.thirdLastMatchResult = thirdLastMatchResult;
        this.averageAmountOfGoalsInTheTeamEvents = averageAmountOfGoalsInTheTeamEvents;
        this.numberOfPlayedEvents = numberOfPlayedEvents;
        this.sumOfGainedPoints = sumOfGainedPoints;
        this.sumOfGoalsScored = sumOfGoalsScored;
        this.sumOfGoalsConceded = sumOfGoalsConceded;
    }

    public char getLastMatchResult() {
        return lastMatchResult;
    }

    public void setLastMatchResult(char lastMatchResult) {
        this.lastMatchResult = lastMatchResult;
    }

    public char getSecondLastMatchResult() {
        return secondLastMatchResult;
    }

    public void setSecondLastMatchResult(char secondLastMatchResult) {
        this.secondLastMatchResult = secondLastMatchResult;
    }

    public char getThirdLastMatchResult() {
        return thirdLastMatchResult;
    }

    public void setThirdLastMatchResult(char thirdLastMatchResult) {
        this.thirdLastMatchResult = thirdLastMatchResult;
    }

    public double getAverageAmountOfGoalsInTheTeamEvents() {
        return averageAmountOfGoalsInTheTeamEvents;
    }

    public void setAverageAmountOfGoalsInTheTeamEvents(double averageAmountOfGoalsInTheTeamEvents) {
        this.averageAmountOfGoalsInTheTeamEvents = averageAmountOfGoalsInTheTeamEvents;
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
