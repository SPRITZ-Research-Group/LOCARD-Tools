package com.facebook.crypto;

public enum f {
    KEY_128((byte) 1, 16),
    KEY_256((byte) 2, 32);
    
    public final byte c;
    public final int d;
    public final int e;
    public final int f;

    private f(int chiperId, int keyLength) {
        this.c = chiperId;
        this.d = keyLength;
        this.e = 12;
        this.f = 16;
    }
}
