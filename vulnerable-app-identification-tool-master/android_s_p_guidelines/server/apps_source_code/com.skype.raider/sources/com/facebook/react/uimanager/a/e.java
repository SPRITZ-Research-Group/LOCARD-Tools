package com.facebook.react.uimanager.a;

enum e {
    LINEAR("linear"),
    EASE_IN("easeIn"),
    EASE_OUT("easeOut"),
    EASE_IN_EASE_OUT("easeInEaseOut"),
    SPRING("spring");
    
    private final String f;

    private e(String name) {
        this.f = name;
    }

    public static e a(String name) {
        for (e type : values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported interpolation type : " + name);
    }

    public final String toString() {
        return this.f;
    }
}
