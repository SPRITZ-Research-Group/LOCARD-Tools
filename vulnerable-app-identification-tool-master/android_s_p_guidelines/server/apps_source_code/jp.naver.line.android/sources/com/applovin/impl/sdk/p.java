package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.json.JSONArray;
import org.json.JSONObject;

final class p {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private LinkedHashSet<n> c;
    private final Object d = new Object();
    private volatile boolean e;

    p(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
        this.c = c();
    }

    private LinkedHashSet<n> b(JSONArray jSONArray) {
        LinkedHashSet<n> linkedHashSet = new LinkedHashSet(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject a = bu.a(jSONArray, i, null, this.a);
            StringBuilder stringBuilder = new StringBuilder("Loading zone: ");
            stringBuilder.append(a);
            stringBuilder.append("...");
            this.b.d("AdZoneManager", stringBuilder.toString());
            linkedHashSet.add(n.a(bu.a(a, "id", null, this.a), a, this.a));
        }
        return linkedHashSet;
    }

    private LinkedHashSet<n> c() {
        LinkedHashSet<n> linkedHashSet = new LinkedHashSet();
        Iterator it;
        try {
            String str = (String) this.a.get(ef.e);
            if (AppLovinSdkUtils.isValidString(str)) {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    linkedHashSet = b(jSONArray);
                } else {
                    this.b.d("AdZoneManager", "Unable to inflate json string: ".concat(String.valueOf(str)));
                }
            }
            if (!linkedHashSet.isEmpty()) {
                this.b.d("AdZoneManager", "Retrieved persisted zones: ".concat(String.valueOf(linkedHashSet)));
                it = linkedHashSet.iterator();
                while (it.hasNext()) {
                    ((n) it.next()).a(this.a);
                }
            }
        } catch (Throwable th) {
            if (!linkedHashSet.isEmpty()) {
                this.b.d("AdZoneManager", "Retrieved persisted zones: ".concat(String.valueOf(linkedHashSet)));
                Iterator it2 = linkedHashSet.iterator();
                while (it2.hasNext()) {
                    ((n) it2.next()).a(this.a);
                }
            }
        }
        return linkedHashSet;
    }

    private void c(JSONArray jSONArray) {
        if (((Boolean) this.a.get(ea.cY)).booleanValue()) {
            this.b.d("AdZoneManager", "Persisting zones...");
            this.a.put(ef.e, jSONArray.toString());
        }
    }

    public final LinkedHashSet<n> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new LinkedHashSet();
        }
        LinkedHashSet<n> linkedHashSet = new LinkedHashSet(jSONArray.length());
        Object obj = null;
        synchronized (this.d) {
            if (!this.e) {
                StringBuilder stringBuilder = new StringBuilder("Found ");
                stringBuilder.append(jSONArray.length());
                stringBuilder.append(" zone(s)...");
                this.b.d("AdZoneManager", stringBuilder.toString());
                obj = b(jSONArray);
                linkedHashSet = new LinkedHashSet(obj);
                linkedHashSet.removeAll(this.c);
                this.c = obj;
                this.e = true;
            }
        }
        if (obj != null) {
            c(jSONArray);
            this.b.d("AdZoneManager", "Finished loading zones");
        }
        return linkedHashSet;
    }

    public final boolean a() {
        return this.e;
    }

    public final boolean a(n nVar) {
        boolean contains;
        synchronized (this.d) {
            contains = this.c.contains(nVar);
        }
        return contains;
    }

    public final LinkedHashSet<n> b() {
        LinkedHashSet<n> linkedHashSet;
        synchronized (this.d) {
            linkedHashSet = this.c;
        }
        return linkedHashSet;
    }
}
