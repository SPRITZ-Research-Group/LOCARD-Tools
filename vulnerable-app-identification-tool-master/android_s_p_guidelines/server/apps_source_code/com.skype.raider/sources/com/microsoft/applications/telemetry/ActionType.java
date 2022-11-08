package com.microsoft.applications.telemetry;

public enum ActionType {
    UNSPECIFIED(0),
    UNKNOWN(1),
    OTHER(2),
    CLICK(3),
    PAN(5),
    ZOOM(6),
    HOVER(7);
    
    private final int val;

    private ActionType(int value) {
        this.val = value;
    }

    public final int getValue() {
        return this.val;
    }

    public final String toString() {
        switch (this.val) {
            case 0:
                return "Unspecified";
            case 1:
                return "Unknown";
            case 2:
                return "Other";
            case 3:
                return "Click";
            case 5:
                return "Pan";
            case 6:
                return "Zoom";
            case 7:
                return "Hover";
            default:
                return "";
        }
    }
}
