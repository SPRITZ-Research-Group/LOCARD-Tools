package com.google.gson.a;

import com.google.gson.a;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.b;
import com.google.gson.c.c;
import com.google.gson.e;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public final class d implements s, Cloneable {
    public static final d a = new d();
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private boolean e;
    private List<a> f = Collections.emptyList();
    private List<a> g = Collections.emptyList();

    protected final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    private d a() {
        try {
            return (d) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final <T> r<T> a(e gson, com.google.gson.b.a<T> type) {
        Class rawType = type.a();
        final boolean skipSerialize = a(rawType, true);
        final boolean skipDeserialize = a(rawType, false);
        if (!skipSerialize && !skipDeserialize) {
            return null;
        }
        final e eVar = gson;
        final com.google.gson.b.a<T> aVar = type;
        return new r<T>(this) {
            final /* synthetic */ d e;
            private r<T> f;

            public final T a(com.google.gson.c.a in) throws IOException {
                if (!skipDeserialize) {
                    return b().a(in);
                }
                in.n();
                return null;
            }

            public final void a(c out, T value) throws IOException {
                if (skipSerialize) {
                    out.f();
                } else {
                    b().a(out, value);
                }
            }

            private r<T> b() {
                r<T> d = this.f;
                if (d != null) {
                    return d;
                }
                d = eVar.a(this.e, aVar);
                this.f = d;
                return d;
            }
        };
    }

    public final boolean a(Field field, boolean serialize) {
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if (this.b != -1.0d && !a((Since) field.getAnnotation(Since.class), (Until) field.getAnnotation(Until.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.e) {
            Expose annotation = (Expose) field.getAnnotation(Expose.class);
            if (annotation == null || (serialize ? !annotation.serialize() : !annotation.deserialize())) {
                return true;
            }
        }
        if (!this.d && b(field.getType())) {
            return true;
        }
        if (a(field.getType())) {
            return true;
        }
        List<a> list = serialize ? this.f : this.g;
        if (!list.isEmpty()) {
            b bVar = new b(field);
            for (a a : list) {
                if (a.a()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean a(Class<?> clazz, boolean serialize) {
        if (this.b != -1.0d && !a((Since) clazz.getAnnotation(Since.class), (Until) clazz.getAnnotation(Until.class))) {
            return true;
        }
        if (!this.d && b(clazz)) {
            return true;
        }
        if (a(clazz)) {
            return true;
        }
        for (a b : serialize ? this.f : this.g) {
            if (b.b()) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(Class<?> clazz) {
        return !Enum.class.isAssignableFrom(clazz) && (clazz.isAnonymousClass() || clazz.isLocalClass());
    }

    private static boolean b(Class<?> clazz) {
        if (clazz.isMemberClass()) {
            boolean z;
            if ((clazz.getModifiers() & 8) != 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Since since, Until until) {
        boolean z;
        if (since == null || since.value() <= this.b) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (until == null || until.value() > this.b) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }
}
