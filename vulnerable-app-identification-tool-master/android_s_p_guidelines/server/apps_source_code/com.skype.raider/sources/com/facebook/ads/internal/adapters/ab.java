package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.a.a;
import com.facebook.ads.d;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.r.b;
import java.util.EnumSet;
import java.util.Map;

public abstract class ab implements a {
    public abstract void a(Context context, a aVar, Map<String, Object> map, c cVar, EnumSet<d> enumSet);

    public final b d() {
        return b.INSTREAM;
    }

    public abstract boolean f();
}
