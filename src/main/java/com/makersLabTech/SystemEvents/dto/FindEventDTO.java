package com.makersLabTech.SystemEvents.dto;

import com.makersLabTech.SystemEvents.enums.EventType;

import java.time.LocalDateTime;

public class FindEventDTO {

    private EventType eventType;
    private LocalDateTime eventTime;

    public FindEventDTO(EventType eventType, LocalDateTime eventTime) {
        this.eventType = eventType;
        this.eventTime = eventTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
}
