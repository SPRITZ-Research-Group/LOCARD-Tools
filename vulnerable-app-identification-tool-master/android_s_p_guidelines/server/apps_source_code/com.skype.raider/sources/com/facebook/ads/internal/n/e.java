package com.facebook.ads.internal.n;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.adapters.ah;
import com.facebook.ads.internal.adapters.ai;
import com.facebook.ads.internal.adapters.aj;
import com.facebook.ads.internal.adapters.v;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.r.f;
import com.facebook.ads.internal.r.h;
import com.facebook.ads.internal.view.w;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class e {
    private static final f b = f.ADS;
    private static final String c = e.class.getSimpleName();
    private static WeakHashMap<View, WeakReference<e>> d = new WeakHashMap();
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private long E;
    @Nullable
    private com.facebook.ads.internal.view.b.c F;
    private d G;
    private com.facebook.ads.internal.adapters.ah.a H;
    private View I;
    @Nullable
    protected ai a;
    private final Context e;
    private final String f;
    private final String g;
    private final com.facebook.ads.internal.d.b h;
    @Nullable
    private h i;
    private final c j;
    private com.facebook.ads.internal.a k;
    private volatile boolean l;
    @Nullable
    private d m;
    private h n;
    @Nullable
    private View o;
    @Nullable
    private g p;
    private final List<View> q;
    private OnTouchListener r;
    private com.facebook.ads.internal.s.a s;
    private com.facebook.ads.internal.s.a.a t;
    private WeakReference<com.facebook.ads.internal.s.a.a> u;
    private final s v;
    @Nullable
    private ah w;
    private a x;
    private w y;
    private k z;

    public interface c {
        boolean a(View view);
    }

    private class a implements OnClickListener, OnLongClickListener, OnTouchListener {
        final /* synthetic */ e a;

        private a(e eVar) {
            this.a = eVar;
        }

        /* synthetic */ a(e eVar, byte b) {
            this(eVar);
        }

        public final void onClick(View view) {
            this.a.v.d();
            int p = com.facebook.ads.internal.l.a.p(this.a.e);
            if (p < 0 || this.a.v.c() >= ((long) p)) {
                Map hashMap = new HashMap();
                hashMap.put("touch", k.a(this.a.v.e()));
                if (this.a.z != null) {
                    hashMap.put("nti", String.valueOf(this.a.z.a()));
                }
                if (this.a.A) {
                    hashMap.put("nhs", String.valueOf(this.a.A));
                }
                this.a.s.a(hashMap);
                if (this.a.a != null) {
                    this.a.a.b(hashMap);
                    return;
                }
                return;
            }
            this.a.v.b();
        }

        public final boolean onLongClick(View view) {
            boolean z = false;
            if (this.a.o == null || this.a.F == null) {
                return false;
            }
            this.a.F.setBounds(0, 0, this.a.o.getWidth(), this.a.o.getHeight());
            com.facebook.ads.internal.view.b.c u = this.a.F;
            if (!this.a.F.a()) {
                z = true;
            }
            u.a(z);
            return true;
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.v.a(motionEvent, this.a.o, view);
            return this.a.r != null && this.a.r.onTouch(view, motionEvent);
        }
    }

    private class b extends com.facebook.ads.internal.adapters.k {
        final /* synthetic */ e a;

        private b(e eVar) {
            this.a = eVar;
        }

        /* synthetic */ b(e eVar, byte b) {
            this(eVar);
        }

        public final void a() {
            if (this.a.i != null) {
                this.a.i.b();
            }
        }
    }

    public e(Context context, ai aiVar, c cVar) {
        this(context, null, cVar);
        this.a = aiVar;
        this.m = null;
        this.l = true;
        this.I = new View(context);
    }

    public e(Context context, String str, c cVar) {
        this.g = UUID.randomUUID().toString();
        this.n = h.NATIVE_UNKNOWN;
        this.q = new ArrayList();
        this.v = new s();
        this.B = false;
        this.C = false;
        this.G = d.ALL;
        this.H = com.facebook.ads.internal.adapters.ah.a.ALL;
        this.e = context;
        this.f = str;
        this.j = cVar;
        this.h = new com.facebook.ads.internal.d.b(context);
        this.I = new View(context);
    }

    public static void a(f fVar, ImageView imageView) {
        if (fVar != null) {
            new com.facebook.ads.internal.view.b.d(imageView).a(fVar.c(), fVar.b()).a(fVar.a());
        }
    }

    private void a(List<View> list, View view) {
        if (this.j != null && this.j.a(view)) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                a((List) list, viewGroup.getChildAt(i));
            }
            return;
        }
        list.add(view);
    }

    private com.facebook.ads.internal.r.b x() {
        return this.n == h.NATIVE_UNKNOWN ? com.facebook.ads.internal.r.b.NATIVE : com.facebook.ads.internal.r.b.NATIVE_BANNER;
    }

    private boolean y() {
        return this.a != null && ((v) this.a).D();
    }

    private void z() {
        for (View view : this.q) {
            view.setOnClickListener(null);
            view.setOnTouchListener(null);
            view.setOnLongClickListener(null);
        }
        this.q.clear();
    }

    public final ai a() {
        return this.a;
    }

    public final void a(View view, g gVar) {
        List arrayList = new ArrayList();
        a(arrayList, view);
        a(view, gVar, arrayList);
    }

    public final void a(android.view.View r9, com.facebook.ads.internal.n.g r10, java.util.List<android.view.View> r11) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r8 = this;
        r4 = 1;
        r6 = 0;
        if (r9 != 0) goto L_0x000c;
    L_0x0004:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Must provide a View";
        r0.<init>(r1);
        throw r0;
    L_0x000c:
        if (r11 == 0) goto L_0x0014;
    L_0x000e:
        r0 = r11.size();
        if (r0 != 0) goto L_0x001c;
    L_0x0014:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Invalid set of clickable views";
        r0.<init>(r1);
        throw r0;
    L_0x001c:
        r0 = r8.c();
        if (r0 != 0) goto L_0x0023;
    L_0x0022:
        return;
    L_0x0023:
        if (r10 != 0) goto L_0x005a;
    L_0x0025:
        r0 = r8.n;
        r1 = com.facebook.ads.internal.r.h.NATIVE_UNKNOWN;
        if (r0 != r1) goto L_0x0044;
    L_0x002b:
        r0 = r8.i;
        if (r0 == 0) goto L_0x003d;
    L_0x002f:
        r0 = r8.i;
        r1 = new com.facebook.ads.internal.r.c;
        r2 = com.facebook.ads.internal.r.a.NO_MEDIAVIEW_IN_NATIVEAD;
        r3 = "MediaView is missing.";
        r1.<init>(r2, r3);
        r0.a(r1);
    L_0x003d:
        r0 = com.facebook.ads.internal.t.a.f();
        if (r0 == 0) goto L_0x0022;
    L_0x0043:
        goto L_0x0022;
    L_0x0044:
        r0 = r8.i;
        if (r0 == 0) goto L_0x0056;
    L_0x0048:
        r0 = r8.i;
        r1 = new com.facebook.ads.internal.r.c;
        r2 = com.facebook.ads.internal.r.a.NO_ICONVIEW_IN_NATIVEBANNERAD;
        r3 = "AdIconView is missing.";
        r1.<init>(r2, r3);
        r0.a(r1);
    L_0x0056:
        com.facebook.ads.internal.t.a.f();
        goto L_0x0022;
    L_0x005a:
        r0 = r10.a();
        if (r0 != 0) goto L_0x0073;
    L_0x0060:
        r0 = r8.i;
        if (r0 == 0) goto L_0x0022;
    L_0x0064:
        r0 = r8.i;
        r1 = new com.facebook.ads.internal.r.c;
        r2 = com.facebook.ads.internal.r.a.UNSUPPORTED_AD_ASSET_NATIVEAD;
        r3 = "ad media type is not supported.";
        r1.<init>(r2, r3);
        r0.a(r1);
        goto L_0x0022;
    L_0x0073:
        r0 = r8.o;
        if (r0 == 0) goto L_0x007a;
    L_0x0077:
        r8.u();
    L_0x007a:
        r0 = d;
        r0 = r0.containsKey(r9);
        if (r0 == 0) goto L_0x00a1;
    L_0x0082:
        r0 = d;
        r0 = r0.get(r9);
        r0 = (java.lang.ref.WeakReference) r0;
        r0 = r0.get();
        if (r0 == 0) goto L_0x00a1;
    L_0x0090:
        r0 = d;
        r0 = r0.get(r9);
        r0 = (java.lang.ref.WeakReference) r0;
        r0 = r0.get();
        r0 = (com.facebook.ads.internal.n.e) r0;
        r0.u();
    L_0x00a1:
        r0 = new com.facebook.ads.internal.n.e$a;
        r0.<init>(r8, r6);
        r8.x = r0;
        r8.o = r9;
        r8.p = r10;
        r0 = r9 instanceof android.view.ViewGroup;
        if (r0 == 0) goto L_0x00c8;
    L_0x00b0:
        r0 = new com.facebook.ads.internal.view.w;
        r1 = r9.getContext();
        r2 = new com.facebook.ads.internal.n.e$3;
        r2.<init>(r8);
        r0.<init>(r1, r2);
        r8.y = r0;
        r0 = r9;
        r0 = (android.view.ViewGroup) r0;
        r1 = r8.y;
        r0.addView(r1);
    L_0x00c8:
        r7 = new java.util.ArrayList;
        r7.<init>(r11);
        r0 = r8.I;
        if (r0 == 0) goto L_0x00d6;
    L_0x00d1:
        r0 = r8.I;
        r7.add(r0);
    L_0x00d6:
        r1 = r7.iterator();
    L_0x00da:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0105;
    L_0x00e0:
        r0 = r1.next();
        r0 = (android.view.View) r0;
        r2 = r8.q;
        r2.add(r0);
        r2 = r8.x;
        r0.setOnClickListener(r2);
        r2 = r8.x;
        r0.setOnTouchListener(r2);
        r2 = r0.getContext();
        r2 = com.facebook.ads.internal.l.a.b(r2);
        if (r2 == 0) goto L_0x00da;
    L_0x00ff:
        r2 = r8.x;
        r0.setOnLongClickListener(r2);
        goto L_0x00da;
    L_0x0105:
        r0 = r8.a;
        r0.a(r9, r7);
        r0 = r8.m;
        if (r0 == 0) goto L_0x01d0;
    L_0x010e:
        r0 = r8.m;
        r2 = r0.f();
    L_0x0114:
        r0 = new com.facebook.ads.internal.n.e$4;
        r0.<init>(r8);
        r8.t = r0;
        r0 = new com.facebook.ads.internal.s.a;
        if (r10 == 0) goto L_0x01e8;
    L_0x011f:
        r1 = r10.a();
    L_0x0123:
        r3 = r8.m;
        if (r3 == 0) goto L_0x01ec;
    L_0x0127:
        r3 = r8.m;
        r3 = r3.g();
    L_0x012d:
        r5 = r8.t;
        r0.<init>(r1, r2, r3, r4, r5);
        r8.s = r0;
        r1 = r8.s;
        r0 = r8.m;
        if (r0 == 0) goto L_0x0204;
    L_0x013a:
        r0 = r8.m;
        r0 = r0.h();
    L_0x0140:
        r1.a(r0);
        r1 = r8.s;
        r0 = r8.m;
        if (r0 == 0) goto L_0x022b;
    L_0x0149:
        r0 = r8.m;
        r0 = r0.i();
    L_0x014f:
        r1.b(r0);
        r0 = new com.facebook.ads.internal.adapters.ah;
        r1 = r8.e;
        r2 = new com.facebook.ads.internal.n.e$b;
        r2.<init>(r8, r6);
        r3 = r8.s;
        r4 = r8.a;
        r0.<init>(r1, r2, r3, r4);
        r8.w = r0;
        r0 = r8.w;
        r0.a(r7);
        r0 = d;
        r1 = new java.lang.ref.WeakReference;
        r1.<init>(r8);
        r0.put(r9, r1);
        r0 = r8.e;
        r0 = com.facebook.ads.internal.l.a.b(r0);
        if (r0 == 0) goto L_0x0022;
    L_0x017b:
        r0 = new com.facebook.ads.internal.view.b.c;
        r0.<init>();
        r8.F = r0;
        r0 = r8.F;
        r1 = r8.f;
        r0.a(r1);
        r0 = r8.F;
        r1 = r8.e;
        r1 = r1.getPackageName();
        r0.b(r1);
        r0 = r8.F;
        r1 = r8.s;
        r0.a(r1);
        r0 = r8.a;
        r0 = r0.C();
        if (r0 <= 0) goto L_0x01b4;
    L_0x01a3:
        r0 = r8.F;
        r1 = r8.a;
        r1 = r1.C();
        r2 = r8.a;
        r2 = r2.B();
        r0.a(r1, r2);
    L_0x01b4:
        r0 = r8.m;
        if (r0 == 0) goto L_0x0253;
    L_0x01b8:
        r0 = r8.F;
        r1 = r8.m;
        r2 = r1.a();
        r0.a(r2);
    L_0x01c3:
        r0 = r8.o;
        r0 = r0.getOverlay();
        r1 = r8.F;
        r0.add(r1);
        goto L_0x0022;
    L_0x01d0:
        r0 = r8.k;
        if (r0 == 0) goto L_0x0273;
    L_0x01d4:
        r0 = r8.k;
        r0 = r0.a();
        if (r0 == 0) goto L_0x0273;
    L_0x01dc:
        r0 = r8.k;
        r0 = r0.a();
        r2 = r0.f();
        goto L_0x0114;
    L_0x01e8:
        r1 = r8.o;
        goto L_0x0123;
    L_0x01ec:
        r3 = r8.k;
        if (r3 == 0) goto L_0x0270;
    L_0x01f0:
        r3 = r8.k;
        r3 = r3.a();
        if (r3 == 0) goto L_0x0270;
    L_0x01f8:
        r3 = r8.k;
        r3 = r3.a();
        r3 = r3.g();
        goto L_0x012d;
    L_0x0204:
        r0 = r8.a;
        if (r0 == 0) goto L_0x0210;
    L_0x0208:
        r0 = r8.a;
        r0 = r0.j();
        goto L_0x0140;
    L_0x0210:
        r0 = r8.k;
        if (r0 == 0) goto L_0x0228;
    L_0x0214:
        r0 = r8.k;
        r0 = r0.a();
        if (r0 == 0) goto L_0x0228;
    L_0x021c:
        r0 = r8.k;
        r0 = r0.a();
        r0 = r0.h();
        goto L_0x0140;
    L_0x0228:
        r0 = r6;
        goto L_0x0140;
    L_0x022b:
        r0 = r8.a;
        if (r0 == 0) goto L_0x0237;
    L_0x022f:
        r0 = r8.a;
        r0 = r0.k();
        goto L_0x014f;
    L_0x0237:
        r0 = r8.k;
        if (r0 == 0) goto L_0x024f;
    L_0x023b:
        r0 = r8.k;
        r0 = r0.a();
        if (r0 == 0) goto L_0x024f;
    L_0x0243:
        r0 = r8.k;
        r0 = r0.a();
        r0 = r0.i();
        goto L_0x014f;
    L_0x024f:
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        goto L_0x014f;
    L_0x0253:
        r0 = r8.k;
        if (r0 == 0) goto L_0x01c3;
    L_0x0257:
        r0 = r8.k;
        r0 = r0.a();
        if (r0 == 0) goto L_0x01c3;
    L_0x025f:
        r0 = r8.F;
        r1 = r8.k;
        r1 = r1.a();
        r2 = r1.a();
        r0.a(r2);
        goto L_0x01c3;
    L_0x0270:
        r3 = r6;
        goto L_0x012d;
    L_0x0273:
        r2 = r4;
        goto L_0x0114;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.n.e.a(android.view.View, com.facebook.ads.internal.n.g, java.util.List):void");
    }

    public final void a(aj ajVar) {
        if (this.a != null) {
            this.a.a(ajVar);
        }
    }

    public final void a(d dVar) {
        if (this.l) {
            throw new IllegalStateException("loadAd cannot be called more than once");
        }
        this.E = System.currentTimeMillis();
        this.l = true;
        this.G = dVar;
        if (dVar.equals(d.NONE)) {
            this.H = com.facebook.ads.internal.adapters.ah.a.NONE;
        }
        this.k = new com.facebook.ads.internal.a(this.e, this.f, this.n, x(), b);
        this.k.a(new com.facebook.ads.internal.adapters.f(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public final void a() {
                if (this.a.i != null) {
                    this.a.i;
                }
            }

            public final void a(ai aiVar) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(com.facebook.ads.internal.j.a.b.a, this.a.x().toString(), System.currentTimeMillis() - this.a.E));
                e.a(this.a, aiVar);
                if (this.a.i != null && aiVar.A() != null) {
                    aj anonymousClass1 = new aj(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public final void a() {
                        }

                        public final void a(ai aiVar) {
                        }

                        public final void a(ai aiVar, com.facebook.ads.internal.r.c cVar) {
                        }

                        public final void b() {
                            if (this.a.a.i != null) {
                                this.a.a.i;
                            }
                        }
                    };
                    for (e a : aiVar.A()) {
                        a.a(anonymousClass1);
                    }
                }
            }

            public final void a(com.facebook.ads.internal.r.c cVar) {
                if (this.a.i != null) {
                    this.a.i.a(cVar);
                }
            }

            public final void b() {
                throw new IllegalStateException("Native ads manager their own impressions.");
            }

            public final void c() {
                if (this.a.k != null) {
                    this.a.k.c();
                }
            }
        });
        this.k.b();
    }

    public final void a(h hVar) {
        this.i = hVar;
    }

    public final void a(h hVar) {
        this.n = hVar;
    }

    public final void a(com.facebook.ads.internal.s.a.a aVar) {
        this.u = new WeakReference(aVar);
    }

    public final void a(boolean z) {
        this.D = z;
    }

    public final void a(boolean z, boolean z2) {
        if (z) {
            if (this.G.equals(d.NONE)) {
                y();
            }
            if (this.s != null) {
                this.s.a();
                return;
            }
            return;
        }
        if (this.s != null) {
            this.s.c();
        }
        if (this.i != null && z2) {
            this.i.a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.BROKEN_MEDIA_ERROR, "Failed to load Media."));
        }
    }

    public final boolean b() {
        return this.k == null || this.k.d();
    }

    public final boolean c() {
        return this.a != null && this.a.g_();
    }

    public final f d() {
        return !c() ? null : this.a.l();
    }

    public final f e() {
        return !c() ? null : this.a.m();
    }

    public final String f() {
        return !c() ? null : this.a.n();
    }

    public final String g() {
        return !c() ? null : this.a.o();
    }

    public final String h() {
        return !c() ? null : this.a.p();
    }

    public final String i() {
        return !c() ? null : this.a.q();
    }

    public final String j() {
        return !c() ? null : this.a.r();
    }

    public final f k() {
        return !c() ? null : this.a.s();
    }

    public final String l() {
        return !c() ? null : this.a.t();
    }

    public final String m() {
        return !c() ? null : this.a.u();
    }

    public final String n() {
        return (!c() || TextUtils.isEmpty(this.a.v())) ? null : this.h.b(this.a.v());
    }

    public final String o() {
        return !c() ? null : this.a.w();
    }

    public final String p() {
        return !c() ? null : this.a.z();
    }

    public final l q() {
        return !c() ? l.DEFAULT : this.a.x();
    }

    public final List<e> r() {
        return !c() ? null : this.a.A();
    }

    @Nullable
    public final String s() {
        return !c() ? null : this.a.c();
    }

    public final void t() {
        this.I.performClick();
    }

    public final void u() {
        if (this.o != null && this.p != null) {
            if (d.containsKey(this.o) && ((WeakReference) d.get(this.o)).get() == this) {
                if ((this.o instanceof ViewGroup) && this.y != null) {
                    ((ViewGroup) this.o).removeView(this.y);
                    this.y = null;
                }
                if (this.a != null) {
                    this.a.f_();
                }
                if (this.F != null && com.facebook.ads.internal.l.a.b(this.e)) {
                    this.F.b();
                    this.o.getOverlay().remove(this.F);
                }
                d.remove(this.o);
                z();
                this.o = null;
                this.p = null;
                if (this.s != null) {
                    this.s.c();
                    this.s = null;
                }
                this.w = null;
                return;
            }
            throw new IllegalStateException("View not registered with this NativeAd");
        }
    }

    public final void v() {
        this.B = true;
    }

    public final void w() {
        this.C = true;
    }

    static /* synthetic */ void a(e eVar, final ai aiVar) {
        if (aiVar != null) {
            if (eVar.G.equals(d.ALL)) {
                if (aiVar.l() != null) {
                    eVar.h.a(aiVar.l().a(), aiVar.l().c(), aiVar.l().b());
                }
                if (aiVar.m() != null) {
                    eVar.h.a(aiVar.m().a(), aiVar.m().c(), aiVar.m().b());
                }
                if (aiVar.A() != null) {
                    for (e eVar2 : aiVar.A()) {
                        if (eVar2.e() != null) {
                            eVar.h.a(eVar2.e().a(), eVar2.e().c(), eVar2.e().b());
                        }
                    }
                }
                if (!TextUtils.isEmpty(aiVar.v())) {
                    eVar.h.a(aiVar.v());
                }
            }
            eVar.h.a(new com.facebook.ads.internal.d.a(eVar) {
                final /* synthetic */ boolean b = true;
                final /* synthetic */ e c;

                public final void a() {
                    this.c.a = aiVar;
                    if (this.c.i != null) {
                        if (this.c.G.equals(d.ALL) && !this.c.y()) {
                            this.c.i;
                        }
                        if (this.b) {
                            this.c.i.a();
                        }
                    }
                }

                public final void b() {
                    if (this.c.a != null) {
                        this.c.a.f_();
                        this.c.a = null;
                    }
                    if (this.c.i != null) {
                        this.c.i.a(com.facebook.ads.internal.r.c.a(com.facebook.ads.internal.r.a.CACHE_FAILURE_ERROR, "Failed to download a media."));
                    }
                }
            });
        }
    }

    static /* synthetic */ boolean q(e eVar) {
        return eVar.q() == l.ON;
    }
}
