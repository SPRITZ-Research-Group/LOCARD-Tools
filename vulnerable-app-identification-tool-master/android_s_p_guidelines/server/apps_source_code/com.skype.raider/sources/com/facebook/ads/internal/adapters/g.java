package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.s.a;
import java.util.Map;

public final class g extends j {
    private final c c;
    private final s d;
    private k e;

    public g(Context context, c cVar, a aVar, s sVar, k kVar) {
        super(context, kVar, aVar);
        this.c = cVar;
        this.d = sVar;
    }

    public final void a(k kVar) {
        this.e = kVar;
    }

    protected final void a(Map<String, String> map) {
        if (this.e != null && !TextUtils.isEmpty(this.e.g())) {
            map.put("touch", com.facebook.ads.internal.q.a.k.a(this.d.e()));
            this.c.a(this.e.g(), map);
        }
    }
}
