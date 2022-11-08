package com.appboy.e.a;

import a.a.ag;
import a.a.cf;
import a.a.cv;
import net.hockeyapp.android.j;
import org.json.JSONObject;

public final class e extends c {
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;

    public e(JSONObject object, ag manager, cf feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.k = object.getString("description");
        this.l = object.getString("image");
        this.m = cv.a(object, "title");
        this.n = cv.a(object, j.FRAGMENT_URL);
        this.o = cv.a(object, "domain");
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

    public final String toString() {
        return "ShortNewsCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mDescription='" + this.k + '\'' + ", mImageUrl='" + this.l + '\'' + ", mTitle='" + this.m + '\'' + ", mUrl='" + this.n + '\'' + ", mDomain='" + this.o + '\'' + "}";
    }
}
