package com.applovin.impl.a;

import com.applovin.impl.sdk.AppLovinSdkImpl;
import java.util.Set;
import org.json.JSONObject;

public class c {
    private JSONObject a;
    private JSONObject b;
    private AppLovinSdkImpl c;
    private long d;
    private String e;
    private String f;
    private k g;
    private o h;
    private f i;
    private Set<l> j;
    private Set<l> k;

    private c() {
    }

    public a a() {
        return new a();
    }

    public c a(long j) {
        this.d = j;
        return this;
    }

    public c a(f fVar) {
        this.i = fVar;
        return this;
    }

    public c a(k kVar) {
        this.g = kVar;
        return this;
    }

    public c a(o oVar) {
        this.h = oVar;
        return this;
    }

    public c a(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl != null) {
            this.c = appLovinSdkImpl;
            return this;
        }
        throw new IllegalArgumentException("No sdk specified.");
    }

    public c a(String str) {
        this.e = str;
        return this;
    }

    public c a(Set<l> set) {
        this.j = set;
        return this;
    }

    public c a(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.a = jSONObject;
            return this;
        }
        throw new IllegalArgumentException("No ad object specified.");
    }

    public c b(String str) {
        this.f = str;
        return this;
    }

    public c b(Set<l> set) {
        this.k = set;
        return this;
    }

    public c b(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.b = jSONObject;
            return this;
        }
        throw new IllegalArgumentException("No full ad response specified.");
    }
}
