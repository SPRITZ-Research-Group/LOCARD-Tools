package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.GuardedBy;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class fy {
    private static final ConcurrentHashMap<Uri, fy> a = new ConcurrentHashMap();
    private static final String[] i = new String[]{PropertiesEntry.COLUMN_NAME_KEY, PropertiesEntry.COLUMN_NAME_VALUE};
    private final ContentResolver b;
    private final Uri c;
    private final ContentObserver d;
    private final Object e = new Object();
    private volatile Map<String, String> f;
    private final Object g = new Object();
    @GuardedBy("listenersLock")
    private final List<Object> h = new ArrayList();

    private fy(ContentResolver contentResolver, Uri uri) {
        this.b = contentResolver;
        this.c = uri;
        this.d = new fz(this);
    }

    public static fy a(ContentResolver contentResolver, Uri uri) {
        fy fyVar = (fy) a.get(uri);
        if (fyVar != null) {
            return fyVar;
        }
        fy fyVar2 = new fy(contentResolver, uri);
        fyVar = (fy) a.putIfAbsent(uri, fyVar2);
        if (fyVar != null) {
            return fyVar;
        }
        fyVar2.b.registerContentObserver(fyVar2.c, false, fyVar2.d);
        return fyVar2;
    }

    private final Map<String, String> c() {
        Cursor query;
        try {
            Map<String, String> hashMap = new HashMap();
            query = this.b.query(this.c, i, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    hashMap.put(query.getString(0), query.getString(1));
                }
                query.close();
            }
            return hashMap;
        } catch (SecurityException e) {
        } catch (SQLiteException e2) {
        } catch (Throwable th) {
            query.close();
        }
        return null;
    }

    public final Map<String, String> a() {
        Map<String, String> c = ga.b("gms:phenotype:phenotype_flag:debug_disable_caching") ? c() : this.f;
        if (c == null) {
            synchronized (this.e) {
                c = this.f;
                if (c == null) {
                    c = c();
                    this.f = c;
                }
            }
        }
        return c != null ? c : Collections.emptyMap();
    }

    public final void b() {
        synchronized (this.e) {
            this.f = null;
        }
    }

    static /* synthetic */ void a(fy fyVar) {
        synchronized (fyVar.g) {
            Iterator it = fyVar.h.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }
}
