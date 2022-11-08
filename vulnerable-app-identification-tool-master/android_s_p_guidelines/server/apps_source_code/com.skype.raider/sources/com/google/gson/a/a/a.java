package com.google.gson.a.a;

import com.google.gson.a.b;
import com.google.gson.c.c;
import com.google.gson.e;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class a<E> extends r<Object> {
    public static final s a = new s() {
        public final <T> r<T> a(e gson, com.google.gson.b.a<T> typeToken) {
            Type type = typeToken.b();
            if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class) type).isArray())) {
                return null;
            }
            Type componentType = b.d(type);
            return new a(gson, gson.a(com.google.gson.b.a.a(componentType)), b.b(componentType));
        }
    };
    private final Class<E> b;
    private final r<E> c;

    public a(e context, r<E> componentTypeAdapter, Class<E> componentType) {
        this.c = new m(context, componentTypeAdapter, componentType);
        this.b = componentType;
    }

    public final Object a(com.google.gson.c.a in) throws IOException {
        if (in.f() == com.google.gson.c.b.NULL) {
            in.j();
            return null;
        }
        List<E> list = new ArrayList();
        in.a();
        while (in.e()) {
            list.add(this.c.a(in));
        }
        in.b();
        Object array = Array.newInstance(this.b, list.size());
        for (int i = 0; i < list.size(); i++) {
            Array.set(array, i, list.get(i));
        }
        return array;
    }

    public final void a(c out, Object array) throws IOException {
        if (array == null) {
            out.f();
            return;
        }
        out.b();
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            this.c.a(out, Array.get(array, i));
        }
        out.c();
    }
}
