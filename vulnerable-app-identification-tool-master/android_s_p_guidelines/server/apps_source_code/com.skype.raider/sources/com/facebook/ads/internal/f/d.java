package com.facebook.ads.internal.f;

import java.util.Map;

public class d {
    private double a;
    private double b = (((double) System.currentTimeMillis()) / 1000.0d);
    private String c;
    private Map<String, String> d;

    public d(double d, String str, Map<String, String> map) {
        this.a = d;
        this.c = str;
        this.d = map;
    }

    public String a() {
        return "debug";
    }

    public final double b() {
        return this.b;
    }

    public final double c() {
        return this.a;
    }

    public final String d() {
        return this.c;
    }

    public final Map<String, String> e() {
        return this.d;
    }
}
