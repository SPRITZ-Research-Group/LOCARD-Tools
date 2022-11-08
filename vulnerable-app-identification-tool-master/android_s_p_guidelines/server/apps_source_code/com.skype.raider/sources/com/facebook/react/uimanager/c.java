package com.facebook.react.uimanager;

public final class c {
    public static boolean a(float f1, float f2) {
        if (Float.isNaN(f1) || Float.isNaN(f2)) {
            if (Float.isNaN(f1) && Float.isNaN(f2)) {
                return true;
            }
            return false;
        } else if (Math.abs(f2 - f1) >= 1.0E-5f) {
            return false;
        } else {
            return true;
        }
    }
}
