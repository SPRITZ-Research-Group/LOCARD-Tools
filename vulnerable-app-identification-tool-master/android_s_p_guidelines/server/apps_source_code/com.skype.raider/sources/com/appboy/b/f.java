package com.appboy.b;

import com.appboy.f.c;

public enum f {
    JANUARY(0),
    FEBRUARY(1),
    MARCH(2),
    APRIL(3),
    MAY(4),
    JUNE(5),
    JULY(6),
    AUGUST(7),
    SEPTEMBER(8),
    OCTOBER(9),
    NOVEMBER(10),
    DECEMBER(11);
    
    private static final String m = null;
    private final int n;

    static {
        m = c.a(f.class);
    }

    private f(int value) {
        this.n = value;
    }

    public final int a() {
        return this.n;
    }

    public static f a(int value) {
        for (f fVar : values()) {
            if (fVar.n == value) {
                return fVar;
            }
        }
        c.g(m, "No month with value " + value + ", value must be in (0,11)");
        return null;
    }
}
