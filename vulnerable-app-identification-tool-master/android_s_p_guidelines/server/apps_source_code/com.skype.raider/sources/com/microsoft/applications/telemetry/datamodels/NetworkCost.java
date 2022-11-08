package com.microsoft.applications.telemetry.datamodels;

public enum NetworkCost {
    UNKNOWN(0),
    UNMETERED(1),
    METERED(2),
    OVER_DATA_LIMIT(3);
    
    private final int cost;

    private NetworkCost(int value) {
        this.cost = value;
    }

    public final int getValue() {
        return this.cost;
    }

    public final String toString() {
        switch (this.cost) {
            case 0:
                return "Unknown";
            case 1:
                return "Unmetered";
            case 2:
                return "Metered";
            case 3:
                return "OverDataLimit";
            default:
                return null;
        }
    }
}
