package com.facebook.share.widget;

@Deprecated
public enum g {
    STANDARD("standard", 0),
    BUTTON("button", 1),
    BOX_COUNT("box_count", 2);
    
    static g DEFAULT;
    private int intValue;
    private String stringValue;

    static {
        DEFAULT = STANDARD;
    }

    static g a(int i) {
        for (g gVar : values()) {
            if (gVar.intValue == i) {
                return gVar;
            }
        }
        return null;
    }

    private g(String str, int i) {
        this.stringValue = str;
        this.intValue = i;
    }

    public final String toString() {
        return this.stringValue;
    }
}
