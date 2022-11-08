package com.microsoft.applications.telemetry;

public enum AggregateType {
    SUM(0),
    MAXIMUM(1),
    MINIMUM(2),
    SUM_OF_SQUARES(3);
    
    private final int val;

    private AggregateType(int value) {
        this.val = value;
    }

    public final int getValue() {
        return this.val;
    }

    public final String toString() {
        switch (this.val) {
            case 0:
                return "Sum";
            case 1:
                return "Maximum";
            case 2:
                return "Minimum";
            case 3:
                return "SumOfSquares";
            default:
                return "";
        }
    }
}
