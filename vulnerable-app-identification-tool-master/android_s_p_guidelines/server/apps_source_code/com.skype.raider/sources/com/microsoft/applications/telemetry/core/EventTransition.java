package com.microsoft.applications.telemetry.core;

enum EventTransition {
    TO_OFFLINE(0),
    OFFLINE_TO_FLIGHT(1),
    FLIGHT_TO_OFFLINE(2),
    TO_FLIGHT(3);
    
    private final int value;

    private EventTransition(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }
}
