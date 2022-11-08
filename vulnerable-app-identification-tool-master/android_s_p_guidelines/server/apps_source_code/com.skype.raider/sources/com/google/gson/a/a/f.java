package com.google.gson.a.a;

import com.google.gson.c.c;
import com.google.gson.g;
import com.google.gson.i;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.n;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class f extends c {
    private static final Writer a = new Writer() {
        public final void write(char[] buffer, int offset, int counter) {
            throw new AssertionError();
        }

        public final void flush() throws IOException {
            throw new AssertionError();
        }

        public final void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final n b = new n("closed");
    private final List<i> c = new ArrayList();
    private String d;
    private i e = k.a;

    public f() {
        super(a);
    }

    public final i a() {
        if (this.c.isEmpty()) {
            return this.e;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.c);
    }

    private i j() {
        return (i) this.c.get(this.c.size() - 1);
    }

    private void a(i value) {
        if (this.d != null) {
            if (!(value instanceof k) || i()) {
                ((l) j()).a(this.d, value);
            }
            this.d = null;
        } else if (this.c.isEmpty()) {
            this.e = value;
        } else {
            i element = j();
            if (element instanceof g) {
                ((g) element).a(value);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public final c b() throws IOException {
        i array = new g();
        a(array);
        this.c.add(array);
        return this;
    }

    public final c c() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof g) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final c d() throws IOException {
        i object = new l();
        a(object);
        this.c.add(object);
        return this;
    }

    public final c e() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof l) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final c a(String name) throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof l) {
            this.d = name;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final c b(String value) throws IOException {
        if (value == null) {
            return f();
        }
        a(new n(value));
        return this;
    }

    public final c f() throws IOException {
        a(k.a);
        return this;
    }

    public final c a(boolean value) throws IOException {
        a(new n(Boolean.valueOf(value)));
        return this;
    }

    public final c a(Boolean value) throws IOException {
        if (value == null) {
            return f();
        }
        a(new n(value));
        return this;
    }

    public final c a(long value) throws IOException {
        a(new n(Long.valueOf(value)));
        return this;
    }

    public final c a(Number value) throws IOException {
        if (value == null) {
            return f();
        }
        if (!g()) {
            double d = value.doubleValue();
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + value);
            }
        }
        a(new n(value));
        return this;
    }

    public final void flush() throws IOException {
    }

    public final void close() throws IOException {
        if (this.c.isEmpty()) {
            this.c.add(b);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
