package com.facebook.marketing.internal;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.facebook.s;
import com.facebook.t;
import defpackage.anu;

public final class MarketingInitProvider extends ContentProvider {
    private static final String a = "MarketingInitProvider";

    public final int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public final String getType(Uri uri) {
        return null;
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public final boolean onCreate() {
        try {
            if (s.a()) {
                b();
            } else {
                s.a(getContext(), new t(this) {
                    final /* synthetic */ MarketingInitProvider a;

                    {
                        this.a = r1;
                    }

                    public final void a() {
                        MarketingInitProvider.b();
                    }
                });
            }
        } catch (Throwable e) {
            Log.i(a, "Failed to auto initialize the Marketing SDK", e);
        }
        return false;
    }

    private static void b() {
        Application application = (Application) s.f();
        s.j();
        anu.a(application);
        new a((Application) s.f(), s.j()).a();
    }
}
