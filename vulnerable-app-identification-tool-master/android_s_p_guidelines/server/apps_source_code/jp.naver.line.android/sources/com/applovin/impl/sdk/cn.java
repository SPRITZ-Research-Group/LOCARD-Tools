package com.applovin.impl.sdk;

import android.content.pm.ApplicationInfo;
import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

class cn {
    private static final Map<String, String> a;
    private final AppLovinSdkImpl b;
    private final AppLovinLogger c;
    private final Object d = new Object();
    private final Map<String, cp> e = new HashMap();
    private final Collection<String> f = new HashSet();
    private final Collection<co> g = new HashSet();

    static {
        Map hashMap = new HashMap();
        a = hashMap;
        hashMap.put("chartboost", "com.applovin.mediation.impl.AppLovinChartboostMediationAdapter");
        a.put("facebook", "com.applovin.mediation.impl.AppLovinFacebookMediationAdapter");
        a.put("google", "com.applovin.mediation.impl.AppLovinGoogleMediationAdapter");
        a.put(AppLovinMediationProvider.HEYZAP, "com.applovin.mediation.impl.AppLovinHeyzapMediationAdapter");
        a.put("inmobi", "com.applovin.mediation.impl.AppLovinInMobiMediationAdapter");
        a.put(AppLovinMediationProvider.MOPUB, "com.applovin.mediation.impl.AppLovinMoPubMediationAdapter");
        a.put(AppLovinMediationProvider.IRONSOURCE, "com.applovin.mediation.impl.AppLovinIronSourceMediationAdapter");
        a.put("vungle", "com.applovin.mediation.impl.AppLovinVungleMediationAdapter");
        a.put("unity", "com.applovin.mediation.impl.AppLovinUnityMediationAdapter");
    }

