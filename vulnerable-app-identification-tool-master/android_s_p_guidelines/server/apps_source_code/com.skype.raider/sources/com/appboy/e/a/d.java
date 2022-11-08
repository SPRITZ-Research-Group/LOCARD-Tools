package com.appboy.e.a;

import a.a.ag;
import a.a.cf;
import a.a.cv;
import com.appboy.b.a;
import com.appboy.f.c;
import net.hockeyapp.android.j;
import org.json.JSONObject;

public final class d extends c {
    private static final String k = c.a(d.class);
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private double p;
    private int q;
    private final double r;
    private final String s;
    private String t;
    private String u;
    private a v;
    private String w;

    public d(JSONObject object, ag manager, cf feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.l = object.getString("title");
        this.m = object.getString("subtitle");
        this.n = object.getString("caption");
        this.o = object.getString("image");
        try {
            this.p = object.getDouble("rating");
            this.q = object.getInt("reviews");
        } catch (Exception e) {
            this.p = 0.0d;
            this.q = 0;
        }
        if (object.has("package")) {
            this.t = object.getString("package");
        }
        if (object.has("kindle_id")) {
            this.u = object.getString("kindle_id");
        }
        this.r = object.getDouble("price");
        if (object.has("display_price")) {
            this.w = object.getString("display_price");
        }
        this.s = object.getString(j.FRAGMENT_URL);
        if (cv.a(object, "store") != null) {
            try {
                String a = cv.a(object, "store");
                if (a != null) {
                    this.v = a.valueOf(a.a(a));
                } else {
                    this.v = a.GOOGLE_PLAY_STORE;
                }
            } catch (Throwable e2) {
                c.d(k, "Caught exception creating cross promotion small card Json.", e2);
                this.v = a.GOOGLE_PLAY_STORE;
            }
        }
    }

    public final String a() {
        return this.l;
    }

    public final String c() {
        return this.m;
    }

    public final String d() {
        return this.n;
    }

    public final String e() {
        return this.o;
    }

    public final double f() {
        return this.p;
    }

    public final int r() {
        return this.q;
    }

    public final double s() {
        return this.r;
    }

    public final String b() {
        return this.s;
    }

    public final String t() {
        return this.t;
    }

    public final String u() {
        return this.u;
    }

    public final a v() {
        return this.v;
    }

    public final String w() {
        return this.w;
    }

    public final String toString() {
        return "CrossPromotionSmallCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mTitle='" + this.l + '\'' + ", mSubtitle='" + this.m + '\'' + ", mCaption='" + this.n + '\'' + ", mImageUrl='" + this.o + '\'' + ", mRating=" + this.p + ", mReviewCount=" + this.q + ", mPrice=" + this.r + ", mPackage=" + this.t + ", mUrl='" + this.s + '\'' + ", mAppStore='" + this.v + '\'' + ", mKindleId='" + this.u + '\'' + ", mDisplayPrice='" + this.w + '\'' + "}";
    }
}
