package com.applovin.impl.a;

import android.net.Uri;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class f {
    private int a;
    private int b;
    private Uri c;
    private i d;
    private Set<l> e = new HashSet();
    private Map<String, Set<l>> f = new HashMap();

    private f() {
    }

    public static f a(gf gfVar, f fVar, g gVar, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk != null) {
            if (fVar == null) {
                try {
                    fVar = new f();
                } catch (Throwable th) {
                    appLovinSdk.getLogger().e("VastCompanionAd", "Error occurred while initializing", th);
                    return null;
                }
            }
            if (fVar.a == 0 && fVar.b == 0) {
                int e = gd.e((String) gfVar.b().get("width"));
                int e2 = gd.e((String) gfVar.b().get("height"));
                if (e > 0 && e2 > 0) {
                    fVar.a = e;
                    fVar.b = e2;
                }
            }
            fVar.d = i.a(gfVar, fVar.d, appLovinSdk);
            if (fVar.c == null) {
                gf b = gfVar.b("CompanionClickThrough");
                if (b != null) {
                    String c = b.c();
                    if (AppLovinSdkUtils.isValidString(c)) {
                        fVar.c = Uri.parse(c);
                    }
                }
            }
            n.a(gfVar.a("CompanionClickTracking"), fVar.e, gVar, appLovinSdk);
            n.a(gfVar, fVar.f, gVar, appLovinSdk);
            return fVar;
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    public Uri a() {
        return this.c;
    }

    public i b() {
        return this.d;
    }

    public Set<l> c() {
        return this.e;
    }

    public Map<String, Set<l>> d() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return (this.a == fVar.a && this.b == fVar.b) ? (this.c == null ? fVar.c == null : this.c.equals(fVar.c)) ? (this.d == null ? fVar.d == null : this.d.equals(fVar.d)) ? (this.e == null ? fVar.e == null : this.e.equals(fVar.e)) ? this.f != null ? this.f.equals(fVar.f) : fVar.f == null : false : false : false : false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.a * 31) + this.b) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("VastCompanionAd{width=");
        stringBuilder.append(this.a);
        stringBuilder.append(", height=");
        stringBuilder.append(this.b);
        stringBuilder.append(", destinationUri=");
        stringBuilder.append(this.c);
        stringBuilder.append(", nonVideoResource=");
        stringBuilder.append(this.d);
        stringBuilder.append(", clickTrackers=");
        stringBuilder.append(this.e);
        stringBuilder.append(", eventTrackers=");
        stringBuilder.append(this.f);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
