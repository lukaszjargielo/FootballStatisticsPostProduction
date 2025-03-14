package com.alansystems.footballstatistics.model;


public class Team {
    private final String name;
    private int numberOfPlayedEvents;
    private int sumOfGainedPoints;
    private int sumOfGoalsScored;
    private int sumOfGoalsConceded;

    public Team(String name, int numberOfPlayedEvents, int sumOfGainedPoints, int sumOfGoalsScored, int sumOfGoalsConceded) {
        this.name = name;
        this.numberOfPlayedEvents = numberOfPlayedEvents;
        this.sumOfGainedPoints = sumOfGainedPoints;
        this.sumOfGoalsScored = sumOfGoalsScored;
        this.sumOfGoalsConceded = sumOfGoalsConceded;
    }

    public String getName() {
        return name;
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
