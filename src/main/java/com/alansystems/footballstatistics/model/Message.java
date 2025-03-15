package com.alansystems.footballstatistics.model;

public class Message {
    private FootballResponseType type;
    private EventResult result;
    private Statistics getStatistics;

    public Message(FootballResponseType type, EventResult eventResult) {
        this.type = type;
        this.result = eventResult;
    }

    public Message(FootballResponseType type, Statistics statistics) {
        this.type = type;
        this.getStatistics = statistics;
    }

    public FootballResponseType getType() {
        return type;
    }

    public void setType(FootballResponseType type) {
        this.type = type;
    }

    public EventResult getResult() {
        return result;
    }

    public void setResult(EventResult result) {
        this.result = result;
    }

    public Statistics getGetStatistics() {
        return getStatistics;
    }

    public void setGetStatistics(Statistics getStatistics) {
        this.getStatistics = getStatistics;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", result=" + result +
                ", getStatistics=" + getStatistics +
                '}';
    }
}
