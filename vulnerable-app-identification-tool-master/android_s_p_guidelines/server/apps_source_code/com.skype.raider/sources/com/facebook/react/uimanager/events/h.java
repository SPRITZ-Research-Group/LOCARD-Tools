package com.facebook.react.uimanager.events;

public enum h {
    START("topTouchStart"),
    END("topTouchEnd"),
    MOVE("topTouchMove"),
    CANCEL("topTouchCancel");
    
    private final String e;

    private h(String jsEventName) {
        this.e = jsEventName;
    }

    public final String a() {
        return this.e;
    }
}
