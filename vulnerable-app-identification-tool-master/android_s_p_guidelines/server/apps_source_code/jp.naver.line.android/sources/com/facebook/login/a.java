package com.facebook.login;

public enum a {
    NONE(null),
    ONLY_ME("only_me"),
    FRIENDS("friends"),
    EVERYONE("everyone");
    
    private final String nativeProtocolAudience;

    private a(String str) {
        this.nativeProtocolAudience = str;
    }

    public final String a() {
        return this.nativeProtocolAudience;
    }
}
