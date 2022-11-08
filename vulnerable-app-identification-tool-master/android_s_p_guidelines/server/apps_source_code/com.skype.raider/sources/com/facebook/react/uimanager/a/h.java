package com.facebook.react.uimanager.a;

enum h {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");
    
    private final String d;

    private h(String name) {
        this.d = name;
    }

    public final String toString() {
        return this.d;
    }
}
