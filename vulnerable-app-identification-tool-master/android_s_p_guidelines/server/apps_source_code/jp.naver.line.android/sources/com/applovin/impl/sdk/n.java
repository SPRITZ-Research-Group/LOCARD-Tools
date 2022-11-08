package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public final class n {
    private static final Map<String, n> a = new HashMap();
    private static final Object b = new Object();
    private AppLovinSdkImpl c;
    private AppLovinLogger d;
    private JSONObject e;
    private final String f;
    private String g;
    private AppLovinAdSize h;
    private AppLovinAdType i;
    private o j;

    private n(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, o oVar, String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (TextUtils.isEmpty(str) && (appLovinAdType == null || appLovinAdSize == null || oVar == o.NONE)) {
            throw new IllegalArgumentException("No zone identifier or type/size/mediation type specified");
        }
        this.c = appLovinSdkImpl;
        this.d = appLovinSdkImpl != null ? appLovinSdkImpl.getLogger() : null;
        this.h = appLovinAdSize;
        this.i = appLovinAdType;
        this.j = oVar;
        if (TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(appLovinAdSize.getLabel());
            stringBuilder.append("_");
            stringBuilder.append(appLovinAdType.getLabel());
            stringBuilder.append("_");
            stringBuilder.append(oVar.toString());
            this.f = stringBuilder.toString().toLowerCase(Locale.ENGLISH);
            return;
        }
        this.f = str.toLowerCase(Locale.ENGLISH);
        this.g = str.toLowerCase(Locale.ENGLISH);
    }

    private ec a(String str, ec ecVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(this.f);
        return this.c.retrieveSetting(stringBuilder.toString(), ecVar);
    }

    public static n a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, o oVar, AppLovinSdkImpl appLovinSdkImpl) {
        return a(appLovinAdSize, appLovinAdType, oVar, null, appLovinSdkImpl);
    }

    public static com.applovin.impl.sdk.n a(com.applovin.sdk.AppLovinAdSize r7, com.applovin.sdk.AppLovinAdType r8, com.applovin.impl.sdk.o r9, java.lang.String r10, com.applovin.impl.sdk.AppLovinSdkImpl r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r6_0 com.applovin.impl.sdk.n) in PHI: PHI: (r6_3 com.applovin.impl.sdk.n) = (r6_2 com.applovin.impl.sdk.n), (r6_0 com.applovin.impl.sdk.n) binds: {(r6_2 com.applovin.impl.sdk.n)=B:5:0x0018, (r6_0 com.applovin.impl.sdk.n)=B:6:0x0022}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
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
        r6 = new com.applovin.impl.sdk.n;
        r0 = r6;
        r1 = r7;
        r2 = r8;
        r3 = r9;
        r4 = r10;
        r5 = r11;
        r0.<init>(r1, r2, r3, r4, r5);
        r7 = b;
        monitor-enter(r7);
        r8 = r6.f;	 Catch:{ all -> 0x0029 }
        r9 = a;	 Catch:{ all -> 0x0029 }
        r9 = r9.containsKey(r8);	 Catch:{ all -> 0x0029 }
        if (r9 == 0) goto L_0x0022;	 Catch:{ all -> 0x0029 }
    L_0x0018:
        r9 = a;	 Catch:{ all -> 0x0029 }
        r8 = r9.get(r8);	 Catch:{ all -> 0x0029 }
        r6 = r8;	 Catch:{ all -> 0x0029 }
        r6 = (com.applovin.impl.sdk.n) r6;	 Catch:{ all -> 0x0029 }
        goto L_0x0027;	 Catch:{ all -> 0x0029 }
    L_0x0022:
        r9 = a;	 Catch:{ all -> 0x0029 }
        r9.put(r8, r6);	 Catch:{ all -> 0x0029 }
    L_0x0027:
        monitor-exit(r7);	 Catch:{ all -> 0x0029 }
        return r6;	 Catch:{ all -> 0x0029 }
    L_0x0029:
        r8 = move-exception;	 Catch:{ all -> 0x0029 }
        monitor-exit(r7);	 Catch:{ all -> 0x0029 }
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.n.a(com.applovin.sdk.AppLovinAdSize, com.applovin.sdk.AppLovinAdType, com.applovin.impl.sdk.o, java.lang.String, com.applovin.impl.sdk.AppLovinSdkImpl):com.applovin.impl.sdk.n");
    }

    public static n a(String str, AppLovinSdkImpl appLovinSdkImpl) {
        return a(null, null, o.NONE, str, appLovinSdkImpl);
    }

    public static n a(String str, JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl) {
        n a = a(str, appLovinSdkImpl);
        a.e = jSONObject;
        return a;
    }

    private boolean a(ec<String> ecVar, AppLovinAdSize appLovinAdSize) {
        return ((String) this.c.get((ec) ecVar)).toUpperCase(Locale.ENGLISH).contains(appLovinAdSize.getLabel());
    }

    public static n b(String str, AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, o.DIRECT, str, appLovinSdkImpl);
    }

    public static Collection<n> b(AppLovinSdkImpl appLovinSdkImpl) {
        Object linkedHashSet = new LinkedHashSet(8);
        Collections.addAll(linkedHashSet, new n[]{c(appLovinSdkImpl), d(appLovinSdkImpl), e(appLovinSdkImpl), f(appLovinSdkImpl), g(appLovinSdkImpl), h(appLovinSdkImpl), i(appLovinSdkImpl), j(appLovinSdkImpl)});
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public static n c(AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.BANNER, AppLovinAdType.REGULAR, o.DIRECT, appLovinSdkImpl);
    }

    public static n d(AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.MREC, AppLovinAdType.REGULAR, o.DIRECT, appLovinSdkImpl);
    }

    public static n e(AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.LEADER, AppLovinAdType.REGULAR, o.DIRECT, appLovinSdkImpl);
    }

    public static n f(AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, o.DIRECT, appLovinSdkImpl);
    }

    public static n g(AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, o.INDIRECT, appLovinSdkImpl);
    }

    public static n h(AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, o.DIRECT, appLovinSdkImpl);
    }

    public static n i(AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, o.INDIRECT, appLovinSdkImpl);
    }

    public static n j(AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, o.DIRECT, appLovinSdkImpl);
    }

    private boolean n() {
        try {
            return !TextUtils.isEmpty(this.g) ? true : d() == o.DIRECT ? AppLovinAdType.INCENTIVIZED.equals(c()) ? ((Boolean) this.c.get(ea.P)).booleanValue() : a(ea.N, b()) : d() == o.INDIRECT ? AppLovinAdType.INCENTIVIZED.equals(c()) ? ((Boolean) this.c.get(ea.Q)).booleanValue() : a(ea.O, b()) : false;
        } catch (Throwable th) {
            this.d.e("AdZone", "Unable to safely test preload merge capability", th);
            return false;
        }
    }

    final String a() {
        return this.f;
    }

    final void a(AppLovinSdkImpl appLovinSdkImpl) {
        this.c = appLovinSdkImpl;
        this.d = appLovinSdkImpl.getLogger();
    }

    final AppLovinAdSize b() {
        if (this.h == null && bu.a(this.e, "ad_size")) {
            this.h = new AppLovinAdSize(bu.a(this.e, "ad_size", null, this.c));
        }
        return this.h;
    }

    final AppLovinAdType c() {
        if (this.i == null && bu.a(this.e, "ad_type")) {
            this.i = new AppLovinAdType(bu.a(this.e, "ad_type", null, this.c));
        }
        return this.i;
    }

    final o d() {
        if (this.j == o.NONE && bu.a(this.e, "type")) {
            this.j = o.a(bu.a(this.e, "type", null, this.c));
        }
        return this.j;
    }

    public final boolean e() {
        return AppLovinAdSize.NATIVE.equals(b()) && AppLovinAdType.NATIVE.equals(c());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f.equalsIgnoreCase(((n) obj).f);
    }

    public final int f() {
        if (bu.a(this.e, "capacity")) {
            return bu.a(this.e, "capacity", 0, this.c);
        }
        if (!TextUtils.isEmpty(this.g)) {
            return e() ? ((Integer) this.c.get(ea.aM)).intValue() : ((Integer) this.c.get(ea.aL)).intValue();
        } else {
            return ((Integer) this.c.get(a("preload_capacity_", ea.ar))).intValue();
        }
    }

    public final int g() {
        if (bu.a(this.e, "extended_capacity")) {
            return bu.a(this.e, "extended_capacity", 0, this.c);
        }
        if (!TextUtils.isEmpty(this.g)) {
            return e() ? 0 : ((Integer) this.c.get(ea.aN)).intValue();
        } else {
            return ((Integer) this.c.get(a("extended_preload_capacity_", ea.aB))).intValue();
        }
    }

    public final int h() {
        return bu.a(this.e, "preload_count", 0, this.c);
    }

    public final int hashCode() {
        return this.f.hashCode();
    }

    public final boolean i() {
        Boolean a;
        if (bu.a(this.e, "refresh_enabled")) {
            a = bu.a(this.e, "refresh_enabled", Boolean.FALSE, this.c);
        } else {
            AppLovinSdkImpl appLovinSdkImpl;
            ec ecVar;
            if (AppLovinAdSize.BANNER.equals(b())) {
                appLovinSdkImpl = this.c;
                ecVar = ea.C;
            } else if (AppLovinAdSize.MREC.equals(b())) {
                appLovinSdkImpl = this.c;
                ecVar = ea.E;
            } else if (!AppLovinAdSize.LEADER.equals(b())) {
                return false;
            } else {
                appLovinSdkImpl = this.c;
                ecVar = ea.G;
            }
            a = (Boolean) appLovinSdkImpl.get(ecVar);
        }
        return a.booleanValue();
    }

    public final long j() {
        return bu.a(this.e, "refresh_seconds") ? (long) bu.a(this.e, "refresh_seconds", 0, this.c) : AppLovinAdSize.BANNER.equals(b()) ? ((Long) this.c.get(ea.D)).longValue() : AppLovinAdSize.MREC.equals(b()) ? ((Long) this.c.get(ea.F)).longValue() : AppLovinAdSize.LEADER.equals(b()) ? ((Long) this.c.get(ea.H)).longValue() : -1;
    }

    public final boolean k() {
        if (!((Boolean) this.c.get(ea.J)).booleanValue() || !n()) {
            return false;
        }
        if (TextUtils.isEmpty(this.g)) {
            ec a = a("preload_merge_init_tasks_", null);
            return a != null && ((Boolean) this.c.get(a)).booleanValue() && f() > 0;
        } else if (this.e != null && h() == 0) {
            return false;
        } else {
            String toUpperCase = ((String) this.c.get(ea.N)).toUpperCase(Locale.ENGLISH);
            return (toUpperCase.contains(AppLovinAdSize.INTERSTITIAL.getLabel()) || toUpperCase.contains(AppLovinAdSize.BANNER.getLabel()) || toUpperCase.contains(AppLovinAdSize.MREC.getLabel()) || toUpperCase.contains(AppLovinAdSize.LEADER.getLabel())) ? ((Boolean) this.c.get(ea.bl)).booleanValue() : this.c.getZoneManager().a(this) && h() > 0 && ((Boolean) this.c.get(ea.cX)).booleanValue();
        }
    }

    public final boolean l() {
        return bu.a(this.e, "wrapped_ads_enabled") ? bu.a(this.e, "wrapped_ads_enabled", Boolean.FALSE, this.c).booleanValue() : b() != null ? this.c.getAsList(ea.cU).contains(b().getLabel()) : ((Boolean) this.c.get(ea.cT)).booleanValue();
    }

    public final boolean m() {
        return b(this.c).contains(this);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("AdZone{identifier=");
        stringBuilder.append(this.f);
        stringBuilder.append(", zoneObject=");
        stringBuilder.append(this.e);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
