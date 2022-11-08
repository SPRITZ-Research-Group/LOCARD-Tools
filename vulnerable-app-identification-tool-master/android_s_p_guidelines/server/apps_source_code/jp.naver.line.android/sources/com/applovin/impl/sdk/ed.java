package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.exoplayer.hls.HlsMediaPlaylist;
import com.google.obf.ly;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class ed {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;
    private final SharedPreferences d;
    private final Object[] e = new Object[ea.b()];

    ed(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
        this.c = appLovinSdkImpl.getApplicationContext();
        this.d = this.c.getSharedPreferences("com.applovin.sdk.1", 0);
    }

    private static Object a(String str, JSONObject jSONObject, Object obj) throws JSONException {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf((float) jSONObject.getDouble(str));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (obj instanceof String) {
            return jSONObject.getString(str);
        }
        StringBuilder stringBuilder = new StringBuilder("SDK Error: unknown value type: ");
        stringBuilder.append(obj.getClass());
        throw new RuntimeException(stringBuilder.toString());
    }

    private String d() {
        StringBuilder stringBuilder = new StringBuilder("com.applovin.sdk.");
        stringBuilder.append(gd.a(this.a.getSdkKey()));
        stringBuilder.append(ly.a);
        return stringBuilder.toString();
    }

    public ec<?> a(String str, ec<?> ecVar) {
        for (ec<?> ecVar2 : ea.a()) {
            if (ecVar2.b().equals(str)) {
                return ecVar2;
            }
        }
        return ecVar;
    }

    public <T> T a(com.applovin.impl.sdk.ec<T> r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ed.a(com.applovin.impl.sdk.ec):T. bs: []
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
        r5 = this;
        if (r6 == 0) goto L_0x0047;
    L_0x0002:
        r0 = r5.e;
        monitor-enter(r0);
        r1 = r5.e;	 Catch:{ Throwable -> 0x001d }
        r2 = r6.a();	 Catch:{ Throwable -> 0x001d }
        r1 = r1[r2];	 Catch:{ Throwable -> 0x001d }
        if (r1 == 0) goto L_0x0015;	 Catch:{ Throwable -> 0x001d }
    L_0x000f:
        r1 = r6.a(r1);	 Catch:{ Throwable -> 0x001d }
        monitor-exit(r0);	 Catch:{ all -> 0x001b }
        return r1;
    L_0x0015:
        r1 = r6.c();	 Catch:{ Throwable -> 0x001d }
        monitor-exit(r0);	 Catch:{ all -> 0x001b }
        return r1;	 Catch:{ all -> 0x001b }
    L_0x001b:
        r6 = move-exception;	 Catch:{ all -> 0x001b }
        goto L_0x0045;	 Catch:{ all -> 0x001b }
    L_0x001d:
        r1 = r5.a;	 Catch:{ all -> 0x001b }
        r1 = r1.getLogger();	 Catch:{ all -> 0x001b }
        r2 = "SettingsManager";	 Catch:{ all -> 0x001b }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x001b }
        r4 = "Unable to retrieve value for setting ";	 Catch:{ all -> 0x001b }
        r3.<init>(r4);	 Catch:{ all -> 0x001b }
        r4 = r6.b();	 Catch:{ all -> 0x001b }
        r3.append(r4);	 Catch:{ all -> 0x001b }
        r4 = "; using default...";	 Catch:{ all -> 0x001b }
        r3.append(r4);	 Catch:{ all -> 0x001b }
        r3 = r3.toString();	 Catch:{ all -> 0x001b }
        r1.e(r2, r3);	 Catch:{ all -> 0x001b }
        r6 = r6.c();	 Catch:{ all -> 0x001b }
        monitor-exit(r0);	 Catch:{ all -> 0x001b }
        return r6;	 Catch:{ all -> 0x001b }
    L_0x0045:
        monitor-exit(r0);	 Catch:{ all -> 0x001b }
        throw r6;
    L_0x0047:
        r6 = new java.lang.IllegalArgumentException;
        r0 = "No setting type specified";
        r6.<init>(r0);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ed.a(com.applovin.impl.sdk.ec):T");
    }

    void a() {
        if (this.c != null) {
            this.b.i("SettingsManager", "Saving settings with the application...");
            String d = d();
            synchronized (this.e) {
                for (ec ecVar : ea.a()) {
                    Object obj = this.e[ecVar.a()];
                    if (obj != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(d);
                        stringBuilder.append(ecVar.b());
                        this.a.put(stringBuilder.toString(), obj, this.d);
                    }
                }
            }
            this.b.d("SettingsManager", "Settings saved with the application.");
            return;
        }
        throw new IllegalArgumentException("No context specified");
    }

    public <T> void a(ec<?> ecVar, Object obj) {
        if (ecVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        } else if (obj != null) {
            synchronized (this.e) {
                this.e[ecVar.a()] = obj;
            }
            StringBuilder stringBuilder = new StringBuilder("Setting update: ");
            stringBuilder.append(ecVar.b());
            stringBuilder.append(" set to \"");
            stringBuilder.append(obj);
            stringBuilder.append("\"");
            this.b.d("SettingsManager", stringBuilder.toString());
        } else {
            throw new IllegalArgumentException("No new value specified");
        }
    }

    void a(AppLovinSdkSettings appLovinSdkSettings) {
        this.b.i("SettingsManager", "Loading user-defined settings...");
        if (appLovinSdkSettings != null) {
            synchronized (this.e) {
                String autoPreloadSizes;
                if (((Boolean) this.a.get(ea.l)).booleanValue()) {
                    this.e[ea.l.a()] = Boolean.valueOf(appLovinSdkSettings.isVerboseLoggingEnabled());
                }
                long bannerAdRefreshSeconds = appLovinSdkSettings.getBannerAdRefreshSeconds();
                long j = 0;
                if (bannerAdRefreshSeconds >= 0) {
                    if (bannerAdRefreshSeconds > 0) {
                        j = Math.max(30, bannerAdRefreshSeconds);
                    }
                    this.e[ea.D.a()] = Long.valueOf(j);
                    this.e[ea.C.a()] = Boolean.TRUE;
                } else if (bannerAdRefreshSeconds == -1) {
                    this.e[ea.C.a()] = Boolean.FALSE;
                }
                if (((Boolean) this.a.get(ea.d)).booleanValue()) {
                    autoPreloadSizes = appLovinSdkSettings.getAutoPreloadSizes();
                    if (!AppLovinSdkUtils.isValidString(autoPreloadSizes)) {
                        autoPreloadSizes = HlsMediaPlaylist.ENCRYPTION_METHOD_NONE;
                    }
                    if (autoPreloadSizes.equals(HlsMediaPlaylist.ENCRYPTION_METHOD_NONE)) {
                        this.e[ea.N.a()] = "";
                        this.e[ea.O.a()] = "";
                    } else {
                        this.e[ea.N.a()] = autoPreloadSizes;
                        this.e[ea.O.a()] = autoPreloadSizes;
                    }
                }
                if (((Boolean) this.a.get(ea.e)).booleanValue()) {
                    boolean z;
                    boolean z2;
                    autoPreloadSizes = appLovinSdkSettings.getAutoPreloadTypes();
                    if (!AppLovinSdkUtils.isValidString(autoPreloadSizes)) {
                        autoPreloadSizes = HlsMediaPlaylist.ENCRYPTION_METHOD_NONE;
                    }
                    Object obj = null;
                    if (HlsMediaPlaylist.ENCRYPTION_METHOD_NONE.equals(autoPreloadSizes)) {
                        z = false;
                        z2 = false;
                    } else {
                        z = false;
                        z2 = false;
                        for (String str : aa.a(autoPreloadSizes)) {
                            if (str.equals(AppLovinAdType.REGULAR.getLabel())) {
                                obj = 1;
                            } else if (str.equals(AppLovinAdType.INCENTIVIZED.getLabel()) || str.contains("INCENT") || str.contains("REWARD")) {
                                z = true;
                            } else if (str.equals(AppLovinAdType.NATIVE.getLabel())) {
                                z2 = true;
                            }
                        }
                    }
                    if (obj == null) {
                        this.e[ea.N.a()] = "";
                        this.e[ea.O.a()] = "";
                    }
                    this.e[ea.P.a()] = Boolean.valueOf(z);
                    this.e[ea.Q.a()] = Boolean.valueOf(z);
                    this.e[ea.bt.a()] = Boolean.valueOf(z2);
                }
                if (appLovinSdkSettings instanceof bt) {
                    for (Entry entry : ((bt) appLovinSdkSettings).b().entrySet()) {
                        this.e[((ec) entry.getKey()).a()] = entry.getValue();
                    }
                }
            }
        }
    }

    void a(JSONObject jSONObject) {
        Throwable th;
        AppLovinLogger appLovinLogger;
        String str;
        String str2;
        this.b.d("SettingsManager", "Loading settings from JSON array...");
        synchronized (this.e) {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                if (str3 != null && str3.length() > 0) {
                    try {
                        ec a = a(str3, null);
                        if (a != null) {
                            Object a2 = a(str3, jSONObject, a.c());
                            this.e[a.a()] = a2;
                            StringBuilder stringBuilder = new StringBuilder("Setting update: ");
                            stringBuilder.append(a.b());
                            stringBuilder.append(" set to \"");
                            stringBuilder.append(a2);
                            stringBuilder.append("\"");
                            this.b.d("SettingsManager", stringBuilder.toString());
                        } else {
                            this.b.w("SettingsManager", "Unknown setting recieved: ".concat(String.valueOf(str3)));
                        }
                    } catch (JSONException e) {
                        th = e;
                        appLovinLogger = this.b;
                        str = "SettingsManager";
                        str2 = "Unable to parse JSON settings array";
                        appLovinLogger.e(str, str2, th);
                    } catch (Throwable th2) {
                        th = th2;
                        appLovinLogger = this.b;
                        str = "SettingsManager";
                        str2 = "Unable to convert setting object ";
                        appLovinLogger.e(str, str2, th);
                    }
                }
            }
        }
    }

    public List<String> b(ec<String> ecVar) {
        return aa.a((String) a((ec) ecVar));
    }

    void b() {
        if (this.c != null) {
            this.b.i("SettingsManager", "Loading settings saved with the application...");
            String d = d();
            synchronized (this.e) {
                for (ec ecVar : ea.a()) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(d);
                        stringBuilder.append(ecVar.b());
                        String stringBuilder2 = stringBuilder.toString();
                        Object c = ecVar.c();
                        c = this.a.get(stringBuilder2, c, c.getClass(), this.d);
                        if (c != null) {
                            this.e[ecVar.a()] = c;
                        } else {
                            this.b.e("SettingsManager", "Unable to find value for setting: ".concat(String.valueOf(stringBuilder2)));
                        }
                    } catch (Throwable e) {
                        StringBuilder stringBuilder3 = new StringBuilder("Unable to load \"");
                        stringBuilder3.append(ecVar.b());
                        stringBuilder3.append("\"");
                        this.b.e("SettingsManager", stringBuilder3.toString(), e);
                    }
                }
            }
            return;
        }
        throw new IllegalArgumentException("No context specified");
    }

    void c() {
        synchronized (this.e) {
            Arrays.fill(this.e, null);
        }
        this.a.clear(this.d);
    }
}
