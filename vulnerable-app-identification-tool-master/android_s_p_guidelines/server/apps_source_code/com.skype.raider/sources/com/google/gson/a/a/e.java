package com.google.gson.a.a;

import com.google.gson.c.a;
import com.google.gson.c.b;
import com.google.gson.g;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.n;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;

public final class e extends a {
    private static final Reader b = new Reader() {
        public final int read(char[] buffer, int offset, int count) throws IOException {
            throw new AssertionError();
        }

        public final void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final Object c = new Object();
    private Object[] d;
    private int e;
    private String[] f;
    private int[] g;

    public final void a() throws IOException {
        a(b.BEGIN_ARRAY);
        a(((g) s()).iterator());
        this.g[this.e - 1] = 0;
    }

    public final void b() throws IOException {
        a(b.END_ARRAY);
        t();
        t();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public final void c() throws IOException {
        a(b.BEGIN_OBJECT);
        a(((l) s()).h().iterator());
    }

    public final void d() throws IOException {
        a(b.END_OBJECT);
        t();
        t();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public final boolean e() throws IOException {
        b token = f();
        return (token == b.END_OBJECT || token == b.END_ARRAY) ? false : true;
    }

    public final b f() throws IOException {
        while (this.e != 0) {
            Iterator<?> o = s();
            if (o instanceof Iterator) {
                boolean isObject = this.d[this.e - 2] instanceof l;
                Iterator<?> iterator = o;
                if (!iterator.hasNext()) {
                    return isObject ? b.END_OBJECT : b.END_ARRAY;
                } else {
                    if (isObject) {
                        return b.NAME;
                    }
                    a(iterator.next());
                }
            } else if (o instanceof l) {
                return b.BEGIN_OBJECT;
            } else {
                if (o instanceof g) {
                    return b.BEGIN_ARRAY;
                }
                if (o instanceof n) {
                    n primitive = (n) o;
                    if (primitive.j()) {
                        return b.STRING;
                    }
                    if (primitive.h()) {
                        return b.BOOLEAN;
                    }
                    if (primitive.i()) {
                        return b.NUMBER;
                    }
                    throw new AssertionError();
                } else if (o instanceof k) {
                    return b.NULL;
                } else {
                    if (o == c) {
                        throw new IllegalStateException("JsonReader is closed");
                    }
                    throw new AssertionError();
                }
            }
        }
        return b.END_DOCUMENT;
    }

    private Object s() {
        return this.d[this.e - 1];
    }

    private Object t() {
        Object[] objArr = this.d;
        int i = this.e - 1;
        this.e = i;
        Object result = objArr[i];
        this.d[this.e] = null;
        return result;
    }

    private void a(b expected) throws IOException {
        if (f() != expected) {
            throw new IllegalStateException("Expected " + expected + " but was " + f() + u());
        }
    }

    public final String g() throws IOException {
        a(b.NAME);
        Entry<?, ?> entry = (Entry) ((Iterator) s()).next();
        String result = (String) entry.getKey();
        this.f[this.e - 1] = result;
        a(entry.getValue());
        return result;
    }

    public final String h() throws IOException {
        b token = f();
        if (token == b.STRING || token == b.NUMBER) {
            String result = ((n) t()).b();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return result;
        }
        throw new IllegalStateException("Expected " + b.STRING + " but was " + token + u());
    }

    public final boolean i() throws IOException {
        a(b.BOOLEAN);
        boolean result = ((n) t()).f();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
        return result;
    }

    public final void j() throws IOException {
        a(b.NULL);
        t();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public final double k() throws IOException {
        b token = f();
        if (token == b.NUMBER || token == b.STRING) {
            double result = ((n) s()).c();
            if (q() || !(Double.isNaN(result) || Double.isInfinite(result))) {
                t();
                if (this.e > 0) {
                    int[] iArr = this.g;
                    int i = this.e - 1;
                    iArr[i] = iArr[i] + 1;
                }
                return result;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + result);
        }
        throw new IllegalStateException("Expected " + b.NUMBER + " but was " + token + u());
    }

    public final long l() throws IOException {
        b token = f();
        if (token == b.NUMBER || token == b.STRING) {
            long result = ((n) s()).d();
            t();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return result;
        }
        throw new IllegalStateException("Expected " + b.NUMBER + " but was " + token + u());
    }

    public final int m() throws IOException {
        b token = f();
        if (token == b.NUMBER || token == b.STRING) {
            int result = ((n) s()).e();
            t();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return result;
        }
        throw new IllegalStateException("Expected " + b.NUMBER + " but was " + token + u());
    }

    public final void close() throws IOException {
        this.d = new Object[]{c};
        this.e = 1;
    }

    public final void n() throws IOException {
        if (f() == b.NAME) {
            g();
            this.f[this.e - 2] = "null";
        } else {
            t();
            this.f[this.e - 1] = "null";
        }
        int[] iArr = this.g;
        int i = this.e - 1;
        iArr[i] = iArr[i] + 1;
    }

    public final String toString() {
        return getClass().getSimpleName();
    }

    public final void o() throws IOException {
        a(b.NAME);
        Entry<?, ?> entry = (Entry) ((Iterator) s()).next();
        a(entry.getValue());
        a(new n((String) entry.getKey()));
    }

    private void a(Object newTop) {
        if (this.e == this.d.length) {
            Object[] newStack = new Object[(this.e * 2)];
            int[] newPathIndices = new int[(this.e * 2)];
            String[] newPathNames = new String[(this.e * 2)];
            System.arraycopy(this.d, 0, newStack, 0, this.e);
            System.arraycopy(this.g, 0, newPathIndices, 0, this.e);
            System.arraycopy(this.f, 0, newPathNames, 0, this.e);
            this.d = newStack;
            this.g = newPathIndices;
            this.f = newPathNames;
        }
        Object[] objArr = this.d;
        int i = this.e;
        this.e = i + 1;
        objArr[i] = newTop;
    }

    public final String p() {
        StringBuilder result = new StringBuilder("$");
        int i = 0;
        while (i < this.e) {
            if (this.d[i] instanceof g) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    result.append('[').append(this.g[i]).append(']');
                }
            } else if (this.d[i] instanceof l) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    result.append('.');
                    if (this.f[i] != null) {
                        result.append(this.f[i]);
                    }
                }
            }
            i++;
        }
        return result.toString();
    }

    private String u() {
        return " at path " + p();
    }
}
