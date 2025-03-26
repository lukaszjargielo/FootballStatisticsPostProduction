package com.alansystems.footballstatistics.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class MessageWithResult {
    private MessageType type;
    private EventResult result;
}
