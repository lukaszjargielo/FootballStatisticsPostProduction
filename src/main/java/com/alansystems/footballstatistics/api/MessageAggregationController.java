package com.alansystems.footballstatistics.api;

import com.alansystems.footballstatistics.model.MessageWithGetStatistics;
import com.alansystems.footballstatistics.model.MessageWithResult;
import com.alansystems.footballstatistics.service.MessageAggregationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RequestMapping("/api/v1/messages")
@RestController
public class MessageAggregationController {

    private MessageAggregationService messageAggregationService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public String processMessage(@RequestBody Object message) {
        if (message instanceof MessageWithResult) {
            MessageWithResult messageWithResult = (MessageWithResult) message;
            messageAggregationService.processMessageWithResult(messageWithResult);
            return "Processed RESULT message: " + messageWithResult.getResult().getHomeTeam() +
                    " " + messageWithResult.getResult().getAwayTeam() +
                    " " + messageWithResult.getResult().getHomeScore() +
                    " " + messageWithResult.getResult().getAwayScore();
        } else if (message instanceof MessageWithGetStatistics) {
            MessageWithGetStatistics messageWithGetStatistics = (MessageWithGetStatistics) message;
            messageAggregationService.processMessageWithGetStatistics(messageWithGetStatistics);
            return "Processed GET_STATISTICS message: " + messageWithGetStatistics.getGetStatistics().getTeams();
        } else {
            return "Invalid message type." + message;
        }
    }
}
