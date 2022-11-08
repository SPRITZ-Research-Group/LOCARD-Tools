package com.facebook.ads.internal.m;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.e.a;
import com.facebook.ads.internal.q.a.n;
import com.facebook.ads.internal.q.c.e;
import java.util.Map;

public class d implements c {
    private static final String a = d.class.getSimpleName();
    private static double b;
    private static String c;
    private static volatile boolean d = false;
    @Nullable
    @SuppressLint({"StaticFieldLeak"})
    private static c h;
    private final b e;
    private final com.facebook.ads.internal.e.d f;
    private final Context g;

    private d(Context context) {
        this.g = context.getApplicationContext();
        this.f = new com.facebook.ads.internal.e.d(context);
        this.e = new b(context, new g(context, this.f));
        this.e.b();
        b(context);
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (d.class) {
            if (h == null) {
                h = new d(context.getApplicationContext());
            }
            cVar = h;
        }
        return cVar;
    }

    private void a(final a aVar) {
        if (aVar.g()) {
            this.f.a(aVar.a(), aVar.h().c, aVar.i().toString(), aVar.b(), aVar.c(), aVar.d(), aVar.e(), new a<String>(this) {
                final /* synthetic */ d b;

                public final /* synthetic */ void a(Object obj) {
                    super.a((String) obj);
                    if (aVar.f()) {
                        this.b.e.a();
                    } else {
                        this.b.e.b();
                    }
                }
            });
        } else {
            new StringBuilder("Attempting to log an invalid ").append(aVar.i()).append(" event.");
        }
    }

    private static synchronized void b(Context context) {
        synchronized (d.class) {
            if (!d) {
                com.facebook.ads.internal.i.a.a(context).a();
                n.a();
                b = n.b();
                c = n.c();
                d = true;
            }
        }
    }

    public final void a(String str) {
        new e(this.g).execute(new String[]{str});
    }

    public final void a(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.IMPRESSION).a(true).a());
        }
    }

    public final void a(String str, Map<String, String> map, String str2, e eVar) {
        a(new a.a().a(str).a(b).b(c).a((Map) map).a(eVar).a(f.a(str2)).a(true).a());
    }

    public final void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a(null).a(e.IMMEDIATE).a(f.INVALIDATION).a(false).a());
        }
    }

    public final void b(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.OPEN_LINK).a(true).a());
        }
    }

    public final void c(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.DEFERRED).a(f.OFF_TARGET_CLICK).a(true).a());
        }
    }

    public final void d(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.VIDEO).a(true).a());
        }
    }

    public final void e(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.DEFERRED).a(f.NATIVE_VIEW).a(false).a());
        }
    }

    public final void f(String str, Map<String, String> map) {
        a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.DEFERRED).a(f.BROWSER_SESSION).a(false).a());
    }

    public final void g(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.STORE).a(true).a());
        }
    }

    public final void h(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.DEFERRED).a(f.CLOSE).a(true).a());
        }
    }

    public final void i(String str, Map<String, String> map) {
        a(new a.a().a(str).a(b).b(c).a((Map) map).a(e.IMMEDIATE).a(f.USER_RETURN).a(true).a());
    }
}
