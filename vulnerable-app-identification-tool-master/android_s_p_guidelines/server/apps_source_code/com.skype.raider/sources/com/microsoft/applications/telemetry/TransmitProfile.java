package com.microsoft.applications.telemetry;

public enum TransmitProfile {
    REAL_TIME(0),
    NEAR_REAL_TIME(1),
    BEST_EFFORT(2);
    
    final int value;

    private TransmitProfile(int val) {
        this.value = val;
    }

    public final int getValue() {
        return this.value;
    }

    public final String toString() {
        switch (this.value) {
            case 0:
                return "RealTime";
            case 1:
                return "NearRealTime";
            case 2:
                return "BestEffort";
            default:
                return "";
        }
    }
}
