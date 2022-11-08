package com.microsoft.applications.telemetry.core;

public enum TransmitCondition {
    UNKNOWN(-1),
    METERED_BATTERY(0),
    METERED_AC(1),
    UNMETERED_BATTERY(2),
    UNMETERED_AC(3);
    
    private final int value;

    private TransmitCondition(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }
}
