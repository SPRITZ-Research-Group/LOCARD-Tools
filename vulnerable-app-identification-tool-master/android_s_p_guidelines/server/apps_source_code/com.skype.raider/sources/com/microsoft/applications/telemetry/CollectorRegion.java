package com.microsoft.applications.telemetry;

public enum CollectorRegion {
    DEFAULT(0),
    UNITED_STATES(1),
    GERMANY(2),
    AUSTRALIA(3),
    JAPAN(4),
    EUROPE(5);
    
    private final int val;

    private CollectorRegion(int value) {
        this.val = value;
    }

    public final int getValue() {
        return this.val;
    }
}
