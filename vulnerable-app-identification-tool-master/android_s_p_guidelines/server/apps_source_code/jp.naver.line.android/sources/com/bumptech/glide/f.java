package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import defpackage.aac;
import defpackage.abc;
import defpackage.ajx;
import defpackage.akj;
import java.util.Map;
import java.util.Map.Entry;

public final class f extends ContextWrapper {
    static final z<?, ?> a = new c();
    private final Handler b = new Handler(Looper.getMainLooper());
    private final abc c;
    private final p d;
    private final akj e;
    private final ajx f;
    private final Map<Class<?>, z<?, ?>> g;
    private final aac h;
    private final int i;

    public f(Context context, abc abc, p pVar, akj akj, ajx ajx, Map<Class<?>, z<?, ?>> map, aac aac, int i) {
        super(context.getApplicationContext());
        this.c = abc;
        this.d = pVar;
        this.e = akj;
        this.f = ajx;
        this.g = map;
        this.h = aac;
        this.i = i;
    }

    public final ajx a() {
        return this.f;
    }

    public final <T> z<?, T> a(Class<T> cls) {
        z<?, T> zVar = (z) this.g.get(cls);
        if (zVar == null) {
            for (Entry entry : this.g.entrySet()) {
                if (((Class) entry.getKey()).isAssignableFrom(cls)) {
                    z zVar2 = (z) entry.getValue();
                }
            }
        }
        return zVar == null ? a : zVar;
    }

    public final Handler b() {
        return this.b;
    }

    public final aac c() {
        return this.h;
    }

    public final p d() {
        return this.d;
    }

    public final int e() {
        return this.i;
    }

    public final abc f() {
        return this.c;
    }
}
