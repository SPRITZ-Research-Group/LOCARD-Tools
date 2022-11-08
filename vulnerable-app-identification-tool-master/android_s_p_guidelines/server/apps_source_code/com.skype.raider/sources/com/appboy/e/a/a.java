package com.appboy.e.a;

import a.a.ag;
import a.a.cf;
import a.a.cv;
import net.hockeyapp.android.j;
import org.json.JSONObject;

public final class a extends c {
    private final String k;
    private final String l;
    private final String m;
    private final float n;

    public a(JSONObject object, ag manager, cf feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.k = object.getString("image");
        this.l = cv.a(object, j.FRAGMENT_URL);
        this.m = cv.a(object, "domain");
        this.n = (float) object.optDouble("aspect_ratio", 0.0d);
    }

    public final String a() {
        return this.k;
    }

    public final String b() {
        return this.l;
    }

    public final float c() {
        return this.n;
    }

    public final String toString() {
        return "BannerImageCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mImageUrl='" + this.k + '\'' + ", mUrl='" + this.l + '\'' + ", mDomain='" + this.m + '\'' + ", mAspectRatio='" + this.n + '\'' + "}";
    }
}
