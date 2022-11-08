package com.facebook.login.widget;

public enum d {
    AUTOMATIC("automatic", 0),
    DISPLAY_ALWAYS("display_always", 1),
    NEVER_DISPLAY("never_display", 2);
    
    public static d DEFAULT;
    private int intValue;
    private String stringValue;

    static {
        DEFAULT = AUTOMATIC;
    }

    public static d a(int i) {
        for (d dVar : values()) {
            if (dVar.intValue == i) {
                return dVar;
            }
        }
        return null;
    }

    private d(String str, int i) {
        this.stringValue = str;
        this.intValue = i;
    }

    public final String toString() {
        return this.stringValue;
    }

    public final int a() {
        return this.intValue;
    }
}
