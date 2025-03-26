package com.alansystems.footballstatistics.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class MessageWithGetStatistics {
    private MessageType type;
    private TeamsForStatistics getStatistics;
}
