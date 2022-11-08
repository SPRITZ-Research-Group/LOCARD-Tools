package com.microsoft.applications.telemetry.datamodels;

public enum PowerSource {
    UNKNOWN(0),
    BATTERY(1),
    AC(2);
    
    private final int source;

    private PowerSource(int value) {
        this.source = value;
    }

    public final int getValue() {
        return this.source;
    }
}
