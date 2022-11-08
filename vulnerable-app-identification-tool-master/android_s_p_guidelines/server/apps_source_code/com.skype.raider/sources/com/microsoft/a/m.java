package com.microsoft.a;

public enum m {
    ONE(1),
    TWO(2);
    
    private short c;

    private m(int value) {
        this.c = (short) value;
    }

    public final short a() {
        return this.c;
    }
}
