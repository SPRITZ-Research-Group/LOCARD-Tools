package com.microsoft.applications.telemetry;

public enum AppLifecycleState {
    UNKNOWN(0),
    LAUNCH(1),
    EXIT(2),
    SUSPEND(3),
    RESUME(4),
    FOREGROUND(5),
    BACKGROUND(6);
    
    private final int val;

    private AppLifecycleState(int value) {
        this.val = value;
    }

    public final int getValue() {
        return this.val;
    }

    public final String toString() {
        switch (this.val) {
            case 0:
                return "Unknown";
            case 1:
                return "Launch";
            case 2:
                return "Exit";
            case 3:
                return "Suspend";
            case 4:
                return "Resume";
            case 5:
                return "Foreground";
            case 6:
                return "Background";
            default:
                return "";
        }
    }
}
