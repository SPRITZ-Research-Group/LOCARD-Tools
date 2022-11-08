package com.skype4life.syncadapter;

public abstract class b {
    private final String a;

    public b(String id) {
        this.a = id;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return "Skype_" + this.a;
    }
}