    cn(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl != null) {
            this.b = appLovinSdkImpl;
            this.c = appLovinSdkImpl.getLogger();
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private cp a(co coVar, Map<String, String> map) {
        if (coVar != null) {
            synchronized (this.d) {
                String a = coVar.a();
                if (this.f.contains(a)) {
                    StringBuilder stringBuilder = new StringBuilder("Not attempting to load ");
                    stringBuilder.append(coVar);
                    stringBuilder.append(" due to prior errors");
                    this.c.d("MediationAdapterManager", stringBuilder.toString());
                    return null;
                } else if (this.e.containsKey(a)) {
                    cp cpVar = (cp) this.e.get(a);
                    return cpVar;
                } else {
                    cp b = b(coVar, map);
                    if (b != null) {
                        this.c.d("MediationAdapterManager", "Loaded ".concat(String.valueOf(coVar)));
                        this.e.put(a, b);
                        return b;
                    }
                    this.c.e("MediationAdapterManager", "Failed to load ".concat(String.valueOf(coVar)));
                    this.f.add(a);
                    return null;
                }
            }
        }
        throw new IllegalArgumentException("No adapter spec specified");
    }

    private String a(String str) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = this.b.getApplicationContext().getPackageManager().getApplicationInfo(this.b.getApplicationContext().getPackageName(), 128);
            Collection<co> b = co.b(applicationInfo.metaData.getString("applovin.mediation:load"), this.c);
            if (!b.isEmpty()) {
                for (co coVar : b) {
                    if (coVar.b().equalsIgnoreCase(str) && AppLovinSdkUtils.isValidString(coVar.a())) {
                        return coVar.a();
                    }
                }
            }
            StringBuilder stringBuilder = new StringBuilder("applovin.mediation.");
            stringBuilder.append(str);
            stringBuilder.append(":class");
            return applicationInfo.metaData.getString(stringBuilder.toString());
        } catch (Throwable th) {
            this.c.e("MediationAdapterManager", "Unable to retrieve classname from Android Manifest for adapter name: ".concat(String.valueOf(str)), th);
            return null;
        }
    }

    private void a(Collection<co> collection, String str) {
        for (co coVar : collection) {
            cp a = a(coVar.b(), coVar.a(), null);
            if (a != null) {
                StringBuilder stringBuilder = new StringBuilder("Loaded ");
                stringBuilder.append(str);
                stringBuilder.append(" adapter: ");
                stringBuilder.append(a);
                this.c.i("MediationAdapterManager", stringBuilder.toString());
            }
        }
    }

    private cp b(co coVar, Map<String, String> map) {
        try {
            Class cls = Class.forName(coVar.a());
            if (cls != null) {
                Object newInstance = cls.newInstance();
                if (newInstance instanceof AppLovinMediationAdapter) {
                    cp cpVar = new cp(coVar.b(), (AppLovinMediationAdapter) newInstance, this.b);
                    cpVar.a((Map) map);
                    if (cpVar.b()) {
                        return cpVar;
                    }
                    this.c.userError("MediationAdapterManager", "Failed to initialize ".concat(String.valueOf(coVar)));
                    return null;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(coVar);
                stringBuilder.append(" error: not an instance of '");
                stringBuilder.append(AppLovinMediationAdapter.class.getName());
                stringBuilder.append("'.");
                this.c.userError("MediationAdapterManager", stringBuilder.toString());
                return null;
            }
            this.c.userError("MediationAdapterManager", "No class found for ".concat(String.valueOf(coVar)));
            return null;
        } catch (Throwable th) {
            this.c.userError("MediationAdapterManager", "Failed to load: ".concat(String.valueOf(coVar)), th);
            return null;
        }
    }

    private Collection<co> e() {
        try {
            ApplicationInfo applicationInfo = this.b.getApplicationContext().getPackageManager().getApplicationInfo(this.b.getApplicationContext().getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return Collections.emptyList();
            }
            String string = applicationInfo.metaData.getString("applovin.mediation:load");
            Collection<co> b = co.b(string, this.c);
            if (b == null || b.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder("No adapter specs found in: '");
                stringBuilder.append(string);
                stringBuilder.append("'");
                this.c.d("MediationAdapterManager", stringBuilder.toString());
                return Collections.emptyList();
            }
            Collection<co> arrayList = new ArrayList(b.size());
            for (co coVar : b) {
                AppLovinLogger appLovinLogger;
                String str;
                String stringBuilder2;
                StringBuilder stringBuilder3;
                if (!AppLovinSdkUtils.isValidString(coVar.b())) {
                    appLovinLogger = this.c;
                    str = "MediationAdapterManager";
                    stringBuilder3 = new StringBuilder("Ignored loading of adapter with class ");
                    stringBuilder3.append(coVar.a());
                    stringBuilder3.append(": no name specified");
                    stringBuilder2 = stringBuilder3.toString();
                } else if (AppLovinSdkUtils.isValidString(coVar.a())) {
                    arrayList.add(coVar);
                } else {
                    Object coVar2;
                    StringBuilder stringBuilder4 = new StringBuilder("applovin.mediation.");
                    stringBuilder4.append(coVar.b());
                    stringBuilder4.append(":class");
                    String string2 = applicationInfo.metaData.getString(stringBuilder4.toString());
                    if (AppLovinSdkUtils.isValidString(string2)) {
                        coVar2 = new co(coVar.b(), string2);
                    } else {
                        string2 = (String) a.get(coVar.b());
                        if (AppLovinSdkUtils.isValidString(string2)) {
                            coVar2 = new co(coVar.b(), string2);
                        } else {
                            appLovinLogger = this.c;
                            str = "MediationAdapterManager";
                            stringBuilder3 = new StringBuilder("Ignored loading of ");
                            stringBuilder3.append(coVar.b());
                            stringBuilder3.append(": no default adapter class found");
                            stringBuilder2 = stringBuilder3.toString();
                        }
                    }
                    arrayList.add(coVar2);
                }
                appLovinLogger.userError(str, stringBuilder2);
            }
            return arrayList;
        } catch (Throwable th) {
            this.c.userError("MediationAdapterManager", "Unable to load applovin.mediation:loadfrom AndroidManifest.xml", th);
            return Collections.emptyList();
        }
    }

    private Collection<co> f() {
        return co.b((String) this.b.get(ef.f), this.c);
    }

    private void g() {
        String a;
        synchronized (this.d) {
            a = co.a(this.g);
        }
        this.b.put(ef.f, a);
    }

    cp a(String str, String str2, Map<String, String> map) {
        StringBuilder stringBuilder;
        if (AppLovinSdkUtils.isValidString(str)) {
            AppLovinLogger appLovinLogger;
            String str3;
            String concat;
            if (AppLovinSdkUtils.isValidString(str2)) {
                appLovinLogger = this.c;
                str3 = "MediationAdapterManager";
                concat = "Loading adapter using explicit classname: ".concat(String.valueOf(str2));
            } else {
                StringBuilder stringBuilder2;
                String str4;
                if (map == null || !map.containsKey("class")) {
                    str2 = a(str);
                    if (!AppLovinSdkUtils.isValidString(str2)) {
                        str2 = (String) a.get(str.toLowerCase(Locale.ENGLISH));
                        if (AppLovinSdkUtils.isValidString(str2)) {
                            appLovinLogger = this.c;
                            str3 = "MediationAdapterManager";
                            stringBuilder2 = new StringBuilder("Loading '");
                            stringBuilder2.append(str);
                            str4 = "' adapter using resolved default classname: ";
                        } else {
                            stringBuilder = new StringBuilder("Unable to find default classname for '");
                            stringBuilder.append(str);
                            stringBuilder.append("'");
                            this.c.w("MediationAdapterManager", stringBuilder.toString());
                            return null;
                        }
                    }
                    return a(new co(str, str2), (Map) map);
                }
                str2 = (String) map.get("class");
                if (AppLovinSdkUtils.isValidString(str2)) {
                    appLovinLogger = this.c;
                    str3 = "MediationAdapterManager";
                    stringBuilder2 = new StringBuilder("Loading '");
                    stringBuilder2.append(str);
                    str4 = "' adapter using configured classname: ";
                } else {
                    stringBuilder = new StringBuilder("Invalid configured classname for '");
                    stringBuilder.append(str);
                    stringBuilder.append("'");
                    this.c.w("MediationAdapterManager", stringBuilder.toString());
                    return null;
                }
                stringBuilder2.append(str4);
                stringBuilder2.append(str2);
                concat = stringBuilder2.toString();
            }
            appLovinLogger.d(str3, concat);
            return a(new co(str, str2), (Map) map);
        }
        stringBuilder = new StringBuilder("No adapter name provided for ");
        stringBuilder.append(str2);
        stringBuilder.append(", not loading the adapter ");
        this.c.e("MediationAdapterManager", stringBuilder.toString());
        return null;
    }

    void a() {
        synchronized (this.d) {
            if (((Boolean) this.b.get(ea.dC)).booleanValue()) {
                a(f(), "last used");
            }
            if (((Boolean) this.b.get(ea.dD)).booleanValue()) {
                a(e(), "AndroidManifest");
            }
        }
    }

    void a(cp cpVar) {
        if (cpVar != null) {
            co coVar = new co(cpVar.a(), cpVar.f());
            synchronized (this.d) {
                if (!this.g.contains(coVar)) {
                    this.g.add(coVar);
                    g();
                }
            }
        }
    }

    Collection<String> b() {
        Collection arrayList;
        synchronized (this.d) {
            arrayList = new ArrayList(this.f);
        }
        return arrayList;
    }

    Collection<cp> c() {
        Collection arrayList;
        synchronized (this.d) {
            arrayList = new ArrayList(this.e.values());
        }
        return arrayList;
    }
}
