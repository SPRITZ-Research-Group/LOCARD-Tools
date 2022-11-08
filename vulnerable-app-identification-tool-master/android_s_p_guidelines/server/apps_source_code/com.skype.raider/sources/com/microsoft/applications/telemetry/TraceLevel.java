package com.microsoft.applications.telemetry;

public enum TraceLevel {
    NONE(0),
    ERROR(1),
    WARNING(2),
    INFORMATION(3),
    VERBOSE(4);
    
    private final int val;

    private TraceLevel(int value) {
        this.val = value;
    }

    public final int getValue() {
        return this.val;
    }

    public final String toString() {
        switch (this.val) {
            case 0:
                return "None";
            case 1:
                return "Error";
            case 2:
                return "Warning";
            case 3:
                return "Information";
            case 4:
                return "Verbose";
            default:
                return "";
        }
    }
}
