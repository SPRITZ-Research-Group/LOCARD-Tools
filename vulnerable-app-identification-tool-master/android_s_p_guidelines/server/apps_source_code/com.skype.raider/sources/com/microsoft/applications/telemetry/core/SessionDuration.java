package com.microsoft.applications.telemetry.core;

enum SessionDuration {
    UNDEFINED(0),
    UP_TO_3_SEC(1),
    UP_TO_10_SEC(2),
    UP_TO_30_SEC(3),
    UP_TO_60_SEC(4),
    UP_TO_3_MIN(5),
    UP_TO_10_MIN(6),
    UP_TO_30_MIN(7),
    ABOVE_30_MIN(8);
    
    private final int val;

    private SessionDuration(int value) {
        this.val = value;
    }

    public final int getValue() {
        return this.val;
    }

    public final String toString() {
        switch (this.val) {
            case 0:
                return "Undefined";
            case 1:
                return "UpTo3Sec";
            case 2:
                return "UpTo10Sec";
            case 3:
                return "UpTo30Sec";
            case 4:
                return "UpTo60Sec";
            case 5:
                return "UpTo3Min";
            case 6:
                return "UpTo10Min";
            case 7:
                return "UpTo30Min";
            case 8:
                return "Above30Min";
            default:
                return null;
        }
    }
}
