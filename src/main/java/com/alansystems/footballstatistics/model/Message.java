package com.alansystems.footballstatistics.model;

public class Message {
    private MessageType type;
    private EventResult result;
    private TeamStatistics getStatistics;

    public Message(MessageType type, EventResult eventResult) {
        this.type = type;
        this.result = eventResult;
    }

    public Message(MessageType type, TeamStatistics teamStatistics) {
        this.type = type;
        this.getStatistics = teamStatistics;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public EventResult getResult() {
        return result;
    }

    public void setResult(EventResult result) {
        this.result = result;
    }

    public TeamStatistics getGetStatistics() {
        return getStatistics;
    }

    public void setGetStatistics(TeamStatistics getTeamStatistics) {
        this.getStatistics = getTeamStatistics;
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
