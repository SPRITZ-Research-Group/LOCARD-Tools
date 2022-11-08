package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.r.b;
import java.util.EnumSet;
import java.util.Map;

public abstract class d implements a {
    public abstract void a(Context context, e eVar, Map<String, Object> map, c cVar, EnumSet<com.facebook.ads.d> enumSet);

    public abstract boolean a();

    public final b d() {
        return b.INTERSTITIAL;
    }
}
