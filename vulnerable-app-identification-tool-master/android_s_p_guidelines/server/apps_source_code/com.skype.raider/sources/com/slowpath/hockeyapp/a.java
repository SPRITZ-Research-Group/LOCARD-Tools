package com.slowpath.hockeyapp;

public final class a {
    b a;
    String b;

    private a(b config, String metadata) {
        this.a = config;
        this.b = metadata;
    }

    public static a a(b config) {
        return new a(config, null);
    }

    public static a a(b config, String metadata) {
        return new a(config, metadata);
    }
}
