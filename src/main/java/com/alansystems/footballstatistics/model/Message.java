package com.alansystems.footballstatistics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Message {
    private MessageType type;
    private EventResult result;

    @JsonProperty("get_statistics")
    private TeamListForStatistics getStatistics;

    public Message() {
    }

    public Message(MessageType type, EventResult eventResult) {
        this.type = type;
        this.result = eventResult;
    }

    public Message(MessageType type, TeamListForStatistics teamStatistics) {
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

    public TeamListForStatistics getGetStatistics() {
        return getStatistics;
    }

    public void setGetStatistics(TeamListForStatistics getTeamStatistics) {
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
