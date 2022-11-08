package com.microsoft.skypemessagetextinput.d;

public final class e {
    private int a;
    private int b;

    public e() {
        this.a = 0;
        this.b = 0;
    }

    public e(int width, int height) {
        this.a = width;
        this.b = height;
    }

    public final int a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final boolean a(e rValue) {
        return this.a == rValue.a && this.b == rValue.b;
    }
}
