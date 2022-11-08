package com.microsoft.applications.telemetry;

public enum UserState {
    UNKNOWN(0),
    CONNECTED(1),
    REACHABLE(2),
    SIGNED_IN(3),
    SIGNED_OUT(4);
    
    private final int val;

    private UserState(int value) {
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
                return "Connected";
            case 2:
                return "Reachable";
            case 3:
                return "SignedIn";
            case 4:
                return "SignedOut";
            default:
                return "";
        }
    }
}
