package com.facebook.ads.internal.h;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class c {
    private List<a> a = new ArrayList();
    private int b = 0;
    private d c;
    @Nullable
    private String d;

    public c(d dVar, @Nullable String str) {
        this.c = dVar;
        this.d = str;
    }

    public final d a() {
        return this.c;
    }

    public final void a(a aVar) {
        this.a.add(aVar);
    }

    @Nullable
    public final String b() {
        return this.d;
    }

    public final a c() {
        if (this.b >= this.a.size()) {
            return null;
        }
        this.b++;
        return (a) this.a.get(this.b - 1);
    }

    public final boolean d() {
        return this.c == null || System.currentTimeMillis() > this.c.a() + ((long) this.c.l());
    }
}
