package com.bumptech.glide.manager;

import android.util.Log;
import defpackage.ajs;
import defpackage.alu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public final class q {
    private final Set<ajs> a = Collections.newSetFromMap(new WeakHashMap());
    private final List<ajs> b = new ArrayList();
    private boolean c;

    public final void a(ajs ajs) {
        this.a.add(ajs);
        if (this.c) {
            ajs.b();
            Log.isLoggable("RequestTracker", 2);
            this.b.add(ajs);
            return;
        }
        ajs.a();
    }

    public final boolean b(ajs ajs) {
        return a(ajs, true);
    }

    private boolean a(ajs ajs, boolean z) {
        boolean z2 = true;
        if (ajs == null) {
            return true;
        }
        boolean remove = this.a.remove(ajs);
        if (!(this.b.remove(ajs) || remove)) {
            z2 = false;
        }
        if (z2) {
            ajs.b();
            if (z) {
                ajs.h();
            }
        }
        return z2;
    }

    public final void a() {
        this.c = true;
        for (ajs ajs : alu.a(this.a)) {
            if (ajs.c()) {
                ajs.b();
                this.b.add(ajs);
            }
        }
    }

    public final void b() {
        this.c = false;
        for (ajs ajs : alu.a(this.a)) {
            if (!(ajs.d() || ajs.c())) {
                ajs.a();
            }
        }
        this.b.clear();
    }

    public final void c() {
        for (ajs a : alu.a(this.a)) {
            a(a, false);
        }
        this.b.clear();
    }

    public final void d() {
        for (ajs ajs : alu.a(this.a)) {
            if (!(ajs.d() || ajs.f())) {
                ajs.b();
                if (this.c) {
                    this.b.add(ajs);
                } else {
                    ajs.a();
                }
            }
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("{numRequests=");
        stringBuilder.append(this.a.size());
        stringBuilder.append(", isPaused=");
        stringBuilder.append(this.c);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
