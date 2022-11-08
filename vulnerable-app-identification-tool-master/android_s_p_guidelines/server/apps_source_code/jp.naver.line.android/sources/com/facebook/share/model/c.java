package com.facebook.share.model;

@Deprecated
public enum c {
    FACEBOOK("facebook"),
    MESSENGER("messenger");
    
    private final String name;

    private c(String str) {
        this.name = str;
    }

    public final String toString() {
        return this.name;
    }
}
