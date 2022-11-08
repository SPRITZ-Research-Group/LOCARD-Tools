package com.applovin.impl.a;

import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.aa;
import com.applovin.impl.sdk.bu;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.o;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.google.android.exoplayer.util.MimeTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

public class g {
    private static final List<String> c = Arrays.asList(new String[]{MimeTypes.VIDEO_MP4, MimeTypes.VIDEO_WEBM, MimeTypes.VIDEO_H263, "video/x-matroska"});
    protected List<gf> a = new ArrayList();
    private final AppLovinSdkImpl b;
    private final JSONObject d;
    private final JSONObject e;
    private final long f = System.currentTimeMillis();

    public g(JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        this.b = appLovinSdkImpl;
        this.d = jSONObject;
        this.e = jSONObject2;
    }

    public int a() {
        return this.a.size();
    }

    public List<gf> b() {
        return this.a;
    }

    public JSONObject c() {
        return this.d;
    }

    public JSONObject d() {
        return this.e;
    }

    public long e() {
        return this.f;
    }

    public n f() {
        String a = bu.a(this.e, "zone_id", null, this.b);
        return n.a(AppLovinAdSize.fromString(bu.a(this.e, "ad_size", null, this.b)), AppLovinAdType.fromString(bu.a(this.e, "ad_type", null, this.b)), o.DIRECT, a, this.b);
    }

    public List<String> g() {
        List<String> a = aa.a(bu.a(this.d, "vast_preferred_video_types", null, null));
        return !a.isEmpty() ? a : c;
    }

    public int h() {
        return gd.a(this.d);
    }
}
