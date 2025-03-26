package com.alansystems.footballstatistics.service;

import com.alansystems.footballstatistics.model.MessageType;
import com.alansystems.footballstatistics.model.MessageWithGetStatistics;
import com.alansystems.footballstatistics.model.MessageWithResult;
import com.alansystems.footballstatistics.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageAggregationService {

    private ResultService resultService;
    private StatisticsService statisticsService;

        public String processMessageWithResult(MessageWithResult messageWithResult) {
            resultService.handleResultMessage(messageWithResult.getResult());
        return "";
    }

    public String processMessageWithGetStatistics(MessageWithGetStatistics messageWithGetStatistics) {
            statisticsService.handleGetStatisticsMessage(messageWithGetStatistics.getGetStatistics());
        return "";
    }




}

