package com.applovin.impl.a;

import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import jp.naver.android.npush.common.NPushIntent;

public class k {
    private String a;
    private String b;

    private k() {
    }

    public static k a(gf gfVar, k kVar, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk != null) {
            if (kVar == null) {
                try {
                    kVar = new k();
                } catch (Throwable th) {
                    appLovinSdk.getLogger().e("VastSystemInfo", "Error occurred while initializing", th);
                    return null;
                }
            }
            if (!AppLovinSdkUtils.isValidString(kVar.a)) {
                String c = gfVar.c();
                if (AppLovinSdkUtils.isValidString(c)) {
                    kVar.a = c;
                }
            }
            if (!AppLovinSdkUtils.isValidString(kVar.b)) {
                String str = (String) gfVar.b().get(NPushIntent.EXTRA_VERSION);
                if (AppLovinSdkUtils.isValidString(str)) {
                    kVar.b = str;
                }
            }
            return kVar;
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return (this.a == null ? kVar.a == null : this.a.equals(kVar.a)) ? this.b != null ? this.b.equals(kVar.b) : kVar.b == null : false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.a != null ? this.a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("VastSystemInfo{name='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", version='");
        stringBuilder.append(this.b);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
