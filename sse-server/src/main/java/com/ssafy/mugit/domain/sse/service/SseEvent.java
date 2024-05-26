package com.ssafy.mugit.domain.sse.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SseEvent {
    CONNECT("connect"),
    @JsonProperty("follow")
    FOLLOW("follow");

    private final String eventName;
}
