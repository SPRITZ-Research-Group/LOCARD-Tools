package com.appboy.e.a;

import a.a.ag;
import a.a.cf;
import a.a.cv;
import net.hockeyapp.android.j;
import org.json.JSONObject;

public final class b extends c {
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final float p;

    public b(JSONObject object, ag manager, cf feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.k = object.getString("image");
        this.l = object.getString("title");
        this.m = object.getString("description");
        this.n = cv.a(object, j.FRAGMENT_URL);
        this.o = cv.a(object, "domain");
        this.p = (float) object.optDouble("aspect_ratio", 0.0d);
    }

    public final String a() {
        return this.k;
    }

    public final String c() {
        return this.l;
    }

    public final String d() {
        return this.m;
    }

    public final String b() {
        return this.n;
    }

    public final String e() {
        return this.o;
    }

    public final float f() {
        return this.p;
    }

    public final String toString() {
        return "CaptionedImageCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mImageUrl='" + this.k + '\'' + ", mTitle='" + this.l + '\'' + ", mDescription='" + this.m + '\'' + ", mUrl='" + this.n + '\'' + ", mDomain='" + this.o + '\'' + ", mAspectRatio='" + this.p + '\'' + "}";
    }
}
