package com.facebook.share.widget;

@Deprecated
public enum a {
    BOTTOM("bottom", 0),
    INLINE("inline", 1),
    TOP("top", 2);
    
    static a DEFAULT;
    private int intValue;
    private String stringValue;

    static {
        DEFAULT = BOTTOM;
    }

    static a a(int i) {
        for (a aVar : values()) {
            if (aVar.intValue == i) {
                return aVar;
            }
        }
        return null;
    }

    private a(String str, int i) {
        this.stringValue = str;
        this.intValue = i;
    }

    public final String toString() {
        return this.stringValue;
    }
}
