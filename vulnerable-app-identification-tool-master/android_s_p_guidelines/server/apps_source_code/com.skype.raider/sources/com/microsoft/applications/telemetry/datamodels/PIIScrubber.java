package com.microsoft.applications.telemetry.datamodels;

public enum PIIScrubber {
    NotSet(0),
    O365(1),
    SkypeBI(2),
    SkypeData(3),
    __INVALID_ENUM_VALUE(4);
    
    private final int value;

    private PIIScrubber(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }

    public static PIIScrubber fromValue(int value) {
        switch (value) {
            case 0:
                return NotSet;
            case 1:
                return O365;
            case 2:
                return SkypeBI;
            case 3:
                return SkypeData;
            default:
                return __INVALID_ENUM_VALUE;
        }
    }
}
