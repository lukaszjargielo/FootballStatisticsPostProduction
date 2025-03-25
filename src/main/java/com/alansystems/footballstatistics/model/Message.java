package com.alansystems.footballstatistics.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Component
public class Message {

    private MessageType type;
    private EventResult result;
    private TeamListForStatistics getStatistics;

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
