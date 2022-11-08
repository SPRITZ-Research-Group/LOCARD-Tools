package com.microsoft.applications.telemetry;

public enum EventPriority {
    UNSPECIFIED(-1),
    LOW(1),
    NORMAL(2),
    HIGH(3),
    IMMEDIATE(4);
    
    private final int val;

    private EventPriority(int value) {
        this.val = value;
    }

    public final int getValue() {
        return this.val;
    }

    public static EventPriority fromValue(int value) {
        switch (value) {
            case -1:
                return UNSPECIFIED;
            case 1:
                return LOW;
            case 2:
                return NORMAL;
            case 3:
                return HIGH;
            case 4:
                return IMMEDIATE;
            default:
                throw new IllegalArgumentException("Unknown EventPriority value: " + value);
        }
    }

    public final String toString() {
        switch (this.val) {
            case -1:
                return "Unspecified";
            case 1:
                return "Low";
            case 2:
                return "Normal";
            case 3:
                return "High";
            case 4:
                return "Immediate";
            default:
                return "";
        }
    }
}
