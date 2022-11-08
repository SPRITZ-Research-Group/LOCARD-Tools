package com.facebook.react.uimanager.a;

enum b {
    OPACITY("opacity"),
    SCALE_XY("scaleXY");
    
    private final String c;

    private b(String name) {
        this.c = name;
    }

    public static b a(String name) {
        for (b property : values()) {
            if (property.toString().equalsIgnoreCase(name)) {
                return property;
            }
        }
        throw new IllegalArgumentException("Unsupported animated property : " + name);
    }

    public final String toString() {
        return this.c;
    }
}
