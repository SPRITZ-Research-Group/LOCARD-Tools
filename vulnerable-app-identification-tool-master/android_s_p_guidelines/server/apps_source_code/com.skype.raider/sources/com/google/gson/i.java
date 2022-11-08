package com.google.gson;

import com.google.gson.a.j;
import com.google.gson.c.c;
import java.io.IOException;
import java.io.StringWriter;

public abstract class i {
    public boolean f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            c jsonWriter = new c(stringWriter);
            jsonWriter.b(true);
            j.a(this, jsonWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final n g() {
        if (this instanceof n) {
            return (n) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }
}
