package com.google.a.f.b;

import com.google.a.f.a.a;
import com.google.a.f.a.b;
import com.google.a.f.a.c;

public final class f {
    private b a;
    private a b;
    private c c;
    private int d = -1;
    private b e;

    public final b a() {
        return this.e;
    }

    public final String toString() {
        StringBuilder result = new StringBuilder(200);
        result.append("<<\n");
        result.append(" mode: ");
        result.append(this.a);
        result.append("\n ecLevel: ");
        result.append(this.b);
        result.append("\n version: ");
        result.append(this.c);
        result.append("\n maskPattern: ");
        result.append(this.d);
        if (this.e == null) {
            result.append("\n matrix: null\n");
        } else {
            result.append("\n matrix:\n");
            result.append(this.e);
        }
        result.append(">>\n");
        return result.toString();
    }

    public final void a(b value) {
        this.a = value;
    }

    public final void a(a value) {
        this.b = value;
    }

    public final void a(c version) {
        this.c = version;
    }

    public final void a(int value) {
        this.d = value;
    }

    public final void a(b value) {
        this.e = value;
    }
}
