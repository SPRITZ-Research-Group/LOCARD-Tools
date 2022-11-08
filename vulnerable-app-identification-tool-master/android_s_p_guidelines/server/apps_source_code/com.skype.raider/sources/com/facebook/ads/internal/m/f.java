package com.facebook.ads.internal.m;

public enum f {
    TEST("test"),
    BROWSER_SESSION("browser_session"),
    CLOSE("close"),
    IMPRESSION("impression"),
    INVALIDATION("invalidation"),
    STORE("store"),
    OFF_TARGET_CLICK("off_target_click"),
    OPEN_LINK("open_link"),
    NATIVE_VIEW("native_view"),
    VIDEO("video"),
    USER_RETURN("user_return");
    
    private String l;

    private f(String str) {
        this.l = str;
    }

    public static f a(String str) {
        for (f fVar : values()) {
            if (fVar.l.equalsIgnoreCase(str)) {
                return fVar;
            }
        }
        return null;
    }

    public final String toString() {
        return this.l;
    }
}
