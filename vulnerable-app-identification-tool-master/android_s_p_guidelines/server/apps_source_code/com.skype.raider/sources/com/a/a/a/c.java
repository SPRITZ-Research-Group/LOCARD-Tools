package com.a.a.a;

import com.a.a.a.b.b;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public abstract class c implements Closeable, Flushable {
    protected i a;

    public enum a {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        FLUSH_PASSED_TO_STREAM(true),
        ESCAPE_NON_ASCII(false);
        
        private final boolean h;
        private final int i;

        public static int a() {
            int i = 0;
            for (a aVar : values()) {
                if (aVar.h) {
                    i |= aVar.i;
                }
            }
            return i;
        }

        private a(boolean z) {
            this.i = 1 << ordinal();
            this.h = z;
        }

        public final int b() {
            return this.i;
        }
    }

    public abstract void a() throws IOException, b;

    public abstract void a(char c) throws IOException, b;

    public abstract void a(double d) throws IOException, b;

    public abstract void a(int i) throws IOException, b;

    public abstract void a(long j) throws IOException, b;

    public abstract void a(String str) throws IOException, b;

    public abstract void b() throws IOException, b;

    public abstract void b(String str) throws IOException, b;

    public abstract void c() throws IOException, b;

    public abstract void c(String str) throws IOException, b;

    public abstract void close() throws IOException;

    public abstract void d() throws IOException, b;

    public abstract void e() throws IOException, b;

    protected c() {
    }

    public c a(j jVar) {
        throw new UnsupportedOperationException();
    }

    public c a(b bVar) {
        return this;
    }

    public void b(j jVar) throws IOException, b {
        c(jVar.a());
    }

    public void a(String str, String str2) throws IOException, b {
        a(str);
        b(str2);
    }

    public final void a(String str, int i) throws IOException, b {
        a(str);
        a(i);
    }

    public final void a(String str, long j) throws IOException, b {
        a(str);
        a(j);
    }
}
