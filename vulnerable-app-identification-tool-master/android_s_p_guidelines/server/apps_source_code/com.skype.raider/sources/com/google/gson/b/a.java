package com.google.gson.b;

import com.google.gson.a.b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class a<T> {
    final Class<? super T> a;
    final Type b;
    final int c;

    protected a() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        this.b = b.a(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        this.a = b.b(this.b);
        this.c = this.b.hashCode();
    }

    private a(Type type) {
        this.b = b.a((Type) com.google.gson.a.a.a((Object) type));
        this.a = b.b(this.b);
        this.c = this.b.hashCode();
    }

    public final Class<? super T> a() {
        return this.a;
    }

    public final Type b() {
        return this.b;
    }

    public final int hashCode() {
        return this.c;
    }

    public final boolean equals(Object o) {
        return (o instanceof a) && b.a(this.b, ((a) o).b);
    }

    public final String toString() {
        return b.c(this.b);
    }

    public static a<?> a(Type type) {
        return new a(type);
    }

    public static <T> a<T> a(Class<T> type) {
        return new a(type);
    }
}
