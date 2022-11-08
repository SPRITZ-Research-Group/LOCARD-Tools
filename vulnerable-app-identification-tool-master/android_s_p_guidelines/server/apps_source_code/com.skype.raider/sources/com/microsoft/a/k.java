package com.microsoft.a;

import java.io.Closeable;
import java.io.IOException;

public abstract class k implements Closeable {

    public static class a {
        public final int a;
        public final a b;

        a(a type, int id) {
            this.b = type;
            this.a = id;
        }
    }

    public static class b {
        public final int a;
        public final a b;

        public b(int size, a type) {
            this.a = size;
            this.b = type;
        }
    }

    public static class c {
        public final int a;
        public final a b;
        public final a c;

        public c(int size, a keyType, a valueType) {
            this.a = size;
            this.b = keyType;
            this.c = valueType;
        }
    }

    public abstract void a(a aVar) throws IOException;

    public abstract b b() throws IOException;

    public abstract c c() throws IOException;

    public abstract boolean d() throws IOException;

    public abstract String e() throws IOException;

    public abstract String f() throws IOException;

    public abstract float g() throws IOException;

    public abstract double h() throws IOException;

    public abstract byte i() throws IOException;

    public abstract short j() throws IOException;

    public abstract int k() throws IOException;

    public abstract long l() throws IOException;

    public abstract byte m() throws IOException;

    public abstract short n() throws IOException;

    public abstract int o() throws IOException;

    public abstract long p() throws IOException;

    public void close() throws IOException {
    }

    public a a() throws IOException {
        return new a(a.BT_UNAVAILABLE, 32767);
    }

    public boolean a(j capability) {
        return false;
    }
}
