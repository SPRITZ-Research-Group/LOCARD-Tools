package com.facebook.share.widget;

@Deprecated
public enum b {
    CENTER("center", 0),
    LEFT("left", 1),
    RIGHT("right", 2);
    
    static b DEFAULT;
    private int intValue;
    private String stringValue;

    static {
        DEFAULT = CENTER;
    }

    static b a(int i) {
        for (b bVar : values()) {
            if (bVar.intValue == i) {
                return bVar;
            }
        }
        return null;
    }

    private b(String str, int i) {
        this.stringValue = str;
        this.intValue = i;
    }

    public final String toString() {
        return this.stringValue;
    }
}
