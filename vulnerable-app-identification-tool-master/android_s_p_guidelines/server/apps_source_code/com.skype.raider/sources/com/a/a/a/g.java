package com.a.a.a;

public abstract class g {
    protected int a;
    protected int b;

    protected g() {
    }

    public final boolean a() {
        return this.a == 1;
    }

    public final boolean b() {
        return this.a == 2;
    }

    public final String c() {
        switch (this.a) {
            case 0:
                return "ROOT";
            case 1:
                return "ARRAY";
            case 2:
                return "OBJECT";
            default:
                return "?";
        }
    }

    public final int d() {
        return this.b + 1;
    }

    public final int e() {
        return this.b < 0 ? 0 : this.b;
    }
}
