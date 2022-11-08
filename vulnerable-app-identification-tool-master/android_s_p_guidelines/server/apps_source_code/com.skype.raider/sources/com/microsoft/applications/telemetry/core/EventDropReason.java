package com.microsoft.applications.telemetry.core;

enum EventDropReason {
    BAD_TENANT(0),
    BAD_TENANT_OFFLINE(1),
    OFFLINE_FULL(2),
    OFFLINE_FAIL(3),
    SERIALIZATION_FAIL_OFFLINE(4),
    EVENT_CORRUPT(6);
    
    private final int value;

    private EventDropReason(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }
}
