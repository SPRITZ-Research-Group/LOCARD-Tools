package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class aw {
    private final AppLovinSdkImpl a;
    private final Map<String, Long> b = new HashMap();

    aw(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl != null) {
            this.a = appLovinSdkImpl;
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private void d() {
        try {
            this.a.put(ef.i, b().toString());
        } catch (Throwable th) {
            this.a.getLogger().e("GlobalStatsManager", "Unable to save stats", th);
        }
    }

    long a(String str) {
        return a(str, 1);
    }

    long a(String str, long j) {
        long longValue;
        synchronized (this.b) {
            Long l = (Long) this.b.get(str);
            if (l == null) {
                l = Long.valueOf(0);
            }
            longValue = l.longValue() + j;
            this.b.put(str, Long.valueOf(longValue));
        }
        d();
        return longValue;
    }

    void a() {
        synchronized (this.b) {
            this.b.clear();
        }
        d();
    }

    long b(String str) {
        long longValue;
        synchronized (this.b) {
            Long l = (Long) this.b.get(str);
            if (l == null) {
                l = Long.valueOf(0);
            }
            longValue = l.longValue();
        }
        return longValue;
    }

    JSONObject b() throws JSONException {
        JSONObject jSONObject;
        synchronized (this.b) {
            jSONObject = new JSONObject();
            for (Entry entry : this.b.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    void b(String str, long j) {
        synchronized (this.b) {
            this.b.put(str, Long.valueOf(j));
        }
        d();
    }

    void c() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.aw.c():void. bs: []
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
        r7 = this;
        r0 = r7.a;
        r1 = com.applovin.impl.sdk.ef.i;
        r2 = "{}";
        r0 = r0.get(r1, r2);
        r0 = (java.lang.String) r0;
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0037 }
        r1.<init>(r0);	 Catch:{ Throwable -> 0x0037 }
        r0 = r7.b;	 Catch:{ Throwable -> 0x0037 }
        monitor-enter(r0);	 Catch:{ Throwable -> 0x0037 }
        r2 = r1.keys();	 Catch:{ all -> 0x0034 }
    L_0x0018:
        r3 = r2.hasNext();	 Catch:{ all -> 0x0034 }
        if (r3 == 0) goto L_0x0032;
    L_0x001e:
        r3 = r2.next();	 Catch:{ JSONException -> 0x0018 }
        r3 = (java.lang.String) r3;	 Catch:{ JSONException -> 0x0018 }
        r4 = r1.getLong(r3);	 Catch:{ JSONException -> 0x0018 }
        r6 = r7.b;	 Catch:{ JSONException -> 0x0018 }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ JSONException -> 0x0018 }
        r6.put(r3, r4);	 Catch:{ JSONException -> 0x0018 }
        goto L_0x0018;
    L_0x0032:
        monitor-exit(r0);	 Catch:{ all -> 0x0034 }
        return;	 Catch:{ all -> 0x0034 }
    L_0x0034:
        r1 = move-exception;	 Catch:{ all -> 0x0034 }
        monitor-exit(r0);	 Catch:{ all -> 0x0034 }
        throw r1;	 Catch:{ Throwable -> 0x0037 }
    L_0x0037:
        r0 = move-exception;
        r1 = r7.a;
        r1 = r1.getLogger();
        r2 = "GlobalStatsManager";
        r3 = "Unable to load stats";
        r1.e(r2, r3, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.aw.c():void");
    }

    void c(String str) {
        synchronized (this.b) {
            this.b.remove(str);
        }
        d();
    }
}
