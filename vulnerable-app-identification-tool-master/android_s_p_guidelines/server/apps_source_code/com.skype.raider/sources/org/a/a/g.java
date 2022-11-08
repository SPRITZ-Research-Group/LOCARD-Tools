package org.a.a;

import java.io.Serializable;

public class g implements Serializable, w {
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected transient e e;
    protected String f;
    protected int g;
    protected int h;
    protected int i;

    public g() {
        this.c = -1;
        this.d = 0;
        this.g = -1;
        this.a = 0;
    }

    public g(e input, int type, int start, int stop) {
        this.c = -1;
        this.d = 0;
        this.g = -1;
        this.e = input;
        this.a = type;
        this.d = 0;
        this.h = start;
        this.i = stop;
    }

    public g(int type, String text) {
        this.c = -1;
        this.d = 0;
        this.g = -1;
        this.a = type;
        this.d = 0;
        this.f = text;
    }

    public g(w oldToken) {
        this.c = -1;
        this.d = 0;
        this.g = -1;
        this.f = oldToken.b();
        this.a = oldToken.a();
        this.b = oldToken.c();
        this.g = oldToken.h();
        this.c = oldToken.d();
        this.d = oldToken.e();
        this.e = oldToken.i();
        if (oldToken instanceof g) {
            this.h = ((g) oldToken).h;
            this.i = ((g) oldToken).i;
        }
    }

    public final int a() {
        return this.a;
    }

    public final void a(int line) {
        this.b = line;
    }

    public final String b() {
        if (this.f != null) {
            return this.f;
        }
        if (this.e == null) {
            return null;
        }
        int n = this.e.c();
        if (this.h >= n || this.i >= n) {
            return "<EOF>";
        }
        return this.e.a(this.h, this.i);
    }

    public final void a(String text) {
        this.f = text;
    }

    public final int c() {
        return this.b;
    }

    public final int d() {
        return this.c;
    }

    public final void b(int charPositionInLine) {
        this.c = charPositionInLine;
    }

    public final int e() {
        return this.d;
    }

    public final void c(int type) {
        this.a = type;
    }

    public final int f() {
        return this.h;
    }

    public final void d(int start) {
        this.h = start;
    }

    public final int g() {
        return this.i;
    }

    public final void e(int stop) {
        this.i = stop;
    }

    public final int h() {
        return this.g;
    }

    public final void f(int index) {
        this.g = index;
    }

    public final e i() {
        return this.e;
    }

    public String toString() {
        String channelStr = "";
        if (this.d > 0) {
            channelStr = ",channel=" + this.d;
        }
        String txt = b();
        if (txt != null) {
            txt = txt.replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r").replaceAll("\t", "\\\\t");
        } else {
            txt = "<no text>";
        }
        return "[@" + this.g + "," + this.h + ":" + this.i + "='" + txt + "',<" + this.a + ">" + channelStr + "," + this.b + ":" + this.c + "]";
    }
}
