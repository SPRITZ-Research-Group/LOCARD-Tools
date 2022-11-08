package com.microsoft.a;

public enum l {
    MARSHALED_PROTOCOL(0),
    MAFIA_PROTOCOL(17997),
    COMPACT_PROTOCOL(16963),
    JSON_PROTOCOL(21322),
    PRETTY_JSON_PROTOCOL(20554),
    SIMPLE_PROTOCOL(20563),
    __INVALID_ENUM_VALUE(21323);
    
    private final int h;

    private l(int value) {
        this.h = value;
    }

    public final int a() {
        return this.h;
    }
}
