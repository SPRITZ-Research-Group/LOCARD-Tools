package com.applovin.impl.a;

import com.applovin.impl.sdk.aa;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.gms.tagmanager.DataLayer;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class l {
    private String a;
    private String b;
    private String c;
    private long d = -1;
    private int e = -1;

    private l() {
    }

    private static int a(String str, g gVar) {
        return "start".equalsIgnoreCase(str) ? 0 : "firstQuartile".equalsIgnoreCase(str) ? 25 : "midpoint".equalsIgnoreCase(str) ? 50 : "thirdQuartile".equalsIgnoreCase(str) ? 75 : "complete".equalsIgnoreCase(str) ? gVar != null ? gVar.h() : 95 : -1;
    }

    public static l a(gf gfVar, g gVar, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk != null) {
            try {
                String c = gfVar.c();
                if (AppLovinSdkUtils.isValidString(c)) {
                    l lVar = new l();
                    lVar.c = c;
                    lVar.a = (String) gfVar.b().get("id");
                    lVar.b = (String) gfVar.b().get(DataLayer.EVENT_KEY);
                    lVar.e = a(lVar.a(), gVar);
                    String str = (String) gfVar.b().get("offset");
                    if (AppLovinSdkUtils.isValidString(str)) {
                        str = str.trim();
                        if (str.contains("%")) {
                            lVar.e = gd.e(str.substring(0, str.length() - 1));
                        } else if (str.contains(":")) {
                            List a = aa.a(str, ":");
                            int size = a.size();
                            if (size > 0) {
                                int i = size - 1;
                                long j = 0;
                                for (int i2 = i; i2 >= 0; i2--) {
                                    String str2 = (String) a.get(i2);
                                    if (gd.d(str2)) {
                                        long j2;
                                        int parseInt = Integer.parseInt(str2);
                                        if (i2 == i) {
                                            j2 = (long) parseInt;
                                        } else if (i2 == size - 2) {
                                            j2 = TimeUnit.MINUTES.toSeconds((long) parseInt);
                                        } else if (i2 == size - 3) {
                                            j2 = TimeUnit.HOURS.toSeconds((long) parseInt);
                                        }
                                        j += j2;
                                    }
                                }
                                lVar.d = j;
                                lVar.e = -1;
                            }
                        } else {
                            appLovinSdk.getLogger().e("VastTracker", "Unable to parse time offset from rawOffsetString = ".concat(String.valueOf(str)));
                        }
                    }
                    return lVar;
                }
                appLovinSdk.getLogger().e("VastTracker", "Unable to create tracker. Could not find URL.");
                return null;
            } catch (Throwable th) {
                appLovinSdk.getLogger().e("VastTracker", "Error occurred while initializing", th);
            }
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    public String a() {
        return this.b;
    }

    public boolean a(long j, int i) {
        return ((((this.d > 0 ? 1 : (this.d == 0 ? 0 : -1)) >= 0 ? 1 : null) == null || (j >= this.d ? 1 : null) == null) && ((this.e >= 0 ? 1 : null) == null || (i >= this.e ? 1 : null) == null)) ? false : true;
    }

    public String b() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return (this.d == lVar.d && this.e == lVar.e) ? (this.a == null ? lVar.a == null : this.a.equals(lVar.a)) ? (this.b == null ? lVar.b == null : this.b.equals(lVar.b)) ? this.c.equals(lVar.c) : false : false : false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.a != null ? this.a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.c.hashCode()) * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) + this.e;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("VastTracker{identifier='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", event='");
        stringBuilder.append(this.b);
        stringBuilder.append('\'');
        stringBuilder.append(", uriString='");
        stringBuilder.append(this.c);
        stringBuilder.append('\'');
        stringBuilder.append(", offsetSeconds=");
        stringBuilder.append(this.d);
        stringBuilder.append(", offsetPercent=");
        stringBuilder.append(this.e);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
