package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.internal.r.b;
import com.facebook.ads.k;
import java.util.Map;

public abstract class h implements a {
    k a;
    int b;

    public final void a(int i) {
        this.b = i;
    }

    public abstract void a(Context context, i iVar, Map<String, Object> map, boolean z);

    public abstract boolean a();

    public final b d() {
        return b.REWARDED_VIDEO;
    }
}
