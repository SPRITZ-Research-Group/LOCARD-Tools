package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

class c {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Object c = new Object();
    private final f d = new f();

    c(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl != null) {
            this.a = appLovinSdkImpl;
            this.b = appLovinSdkImpl.getLogger();
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private e a(q qVar) {
        e eVar;
        synchronized (this.c) {
            String o = qVar.o();
            eVar = (e) this.d.get(o);
            if (eVar == null) {
                eVar = new e(o, qVar.p(), qVar.q(), null);
                this.d.put(o, eVar);
            }
        }
        return eVar;
    }

    private void a(JSONObject jSONObject) {
        dx dVar = new d(this, "POST", new JSONObject(), "RepeatSubmitAdEvents", this.a);
        dVar.a(c());
        dVar.a(jSONObject);
        dVar.b(d());
        dVar.b(((Integer) this.a.get(ea.dI)).intValue());
        dVar.c(((Integer) this.a.get(ea.dJ)).intValue());
        dVar.a(ea.m);
        dVar.b(ea.q);
        this.a.getTaskManager().a(dVar, fe.BACKGROUND);
    }

    private String c() {
        return ag.a("s", null, this.a);
    }

    private String d() {
        return ag.c("s", null, this.a);
    }

    private void e() {
        HashSet hashSet;
        synchronized (this.c) {
            hashSet = new HashSet(this.d.size());
            for (e eVar : this.d.values()) {
                try {
                    String a = eVar.b();
                    if (a != null) {
                        hashSet.add(a);
                    }
                } catch (Throwable e) {
                    this.b.e("AdEventStatsManager", "Failed to serialize ".concat(String.valueOf(eVar)), e);
                }
            }
        }
        this.a.put(ef.j, hashSet);
    }

    void a() {
        if (((Boolean) this.a.get(ea.dH)).booleanValue()) {
            if (ab.b()) {
                Set<String> set = (Set) this.a.get(ef.j, new HashSet(0));
                this.a.remove(ef.j);
                if (set == null || set.isEmpty()) {
                    this.b.d("AdEventStatsManager", "No serialized ad events found");
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder("De-serializing ");
                stringBuilder.append(set.size());
                stringBuilder.append(" stat ad events");
                this.b.d("AdEventStatsManager", stringBuilder.toString());
                JSONArray jSONArray = new JSONArray();
                for (String str : set) {
                    try {
                        jSONArray.put(new JSONObject(str));
                    } catch (Throwable e) {
                        this.b.e("AdEventStatsManager", "Failed to parse: ".concat(String.valueOf(str)), e);
                    }
                }
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("stats", jSONArray);
                    a(jSONObject);
                    return;
                } catch (Throwable e2) {
                    this.b.e("AdEventStatsManager", "Failed to create stats to submit", e2);
                    return;
                }
            }
            this.b.d("AdEventStatsManager", "Not loading new event stat due to old Android version...");
        }
    }

    void a(b bVar, long j, q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (bVar == null) {
            throw new IllegalArgumentException("No key specified");
        } else if (((Boolean) this.a.get(ea.dH)).booleanValue()) {
            synchronized (this.c) {
                a(qVar).a(bVar.a(), j);
            }
            e();
        }
    }

    void b() {
        synchronized (this.c) {
            this.d.clear();
        }
    }
}
