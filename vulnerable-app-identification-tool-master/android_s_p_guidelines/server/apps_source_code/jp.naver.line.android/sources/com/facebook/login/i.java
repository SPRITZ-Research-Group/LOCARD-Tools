package com.facebook.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import androidx.fragment.app.Fragment;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.internal.ad;
import com.facebook.internal.bn;
import com.facebook.internal.g;
import com.facebook.internal.h;
import com.facebook.k;
import com.facebook.l;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import com.facebook.n;
import com.facebook.s;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class i {
    private static final Set<String> a = Collections.unmodifiableSet(new HashSet<String>() {
        {
            add("ads_management");
            add("create_event");
            add("rsvp_event");
        }
    });
    private static volatile i b;
    private d c = d.NATIVE_WITH_FALLBACK;
    private a d = a.FRIENDS;
    private final SharedPreferences e;
    private String f = "rerequest";

    i() {
        bn.a();
        this.e = s.f().getSharedPreferences("com.facebook.loginManager", 0);
    }

    public static i b() {
        if (b == null) {
            synchronized (i.class) {
                if (b == null) {
                    b = new i();
                }
            }
        }
        return b;
    }

    public final void a(com.facebook.i iVar, final l<m> lVar) {
        if (iVar instanceof g) {
            ((g) iVar).b(com.facebook.internal.i.Login.a(), new h(this) {
                final /* synthetic */ i b;

                public final boolean a(int i, Intent intent) {
                    return this.b.a(i, intent, lVar);
                }
            });
            return;
        }
        throw new n("Unexpected CallbackManager, please use the provided Factory.");
    }

    final boolean a(int i, Intent intent) {
        return a(i, intent, null);
    }

    final boolean a(int i, Intent intent, l<m> lVar) {
        Map map;
        g gVar;
        boolean z;
        AccessToken accessToken;
        Request request;
        int i2 = i;
        Intent intent2 = intent;
        g gVar2 = g.ERROR;
        Exception exception = null;
        boolean z2 = false;
        if (intent2 != null) {
            AccessToken accessToken2;
            Map map2;
            Request request2;
            Result result = (Result) intent2.getParcelableExtra("com.facebook.LoginFragment:Result");
            if (result != null) {
                Request request3 = result.e;
                g gVar3 = result.a;
                if (i2 == -1) {
                    if (result.a == g.SUCCESS) {
                        accessToken2 = result.b;
                    } else {
                        exception = new k(result.c);
                        accessToken2 = null;
                    }
                } else if (i2 == 0) {
                    accessToken2 = null;
                    z2 = true;
                } else {
                    accessToken2 = null;
                }
                map2 = result.f;
                g gVar4 = gVar3;
                request2 = request3;
                gVar2 = gVar4;
            } else {
                accessToken2 = null;
                map2 = accessToken2;
                request2 = map2;
            }
            map = map2;
            gVar = gVar2;
            z = z2;
            Request request4 = request2;
            accessToken = accessToken2;
            request = request4;
        } else if (i2 == 0) {
            gVar = g.CANCEL;
            request = null;
            accessToken = request;
            map = accessToken;
            z = true;
        } else {
            gVar = gVar2;
            request = null;
            accessToken = request;
            map = accessToken;
            z = false;
        }
        if (exception == null && accessToken == null && !z) {
            exception = new n("Unexpected call to LoginManager.onActivityResult");
        }
        a(null, gVar, map, exception, true, request);
        a(accessToken, request, exception, z, lVar);
        return true;
    }

    public final i a(d dVar) {
        this.c = dVar;
        return this;
    }

    public final i a(a aVar) {
        this.d = aVar;
        return this;
    }

    public final i a(String str) {
        this.f = str;
        return this;
    }

    public final void c() {
        AccessToken.a(null);
        Profile.a(null);
        a(false);
    }

    public final void a(Fragment fragment, Collection<String> collection) {
        a(new ad(fragment), (Collection) collection);
    }

    public final void a(android.app.Fragment fragment, Collection<String> collection) {
        a(new ad(fragment), (Collection) collection);
    }

    private void a(ad adVar, Collection<String> collection) {
        b((Collection) collection);
        a(new k(adVar), a((Collection) collection));
    }

    public final void a(Activity activity, Collection<String> collection) {
        b((Collection) collection);
        a(new j(activity), a((Collection) collection));
    }

    public final void b(Fragment fragment, Collection<String> collection) {
        b(new ad(fragment), (Collection) collection);
    }

    public final void b(android.app.Fragment fragment, Collection<String> collection) {
        b(new ad(fragment), (Collection) collection);
    }

    private void b(ad adVar, Collection<String> collection) {
        c(collection);
        a(new k(adVar), a((Collection) collection));
    }

    public final void b(Activity activity, Collection<String> collection) {
        c(collection);
        a(new j(activity), a((Collection) collection));
    }

    private static void b(Collection<String> collection) {
        if (collection != null) {
            for (String b : collection) {
                if (b(b)) {
                    throw new n(String.format("Cannot pass a publish or manage permission (%s) to a request for read authorization", new Object[]{(String) r3.next()}));
                }
            }
        }
    }

    private static void c(Collection<String> collection) {
        if (collection != null) {
            for (String b : collection) {
                if (!b(b)) {
                    throw new n(String.format("Cannot pass a read permission (%s) to a request for publish authorization", new Object[]{(String) r3.next()}));
                }
            }
        }
    }

    static boolean b(String str) {
        return str != null && (str.startsWith("publish") || str.startsWith("manage") || a.contains(str));
    }

    protected Request a(Collection<String> collection) {
        Request request = new Request(this.c, Collections.unmodifiableSet(collection != null ? new HashSet(collection) : new HashSet()), this.d, this.f, s.j(), UUID.randomUUID().toString());
        request.a(AccessToken.b());
        return request;
    }

    private void a(v vVar, Request request) throws n {
        a(vVar.a(), request);
        g.a(com.facebook.internal.i.Login.a(), new h(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public final boolean a(int i, Intent intent) {
                return this.a.a(i, intent);
            }
        });
        if (!b(vVar, request)) {
            Exception nVar = new n("Log in attempt failed: FacebookActivity could not be started. Please make sure you added FacebookActivity to the AndroidManifest.");
            a(vVar.a(), g.ERROR, null, nVar, false, request);
            throw nVar;
        }
    }

    private static void a(Context context, Request request) {
        h a = l.b(context);
        if (a != null && request != null) {
            a.a(request);
        }
    }

    private static void a(Context context, g gVar, Map<String, String> map, Exception exception, boolean z, Request request) {
        h a = l.b(context);
        if (a != null) {
            if (request == null) {
                a.c("fb_mobile_login_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.");
                return;
            }
            Map hashMap = new HashMap();
            hashMap.put("try_login_activity", z ? com.linecorp.yuki.effect.android.g.a : "0");
            a.a(request.e(), hashMap, gVar, map, exception);
        }
    }

    private static m a(Request request, AccessToken accessToken) {
        Collection a = request.a();
        Object hashSet = new HashSet(accessToken.f());
        if (request.f()) {
            hashSet.retainAll(a);
        }
        Set hashSet2 = new HashSet(a);
        hashSet2.removeAll(hashSet);
        return new m(accessToken, hashSet, hashSet2);
    }

    private void a(AccessToken accessToken, Request request, n nVar, boolean z, l<m> lVar) {
        if (accessToken != null) {
            AccessToken.a(accessToken);
            Profile.b();
        }
        if (lVar != null) {
            Object a = accessToken != null ? a(request, accessToken) : null;
            if (z || (a != null && a.b().size() == 0)) {
                lVar.a();
            } else if (nVar != null) {
                lVar.a(nVar);
            } else if (accessToken != null) {
                a(true);
                lVar.a(a);
            }
        }
    }

    private void a(boolean z) {
        Editor edit = this.e.edit();
        edit.putBoolean("express_login_allowed", z);
        edit.apply();
    }

    private static boolean b(com.facebook.login.v r3, com.facebook.login.LoginClient.Request r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.login.i.b(com.facebook.login.v, com.facebook.login.LoginClient$Request):boolean. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = new android.content.Intent;
        r0.<init>();
        r1 = com.facebook.s.f();
        r2 = com.facebook.FacebookActivity.class;
        r0.setClass(r1, r2);
        r1 = r4.b();
        r1 = r1.toString();
        r0.setAction(r1);
        r1 = new android.os.Bundle;
        r1.<init>();
        r2 = "request";
        r1.putParcelable(r2, r4);
        r4 = "com.facebook.LoginFragment:Request";
        r0.putExtra(r4, r1);
        r4 = com.facebook.s.f();
        r4 = r4.getPackageManager();
        r1 = 0;
        r4 = r4.resolveActivity(r0, r1);
        r2 = 1;
        if (r4 == 0) goto L_0x003a;
    L_0x0038:
        r4 = 1;
        goto L_0x003b;
    L_0x003a:
        r4 = 0;
    L_0x003b:
        if (r4 != 0) goto L_0x003e;
    L_0x003d:
        return r1;
    L_0x003e:
        r4 = com.facebook.login.LoginClient.a();	 Catch:{ ActivityNotFoundException -> 0x0046 }
        r3.a(r0, r4);	 Catch:{ ActivityNotFoundException -> 0x0046 }
        return r2;
    L_0x0046:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.i.b(com.facebook.login.v, com.facebook.login.LoginClient$Request):boolean");
    }
}
