package com.microsoft.applications.telemetry.core;

enum EventRejectedReason {
    UNKNOWN(0),
    EVENT_NAME_MISSING(1),
    EVENT_SIZE_LIMIT_EXCEEDED_WHEN_STORING_OFFLINE(2),
    VALIDATION_FAIL(3);
    
    private final int value;

    private EventRejectedReason(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }
}
