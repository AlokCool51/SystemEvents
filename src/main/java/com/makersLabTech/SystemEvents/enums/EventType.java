package com.makersLabTech.SystemEvents.enums;

public enum EventType {

    Registered("Registered"), NonRegistered("NonRegistered");

    private String eventType;

    EventType(String eventType) {
        this.eventType = eventType;
    }
}
