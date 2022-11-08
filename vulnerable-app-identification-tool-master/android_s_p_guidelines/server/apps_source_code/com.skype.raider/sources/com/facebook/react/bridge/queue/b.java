package com.facebook.react.bridge.queue;

public final class b {
    private static final b a = new b(a.MAIN_UI, "main_ui");
    private final a b;
    private final String c;
    private final long d;

    protected enum a {
        MAIN_UI,
        NEW_BACKGROUND
    }

    public static b a(String name) {
        return new b(a.NEW_BACKGROUND, name);
    }

    public static b b(String name) {
        return new b(a.NEW_BACKGROUND, name);
    }

    public static b c(String name) {
        return new b(a.NEW_BACKGROUND, name, 2000000);
    }

    public static b a() {
        return a;
    }

    private b(a threadType, String name) {
        this(threadType, name, 0);
    }

    private b(a threadType, String name, long stackSize) {
        this.b = threadType;
        this.c = name;
        this.d = stackSize;
    }

    public final a b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final long d() {
        return this.d;
    }
}
