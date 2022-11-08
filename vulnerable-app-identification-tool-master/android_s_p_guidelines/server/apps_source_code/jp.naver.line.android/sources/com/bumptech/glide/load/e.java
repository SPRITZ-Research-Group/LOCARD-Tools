package com.bumptech.glide.load;

import java.io.IOException;

public final class e extends IOException {
    private static final long serialVersionUID = 1;
    private final int a;

    public e(int i) {
        this("Http request failed with status code: ".concat(String.valueOf(i)), i);
    }

    public e(String str) {
        this(str, -1);
    }

    public e(String str, int i) {
        this(str, i, (byte) 0);
    }

    private e(String str, int i, byte b) {
        super(str, null);
        this.a = i;
    }

    public final int a() {
        return this.a;
    }
}
