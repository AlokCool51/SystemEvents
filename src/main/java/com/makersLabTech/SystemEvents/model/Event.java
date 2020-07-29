package com.makersLabTech.SystemEvents.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.makersLabTech.SystemEvents.enums.EventType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@DynamoDBTable(tableName = "Event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @DynamoDBHashKey(attributeName = "eventId")
    @DynamoDBAutoGeneratedKey
    private String eventId;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute
    private EventType eventType;

    @DynamoDBAttribute
    private String eventPayload;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId) &&
                eventType == event.eventType &&
                Objects.equals(eventPayload, event.eventPayload) &&
                Objects.equals(eventTime, event.eventTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventType, eventPayload, eventTime);
    }

    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime eventTime;

    public Event(EventType eventType, String eventPayload, LocalDateTime eventTime) {
        this.eventType = eventType;
        this.eventPayload = eventPayload;
        this.eventTime = eventTime;
    }

    public Event() {

    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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


    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventType=" + eventType +
                ", eventPayload='" + eventPayload + '\'' +
                ", eventTime=" + eventTime +
                '}';
    }

}