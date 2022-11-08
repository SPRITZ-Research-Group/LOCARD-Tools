package com.appboy.e.a;

import a.a.ag;
import a.a.cf;
import a.a.cv;
import net.hockeyapp.android.j;
import org.json.JSONObject;

public final class f extends c {
    private final String k;
    private final String l;
    private final String m;
    private final String n;

    public f(JSONObject object, ag manager, cf feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.l = cv.a(object, "title");
        this.k = object.getString("description");
        this.m = cv.a(object, j.FRAGMENT_URL);
        this.n = cv.a(object, "domain");
    }

    public final String a() {
        return this.k;
    }

    public final String c() {
        return this.l;
    }

    public final String b() {
        return this.m;
    }

    public final String d() {
        return this.n;
    }

    public final String toString() {
        return "TextAnnouncementCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mDescription='" + this.k + '\'' + ", mTitle='" + this.l + '\'' + ", mUrl='" + this.m + '\'' + ", mDomain='" + this.n + '\'' + "}";
    }
}
