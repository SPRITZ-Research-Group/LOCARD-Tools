package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Locale;

public class r {
    private Uri a;
    private Uri b;
    private s c;
    private String d;
    private int e;
    private int f;
    private int g;

    private r() {
    }

    public static r a(gf gfVar, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk != null) {
            try {
                String c = gfVar.c();
                if (URLUtil.isValidUrl(c)) {
                    Uri parse = Uri.parse(c);
                    r rVar = new r();
                    rVar.a = parse;
                    rVar.b = parse;
                    rVar.g = gd.e((String) gfVar.b().get("bitrate"));
                    rVar.c = a((String) gfVar.b().get("delivery"));
                    rVar.f = gd.e((String) gfVar.b().get("height"));
                    rVar.e = gd.e((String) gfVar.b().get("width"));
                    rVar.d = ((String) gfVar.b().get("type")).toLowerCase(Locale.ENGLISH);
                    return rVar;
                }
                appLovinSdk.getLogger().e("VastVideoFile", "Unable to create video file. Could not find URL.");
                return null;
            } catch (Throwable th) {
                appLovinSdk.getLogger().e("VastVideoFile", "Error occurred while initializing", th);
            }
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    private static s a(String str) {
        if (AppLovinSdkUtils.isValidString(str)) {
            if ("progressive".equalsIgnoreCase(str)) {
                return s.Progressive;
            }
            if ("streaming".equalsIgnoreCase(str)) {
                return s.Streaming;
            }
        }
        return s.Progressive;
    }

    public Uri a() {
        return this.a;
    }

    public void a(Uri uri) {
        this.b = uri;
    }

    public Uri b() {
        return this.b;
    }

    public boolean c() {
        return this.c == s.Streaming;
    }

    public String d() {
        return this.d;
    }

    public int e() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return (this.e == rVar.e && this.f == rVar.f && this.g == rVar.g) ? (this.a == null ? rVar.a == null : this.a.equals(rVar.a)) ? (this.b == null ? rVar.b == null : this.b.equals(rVar.b)) ? this.c != rVar.c ? false : this.d != null ? this.d.equals(rVar.d) : rVar.d == null : false : false : false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.e) * 31) + this.f) * 31) + this.g;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("VastVideoFile{sourceVideoUri=");
        stringBuilder.append(this.a);
        stringBuilder.append(", videoUri=");
        stringBuilder.append(this.b);
        stringBuilder.append(", deliveryType=");
        stringBuilder.append(this.c);
        stringBuilder.append(", fileType='");
        stringBuilder.append(this.d);
        stringBuilder.append('\'');
        stringBuilder.append(", width=");
        stringBuilder.append(this.e);
        stringBuilder.append(", height=");
        stringBuilder.append(this.f);
        stringBuilder.append(", bitrate=");
        stringBuilder.append(this.g);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
