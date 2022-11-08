package com.google.gson.a.a;

import com.google.gson.a.g;
import com.google.gson.b.a;
import com.google.gson.c.c;
import com.google.gson.e;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class h extends r<Object> {
    public static final s a = new s() {
        public final <T> r<T> a(e gson, a<T> type) {
            if (type.a() == Object.class) {
                return new h(gson);
            }
            return null;
        }
    };
    private final e b;

    h(e gson) {
        this.b = gson;
    }

    public final Object a(com.google.gson.c.a in) throws IOException {
        switch (in.f()) {
            case BEGIN_ARRAY:
                List<Object> list = new ArrayList();
                in.a();
                while (in.e()) {
                    list.add(a(in));
                }
                in.b();
                return list;
            case BEGIN_OBJECT:
                Map<String, Object> map = new g();
                in.c();
                while (in.e()) {
                    map.put(in.g(), a(in));
                }
                in.d();
                return map;
            case STRING:
                return in.h();
            case NUMBER:
                return Double.valueOf(in.k());
            case BOOLEAN:
                return Boolean.valueOf(in.i());
            case NULL:
                in.j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public final void a(c out, Object value) throws IOException {
        if (value == null) {
            out.f();
            return;
        }
        r<Object> typeAdapter = this.b.a(value.getClass());
        if (typeAdapter instanceof h) {
            out.d();
            out.e();
            return;
        }
        typeAdapter.a(out, value);
    }
}
