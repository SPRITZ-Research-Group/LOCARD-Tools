package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.internal.j.a.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.m.e;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.util.Map;

class j extends b {
    private static final String d = j.class.getSimpleName();
    private final Uri e;
    private final Map<String, String> f;

    j(Context context, c cVar, String str, Uri uri, Map<String, String> map) {
        super(context, cVar, str);
        this.e = uri;
        this.f = map;
    }

    public final a a() {
        return null;
    }

    public final void b() {
        e eVar = e.IMMEDIATE;
        Object queryParameter = this.e.getQueryParameter(EventsEntry.COLUMN_NAME_PRIORITY);
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                eVar = e.values()[Integer.valueOf(queryParameter).intValue()];
            } catch (Exception e) {
            }
        }
        this.b.a(this.c, this.f, this.e.getQueryParameter("type"), eVar);
    }
}
