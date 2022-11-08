package com.facebook.ads.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.d;
import com.facebook.ads.internal.adapters.ab;
import com.facebook.ads.internal.adapters.ac;
import com.facebook.ads.internal.adapters.af;
import com.facebook.ads.internal.adapters.ai;
import com.facebook.ads.internal.adapters.aj;
import com.facebook.ads.internal.adapters.f;
import com.facebook.ads.internal.adapters.i;
import com.facebook.ads.internal.adapters.m;
import com.facebook.ads.internal.q.a.l;
import com.facebook.ads.internal.q.a.o;
import com.facebook.ads.internal.q.a.v;
import com.facebook.ads.internal.r.e;
import com.facebook.ads.internal.r.g;
import com.facebook.ads.internal.r.h;
import com.facebook.ads.internal.r.j;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class a implements com.facebook.ads.internal.o.c.a {
    private static final String b = a.class.getSimpleName();
    private static final Handler h = new Handler(Looper.getMainLooper());
    private static boolean i = false;
    private boolean A;
    private final com.facebook.ads.internal.m.c B;
    private final EnumSet<d> C;
    protected f a;
    private final Context c;
    private final String d;
    private final com.facebook.ads.internal.r.b e;
    private final com.facebook.ads.internal.o.c f;
    private final Handler g;
    private final Runnable j;
    private final Runnable k;
    private volatile boolean l;
    private boolean m;
    private volatile boolean n;
    private com.facebook.ads.internal.adapters.a o;
    private com.facebook.ads.internal.adapters.a p;
    private View q;
    private com.facebook.ads.internal.h.c r;
    private com.facebook.ads.internal.o.b s;
    private h t;
    private com.facebook.ads.internal.r.f u;
    private g v;
    private int w;
    private boolean x;
    private int y;
    private final c z;

    private static final class a extends v<a> {
        public a(a aVar) {
            super(aVar);
        }

        public final void run() {
            a aVar = (a) a();
            if (aVar != null) {
                aVar.l = false;
                aVar.f();
            }
        }
    }

    private static final class b extends v<a> {
        public b(a aVar) {
            super(aVar);
        }

        public final void run() {
            a aVar = (a) a();
            if (aVar != null) {
                aVar.h();
            }
        }
    }

    private class c extends BroadcastReceiver {
        final /* synthetic */ a a;

        private c(a aVar) {
            this.a = aVar;
        }

        /* synthetic */ c(a aVar, byte b) {
            this(aVar);
        }

        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                a.m(this.a);
            } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                this.a.h();
            }
        }
    }

    static {
        com.facebook.ads.internal.q.a.d.a();
    }

    public a(Context context, String str, h hVar, com.facebook.ads.internal.r.b bVar, com.facebook.ads.internal.r.f fVar) {
        this(context, str, hVar, bVar, fVar, EnumSet.of(d.NONE));
    }

    private a(Context context, String str, h hVar, com.facebook.ads.internal.r.b bVar, com.facebook.ads.internal.r.f fVar, EnumSet<d> enumSet) {
        this.g = new Handler();
        this.x = false;
        this.y = -1;
        this.c = context.getApplicationContext();
        this.d = str;
        this.t = hVar;
        this.e = bVar;
        this.v = null;
        this.u = fVar;
        this.w = 1;
        this.z = new c();
        this.C = enumSet;
        this.f = new com.facebook.ads.internal.o.c(this.c);
        this.f.a((com.facebook.ads.internal.o.c.a) this);
        this.j = new a(this);
        this.k = new b(this);
        this.m = true;
        if (!this.m) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.c.registerReceiver(this.z, intentFilter);
            this.A = true;
        }
        try {
            CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(this.c);
            }
        } catch (Exception e) {
        }
        com.facebook.ads.internal.i.a.a(this.c).a();
        this.B = com.facebook.ads.internal.m.d.a(this.c);
    }

    private com.facebook.ads.internal.r.b e() {
        return this.e != null ? this.e : this.v == null ? com.facebook.ads.internal.r.b.NATIVE : this.v == g.INTERSTITIAL ? com.facebook.ads.internal.r.b.INTERSTITIAL : com.facebook.ads.internal.r.b.BANNER;
    }

    private void f() {
        try {
            j jVar = new j(this.c, this.d, this.t);
            Context context = this.c;
            com.facebook.ads.internal.i.c cVar = new com.facebook.ads.internal.i.c(this.c, false);
            String str = this.d;
            l lVar = this.v != null ? new l(this.v.b(), this.v.a()) : null;
            h hVar = this.t;
            com.facebook.ads.internal.r.f fVar = this.u;
            String a = com.facebook.ads.c.d() != com.facebook.ads.c.a.DEFAULT ? com.facebook.ads.c.d().a() : null;
            String a2 = m.a(e.a(this.t).a());
            int i = this.w;
            boolean a3 = com.facebook.ads.c.a(this.c);
            boolean c = com.facebook.ads.c.c();
            int q = com.facebook.ads.internal.l.a.q(this.c);
            String a4 = (q <= 0 || new Random().nextFloat() >= 1.0f / ((float) q)) ? null : o.a(Thread.currentThread().getStackTrace());
            this.s = new com.facebook.ads.internal.o.b(context, cVar, str, lVar, hVar, fVar, a, a2, i, a3, c, jVar, a4);
            this.f.a(this.s);
        } catch (com.facebook.ads.internal.r.d e) {
            a(com.facebook.ads.internal.r.c.a(e));
        }
    }

    private synchronized void g() {
        h.post(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void run() {
                try {
                    a.e(this.a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void h() {
        if (!this.m && !this.l) {
            switch (e()) {
                case INTERSTITIAL:
                    if (!com.facebook.ads.internal.q.e.a.a(this.c)) {
                        this.g.postDelayed(this.k, 1000);
                    }
                    long c = this.r == null ? 30000 : this.r.a().c();
                    if (c > 0) {
                        this.g.postDelayed(this.j, c);
                        this.l = true;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private Handler i() {
        return !j() ? this.g : h;
    }

    private static synchronized boolean j() {
        boolean z;
        synchronized (a.class) {
            z = i;
        }
        return z;
    }

    public final com.facebook.ads.internal.h.d a() {
        return this.r == null ? null : this.r.a();
    }

    public final void a(f fVar) {
        this.a = fVar;
    }

    public final synchronized void a(final com.facebook.ads.internal.o.f fVar) {
        i().post(new Runnable(this) {
            final /* synthetic */ a b;

            public final void run() {
                com.facebook.ads.internal.h.c a = fVar.a();
                if (a == null || a.a() == null) {
                    throw new IllegalStateException("invalid placement in response");
                }
                this.b.r = a;
                this.b.g();
            }
        });
    }

    public final synchronized void a(final com.facebook.ads.internal.r.c cVar) {
        i().post(new Runnable(this) {
            final /* synthetic */ a b;

            public final void run() {
                this.b.a.a(cVar);
            }
        });
    }

    public final void b() {
        f();
    }

    public final void c() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r6 = this;
        r0 = r6.p;
        if (r0 != 0) goto L_0x0028;
    L_0x0004:
        r0 = r6.c;
        r1 = "api";
        r2 = com.facebook.ads.internal.q.d.b.e;
        r3 = new com.facebook.ads.internal.r.d;
        r4 = com.facebook.ads.internal.r.a.NO_ADAPTER_ON_START;
        r5 = "Adapter is null on startAd";
        r3.<init>(r4, r5);
        com.facebook.ads.internal.q.d.a.a(r0, r1, r2, r3);
        r0 = r6.a;
        r1 = com.facebook.ads.internal.r.a.INTERNAL_ERROR;
        r2 = com.facebook.ads.internal.r.a.INTERNAL_ERROR;
        r2 = r2.b();
        r1 = com.facebook.ads.internal.r.c.a(r1, r2);
        r0.a(r1);
    L_0x0027:
        return;
    L_0x0028:
        r0 = r6.n;
        if (r0 == 0) goto L_0x0050;
    L_0x002c:
        r0 = r6.c;
        r1 = "api";
        r2 = com.facebook.ads.internal.q.d.b.c;
        r3 = new com.facebook.ads.internal.r.d;
        r4 = com.facebook.ads.internal.r.a.AD_ALREADY_STARTED;
        r5 = "ad already started";
        r3.<init>(r4, r5);
        com.facebook.ads.internal.q.d.a.a(r0, r1, r2, r3);
        r0 = r6.a;
        r1 = com.facebook.ads.internal.r.a.AD_ALREADY_STARTED;
        r2 = com.facebook.ads.internal.r.a.AD_ALREADY_STARTED;
        r2 = r2.b();
        r1 = com.facebook.ads.internal.r.c.a(r1, r2);
        r0.a(r1);
        goto L_0x0027;
    L_0x0050:
        r0 = 1;
        r6.n = r0;
        r0 = r6.p;
        r0 = r0.d();
        r1 = com.facebook.ads.internal.a.AnonymousClass7.a;
        r0 = r0.ordinal();
        r0 = r1[r0];
        switch(r0) {
            case 1: goto L_0x0065;
            case 2: goto L_0x006d;
            case 3: goto L_0x0072;
            case 4: goto L_0x0072;
            case 5: goto L_0x008a;
            case 6: goto L_0x0092;
            default: goto L_0x0064;
        };
    L_0x0064:
        goto L_0x0027;
    L_0x0065:
        r0 = r6.p;
        r0 = (com.facebook.ads.internal.adapters.d) r0;
        r0.a();
        goto L_0x0027;
    L_0x006d:
        r0 = r6.q;
        if (r0 == 0) goto L_0x0027;
    L_0x0071:
        goto L_0x0027;
    L_0x0072:
        r0 = r6.p;
        r0 = (com.facebook.ads.internal.adapters.ai) r0;
        r1 = r0.g_();
        if (r1 != 0) goto L_0x0084;
    L_0x007c:
        r0 = new java.lang.IllegalStateException;
        r1 = "ad is not ready or already displayed";
        r0.<init>(r1);
        throw r0;
    L_0x0084:
        r1 = r6.a;
        r1.a(r0);
        goto L_0x0027;
    L_0x008a:
        r0 = r6.p;
        r0 = (com.facebook.ads.internal.adapters.ab) r0;
        r0.f();
        goto L_0x0027;
    L_0x0092:
        r0 = r6.p;
        r0 = (com.facebook.ads.internal.adapters.h) r0;
        r1 = r6.y;
        r0.a(r1);
        r0.a();
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.a.c():void");
    }

    public final boolean d() {
        return this.r == null || this.r.d();
    }

    static /* synthetic */ void e(a aVar) {
        aVar.o = null;
        com.facebook.ads.internal.h.c cVar = aVar.r;
        final com.facebook.ads.internal.h.a c = cVar.c();
        if (c == null) {
            aVar.a.a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.NO_FILL, ""));
            aVar.h();
            return;
        }
        com.facebook.ads.internal.adapters.a a = m.a(c.a(), cVar.a().b());
        if (a == null) {
            aVar.g();
        } else if (aVar.e() != a.d()) {
            aVar.a.a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.INTERNAL_ERROR, ""));
        } else {
            aVar.o = a;
            Map hashMap = new HashMap();
            com.facebook.ads.internal.h.d a2 = cVar.a();
            hashMap.put("data", c.b());
            hashMap.put("definition", a2);
            hashMap.put("placementId", aVar.d);
            hashMap.put("requestTime", Long.valueOf(a2.a()));
            if (aVar.s == null) {
                aVar.a.a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.UNKNOWN_ERROR, "environment is empty"));
                return;
            }
            switch (a.d()) {
                case INTERSTITIAL:
                    final com.facebook.ads.internal.adapters.d dVar = (com.facebook.ads.internal.adapters.d) a;
                    final Runnable anonymousClass2 = new Runnable(aVar) {
                        final /* synthetic */ a b;

                        public final void run() {
                            a.a(dVar);
                            this.b.g();
                        }
                    };
                    aVar.g.postDelayed(anonymousClass2, (long) cVar.a().j());
                    dVar.a(aVar.c, new com.facebook.ads.internal.adapters.e(aVar) {
                        final /* synthetic */ a b;

                        public final void a() {
                            this.b.a.b();
                        }

                        public final void a(com.facebook.ads.internal.adapters.d dVar) {
                            if (dVar == this.b.o) {
                                if (dVar == null) {
                                    com.facebook.ads.internal.q.d.a.a(this.b.c, "api", com.facebook.ads.internal.q.d.b.b, new com.facebook.ads.internal.r.d(com.facebook.ads.internal.r.a.NO_ADAPTER_ON_LOAD, "Adapter is null on loadInterstitialAd"));
                                    a(dVar, com.facebook.ads.a.e);
                                    return;
                                }
                                this.b.g.removeCallbacks(anonymousClass2);
                                this.b.p = dVar;
                                this.b.a.c();
                                this.b.h();
                            }
                        }

                        public final void a(com.facebook.ads.internal.adapters.d dVar, com.facebook.ads.a aVar) {
                            if (dVar == this.b.o) {
                                this.b.g.removeCallbacks(anonymousClass2);
                                a.a((com.facebook.ads.internal.adapters.a) dVar);
                                this.b.g();
                                this.b.a.a(new com.facebook.ads.internal.r.c(aVar.a(), aVar.b()));
                            }
                        }

                        public final void a(String str) {
                            this.b.a.a();
                            if ((!TextUtils.isEmpty(str) ? 1 : null) != null) {
                                Intent intent = new Intent("android.intent.action.VIEW");
                                if (!(this.b.s.b instanceof Activity)) {
                                    intent.addFlags(ErrorDialogData.BINDER_CRASH);
                                }
                                intent.setData(Uri.parse(str));
                                this.b.s.b.startActivity(intent);
                            }
                        }

                        public final void b() {
                            f fVar = this.b.a;
                        }

                        public final void c() {
                            f fVar = this.b.a;
                        }

                        public final void d() {
                            f fVar = this.b.a;
                        }
                    }, hashMap, aVar.B, aVar.C);
                    return;
                case BANNER:
                    final com.facebook.ads.internal.adapters.b bVar = (com.facebook.ads.internal.adapters.b) a;
                    final Runnable anonymousClass11 = new Runnable(aVar) {
                        final /* synthetic */ a b;

                        public final void run() {
                            a.a(bVar);
                            this.b.g();
                        }
                    };
                    aVar.g.postDelayed(anonymousClass11, (long) cVar.a().j());
                    bVar.a(aVar.c, aVar.B, new com.facebook.ads.internal.adapters.c(aVar) {
                        final /* synthetic */ a b;

                        public final void a() {
                            this.b.a.b();
                        }

                        public final void a(com.facebook.ads.internal.adapters.b bVar) {
                            if (bVar == this.b.o) {
                                this.b.g.removeCallbacks(anonymousClass11);
                                a.a((com.facebook.ads.internal.adapters.a) bVar);
                                this.b.g();
                            }
                        }

                        public final void a(com.facebook.ads.internal.adapters.b bVar, View view) {
                            if (bVar == this.b.o) {
                                this.b.g.removeCallbacks(anonymousClass11);
                                com.facebook.ads.internal.adapters.a i = this.b.p;
                                this.b.p = bVar;
                                this.b.q = view;
                                if (this.b.n) {
                                    f fVar = this.b.a;
                                    a.a(i);
                                    return;
                                }
                                this.b.a.c();
                            }
                        }

                        public final void b() {
                            this.b.a.a();
                        }
                    }, hashMap);
                    return;
                case NATIVE:
                case NATIVE_BANNER:
                    final ai aiVar = (ai) a;
                    final long currentTimeMillis = System.currentTimeMillis();
                    Runnable anonymousClass4 = new Runnable(aVar) {
                        final /* synthetic */ a d;

                        public final void run() {
                            a.a(aiVar);
                            if (aiVar instanceof ac) {
                                com.facebook.ads.internal.q.a.d.a(this.d.c, af.a(((ac) aiVar).D()) + " Failed. Ad request timed out");
                            }
                            Map a = a.a(currentTimeMillis);
                            a.put("error", "-1");
                            a.put("msg", "timeout");
                            a.a(this.d, c.a(com.facebook.ads.internal.h.e.REQUEST), a);
                            this.d.g();
                        }
                    };
                    aVar.g.postDelayed(anonymousClass4, (long) cVar.a().j());
                    final Runnable runnable = anonymousClass4;
                    final long j = currentTimeMillis;
                    final com.facebook.ads.internal.h.a aVar2 = c;
                    aiVar.a(aVar.c, new aj(aVar) {
                        boolean a = false;
                        boolean b = false;
                        boolean c = false;
                        final /* synthetic */ a g;

                        public final void a() {
                            if (!this.b) {
                                this.b = true;
                                a.a(this.g, aVar2.a(com.facebook.ads.internal.h.e.IMPRESSION), null);
                            }
                        }

                        public final void a(ai aiVar) {
                            if (aiVar == this.g.o) {
                                this.g.g.removeCallbacks(runnable);
                                this.g.p = aiVar;
                                this.g.a.c();
                                if (!this.a) {
                                    this.a = true;
                                    a.a(this.g, aVar2.a(com.facebook.ads.internal.h.e.REQUEST), a.a(j));
                                }
                            }
                        }

                        public final void a(ai aiVar, com.facebook.ads.internal.r.c cVar) {
                            if (aiVar == this.g.o) {
                                this.g.g.removeCallbacks(runnable);
                                a.a((com.facebook.ads.internal.adapters.a) aiVar);
                                if (!this.a) {
                                    this.a = true;
                                    Map a = a.a(j);
                                    a.put("error", String.valueOf(cVar.a().a()));
                                    a.put("msg", String.valueOf(cVar.b()));
                                    a.a(this.g, aVar2.a(com.facebook.ads.internal.h.e.REQUEST), a);
                                }
                                this.g.g();
                            }
                        }

                        public final void b() {
                            if (!this.c) {
                                this.c = true;
                                a.a(this.g, aVar2.a(com.facebook.ads.internal.h.e.CLICK), null);
                            }
                            if (this.g.a != null) {
                                this.g.a.a();
                            }
                        }
                    }, aVar.B, hashMap, com.facebook.ads.h.b());
                    return;
                case INSTREAM:
                    ((ab) a).a(aVar.c, new com.facebook.ads.a.a(aVar) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public final void a() {
                            f fVar = this.a.a;
                        }

                        public final void a(com.facebook.ads.a aVar) {
                            this.a.a.a(new com.facebook.ads.internal.r.c(aVar.a(), aVar.b()));
                        }

                        public final void a(ab abVar) {
                            this.a.p = abVar;
                            this.a.n = false;
                            this.a.a.c();
                        }

                        public final void b() {
                            this.a.a.a();
                        }

                        public final void c() {
                            this.a.a.b();
                        }

                        public final void d() {
                            f fVar = this.a.a;
                        }
                    }, hashMap, aVar.B, aVar.C);
                    return;
                case REWARDED_VIDEO:
                    ((com.facebook.ads.internal.adapters.h) a).a(aVar.c, new i(aVar) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public final void a() {
                            this.a.a.a();
                        }

                        public final void a(com.facebook.ads.internal.adapters.h hVar) {
                            this.a.p = hVar;
                            this.a.a.c();
                        }

                        public final void b() {
                            this.a.a.b();
                        }

                        public final void b(com.facebook.ads.internal.adapters.h hVar) {
                            this.a.a.a(new com.facebook.ads.internal.r.c(com.facebook.ads.internal.r.a.INTERNAL_ERROR, null));
                            a.a((com.facebook.ads.internal.adapters.a) hVar);
                            this.a.g();
                        }

                        public final void c() {
                            f fVar = this.a.a;
                        }

                        public final void d() {
                            f fVar = this.a.a;
                        }

                        public final void e() {
                            f fVar = this.a.a;
                        }

                        public final void f() {
                            f fVar = this.a.a;
                        }

                        public final void g() {
                            f fVar = this.a.a;
                        }
                    }, hashMap, aVar.x);
                    return;
                default:
                    return;
            }
        }
    }

    static /* synthetic */ void a(com.facebook.ads.internal.adapters.a aVar) {
        if (aVar != null) {
            aVar.e();
        }
    }

    static /* synthetic */ Map a(long j) {
        Map hashMap = new HashMap();
        hashMap.put("delay", String.valueOf(System.currentTimeMillis() - j));
        return hashMap;
    }

    static /* synthetic */ void a(a aVar, List list, Map map) {
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                new com.facebook.ads.internal.q.c.e(aVar.c, map).execute(new String[]{str});
            }
        }
    }

    static /* synthetic */ void m(a aVar) {
        if (aVar.l) {
            aVar.g.removeCallbacks(aVar.j);
            aVar.l = false;
        }
    }
}
