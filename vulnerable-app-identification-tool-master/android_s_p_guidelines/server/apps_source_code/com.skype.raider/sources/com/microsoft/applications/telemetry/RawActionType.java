package com.microsoft.applications.telemetry;

public enum RawActionType {
    UNSPECIFIED(0),
    UNKNOWN(1),
    OTHER(2),
    L_BUTTON_DOUBLE_CLICK(11),
    L_BUTTON_DOWN(12),
    L_BUTTON_UP(13),
    M_BUTTON_DOUBLE_CLICK(14),
    M_BUTTON_DOWN(15),
    M_BUTTON_UP(16),
    MOUSE_HOVER(17),
    MOUSE_WHEEL(18),
    MOUSE_MOVE(20),
    R_BUTTON_DOUBLE_CLICK(22),
    R_BUTTON_DOWN(23),
    R_BUTTON_UP(24),
    TOUCH_TAP(50),
    TOUCH_DOUBLE_TAP(51),
    TOUCH_LONG_PRESS(52),
    TOUCH_SCROLL(53),
    TOUCH_PAN(54),
    TOUCH_FLICK(55),
    TOUCH_PINCH(56),
    TOUCH_ZOOM(57),
    TOUCH_ROTATE(58),
    KEYBOARD_PRESS(100),
    KEYBOARD_ENTER(101);
    
    private final int val;

    private RawActionType(int value) {
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
            case 11:
                return "LButtonDoubleClick";
            case 12:
                return "LButtonDown";
            case 13:
                return "LButtonUp";
            case 14:
                return "MButtonDoubleClick";
            case 15:
                return "MButtonDown";
            case 16:
                return "MButtonUp";
            case 17:
                return "MouseHover";
            case 18:
                return "MouseHWheel";
            case 20:
                return "MouseMove";
            case 21:
                return "MouseWheel";
            case 22:
                return "RButtonDoubleClick";
            case 23:
                return "RButtonDown";
            case 24:
                return "RButtonUp";
            case 50:
                return "TouchTap";
            case 51:
                return "TouchDoubleTap";
            case 52:
                return "TouchLongPress";
            case 53:
                return "TouchScroll";
            case 54:
                return "TouchPan";
            case 55:
                return "TouchFlick";
            case 56:
                return "TouchPinch";
            case 57:
                return "TouchZoom";
            case 58:
                return "TouchRotate";
            case 100:
                return "KeyboardPress";
            case 101:
                return "KeyboardEnter";
            default:
                return "";
        }
    }
}
