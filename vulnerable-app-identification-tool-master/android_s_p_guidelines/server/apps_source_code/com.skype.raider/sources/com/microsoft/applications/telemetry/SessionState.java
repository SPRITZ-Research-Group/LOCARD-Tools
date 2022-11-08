package com.microsoft.applications.telemetry;

public enum SessionState {
    STARTED(0),
    ENDED(1);
    
    private final int val;

    private SessionState(int value) {
        this.val = value;
    }

    public final int getValue() {
        return this.val;
    }

    public final String toString() {
        switch (this.val) {
            case 0:
                return "Started";
            case 1:
                return "Ended";
            default:
                return "";
        }
    }
}
