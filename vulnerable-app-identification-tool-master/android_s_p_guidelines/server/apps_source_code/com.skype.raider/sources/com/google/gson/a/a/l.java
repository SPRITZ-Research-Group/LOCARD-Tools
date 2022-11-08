package com.google.gson.a.a;

import com.google.gson.a.j;
import com.google.gson.c.c;
import com.google.gson.e;
import com.google.gson.h;
import com.google.gson.k;
import com.google.gson.o;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;

public final class l<T> extends r<T> {
    private final o<T> a;
    private final h<T> b;
    private final e c;
    private final com.google.gson.b.a<T> d;
    private final s e;
    private final a f = new a();
    private r<T> g;

    private final class a {
        final /* synthetic */ l a;

        private a(l lVar) {
            this.a = lVar;
        }

        /* synthetic */ a(l x0, byte b) {
            this(x0);
        }
    }

    public l(o<T> serializer, h<T> deserializer, e gson, com.google.gson.b.a<T> typeToken) {
        this.a = serializer;
        this.b = deserializer;
        this.c = gson;
        this.d = typeToken;
        this.e = null;
    }

    public final T a(com.google.gson.c.a in) throws IOException {
        if (this.b == null) {
            return b().a(in);
        }
        if (j.a(in) instanceof k) {
            return null;
        }
        return this.b.a();
    }

    public final void a(c out, T value) throws IOException {
        if (this.a == null) {
            b().a(out, value);
        } else if (value == null) {
            out.f();
        } else {
            j.a(this.a.a(), out);
        }
    }

    private r<T> b() {
        r<T> d = this.g;
        if (d != null) {
            return d;
        }
        d = this.c.a(this.e, this.d);
        this.g = d;
        return d;
    }
}
