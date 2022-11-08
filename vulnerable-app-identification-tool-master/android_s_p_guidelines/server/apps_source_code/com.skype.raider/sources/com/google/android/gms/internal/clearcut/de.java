package com.google.android.gms.internal.clearcut;

public enum de {
    DOUBLE(dj.DOUBLE, 1),
    FLOAT(dj.FLOAT, 5),
    INT64(dj.LONG, 0),
    UINT64(dj.LONG, 0),
    INT32(dj.INT, 0),
    FIXED64(dj.LONG, 1),
    FIXED32(dj.INT, 5),
    BOOL(dj.BOOLEAN, 0),
    STRING,
    GROUP,
    MESSAGE,
    BYTES,
    UINT32(dj.INT, 0),
    ENUM(dj.ENUM, 0),
    SFIXED32(dj.INT, 5),
    SFIXED64(dj.LONG, 1),
    SINT32(dj.INT, 0),
    SINT64(dj.LONG, 0);
    
    private final dj s;
    private final int t;

    private de(dj djVar, int i) {
        this.s = djVar;
        this.t = i;
    }

    public final dj a() {
        return this.s;
    }

    public final int b() {
        return this.t;
    }
}
