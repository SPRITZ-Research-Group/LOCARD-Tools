package com.google.a.c.a;

import com.google.a.b;
import java.nio.charset.StandardCharsets;

final class h {
    int a;
    private final String b;
    private l c;
    private b d;
    private b e;
    private final StringBuilder f;
    private int g;
    private k h;
    private int i;

    h(String msg) {
        byte[] msgBinary = msg.getBytes(StandardCharsets.ISO_8859_1);
        StringBuilder sb = new StringBuilder(msgBinary.length);
        int i = 0;
        int c = msgBinary.length;
        while (i < c) {
            char ch = (char) (msgBinary[i] & 255);
            if (ch != '?' || msg.charAt(i) == '?') {
                sb.append(ch);
                i++;
            } else {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
        }
        this.b = sb.toString();
        this.c = l.FORCE_NONE;
        this.f = new StringBuilder(msg.length());
        this.g = -1;
    }

    public final void a(l shape) {
        this.c = shape;
    }

    public final void a(b minSize, b maxSize) {
        this.d = minSize;
        this.e = maxSize;
    }

    public final String a() {
        return this.b;
    }

    public final void b() {
        this.i = 2;
    }

    public final char c() {
        return this.b.charAt(this.a);
    }

    public final StringBuilder d() {
        return this.f;
    }

    public final void a(String codewords) {
        this.f.append(codewords);
    }

    public final void a(char codeword) {
        this.f.append(codeword);
    }

    public final int e() {
        return this.f.length();
    }

    public final int f() {
        return this.g;
    }

    public final void a(int encoding) {
        this.g = encoding;
    }

    public final void g() {
        this.g = -1;
    }

    public final boolean h() {
        return this.a < m();
    }

    private int m() {
        return this.b.length() - this.i;
    }

    public final int i() {
        return m() - this.a;
    }

    public final k j() {
        return this.h;
    }

    public final void b(int len) {
        if (this.h == null || len > this.h.f()) {
            this.h = k.a(len, this.c, this.d, this.e);
        }
    }

    public final void l() {
        this.h = null;
    }

    public final void k() {
        b(this.f.length());
    }
}
