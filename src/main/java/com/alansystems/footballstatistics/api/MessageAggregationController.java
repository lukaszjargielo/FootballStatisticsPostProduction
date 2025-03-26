package com.alansystems.footballstatistics.api;

import com.alansystems.footballstatistics.model.Message;
import com.alansystems.footballstatistics.service.MessageAggregationService;
import com.alansystems.footballstatistics.utils.MessageProcessor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RequestMapping("/api/v1/messages")
@RestController
public class MessageAggregationController {

    private MessageAggregationService messageAggregationService;

    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        return messageAggregationService.processMessage(message);
    }
}
