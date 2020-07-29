package com.makersLabTech.SystemEvents.dto;

import com.makersLabTech.SystemEvents.enums.EventType;

import java.time.LocalDateTime;

public class EventDTO {

    private EventType eventType;
    private String eventPayload;
    private LocalDateTime eventTime;

    public EventDTO(EventType eventType, String eventPayload, LocalDateTime eventTime) {
        this.eventType = eventType;
        this.eventPayload = eventPayload;
        this.eventTime = eventTime;
    }

    public EventDTO() {

    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getEventPayload() {
        return eventPayload;
    }

    public void setEventPayload(String eventPayload) {
        this.eventPayload = eventPayload;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
}
