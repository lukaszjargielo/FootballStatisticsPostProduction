package com.alansystems.footballstatistics.api;


import com.alansystems.footballstatistics.utils.MessageProcessor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AllArgsConstructor

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
public class RestController {

    private MessageProcessor messageProcessor;

    @GetMapping("/team-statistics")
    public String getTeamStatistics(@RequestParam List<String> teams) {


    }

}
