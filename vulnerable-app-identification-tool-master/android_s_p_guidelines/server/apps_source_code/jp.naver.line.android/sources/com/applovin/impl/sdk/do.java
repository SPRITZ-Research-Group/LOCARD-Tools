package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class do {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private ArrayList<dq> c;
    private ArrayList<dq> d;
    private final Object e;
    private final SharedPreferences f;

    do(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl != null) {
            this.a = appLovinSdkImpl;
            this.b = appLovinSdkImpl.getLogger();
            this.f = appLovinSdkImpl.getApplicationContext().getSharedPreferences("com.applovin.sdk.impl.postbackQueue.domain", 0);
            this.e = new Object();
            this.c = c();
            this.d = new ArrayList();
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private void a(dq dqVar) {
        synchronized (this.e) {
            b(dqVar);
            c(dqVar);
        }
    }

    private dq b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("attemptNumber");
            return new dq(jSONObject.getString("targetUrl"), bu.a(jSONObject.getJSONObject("requestBody")), i, jSONObject.getString("backupUrl"));
        } catch (Throwable e) {
            this.b.w("PersistentPostbackManager", "Unable to inflate postback request from JSON.", e);
            return null;
        }
    }

    private void b(dq dqVar) {
        synchronized (this.e) {
            if (this.c.size() < ((Integer) this.a.get(ea.bT)).intValue()) {
                this.c.add(dqVar);
                d();
                this.b.d("PersistentPostbackManager", "Enqueued postback: ".concat(String.valueOf(dqVar)));
            } else {
                this.b.w("PersistentPostbackManager", "Persistent queue has reached maximum size; postback retried in memory only.".concat(String.valueOf(dqVar)));
            }
        }
    }

    private ArrayList<dq> c() {
        if (ab.b()) {
            Set<String> set = (Set) this.a.get(ef.b, new LinkedHashSet(0), this.f);
            ArrayList<dq> arrayList = new ArrayList(Math.max(1, set.size()));
            int intValue = ((Integer) this.a.get(ea.bU)).intValue();
            StringBuilder stringBuilder = new StringBuilder("Deserializing ");
            stringBuilder.append(set.size());
            stringBuilder.append(" postback(s).");
            this.b.d("PersistentPostbackManager", stringBuilder.toString());
            for (String str : set) {
                dq b = b(str);
                if (b == null) {
                    this.b.e("PersistentPostbackManager", "Unable to deserialize postback json: ".concat(String.valueOf(str)));
                } else if (b.a() > intValue) {
                    arrayList.add(b);
                } else {
                    this.b.d("PersistentPostbackManager", "Skipping deserialization because maximum attempt count exceeded for postback: ".concat(String.valueOf(b)));
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder("Successfully loaded postback queue with ");
            stringBuilder2.append(arrayList.size());
            stringBuilder2.append(" postback(s).");
            this.b.d("PersistentPostbackManager", stringBuilder2.toString());
            return arrayList;
        }
        this.b.d("PersistentPostbackManager", "Loading new postback queue due to old Android version...");
        return new ArrayList();
    }

    private void c(dq dqVar) {
        this.b.d("PersistentPostbackManager", "Preparing to submit postback...".concat(String.valueOf(dqVar)));
        if (this.a.e()) {
            this.b.d("PersistentPostbackManager", "Skipping postback dispatch because SDK is still initializing - postback will be dispatched afterwards");
            return;
        }
        synchronized (this.e) {
            dqVar.a(dqVar.a() + 1);
            d();
        }
        int intValue = ((Integer) this.a.get(ea.bU)).intValue();
        if (dqVar.a() > intValue) {
            StringBuilder stringBuilder = new StringBuilder("Exceeded maximum persisted attempt count of ");
            stringBuilder.append(intValue);
            stringBuilder.append(". Dequeuing postback: ");
            stringBuilder.append(dqVar);
            this.b.w("PersistentPostbackManager", stringBuilder.toString());
            d(dqVar);
            return;
        }
        this.a.getPostbackService().dispatchPostbackAsync(dqVar.b(), dqVar.d(), dqVar.c(), new dp(this, dqVar));
    }

    private void d() {
        String f;
        AppLovinLogger appLovinLogger;
        String str;
        if (ab.c()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.c.size());
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                f = f((dq) it.next());
                if (f != null) {
                    linkedHashSet.add(f);
                }
            }
            this.a.put(ef.b, linkedHashSet);
            appLovinLogger = this.b;
            str = "PersistentPostbackManager";
            f = "Wrote updated postback queue to disk.";
        } else {
            appLovinLogger = this.b;
            str = "PersistentPostbackManager";
            f = "Skipping writing postback queue to disk due to old Android version...";
        }
        appLovinLogger.d(str, f);
    }

    private void d(dq dqVar) {
        synchronized (this.e) {
            this.c.remove(dqVar);
            d();
        }
        this.b.d("PersistentPostbackManager", "Dequeued successfully transmitted postback: ".concat(String.valueOf(dqVar)));
    }

    private void e(dq dqVar) {
        synchronized (this.e) {
            this.d.add(dqVar);
        }
    }

    private String f(dq dqVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("attemptNumber", dqVar.a());
            jSONObject.put("targetUrl", dqVar.b());
            String c = dqVar.c();
            if (AppLovinSdkUtils.isValidString(c)) {
                jSONObject.put("backupUrl", c);
            }
            Map d = dqVar.d();
            if (d != null) {
                jSONObject.put("requestBody", new JSONObject(d));
            }
            return jSONObject.toString();
        } catch (Throwable e) {
            this.b.w("PersistentPostbackManager", "Unable to serialize postback request to JSON.", e);
            return null;
        }
    }

    public void a() {
        synchronized (this.e) {
            if (this.c != null) {
                for (dq c : new ArrayList(this.c)) {
                    c(c);
                }
            }
        }
    }

    public void a(String str) {
        a(str, null);
    }

    public void a(String str, Map<String, String> map) {
        a(str, map, true);
    }

    public void a(String str, Map<String, String> map, boolean z) {
        a(str, map, z, null);
    }

    public void a(String str, Map<String, String> map, boolean z, String str2) {
        if (AppLovinSdkUtils.isValidString(str)) {
            if (z) {
                StringBuilder stringBuilder = new StringBuilder("&postback_ts=");
                stringBuilder.append(System.currentTimeMillis());
                String stringBuilder2 = stringBuilder.toString();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str);
                stringBuilder3.append(stringBuilder2);
                str = stringBuilder3.toString();
                if (AppLovinSdkUtils.isValidString(str2)) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str2);
                    stringBuilder3.append(stringBuilder2);
                    str2 = stringBuilder3.toString();
                }
            }
            a(new dq(str, map, 0, str2));
        }
    }

    public void b() {
        synchronized (this.e) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                c((dq) it.next());
            }
            this.d.clear();
        }
    }
}
