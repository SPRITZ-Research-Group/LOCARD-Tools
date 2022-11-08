package com.microsoft.applications.telemetry;

public enum InputDeviceType {
    UNSPECIFIED(0),
    UNKNOWN(1),
    OTHER(2),
    MOUSE(3),
    KEYBOARD(4),
    TOUCH(5),
    STYLUS(6),
    MICROPHONE(7),
    KINECT(8),
    CAMERA(9);
    
    private final int val;

    private InputDeviceType(int value) {
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
                return "Mouse";
            case 4:
                return "Keyboard";
            case 5:
                return "Touch";
            case 6:
                return "Stylus";
            case 7:
                return "Microphone";
            case 8:
                return "Kinect";
            case 9:
                return "Camera";
            default:
                return "";
        }
    }
}
