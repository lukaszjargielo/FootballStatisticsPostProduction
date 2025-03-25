package com.alansystems.footballstatistics.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter

@Component
public class Message {
    private MessageType type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EventResult result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
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



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Message{");

        if (type != null) {
            sb.append("type=").append(type).append(", ");
        }
        if (result != null) {
            sb.append("result=").append(result).append(", ");
        }
        if (getStatistics != null) {
            sb.append("getStatistics=").append(getStatistics);
        }
        if (sb.charAt(sb.length() - 1) == ' ') {
            sb.delete(sb.length() - 2, sb.length());
        }

        sb.append('}');
        return sb.toString();
    }
}
