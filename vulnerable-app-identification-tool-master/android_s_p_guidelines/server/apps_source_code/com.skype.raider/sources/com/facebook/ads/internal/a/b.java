package com.facebook.ads.internal.a;

import android.content.Context;
import com.facebook.ads.internal.j.a.a;
import com.facebook.ads.internal.m.c;

public abstract class b {
    protected final Context a;
    protected final c b;
    protected final String c;

    public b(Context context, c cVar, String str) {
        this.a = context;
        this.b = cVar;
        this.c = str;
    }

    @Deprecated
    public abstract a a();

    public abstract void b();
}
