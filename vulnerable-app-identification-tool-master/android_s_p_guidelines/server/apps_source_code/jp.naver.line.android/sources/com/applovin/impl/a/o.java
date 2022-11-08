package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.applovin.impl.sdk.aa;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class o {
    private List<r> a = Collections.EMPTY_LIST;
    private List<String> b = Collections.EMPTY_LIST;
    private int c;
    private Uri d;
    private final Set<l> e = new HashSet();
    private final Map<String, Set<l>> f = new HashMap();

    private o() {
    }

    private o(g gVar) {
        this.b = gVar.g();
    }

    private static int a(java.lang.String r8, com.applovin.sdk.AppLovinSdk r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.a.o.a(java.lang.String, com.applovin.sdk.AppLovinSdk):int. bs: []
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
        r0 = 0;
        r1 = ":";	 Catch:{ Throwable -> 0x0042 }
        r1 = com.applovin.impl.sdk.aa.a(r8, r1);	 Catch:{ Throwable -> 0x0042 }
        r2 = r1.size();	 Catch:{ Throwable -> 0x0042 }
        r3 = 3;	 Catch:{ Throwable -> 0x0042 }
        if (r2 != r3) goto L_0x005e;	 Catch:{ Throwable -> 0x0042 }
    L_0x000e:
        r2 = r1.get(r0);	 Catch:{ Throwable -> 0x0042 }
        r2 = (java.lang.String) r2;	 Catch:{ Throwable -> 0x0042 }
        r2 = com.applovin.impl.sdk.gd.e(r2);	 Catch:{ Throwable -> 0x0042 }
        r3 = 1;	 Catch:{ Throwable -> 0x0042 }
        r3 = r1.get(r3);	 Catch:{ Throwable -> 0x0042 }
        r3 = (java.lang.String) r3;	 Catch:{ Throwable -> 0x0042 }
        r3 = com.applovin.impl.sdk.gd.e(r3);	 Catch:{ Throwable -> 0x0042 }
        r4 = 2;	 Catch:{ Throwable -> 0x0042 }
        r1 = r1.get(r4);	 Catch:{ Throwable -> 0x0042 }
        r1 = (java.lang.String) r1;	 Catch:{ Throwable -> 0x0042 }
        r1 = com.applovin.impl.sdk.gd.e(r1);	 Catch:{ Throwable -> 0x0042 }
        r4 = java.util.concurrent.TimeUnit.HOURS;	 Catch:{ Throwable -> 0x0042 }
        r5 = (long) r2;	 Catch:{ Throwable -> 0x0042 }
        r4 = r4.toSeconds(r5);	 Catch:{ Throwable -> 0x0042 }
        r2 = java.util.concurrent.TimeUnit.MINUTES;	 Catch:{ Throwable -> 0x0042 }
        r6 = (long) r3;	 Catch:{ Throwable -> 0x0042 }
        r2 = r2.toSeconds(r6);	 Catch:{ Throwable -> 0x0042 }
        r8 = 0;
        r4 = r4 + r2;
        r8 = (long) r1;
        r4 = r4 + r8;
        r8 = (int) r4;
        return r8;
    L_0x0042:
        r9 = r9.getLogger();
        r1 = "VastVideoCreative";
        r2 = new java.lang.StringBuilder;
        r3 = "Unable to parse duration from \"";
        r2.<init>(r3);
        r2.append(r8);
        r8 = "\"";
        r2.append(r8);
        r8 = r2.toString();
        r9.e(r1, r8);
    L_0x005e:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.a.o.a(java.lang.String, com.applovin.sdk.AppLovinSdk):int");
    }

    public static o a(gf gfVar, o oVar, g gVar, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (gVar == null) {
            throw new IllegalArgumentException("No context specified.");
        } else if (appLovinSdk != null) {
            gf b;
            if (oVar == null) {
                try {
                    oVar = new o(gVar);
                } catch (Throwable th) {
                    appLovinSdk.getLogger().e("VastVideoCreative", "Error occurred while initializing", th);
                    return null;
                }
            }
            if (oVar.c == 0) {
                b = gfVar.b("Duration");
                if (b != null) {
                    int a = a(b.c(), appLovinSdk);
                    if (a > 0) {
                        oVar.c = a;
                    }
                }
            }
            b = gfVar.b("MediaFiles");
            if (b != null) {
                List a2 = a(b, appLovinSdk);
                if (a2 != null && a2.size() > 0) {
                    if (oVar.a != null) {
                        a2.addAll(oVar.a);
                    }
                    oVar.a = a2;
                }
            }
            b = gfVar.b("VideoClicks");
            if (b != null) {
                if (oVar.d == null) {
                    gf b2 = b.b("ClickThrough");
                    if (b2 != null) {
                        String c = b2.c();
                        if (AppLovinSdkUtils.isValidString(c)) {
                            oVar.d = Uri.parse(c);
                        }
                    }
                }
                n.a(b.a("ClickTracking"), oVar.e, gVar, appLovinSdk);
            }
            n.a(gfVar, oVar.f, gVar, appLovinSdk);
            return oVar;
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static List<r> a(gf gfVar, AppLovinSdk appLovinSdk) {
        List<gf> a = gfVar.a("MediaFile");
        List<r> arrayList = new ArrayList(a.size());
        ee eeVar = new ee(appLovinSdk);
        List a2 = aa.a(eeVar.aa());
        List a3 = aa.a(eeVar.ab());
        for (gf a4 : a) {
            r a5 = r.a(a4, appLovinSdk);
            if (a5 != null) {
                try {
                    String d = a5.d();
                    if (!AppLovinSdkUtils.isValidString(d) || a2.contains(d)) {
                        if (eeVar.ac()) {
                            d = MimeTypeMap.getFileExtensionFromUrl(a5.b().toString());
                            if (AppLovinSdkUtils.isValidString(d)) {
                            }
                        }
                        appLovinSdk.getLogger().w("VastVideoCreative", "Video file not supported: ".concat(String.valueOf(a5)));
                    }
                    arrayList.add(a5);
                } catch (Throwable th) {
                    appLovinSdk.getLogger().e("VastVideoCreative", "Failed to validate vidoe file: ".concat(String.valueOf(a5)), th);
                }
            }
        }
        return arrayList;
    }

    public r a(q qVar) {
        if (this.a == null || this.a.size() == 0) {
            return null;
        }
        List arrayList = new ArrayList(3);
        for (String str : this.b) {
            for (r rVar : this.a) {
                String d = rVar.d();
                if (AppLovinSdkUtils.isValidString(d) && str.equalsIgnoreCase(d)) {
                    arrayList.add(rVar);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = this.a;
        }
        Collections.sort(arrayList, new p(this));
        int size = qVar == q.LOW ? 0 : qVar == q.MEDIUM ? arrayList.size() / 2 : arrayList.size() - 1;
        return (r) arrayList.get(size);
    }

    public List<r> a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public Uri c() {
        return this.d;
    }

    public Set<l> d() {
        return this.e;
    }

    public Map<String, Set<l>> e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return this.c != oVar.c ? false : (this.a == null ? oVar.a == null : this.a.equals(oVar.a)) ? (this.d == null ? oVar.d == null : this.d.equals(oVar.d)) ? (this.e == null ? oVar.e == null : this.e.equals(oVar.e)) ? this.f != null ? this.f.equals(oVar.f) : oVar.f == null : false : false : false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((this.a != null ? this.a.hashCode() : 0) * 31) + this.c) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("VastVideoCreative{videoFiles=");
        stringBuilder.append(this.a);
        stringBuilder.append(", durationSeconds=");
        stringBuilder.append(this.c);
        stringBuilder.append(", destinationUri=");
        stringBuilder.append(this.d);
        stringBuilder.append(", clickTrackers=");
        stringBuilder.append(this.e);
        stringBuilder.append(", eventTrackers=");
        stringBuilder.append(this.f);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
