package com.facebook.share.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.ac;
import com.facebook.ad;
import com.facebook.ai;
import com.facebook.g;
import com.facebook.internal.a;
import com.facebook.internal.ar;
import com.facebook.internal.av;
import com.facebook.internal.be;
import com.facebook.internal.bj;
import com.facebook.internal.bt;
import com.facebook.internal.h;
import com.facebook.internal.i;
import com.facebook.internal.v;
import com.facebook.internal.z;
import com.facebook.n;
import com.facebook.p;
import com.facebook.s;
import defpackage.amm;
import defpackage.lj;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

@Deprecated
public class e {
    private static final String a = "e";
    private static v b;
    private static final ConcurrentHashMap<String, e> c = new ConcurrentHashMap();
    private static bt d = new bt(1);
    private static bt e = new bt(1);
    private static Handler f;
    private static String g;
    private static boolean h;
    private static volatile int i;
    private static g j;
    private String k;
    private com.facebook.share.widget.e l;
    private boolean m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private boolean t;
    private boolean u;
    private boolean v;
    private Bundle w;
    private amm x;

    /* renamed from: com.facebook.share.internal.e$4 */
    final /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[com.facebook.share.widget.e.values().length];

        static {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.share.internal.e.4.<clinit>():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r0 = com.facebook.share.widget.e.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = com.facebook.share.widget.e.PAGE;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.e.4.<clinit>():void");
        }
    }

    @Deprecated
    public static boolean a(final int i, final int i2, final Intent intent) {
        if (bj.a(g)) {
            g = s.f().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getString("PENDING_CONTROLLER_KEY", null);
        }
        if (bj.a(g)) {
            return false;
        }
        a(g, com.facebook.share.widget.e.UNKNOWN, new h() {
            public final void a(e eVar, n nVar) {
                if (nVar == null) {
                    e.a(eVar, i, intent);
                } else {
                    bj.a(e.a, (Exception) nVar);
                }
            }
        });
        return true;
    }

    @Deprecated
    public static void a(String str, com.facebook.share.widget.e eVar, h hVar) {
        if (!h) {
            h();
        }
        e b = b(str);
        if (b != null) {
            a(b, eVar, hVar);
        } else {
            e.a(new g(str, eVar, hVar));
        }
    }

    private static void a(e eVar, com.facebook.share.widget.e eVar2, h hVar) {
        n nVar;
        com.facebook.share.widget.e a = am.a(eVar2, eVar.l);
        if (a == null) {
            nVar = new n("Object with id:\"%s\" is already marked as type:\"%s\". Cannot change the type to:\"%s\"", eVar.k, eVar.l.toString(), eVar2.toString());
            eVar = null;
        } else {
            eVar.l = a;
            nVar = null;
        }
        a(hVar, eVar, nVar);
    }

    private static synchronized void h() {
        synchronized (e.class) {
            if (h) {
                return;
            }
            f = new Handler(Looper.getMainLooper());
            i = s.f().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getInt("OBJECT_SUFFIX", 1);
            b = new v(a, new z());
            j = new g() {
                protected final void a(AccessToken accessToken) {
                    Context f = s.f();
                    if (accessToken == null) {
                        e.i = (e.i + 1) % 1000;
                        f.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putInt("OBJECT_SUFFIX", e.i).apply();
                        e.c.clear();
                        e.b.a();
                    }
                    e.c(null, "com.facebook.sdk.LikeActionController.DID_RESET", null);
                }
            };
            com.facebook.internal.g.a(i.Like.a(), new h() {
                public final boolean a(int i, Intent intent) {
                    return e.a(i.Like.a(), i, intent);
                }
            });
            h = true;
        }
    }

    private static void a(final h hVar, final e eVar, final n nVar) {
        if (hVar != null) {
            f.post(new Runnable() {
                public final void run() {
                    hVar.a(eVar, nVar);
                }
            });
        }
    }

    private static e b(String str) {
        str = e(str);
        e eVar = (e) c.get(str);
        if (eVar != null) {
            d.a(new o(str, false));
        }
        return eVar;
    }

    private static void m(e eVar) {
        String n = n(eVar);
        String e = e(eVar.k);
        if (!bj.a(n) && !bj.a(e)) {
            e.a(new t(e, n));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static e c(String str) {
        Throwable e;
        Throwable th;
        e eVar = null;
        Closeable a;
        try {
            a = b.a(e(str), null);
            if (a != null) {
                try {
                    String a2 = bj.a((InputStream) a);
                    if (!bj.a(a2)) {
                        eVar = d(a2);
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        Log.e(a, "Unable to deserialize controller from disk", e);
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            bj.a(a);
                        }
                        throw th;
                    }
                }
            }
        } catch (IOException e3) {
            e = e3;
            a = null;
            Log.e(a, "Unable to deserialize controller from disk", e);
        } catch (Throwable th3) {
            th = th3;
            a = null;
            if (a != null) {
                bj.a(a);
            }
            throw th;
        }
    }

    private static e d(String str) {
        e eVar = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("com.facebook.share.internal.LikeActionController.version", -1) != 3) {
                return null;
            }
            e eVar2 = new e(jSONObject.getString("object_id"), com.facebook.share.widget.e.a(jSONObject.optInt("object_type", com.facebook.share.widget.e.UNKNOWN.a())));
            eVar2.n = jSONObject.optString("like_count_string_with_like", null);
            eVar2.o = jSONObject.optString("like_count_string_without_like", null);
            eVar2.p = jSONObject.optString("social_sentence_with_like", null);
            eVar2.q = jSONObject.optString("social_sentence_without_like", null);
            eVar2.m = jSONObject.optBoolean("is_object_liked");
            eVar2.r = jSONObject.optString("unlike_token", null);
            JSONObject optJSONObject = jSONObject.optJSONObject("facebook_dialog_analytics_bundle");
            if (optJSONObject != null) {
                eVar2.w = com.facebook.internal.e.a(optJSONObject);
            }
            eVar = eVar2;
            return eVar;
        } catch (Throwable e) {
            Log.e(a, "Unable to deserialize controller from JSON", e);
        }
    }

    private static String n(e eVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("com.facebook.share.internal.LikeActionController.version", 3);
            jSONObject.put("object_id", eVar.k);
            jSONObject.put("object_type", eVar.l.a());
            jSONObject.put("like_count_string_with_like", eVar.n);
            jSONObject.put("like_count_string_without_like", eVar.o);
            jSONObject.put("social_sentence_with_like", eVar.p);
            jSONObject.put("social_sentence_without_like", eVar.q);
            jSONObject.put("is_object_liked", eVar.m);
            jSONObject.put("unlike_token", eVar.r);
            if (eVar.w != null) {
                jSONObject.put("facebook_dialog_analytics_bundle", com.facebook.internal.e.a(eVar.w));
            }
            return jSONObject.toString();
        } catch (Throwable e) {
            Log.e(a, "Unable to serialize controller to JSON", e);
            return null;
        }
    }

    private static String e(String str) {
        String d = AccessToken.b() ? AccessToken.a().d() : null;
        if (d != null) {
            d = bj.b(d);
        }
        return String.format(Locale.ROOT, "%s|%s|com.fb.sdk.like|%d", new Object[]{str, bj.a(d, ""), Integer.valueOf(i)});
    }

    private static void c(e eVar, String str, Bundle bundle) {
        Intent intent = new Intent(str);
        if (eVar != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("com.facebook.sdk.LikeActionController.OBJECT_ID", eVar.k);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        lj.a(s.f()).a(intent);
    }

    private e(String str, com.facebook.share.widget.e eVar) {
        this.k = str;
        this.l = eVar;
    }

    @Deprecated
    public final String a() {
        return this.m ? this.n : this.o;
    }

    @Deprecated
    public final String b() {
        return this.m ? this.p : this.q;
    }

    @Deprecated
    public final boolean c() {
        return this.m;
    }

    @Deprecated
    public final void a(Bundle bundle) {
        boolean z = true;
        boolean z2 = this.m ^ true;
        if (j()) {
            b(z2);
            if (this.v) {
                i().b("fb_like_control_did_undo_quickly", bundle);
                return;
            }
            if (!a(z2, bundle)) {
                if (z2) {
                    z = false;
                }
                b(z);
            }
        }
        v.e();
        v.f();
        a("present_dialog", bundle);
        bj.a();
        c(null, "com.facebook.sdk.LikeActionController.UPDATED", null);
    }

    private amm i() {
        if (this.x == null) {
            this.x = amm.a(s.f());
        }
        return this.x;
    }

    private boolean a(boolean z, Bundle bundle) {
        if (j()) {
            if (z) {
                b(bundle);
                return true;
            } else if (!bj.a(this.r)) {
                c(bundle);
                return true;
            }
        }
        return false;
    }

    private void a(boolean z) {
        b(z);
        Bundle bundle = new Bundle();
        bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Unable to publish the like/unlike action");
        c(this, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
    }

    private void b(boolean z) {
        a(z, this.n, this.o, this.p, this.q, this.r);
    }

    private void a(boolean z, String str, String str2, String str3, String str4, String str5) {
        Object a = bj.a(str, null);
        Object a2 = bj.a(str2, null);
        Object a3 = bj.a(str3, null);
        Object a4 = bj.a(str4, null);
        Object a5 = bj.a(str5, null);
        Object obj = (z == this.m && bj.a(a, this.n) && bj.a(a2, this.o) && bj.a(a3, this.p) && bj.a(a4, this.q) && bj.a(a5, this.r)) ? null : 1;
        if (obj != null) {
            this.m = z;
            this.n = a;
            this.o = a2;
            this.p = a3;
            this.q = a4;
            this.r = a5;
            m(this);
            c(this, "com.facebook.sdk.LikeActionController.UPDATED", null);
        }
    }

    private boolean j() {
        AccessToken a = AccessToken.a();
        return (this.t || this.s == null || !AccessToken.b() || a.f() == null || !a.f().contains("publish_actions")) ? false : true;
    }

    private void b(final Bundle bundle) {
        this.v = true;
        a(new r(this) {
            final /* synthetic */ e b;

            public final void a() {
                if (bj.a(this.b.s)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Invalid Object Id");
                    e.c(this.b, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
                    return;
                }
                ac acVar = new ac();
                final p pVar = new p(this.b, this.b.s, this.b.l);
                pVar.a(acVar);
                acVar.a(new ad(this) {
                    final /* synthetic */ AnonymousClass10 b;

                    public final void a() {
                        this.b.b.v = false;
                        if (pVar.a() != null) {
                            this.b.b.a(false);
                            return;
                        }
                        this.b.b.r = bj.a(pVar.e, null);
                        this.b.b.u = true;
                        this.b.b.i().b("fb_like_control_did_like", bundle);
                        e.a(this.b.b, bundle);
                    }
                });
                GraphRequest.b(acVar);
            }
        });
    }

    private void c(final Bundle bundle) {
        this.v = true;
        ac acVar = new ac();
        final q qVar = new q(this, this.r);
        qVar.a(acVar);
        acVar.a(new ad(this) {
            final /* synthetic */ e c;

            public final void a() {
                this.c.v = false;
                if (qVar.a() != null) {
                    this.c.a(true);
                    return;
                }
                this.c.r = null;
                this.c.u = false;
                this.c.i().b("fb_like_control_did_unlike", bundle);
                e.a(this.c, bundle);
            }
        });
        GraphRequest.b(acVar);
    }

    private void a(final r rVar) {
        if (bj.a(this.s)) {
            final j jVar = new j(this, this.k, this.l);
            final l lVar = new l(this, this.k, this.l);
            ac acVar = new ac();
            jVar.a(acVar);
            lVar.a(acVar);
            acVar.a(new ad(this) {
                final /* synthetic */ e d;

                public final void a() {
                    this.d.s = jVar.e;
                    if (bj.a(this.d.s)) {
                        this.d.s = lVar.e;
                        this.d.t = lVar.f;
                    }
                    if (bj.a(this.d.s)) {
                        FacebookRequestError a;
                        ar.a(ai.DEVELOPER_ERRORS, e.a, "Unable to verify the FB id for '%s'. Verify that it is a valid FB object or page", this.d.k);
                        e eVar = this.d;
                        String str = "get_verified_id";
                        if (lVar.a() != null) {
                            a = lVar.a();
                        } else {
                            a = jVar.a();
                        }
                        e.a(eVar, str, a);
                    }
                    if (rVar != null) {
                        rVar.a();
                    }
                }
            });
            GraphRequest.b(acVar);
            return;
        }
        rVar.a();
    }

    private void a(String str, Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString("object_id", this.k);
        bundle2.putString("object_type", this.l.toString());
        bundle2.putString("current_action", str);
        i().b("fb_like_control_error", bundle2);
    }

    static /* synthetic */ void a(e eVar, int i, Intent intent) {
        final Bundle bundle = eVar.w;
        am.a(i, intent, new ah(eVar) {
            final /* synthetic */ e b;

            public final void a(a aVar, Bundle bundle) {
                if (bundle != null && bundle.containsKey("object_is_liked")) {
                    String string;
                    String str;
                    String string2;
                    String str2;
                    String string3;
                    boolean z = bundle.getBoolean("object_is_liked");
                    String b = this.b.n;
                    String c = this.b.o;
                    if (bundle.containsKey("like_count_string")) {
                        string = bundle.getString("like_count_string");
                        str = string;
                    } else {
                        string = b;
                        str = c;
                    }
                    b = this.b.p;
                    c = this.b.q;
                    if (bundle.containsKey("social_sentence")) {
                        string2 = bundle.getString("social_sentence");
                        str2 = string2;
                    } else {
                        string2 = b;
                        str2 = c;
                    }
                    if (bundle.containsKey("object_is_liked")) {
                        string3 = bundle.getString("unlike_token");
                    } else {
                        string3 = this.b.r;
                    }
                    String str3 = string3;
                    bundle = bundle == null ? new Bundle() : bundle;
                    bundle.putString("call_id", aVar.b().toString());
                    this.b.i().b("fb_like_control_dialog_did_succeed", bundle);
                    this.b.a(z, string, str, string2, str2, str3);
                }
            }

            public final void a(a aVar, n nVar) {
                ar.a(ai.REQUESTS, e.a, "Like Dialog failed with error : %s", nVar);
                Bundle bundle = bundle == null ? new Bundle() : bundle;
                bundle.putString("call_id", aVar.b().toString());
                this.b.a("present_dialog", bundle);
                e.c(this.b, "com.facebook.sdk.LikeActionController.DID_ERROR", av.a(nVar));
            }

            public final void a(a aVar) {
                a(aVar, new p());
            }
        });
        eVar.w = null;
        g = null;
        s.f().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putString("PENDING_CONTROLLER_KEY", g).apply();
    }

    static /* synthetic */ void a(e eVar) {
        if (AccessToken.b()) {
            eVar.a(new r(eVar) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public final void a() {
                    n kVar;
                    if (AnonymousClass4.a[this.a.l.ordinal()] != 1) {
                        kVar = new k(this.a, this.a.s, this.a.l);
                    } else {
                        kVar = new m(this.a, this.a.s);
                    }
                    final i iVar = new i(this.a, this.a.s, this.a.l);
                    ac acVar = new ac();
                    kVar.a(acVar);
                    iVar.a(acVar);
                    acVar.a(new ad(this) {
                        final /* synthetic */ AnonymousClass12 c;

                        public final void a() {
                            if (kVar.a() == null && iVar.a() == null) {
                                this.c.a.a(kVar.b(), iVar.e, iVar.f, iVar.g, iVar.h, kVar.c());
                                return;
                            }
                            ar.a(ai.REQUESTS, e.a, "Unable to refresh like state for id: '%s'", this.c.a.k);
                        }
                    });
                    GraphRequest.b(acVar);
                }
            });
            return;
        }
        z zVar = new z(s.f(), s.j(), eVar.k);
        if (zVar.a()) {
            zVar.a(new be(eVar) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public final void a(Bundle bundle) {
                    if (bundle != null && bundle.containsKey("com.facebook.platform.extra.OBJECT_IS_LIKED")) {
                        String string;
                        String string2;
                        boolean z = bundle.getBoolean("com.facebook.platform.extra.OBJECT_IS_LIKED");
                        if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE")) {
                            string = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE");
                        } else {
                            string = this.a.n;
                        }
                        String str = string;
                        if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE")) {
                            string = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE");
                        } else {
                            string = this.a.o;
                        }
                        String str2 = string;
                        if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE")) {
                            string = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE");
                        } else {
                            string = this.a.p;
                        }
                        String str3 = string;
                        if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE")) {
                            string = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE");
                        } else {
                            string = this.a.q;
                        }
                        String str4 = string;
                        if (bundle.containsKey("com.facebook.platform.extra.UNLIKE_TOKEN")) {
                            string2 = bundle.getString("com.facebook.platform.extra.UNLIKE_TOKEN");
                        } else {
                            string2 = this.a.r;
                        }
                        this.a.a(z, str, str2, str3, str4, string2);
                    }
                }
            });
        }
    }

    static /* synthetic */ void a(e eVar, Bundle bundle) {
        if (eVar.m != eVar.u && !eVar.a(eVar.m, bundle)) {
            eVar.a(eVar.m ^ 1);
        }
    }

    static /* synthetic */ void a(e eVar, String str, FacebookRequestError facebookRequestError) {
        Bundle bundle = new Bundle();
        if (facebookRequestError != null) {
            JSONObject f = facebookRequestError.f();
            if (f != null) {
                bundle.putString("error", f.toString());
            }
        }
        eVar.a(str, bundle);
    }

    static /* synthetic */ void a(String str, String str2) {
        Throwable e;
        Closeable closeable = null;
        try {
            Closeable b = b.b(str, null);
            try {
                b.write(str2.getBytes());
                bj.a(b);
            } catch (IOException e2) {
                e = e2;
                closeable = b;
                try {
                    Log.e(a, "Unable to serialize controller to disk", e);
                    if (closeable != null) {
                        bj.a(closeable);
                    }
                } catch (Throwable th) {
                    e = th;
                    if (closeable != null) {
                        bj.a(closeable);
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                closeable = b;
                if (closeable != null) {
                    bj.a(closeable);
                }
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
            Log.e(a, "Unable to serialize controller to disk", e);
            if (closeable != null) {
                bj.a(closeable);
            }
        }
    }

    static /* synthetic */ void b(String str, com.facebook.share.widget.e eVar, h hVar) {
        e b = b(str);
        if (b != null) {
            a(b, eVar, hVar);
            return;
        }
        b = c(str);
        if (b == null) {
            b = new e(str, eVar);
            m(b);
        }
        str = e(str);
        d.a(new o(str, true));
        c.put(str, b);
        f.post(new Runnable(b) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public final void run() {
                e.a(this.a);
            }
        });
        a(hVar, b, null);
    }
}
