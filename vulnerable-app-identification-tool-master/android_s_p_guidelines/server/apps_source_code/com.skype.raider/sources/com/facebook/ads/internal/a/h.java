package com.facebook.ads.internal.a;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.d;
import java.util.HashMap;
import java.util.Map;

public abstract class h extends b {
    protected final l d;

    public h(Context context, c cVar, String str, l lVar) {
        super(context, cVar, str);
        this.d = lVar;
    }

    protected final void a(Map<String, String> map, a aVar) {
        if (!TextUtils.isEmpty(this.c)) {
            if (this instanceof f) {
                this.b.g(this.c, map);
            } else {
                this.b.b(this.c, map);
            }
            boolean a = a.a(aVar);
            if (this.d != null) {
                this.d.a(aVar);
                if (a) {
                    this.d.a();
                }
            } else {
                Map hashMap = new HashMap();
                hashMap.put("leave_time", Long.toString(-1));
                hashMap.put("back_time", Long.toString(-1));
                hashMap.put("outcome", a.CANNOT_TRACK.name());
                this.b.i(this.c, hashMap);
            }
        }
        d.a(this.a, "Click logged");
    }

    public final void b() {
        if (this.d != null) {
            this.d.a(this.c);
        }
        c();
    }

    abstract void c();
}
