package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.c.a;
import java.util.Map;

public class z extends j {
    private static final String c = z.class.getSimpleName();
    private final a d;
    private final c e;
    private y f;
    private boolean g;

    public z(Context context, c cVar, a aVar, com.facebook.ads.internal.s.a aVar2, k kVar) {
        super(context, kVar, aVar2);
        this.e = cVar;
        this.d = aVar;
    }

    public final void a(y yVar) {
        this.f = yVar;
    }

    protected final void a(Map<String, String> map) {
        if (this.f != null && !TextUtils.isEmpty(this.f.c())) {
            this.e.a(this.f.c(), map);
        }
    }

    public final synchronized void b() {
        if (!(this.g || this.f == null)) {
            this.g = true;
            if (!(this.d == null || TextUtils.isEmpty(this.f.e()))) {
                this.d.post(new Runnable(this) {
                    final /* synthetic */ z a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        if (this.a.d.c()) {
                            z.c;
                        } else {
                            this.a.d.loadUrl("javascript:" + this.a.f.e());
                        }
                    }
                });
            }
        }
    }
}
