package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

public class i {
    private j a;
    private Uri b;
    private String c;

    private i() {
    }

    static i a(gf gfVar, i iVar, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk != null) {
            if (iVar == null) {
                try {
                    iVar = new i();
                } catch (Throwable th) {
                    appLovinSdk.getLogger().e("VastNonVideoResource", "Error occurred while initializing", th);
                    return null;
                }
            }
            if (iVar.b == null && !AppLovinSdkUtils.isValidString(iVar.c)) {
                String a = a(gfVar, "StaticResource");
                if (URLUtil.isValidUrl(a)) {
                    iVar.b = Uri.parse(a);
                    iVar.a = j.STATIC;
                    return iVar;
                }
                a = a(gfVar, "IFrameResource");
                if (AppLovinSdkUtils.isValidString(a)) {
                    iVar.a = j.IFRAME;
                    if (URLUtil.isValidUrl(a)) {
                        iVar.b = Uri.parse(a);
                    } else {
                        iVar.c = a;
                    }
                    return iVar;
                }
                String a2 = a(gfVar, "HTMLResource");
                if (AppLovinSdkUtils.isValidString(a2)) {
                    iVar.a = j.HTML;
                    if (URLUtil.isValidUrl(a2)) {
                        iVar.b = Uri.parse(a2);
                    } else {
                        iVar.c = a2;
                    }
                }
            }
            return iVar;
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    private static String a(gf gfVar, String str) {
        gfVar = gfVar.b(str);
        return gfVar != null ? gfVar.c() : null;
    }

    public j a() {
        return this.a;
    }

    public void a(Uri uri) {
        this.b = uri;
    }

    public void a(String str) {
        this.c = str;
    }

    public Uri b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.a != iVar.a ? false : (this.b == null ? iVar.b == null : this.b.equals(iVar.b)) ? this.c != null ? this.c.equals(iVar.c) : iVar.c == null : false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31;
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("VastNonVideoResource{type=");
        stringBuilder.append(this.a);
        stringBuilder.append(", resourceUri=");
        stringBuilder.append(this.b);
        stringBuilder.append(", resourceContents='");
        stringBuilder.append(this.c);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
