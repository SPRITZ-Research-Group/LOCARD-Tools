package com.microsoft.applications.telemetry.datamodels;

public enum NetworkType {
    UNKNOWN(0),
    WIRED(1),
    WIFI(2),
    WWAN(3);
    
    private final int type;

    private NetworkType(int value) {
        this.type = value;
    }

    public final int getValue() {
        return this.type;
    }

    public final String toString() {
        switch (this.type) {
            case 0:
                return "Unknown";
            case 1:
                return "Wired";
            case 2:
                return "Wifi";
            case 3:
                return "WWAN";
            default:
                return null;
        }
    }
}
