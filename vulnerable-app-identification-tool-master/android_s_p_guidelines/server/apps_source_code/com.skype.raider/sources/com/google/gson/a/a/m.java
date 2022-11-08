package com.google.gson.a.a;

import com.google.gson.c.a;
import com.google.gson.c.c;
import com.google.gson.e;
import com.google.gson.r;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class m<T> extends r<T> {
    private final e a;
    private final r<T> b;
    private final Type c;

    m(e context, r<T> delegate, Type type) {
        this.a = context;
        this.b = delegate;
        this.c = type;
    }

    public final T a(a in) throws IOException {
        return this.b.a(in);
    }

    public final void a(c out, T value) throws IOException {
        r chosen = this.b;
        Type runtimeType = this.c;
        if (value != null && (runtimeType == Object.class || (runtimeType instanceof TypeVariable) || (runtimeType instanceof Class))) {
            runtimeType = value.getClass();
        }
        if (runtimeType != this.c) {
            r runtimeTypeAdapter = this.a.a(com.google.gson.b.a.a(runtimeType));
            if (!(runtimeTypeAdapter instanceof i.a) || (this.b instanceof i.a)) {
                chosen = runtimeTypeAdapter;
            } else {
                chosen = this.b;
            }
        }
        chosen.a(out, value);
    }
}
