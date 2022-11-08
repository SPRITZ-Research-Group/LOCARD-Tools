package com.applovin.impl.adview;

import com.applovin.impl.sdk.bu;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import org.json.JSONObject;

public class cm {
    private final AppLovinLogger a;
    private int b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private int g;
    private int h;
    private int i;
    private float j;
    private float k;

    public cm(JSONObject jSONObject, AppLovinSdk appLovinSdk) {
        this.a = appLovinSdk.getLogger();
        this.a.i("VideoButtonProperties", "Updating video button properties with JSON = ".concat(String.valueOf(jSONObject)));
        this.b = bu.a(jSONObject, "width", 64, appLovinSdk);
        this.c = bu.a(jSONObject, "height", 7, appLovinSdk);
        this.d = bu.a(jSONObject, "margin", 20, appLovinSdk);
        this.e = bu.a(jSONObject, "gravity", 85, appLovinSdk);
        this.f = bu.a(jSONObject, "tap_to_fade", Boolean.FALSE, appLovinSdk).booleanValue();
        this.g = bu.a(jSONObject, "tap_to_fade_duration_milliseconds", 500, appLovinSdk);
        this.h = bu.a(jSONObject, "fade_in_duration_milliseconds", 500, appLovinSdk);
        this.i = bu.a(jSONObject, "fade_out_duration_milliseconds", 500, appLovinSdk);
        this.j = bu.a(jSONObject, "fade_in_delay_seconds", 1.0f, appLovinSdk);
        this.k = bu.a(jSONObject, "fade_out_delay_seconds", 6.0f, appLovinSdk);
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public boolean e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            cm cmVar = (cm) obj;
            if (this.b == cmVar.b && this.c == cmVar.c && this.d == cmVar.d && this.e == cmVar.e && this.f == cmVar.f && this.g == cmVar.g && this.h == cmVar.h && this.i == cmVar.i && Float.compare(cmVar.j, this.j) == 0 && Float.compare(cmVar.k, this.k) == 0) {
                return true;
            }
        }
        return false;
    }

    public long f() {
        return (long) this.g;
    }

    public long g() {
        return (long) this.h;
    }

    public long h() {
        return (long) this.i;
    }

    public int hashCode() {
        int i = 0;
        int floatToIntBits = ((((((((((((((((this.b * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31) + (this.j != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(this.j) : 0)) * 31;
        if (this.k != BitmapDescriptorFactory.HUE_RED) {
            i = Float.floatToIntBits(this.k);
        }
        return floatToIntBits + i;
    }

    public float i() {
        return this.j;
    }

    public float j() {
        return this.k;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("VideoButtonProperties{widthPercentOfScreen=");
        stringBuilder.append(this.b);
        stringBuilder.append(", heightPercentOfScreen=");
        stringBuilder.append(this.c);
        stringBuilder.append(", margin=");
        stringBuilder.append(this.d);
        stringBuilder.append(", gravity=");
        stringBuilder.append(this.e);
        stringBuilder.append(", tapToFade=");
        stringBuilder.append(this.f);
        stringBuilder.append(", tapToFadeDurationMillis=");
        stringBuilder.append(this.g);
        stringBuilder.append(", fadeInDurationMillis=");
        stringBuilder.append(this.h);
        stringBuilder.append(", fadeOutDurationMillis=");
        stringBuilder.append(this.i);
        stringBuilder.append(", fadeInDelay=");
        stringBuilder.append(this.j);
        stringBuilder.append(", fadeOutDelay=");
        stringBuilder.append(this.k);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
