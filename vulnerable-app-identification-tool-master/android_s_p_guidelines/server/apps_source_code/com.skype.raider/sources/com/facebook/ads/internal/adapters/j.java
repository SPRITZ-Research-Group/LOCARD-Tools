package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.internal.q.a.d;
import com.facebook.ads.internal.s.a;
import java.util.HashMap;
import java.util.Map;

public abstract class j {
    protected final k a;
    protected final a b;
    private final Context c;
    private boolean d;

    public j(Context context, k kVar, a aVar) {
        this.c = context;
        this.a = kVar;
        this.b = aVar;
    }

    public final void a() {
        if (!this.d) {
            if (this.a != null) {
                this.a.a();
            }
            Map hashMap = new HashMap();
            if (this.b != null) {
                this.b.a(hashMap);
            }
            a(hashMap);
            this.d = true;
            d.a(this.c, "Impression logged");
        }
    }

    protected abstract void a(Map<String, String> map);
}
