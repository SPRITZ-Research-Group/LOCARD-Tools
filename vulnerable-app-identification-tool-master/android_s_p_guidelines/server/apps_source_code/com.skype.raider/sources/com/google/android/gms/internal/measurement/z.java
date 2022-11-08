package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.a;
import android.text.TextUtils;
import android.util.Pair;
import com.adjust.sdk.Constants;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class z extends en {
    private static final String[] b = new String[]{"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    private static final String[] c = new String[]{"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] d = new String[]{"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;"};
    private static final String[] e = new String[]{"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] f = new String[]{"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] g = new String[]{"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final ac h = new ac(this, k(), "google_app_measurement.db");
    private final ej i = new ej(j());

    z(eo eoVar) {
        super(eoVar);
    }

    private final boolean P() {
        return k().getDatabasePath("google_app_measurement.db").exists();
    }

    @WorkerThread
    private final long a(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            cursor = x().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            q().v().a("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final Object a(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                q().v().a("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                q().v().a("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                q().v().a("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    private static Set<String> a(SQLiteDatabase sQLiteDatabase, String str) {
        Set<String> hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    @WorkerThread
    private static void a(ContentValues contentValues, String str, Object obj) {
        ab.a(str);
        ab.a(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    static void a(av avVar, SQLiteDatabase sQLiteDatabase) {
        if (avVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            avVar.y().a("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            avVar.y().a("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            avVar.y().a("Failed to turn on database read permission for owner");
        }
        if (!file.setWritable(true, true)) {
            avVar.y().a("Failed to turn on database write permission for owner");
        }
    }

    @WorkerThread
    static void a(av avVar, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        int i = 0;
        if (avVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!a(avVar, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        if (avVar == null) {
            try {
                throw new IllegalArgumentException("Monitor must not be null");
            } catch (SQLiteException e) {
                avVar.v().a("Failed to verify columns on table that was just created", str);
                throw e;
            }
        }
        Iterable a = a(sQLiteDatabase, str);
        String[] split = str3.split(",");
        int length = split.length;
        int i2 = 0;
        while (i2 < length) {
            String str4 = split[i2];
            if (a.remove(str4)) {
                i2++;
            } else {
                throw new SQLiteException(new StringBuilder((String.valueOf(str).length() + 35) + String.valueOf(str4).length()).append("Table ").append(str).append(" is missing required column: ").append(str4).toString());
            }
        }
        if (strArr != null) {
            while (i < strArr.length) {
                if (!a.remove(strArr[i])) {
                    sQLiteDatabase.execSQL(strArr[i + 1]);
                }
                i += 2;
            }
        }
        if (!a.isEmpty()) {
            avVar.y().a("Table has extra columns. table, columns", str, TextUtils.join(", ", a));
        }
    }

    @WorkerThread
    private static boolean a(av avVar, SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query;
        Object e;
        Throwable th;
        Cursor cursor = null;
        if (avVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            try {
                boolean moveToFirst = query.moveToFirst();
                if (query == null) {
                    return moveToFirst;
                }
                query.close();
                return moveToFirst;
            } catch (SQLiteException e2) {
                e = e2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        try {
            avVar.y().a("Error querying for table", str, e);
            if (query != null) {
                query.close();
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            cursor = query;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private final boolean a(String str, int i, fa faVar) {
        N();
        c();
        ab.a(str);
        ab.a((Object) faVar);
        if (TextUtils.isEmpty(faVar.d)) {
            q().y().a("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", av.a(str), Integer.valueOf(i), String.valueOf(faVar.c));
            return false;
        }
        try {
            byte[] bArr = new byte[faVar.d()];
            b a = b.a(bArr, bArr.length);
            faVar.a(a);
            a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", faVar.c);
            contentValues.put("event_name", faVar.d);
            contentValues.put("data", bArr);
            try {
                if (x().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    q().v().a("Failed to insert event filter (got -1). appId", av.a(str));
                }
                return true;
            } catch (SQLiteException e) {
                q().v().a("Error storing event filter. appId", av.a(str), e);
                return false;
            }
        } catch (IOException e2) {
            q().v().a("Configuration loss. Failed to serialize event filter. appId", av.a(str), e2);
            return false;
        }
    }

    @WorkerThread
    private final boolean a(String str, int i, fd fdVar) {
        N();
        c();
        ab.a(str);
        ab.a((Object) fdVar);
        if (TextUtils.isEmpty(fdVar.d)) {
            q().y().a("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", av.a(str), Integer.valueOf(i), String.valueOf(fdVar.c));
            return false;
        }
        try {
            byte[] bArr = new byte[fdVar.d()];
            b a = b.a(bArr, bArr.length);
            fdVar.a(a);
            a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", fdVar.c);
            contentValues.put("property_name", fdVar.d);
            contentValues.put("data", bArr);
            try {
                if (x().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                q().v().a("Failed to insert property filter (got -1). appId", av.a(str));
                return false;
            } catch (SQLiteException e) {
                q().v().a("Error storing property filter. appId", av.a(str), e);
                return false;
            }
        } catch (IOException e2) {
            q().v().a("Configuration loss. Failed to serialize property filter. appId", av.a(str), e2);
            return false;
        }
    }

    private final boolean a(String str, List<Integer> list) {
        ab.a(str);
        N();
        c();
        SQLiteDatabase x = x();
        try {
            if (b("select count(1) from audience_filter_values where app_id=?", new String[]{str}) <= ((long) Math.max(0, Math.min(2000, s().b(str, al.G))))) {
                return false;
            }
            Iterable arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = (Integer) list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            join = new StringBuilder(String.valueOf(join).length() + 2).append("(").append(join).append(")").toString();
            return x.delete("audience_filter_values", new StringBuilder(String.valueOf(join).length() + 140).append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ").append(join).append(" order by rowid desc limit -1 offset ?)").toString(), new String[]{str, Integer.toString(r5)}) > 0;
        } catch (SQLiteException e) {
            q().v().a("Database error querying filters. appId", av.a(str), e);
            return false;
        }
    }

    @WorkerThread
    private final long b(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = x().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            q().v().a("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @WorkerThread
    final void A() {
        c();
        N();
        if (P()) {
            long a = r().f.a();
            long b = j().b();
            if (Math.abs(b - a) > ((Long) al.z.b()).longValue()) {
                r().f.a(b);
                c();
                N();
                if (P()) {
                    int delete = x().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(j().a()), String.valueOf(w.v())});
                    if (delete > 0) {
                        q().C().a("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    @WorkerThread
    public final long B() {
        return a("select max(bundle_end_timestamp) from queue", null, 0);
    }

    @WorkerThread
    public final long C() {
        return a("select max(timestamp) from raw_events", null, 0);
    }

    public final boolean D() {
        return b("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean E() {
        return b("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    public final long F() {
        long j = -1;
        Cursor cursor = null;
        try {
            cursor = x().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException e) {
            q().v().a("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return j;
    }

    public final long a(fm fmVar) throws IOException {
        c();
        N();
        ab.a((Object) fmVar);
        ab.a(fmVar.q);
        try {
            long j;
            Object obj = new byte[fmVar.d()];
            b a = b.a((byte[]) obj, obj.length);
            fmVar.a(a);
            a.a();
            cs n = n();
            ab.a(obj);
            n.c();
            MessageDigest e = ew.e(Constants.MD5);
            if (e == null) {
                n.q().v().a("Failed to get MD5");
                j = 0;
            } else {
                j = ew.c(e.digest(obj));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", fmVar.q);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("metadata", obj);
            try {
                x().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return j;
            } catch (SQLiteException e2) {
                q().v().a("Error storing raw event metadata. appId", av.a(fmVar.q), e2);
                throw e2;
            }
        } catch (IOException e3) {
            q().v().a("Data loss. Failed to serialize event metadata. appId", av.a(fmVar.q), e3);
            throw e3;
        }
    }

    public final Pair<fj, Long> a(String str, Long l) {
        Cursor rawQuery;
        Object e;
        Throwable th;
        Pair<fj, Long> pair = null;
        c();
        N();
        try {
            rawQuery = x().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{str, String.valueOf(l)});
            try {
                if (rawQuery.moveToFirst()) {
                    byte[] blob = rawQuery.getBlob(0);
                    Long valueOf = Long.valueOf(rawQuery.getLong(1));
                    a a = a.a(blob, blob.length);
                    j fjVar = new fj();
                    try {
                        fjVar.a(a);
                        pair = Pair.create(fjVar, valueOf);
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                    } catch (IOException e2) {
                        q().v().a("Failed to merge main event. appId, eventId", av.a(str), l, e2);
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                    }
                } else {
                    q().C().a("Main event not found");
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
            }
        } catch (SQLiteException e4) {
            e = e4;
            rawQuery = pair;
        } catch (Throwable th2) {
            rawQuery = pair;
            th = th2;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return pair;
        try {
            q().v().a("Error selecting main event", e);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return pair;
        } catch (Throwable th3) {
            th = th3;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final aa a(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Object e;
        Throwable th;
        ab.a(str);
        c();
        N();
        String[] strArr = new String[]{str};
        aa aaVar = new aa();
        Cursor query;
        try {
            SQLiteDatabase x = x();
            query = x.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    if (query.getLong(0) == j) {
                        aaVar.b = query.getLong(1);
                        aaVar.a = query.getLong(2);
                        aaVar.c = query.getLong(3);
                        aaVar.d = query.getLong(4);
                        aaVar.e = query.getLong(5);
                    }
                    if (z) {
                        aaVar.b++;
                    }
                    if (z2) {
                        aaVar.a++;
                    }
                    if (z3) {
                        aaVar.c++;
                    }
                    if (z4) {
                        aaVar.d++;
                    }
                    if (z5) {
                        aaVar.e++;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("day", Long.valueOf(j));
                    contentValues.put("daily_public_events_count", Long.valueOf(aaVar.a));
                    contentValues.put("daily_events_count", Long.valueOf(aaVar.b));
                    contentValues.put("daily_conversions_count", Long.valueOf(aaVar.c));
                    contentValues.put("daily_error_events_count", Long.valueOf(aaVar.d));
                    contentValues.put("daily_realtime_events_count", Long.valueOf(aaVar.e));
                    x.update("apps", contentValues, "app_id=?", strArr);
                    if (query != null) {
                        query.close();
                    }
                    return aaVar;
                }
                q().y().a("Not updating daily counts, app is not known. appId", av.a(str));
                if (query != null) {
                    query.close();
                }
                return aaVar;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    q().v().a("Error updating daily counts. appId", av.a(str), e);
                    if (query != null) {
                        query.close();
                    }
                    return aaVar;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final ah a(String str, String str2) {
        Cursor query;
        Object e;
        Cursor cursor;
        Throwable th;
        ab.a(str);
        ab.a(str2);
        c();
        N();
        try {
            query = x().query(EventsEntry.TABLE_NAME, new String[]{"lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    long j = query.getLong(0);
                    long j2 = query.getLong(1);
                    long j3 = query.getLong(2);
                    long j4 = query.isNull(3) ? 0 : query.getLong(3);
                    Long valueOf = query.isNull(4) ? null : Long.valueOf(query.getLong(4));
                    Long valueOf2 = query.isNull(5) ? null : Long.valueOf(query.getLong(5));
                    Boolean bool = null;
                    if (!query.isNull(6)) {
                        bool = Boolean.valueOf(query.getLong(6) == 1);
                    }
                    ah ahVar = new ah(str, str2, j, j2, j3, j4, valueOf, valueOf2, bool);
                    if (query.moveToNext()) {
                        q().v().a("Got multiple records for event aggregates, expected one. appId", av.a(str));
                    }
                    if (query == null) {
                        return ahVar;
                    }
                    query.close();
                    return ahVar;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        try {
            q().v().a("Error querying events. appId", av.a(str), m().a(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            query = cursor;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final String a(long j) {
        Cursor rawQuery;
        Object e;
        Throwable th;
        String str = null;
        c();
        N();
        try {
            rawQuery = x().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(j)});
            try {
                if (rawQuery.moveToFirst()) {
                    str = rawQuery.getString(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else {
                    q().C().a("No expired configs for apps with pending events");
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                }
            } catch (SQLiteException e2) {
                e = e2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            rawQuery = str;
        } catch (Throwable th2) {
            rawQuery = str;
            th = th2;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return str;
        try {
            q().v().a("Error selecting expired configs", e);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List<ev> a(String str) {
        Object e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        ab.a(str);
        c();
        N();
        List<ev> arrayList = new ArrayList();
        try {
            Cursor query = x().query("user_attributes", new String[]{"name", "origin", "set_timestamp", PropertiesEntry.COLUMN_NAME_VALUE}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        String string2 = query.getString(1);
                        if (string2 == null) {
                            string2 = "";
                        }
                        long j = query.getLong(2);
                        Object a = a(query, 3);
                        if (a == null) {
                            q().v().a("Read invalid user property value, ignoring it. appId", av.a(str));
                        } else {
                            arrayList.add(new ev(str, string2, string, j, a));
                        }
                    } while (query.moveToNext());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
                cursor2 = query;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        try {
            q().v().a("Error querying user properties. appId", av.a(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            cursor2 = cursor;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List<Pair<fm, Long>> a(String str, int i, int i2) {
        Cursor query;
        List<Pair<fm, Long>> arrayList;
        Object e;
        Cursor cursor;
        Throwable th;
        boolean z = true;
        c();
        N();
        ab.b(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        ab.b(z);
        ab.a(str);
        try {
            query = x().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
            try {
                if (query.moveToFirst()) {
                    arrayList = new ArrayList();
                    int i3 = 0;
                    while (true) {
                        long j = query.getLong(0);
                        int length;
                        try {
                            byte[] b = n().b(query.getBlob(1));
                            if (!arrayList.isEmpty() && b.length + i3 > i2) {
                                break;
                            }
                            a a = a.a(b, b.length);
                            j fmVar = new fm();
                            try {
                                fmVar.a(a);
                                if (!query.isNull(2)) {
                                    fmVar.J = Integer.valueOf(query.getInt(2));
                                }
                                length = b.length + i3;
                                arrayList.add(Pair.create(fmVar, Long.valueOf(j)));
                            } catch (IOException e2) {
                                q().v().a("Failed to merge queued bundle. appId", av.a(str), e2);
                                length = i3;
                            }
                            if (!query.moveToNext() || length > i2) {
                                break;
                            }
                            i3 = length;
                        } catch (IOException e22) {
                            q().v().a("Failed to unzip queued bundle. appId", av.a(str), e22);
                            length = i3;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } else {
                    arrayList = Collections.emptyList();
                    if (query != null) {
                        query.close();
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return arrayList;
        try {
            q().v().a("Error querying bundles. appId", av.a(str), e);
            arrayList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            query = cursor;
        }
    }

    @WorkerThread
    public final List<ev> a(String str, String str2, String str3) {
        Object e;
        Cursor cursor;
        Object str22;
        Throwable th;
        Cursor cursor2 = null;
        ab.a(str);
        c();
        N();
        List<ev> arrayList = new ArrayList();
        try {
            List arrayList2 = new ArrayList(3);
            arrayList2.add(str);
            StringBuilder stringBuilder = new StringBuilder("app_id=?");
            if (!TextUtils.isEmpty(str22)) {
                arrayList2.add(str22);
                stringBuilder.append(" and origin=?");
            }
            if (!TextUtils.isEmpty(str3)) {
                arrayList2.add(String.valueOf(str3).concat("*"));
                stringBuilder.append(" and name glob ?");
            }
            String[] strArr = new String[]{"name", "set_timestamp", PropertiesEntry.COLUMN_NAME_VALUE, "origin"};
            Cursor query = x().query("user_attributes", strArr, stringBuilder.toString(), (String[]) arrayList2.toArray(new String[arrayList2.size()]), null, null, "rowid", "1001");
            try {
                if (query.moveToFirst()) {
                    while (arrayList.size() < Constants.ONE_SECOND) {
                        String string = query.getString(0);
                        long j = query.getLong(1);
                        Object a = a(query, 2);
                        String string2 = query.getString(3);
                        if (a == null) {
                            try {
                                q().v().a("(2)Read invalid user property value, ignoring it", av.a(str), string2, str3);
                            } catch (SQLiteException e2) {
                                e = e2;
                                cursor = query;
                                str22 = string2;
                            } catch (Throwable th2) {
                                th = th2;
                                cursor2 = query;
                            }
                        } else {
                            arrayList.add(new ev(str, string2, string, j, a));
                        }
                        if (!query.moveToNext()) {
                            break;
                        }
                        str22 = string2;
                    }
                    q().v().a("Read more than the max allowed user properties, ignoring excess", Integer.valueOf(Constants.ONE_SECOND));
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e3) {
                e = e3;
                cursor = query;
            } catch (Throwable th22) {
                th = th22;
                cursor2 = query;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            try {
                q().v().a("(2)Error querying user properties", av.a(str), str22, e);
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final List<zzed> a(String str, String[] strArr) {
        Cursor query;
        Object e;
        Cursor cursor;
        Throwable th;
        c();
        N();
        List<zzed> arrayList = new ArrayList();
        try {
            query = x().query("conditional_properties", new String[]{"app_id", "origin", "name", PropertiesEntry.COLUMN_NAME_VALUE, AppStateModule.APP_STATE_ACTIVE, "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, str, strArr, null, null, "rowid", "1001");
            try {
                if (query.moveToFirst()) {
                    do {
                        if (arrayList.size() >= Constants.ONE_SECOND) {
                            q().v().a("Read more than the max allowed conditional properties, ignoring extra", Integer.valueOf(Constants.ONE_SECOND));
                            break;
                        }
                        String string = query.getString(0);
                        String string2 = query.getString(1);
                        String string3 = query.getString(2);
                        Object a = a(query, 3);
                        boolean z = query.getInt(4) != 0;
                        String string4 = query.getString(5);
                        long j = query.getLong(6);
                        zzeu zzeu = (zzeu) n().a(query.getBlob(7), zzeu.CREATOR);
                        long j2 = query.getLong(8);
                        zzeu zzeu2 = (zzeu) n().a(query.getBlob(9), zzeu.CREATOR);
                        long j3 = query.getLong(10);
                        List<zzed> list = arrayList;
                        list.add(new zzed(string, string2, new zzjx(string3, j3, a, string2), j2, z, string4, zzeu, j, zzeu2, query.getLong(11), (zzeu) n().a(query.getBlob(12), zzeu.CREATOR)));
                    } while (query.moveToNext());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        try {
            q().v().a("Error querying conditional user property value", e);
            List<zzed> emptyList = Collections.emptyList();
            if (cursor == null) {
                return emptyList;
            }
            cursor.close();
            return emptyList;
        } catch (Throwable th4) {
            th = th4;
            query = cursor;
        }
    }

    @WorkerThread
    public final void a(ah ahVar) {
        Long l = null;
        ab.a((Object) ahVar);
        c();
        N();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", ahVar.a);
        contentValues.put("name", ahVar.b);
        contentValues.put("lifetime_count", Long.valueOf(ahVar.c));
        contentValues.put("current_bundle_count", Long.valueOf(ahVar.d));
        contentValues.put("last_fire_timestamp", Long.valueOf(ahVar.e));
        contentValues.put("last_bundled_timestamp", Long.valueOf(ahVar.f));
        contentValues.put("last_sampled_complex_event_id", ahVar.g);
        contentValues.put("last_sampling_rate", ahVar.h);
        if (ahVar.i != null && ahVar.i.booleanValue()) {
            l = Long.valueOf(1);
        }
        contentValues.put("last_exempt_from_sampling", l);
        try {
            if (x().insertWithOnConflict(EventsEntry.TABLE_NAME, null, contentValues, 5) == -1) {
                q().v().a("Failed to insert/update event aggregates (got -1). appId", av.a(ahVar.a));
            }
        } catch (SQLiteException e) {
            q().v().a("Error storing event aggregates. appId", av.a(ahVar.a), e);
        }
    }

    @WorkerThread
    public final void a(r rVar) {
        ab.a((Object) rVar);
        c();
        N();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", rVar.b());
        contentValues.put("app_instance_id", rVar.c());
        contentValues.put("gmp_app_id", rVar.d());
        contentValues.put("resettable_device_id_hash", rVar.e());
        contentValues.put("last_bundle_index", Long.valueOf(rVar.o()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(rVar.g()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(rVar.h()));
        contentValues.put("app_version", rVar.i());
        contentValues.put("app_store", rVar.k());
        contentValues.put("gmp_version", Long.valueOf(rVar.l()));
        contentValues.put("dev_cert_hash", Long.valueOf(rVar.m()));
        contentValues.put("measurement_enabled", Boolean.valueOf(rVar.n()));
        contentValues.put("day", Long.valueOf(rVar.s()));
        contentValues.put("daily_public_events_count", Long.valueOf(rVar.t()));
        contentValues.put("daily_events_count", Long.valueOf(rVar.u()));
        contentValues.put("daily_conversions_count", Long.valueOf(rVar.v()));
        contentValues.put("config_fetched_time", Long.valueOf(rVar.p()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(rVar.q()));
        contentValues.put("app_version_int", Long.valueOf(rVar.j()));
        contentValues.put("firebase_instance_id", rVar.f());
        contentValues.put("daily_error_events_count", Long.valueOf(rVar.x()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(rVar.w()));
        contentValues.put("health_monitor_sample", rVar.y());
        contentValues.put("android_id", Long.valueOf(rVar.A()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(rVar.B()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(rVar.C()));
        try {
            SQLiteDatabase x = x();
            if (((long) x.update("apps", contentValues, "app_id = ?", new String[]{rVar.b()})) == 0 && x.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                q().v().a("Failed to insert/update app (got -1). appId", av.a(rVar.b()));
            }
        } catch (SQLiteException e) {
            q().v().a("Error storing app. appId", av.a(rVar.b()), e);
        }
    }

    @WorkerThread
    final void a(String str, ez[] ezVarArr) {
        int i = 0;
        N();
        c();
        ab.a(str);
        ab.a((Object) ezVarArr);
        SQLiteDatabase x = x();
        x.beginTransaction();
        try {
            int i2;
            N();
            c();
            ab.a(str);
            SQLiteDatabase x2 = x();
            x2.delete("property_filters", "app_id=?", new String[]{str});
            x2.delete("event_filters", "app_id=?", new String[]{str});
            for (Object obj : ezVarArr) {
                N();
                c();
                ab.a(str);
                ab.a(obj);
                ab.a(obj.e);
                ab.a(obj.d);
                if (obj.c == null) {
                    q().y().a("Audience with no ID. appId", av.a(str));
                } else {
                    int intValue = obj.c.intValue();
                    for (fa faVar : obj.e) {
                        if (faVar.c == null) {
                            q().y().a("Event filter with no ID. Audience definition ignored. appId, audienceId", av.a(str), obj.c);
                            break;
                        }
                    }
                    for (fd fdVar : obj.d) {
                        if (fdVar.c == null) {
                            q().y().a("Property filter with no ID. Audience definition ignored. appId, audienceId", av.a(str), obj.c);
                            break;
                        }
                    }
                    for (fa faVar2 : obj.e) {
                        if (!a(str, intValue, faVar2)) {
                            i2 = 0;
                            break;
                        }
                    }
                    i2 = 1;
                    if (i2 != 0) {
                        for (fd fdVar2 : obj.d) {
                            if (!a(str, intValue, fdVar2)) {
                                i2 = 0;
                                break;
                            }
                        }
                    }
                    if (i2 == 0) {
                        N();
                        c();
                        ab.a(str);
                        SQLiteDatabase x3 = x();
                        x3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                        x3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                    }
                }
            }
            List arrayList = new ArrayList();
            i2 = ezVarArr.length;
            while (i < i2) {
                arrayList.add(ezVarArr[i].c);
                i++;
            }
            a(str, arrayList);
            x.setTransactionSuccessful();
        } finally {
            x.endTransaction();
        }
    }

    @WorkerThread
    @VisibleForTesting
    final void a(List<Long> list) {
        c();
        N();
        ab.a((Object) list);
        if (list.size() == 0) {
            throw new IllegalArgumentException("Given Integer is zero");
        } else if (P()) {
            String join = TextUtils.join(",", list);
            join = new StringBuilder(String.valueOf(join).length() + 2).append("(").append(join).append(")").toString();
            if (b(new StringBuilder(String.valueOf(join).length() + 80).append("SELECT COUNT(1) FROM queue WHERE rowid IN ").append(join).append(" AND retry_count =  2147483647 LIMIT 1").toString(), null) > 0) {
                q().y().a("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                x().execSQL(new StringBuilder(String.valueOf(join).length() + 127).append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ").append(join).append(" AND (retry_count IS NULL OR retry_count < 2147483647)").toString());
            } catch (SQLiteException e) {
                q().v().a("Error incrementing retry count. error", e);
            }
        }
    }

    public final boolean a(ag agVar, long j, boolean z) {
        c();
        N();
        ab.a((Object) agVar);
        ab.a(agVar.a);
        j fjVar = new fj();
        fjVar.f = Long.valueOf(agVar.d);
        fjVar.c = new fk[agVar.e.a()];
        Iterator it = agVar.e.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            fk fkVar = new fk();
            int i2 = i + 1;
            fjVar.c[i] = fkVar;
            fkVar.c = str;
            n().a(fkVar, agVar.e.a(str));
            i = i2;
        }
        try {
            byte[] bArr = new byte[fjVar.d()];
            b a = b.a(bArr, bArr.length);
            fjVar.a(a);
            a.a();
            q().C().a("Saving event, name, data size", m().a(agVar.b), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", agVar.a);
            contentValues.put("name", agVar.b);
            contentValues.put("timestamp", Long.valueOf(agVar.c));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (x().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                q().v().a("Failed to insert raw event (got -1). appId", av.a(agVar.a));
                return false;
            } catch (SQLiteException e) {
                q().v().a("Error storing raw event. appId", av.a(agVar.a), e);
                return false;
            }
        } catch (IOException e2) {
            q().v().a("Data loss. Failed to serialize event params/data. appId", av.a(agVar.a), e2);
            return false;
        }
    }

    @WorkerThread
    public final boolean a(ev evVar) {
        ab.a((Object) evVar);
        c();
        N();
        if (c(evVar.a, evVar.c) == null) {
            if (ew.a(evVar.c)) {
                if (b("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{evVar.a}) >= 25) {
                    return false;
                }
            }
            if (b("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{evVar.a, evVar.b}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", evVar.a);
        contentValues.put("origin", evVar.b);
        contentValues.put("name", evVar.c);
        contentValues.put("set_timestamp", Long.valueOf(evVar.d));
        a(contentValues, PropertiesEntry.COLUMN_NAME_VALUE, evVar.e);
        try {
            if (x().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                q().v().a("Failed to insert/update user property (got -1). appId", av.a(evVar.a));
            }
        } catch (SQLiteException e) {
            q().v().a("Error storing user property. appId", av.a(evVar.a), e);
        }
        return true;
    }

    @WorkerThread
    public final boolean a(fm fmVar, boolean z) {
        c();
        N();
        ab.a((Object) fmVar);
        ab.a(fmVar.q);
        ab.a(fmVar.h);
        A();
        long a = j().a();
        if (fmVar.h.longValue() < a - w.v() || fmVar.h.longValue() > w.v() + a) {
            q().y().a("Storing bundle outside of the max uploading time span. appId, now, timestamp", av.a(fmVar.q), Long.valueOf(a), fmVar.h);
        }
        try {
            byte[] bArr = new byte[fmVar.d()];
            b a2 = b.a(bArr, bArr.length);
            fmVar.a(a2);
            a2.a();
            bArr = n().a(bArr);
            q().C().a("Saving bundle, size", Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", fmVar.q);
            contentValues.put("bundle_end_timestamp", fmVar.h);
            contentValues.put("data", bArr);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (fmVar.J != null) {
                contentValues.put("retry_count", fmVar.J);
            }
            try {
                if (x().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                q().v().a("Failed to insert bundle (got -1). appId", av.a(fmVar.q));
                return false;
            } catch (SQLiteException e) {
                q().v().a("Error storing bundle. appId", av.a(fmVar.q), e);
                return false;
            }
        } catch (IOException e2) {
            q().v().a("Data loss. Failed to serialize bundle. appId", av.a(fmVar.q), e2);
            return false;
        }
    }

    @WorkerThread
    public final boolean a(zzed zzed) {
        ab.a((Object) zzed);
        c();
        N();
        if (c(zzed.a, zzed.c.a) == null) {
            if (b("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzed.a}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzed.a);
        contentValues.put("origin", zzed.b);
        contentValues.put("name", zzed.c.a);
        a(contentValues, PropertiesEntry.COLUMN_NAME_VALUE, zzed.c.a());
        contentValues.put(AppStateModule.APP_STATE_ACTIVE, Boolean.valueOf(zzed.e));
        contentValues.put("trigger_event_name", zzed.f);
        contentValues.put("trigger_timeout", Long.valueOf(zzed.h));
        n();
        contentValues.put("timed_out_event", ew.a(zzed.g));
        contentValues.put("creation_timestamp", Long.valueOf(zzed.d));
        n();
        contentValues.put("triggered_event", ew.a(zzed.i));
        contentValues.put("triggered_timestamp", Long.valueOf(zzed.c.b));
        contentValues.put("time_to_live", Long.valueOf(zzed.j));
        n();
        contentValues.put("expired_event", ew.a(zzed.k));
        try {
            if (x().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                q().v().a("Failed to insert/update conditional user property (got -1)", av.a(zzed.a));
            }
        } catch (SQLiteException e) {
            q().v().a("Error storing conditional user property", av.a(zzed.a), e);
        }
        return true;
    }

    public final boolean a(String str, Long l, long j, fj fjVar) {
        c();
        N();
        ab.a((Object) fjVar);
        ab.a(str);
        ab.a((Object) l);
        try {
            byte[] bArr = new byte[fjVar.d()];
            b a = b.a(bArr, bArr.length);
            fjVar.a(a);
            a.a();
            q().C().a("Saving complex main event, appId, data size", m().a(str), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("event_id", l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (x().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                    return true;
                }
                q().v().a("Failed to insert complex main event (got -1). appId", av.a(str));
                return false;
            } catch (SQLiteException e) {
                q().v().a("Error storing complex main event. appId", av.a(str), e);
                return false;
            }
        } catch (IOException e2) {
            q().v().a("Data loss. Failed to serialize event params/data. appId, eventId", av.a(str), l, e2);
            return false;
        }
    }

    @WorkerThread
    public final r b(String str) {
        Cursor query;
        Object e;
        Throwable th;
        ab.a(str);
        c();
        N();
        try {
            query = x().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "ssaid_reporting_enabled"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    r rVar = new r(this.a.N(), str);
                    rVar.a(query.getString(0));
                    rVar.b(query.getString(1));
                    rVar.c(query.getString(2));
                    rVar.f(query.getLong(3));
                    rVar.a(query.getLong(4));
                    rVar.b(query.getLong(5));
                    rVar.e(query.getString(6));
                    rVar.f(query.getString(7));
                    rVar.d(query.getLong(8));
                    rVar.e(query.getLong(9));
                    boolean z = query.isNull(10) || query.getInt(10) != 0;
                    rVar.a(z);
                    rVar.i(query.getLong(11));
                    rVar.j(query.getLong(12));
                    rVar.k(query.getLong(13));
                    rVar.l(query.getLong(14));
                    rVar.g(query.getLong(15));
                    rVar.h(query.getLong(16));
                    rVar.c(query.isNull(17) ? -2147483648L : (long) query.getInt(17));
                    rVar.d(query.getString(18));
                    rVar.n(query.getLong(19));
                    rVar.m(query.getLong(20));
                    rVar.g(query.getString(21));
                    rVar.o(query.isNull(22) ? 0 : query.getLong(22));
                    z = query.isNull(23) || query.getInt(23) != 0;
                    rVar.b(z);
                    z = query.isNull(24) || query.getInt(24) != 0;
                    rVar.c(z);
                    rVar.a();
                    if (query.moveToNext()) {
                        q().v().a("Got multiple records for app, expected one. appId", av.a(str));
                    }
                    if (query == null) {
                        return rVar;
                    }
                    query.close();
                    return rVar;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
        } catch (Throwable th2) {
            th = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        try {
            q().v().a("Error querying app. appId", av.a(str), e);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List<zzed> b(String str, String str2, String str3) {
        ab.a(str);
        c();
        N();
        List arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder stringBuilder = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            stringBuilder.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            stringBuilder.append(" and name glob ?");
        }
        return a(stringBuilder.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    @WorkerThread
    public final void b(String str, String str2) {
        ab.a(str);
        ab.a(str2);
        c();
        N();
        try {
            q().C().a("Deleted user attribute rows", Integer.valueOf(x().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            q().v().a("Error deleting user attribute. appId", av.a(str), m().c(str2), e);
        }
    }

    public final long c(String str) {
        ab.a(str);
        c();
        N();
        try {
            SQLiteDatabase x = x();
            String valueOf = String.valueOf(Math.max(0, Math.min(1000000, s().b(str, al.q))));
            return (long) x.delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, valueOf});
        } catch (SQLiteException e) {
            q().v().a("Error deleting over the limit events. appId", av.a(str), e);
            return 0;
        }
    }

    @WorkerThread
    public final ev c(String str, String str2) {
        Object e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        ab.a(str);
        ab.a(str2);
        c();
        N();
        try {
            Cursor query = x().query("user_attributes", new String[]{"set_timestamp", PropertiesEntry.COLUMN_NAME_VALUE, "origin"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    String str3 = str;
                    ev evVar = new ev(str3, query.getString(2), str2, query.getLong(0), a(query, 1));
                    if (query.moveToNext()) {
                        q().v().a("Got multiple records for user property, expected one. appId", av.a(str));
                    }
                    if (query == null) {
                        return evVar;
                    }
                    query.close();
                    return evVar;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        try {
            q().v().a("Error querying user property. appId", av.a(str), m().c(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            cursor2 = cursor;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final zzed d(String str, String str2) {
        Cursor query;
        Object e;
        Cursor cursor;
        Throwable th;
        ab.a(str);
        ab.a(str2);
        c();
        N();
        try {
            query = x().query("conditional_properties", new String[]{"origin", PropertiesEntry.COLUMN_NAME_VALUE, AppStateModule.APP_STATE_ACTIVE, "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    Object a = a(query, 1);
                    boolean z = query.getInt(2) != 0;
                    String string2 = query.getString(3);
                    long j = query.getLong(4);
                    zzeu zzeu = (zzeu) n().a(query.getBlob(5), zzeu.CREATOR);
                    long j2 = query.getLong(6);
                    zzeu zzeu2 = (zzeu) n().a(query.getBlob(7), zzeu.CREATOR);
                    long j3 = query.getLong(8);
                    zzed zzed = new zzed(str, string, new zzjx(str2, j3, a, string), j2, z, string2, zzeu, j, zzeu2, query.getLong(9), (zzeu) n().a(query.getBlob(10), zzeu.CREATOR));
                    if (query.moveToNext()) {
                        q().v().a("Got multiple records for conditional property, expected one", av.a(str), m().c(str2));
                    }
                    if (query == null) {
                        return zzed;
                    }
                    query.close();
                    return zzed;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        try {
            q().v().a("Error querying conditional property", av.a(str), m().c(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            query = cursor;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final byte[] d(String str) {
        Cursor query;
        Object e;
        Throwable th;
        ab.a(str);
        c();
        N();
        try {
            query = x().query("apps", new String[]{"remote_config"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    byte[] blob = query.getBlob(0);
                    if (query.moveToNext()) {
                        q().v().a("Got multiple records for app config, expected one. appId", av.a(str));
                    }
                    if (query == null) {
                        return blob;
                    }
                    query.close();
                    return blob;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
        } catch (Throwable th2) {
            th = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        try {
            q().v().a("Error querying remote config. appId", av.a(str), e);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final int e(String str, String str2) {
        int i = 0;
        ab.a(str);
        ab.a(str2);
        c();
        N();
        try {
            return x().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            q().v().a("Error deleting conditional property", av.a(str), m().c(str2), e);
            return i;
        }
    }

    final Map<Integer, fn> e(String str) {
        Throwable th;
        N();
        c();
        ab.a(str);
        Cursor query;
        Object e;
        try {
            query = x().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, null, null, null);
            if (query.moveToFirst()) {
                Map<Integer, fn> aVar = new a();
                do {
                    int i = query.getInt(0);
                    byte[] blob = query.getBlob(1);
                    a a = a.a(blob, blob.length);
                    j fnVar = new fn();
                    try {
                        fnVar.a(a);
                        try {
                            aVar.put(Integer.valueOf(i), fnVar);
                        } catch (SQLiteException e2) {
                            e = e2;
                        }
                    } catch (IOException e3) {
                        q().v().a("Failed to merge filter results. appId, audienceId, error", av.a(str), Integer.valueOf(i), e3);
                    }
                } while (query.moveToNext());
                if (query == null) {
                    return aVar;
                }
                query.close();
                return aVar;
            }
            if (query != null) {
                query.close();
            }
            return null;
            try {
                q().v().a("Database error querying filter results. appId", av.a(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
            query = null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final long f(String str) {
        ab.a(str);
        return a("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    final Map<Integer, List<fa>> f(String str, String str2) {
        Throwable th;
        N();
        c();
        ab.a(str);
        ab.a(str2);
        Map<Integer, List<fa>> aVar = new a();
        Cursor query;
        Object e;
        try {
            query = x().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, null, null, null);
            if (query.moveToFirst()) {
                do {
                    byte[] blob = query.getBlob(1);
                    a a = a.a(blob, blob.length);
                    j faVar = new fa();
                    try {
                        faVar.a(a);
                        int i = query.getInt(0);
                        List list = (List) aVar.get(Integer.valueOf(i));
                        if (list == null) {
                            list = new ArrayList();
                            aVar.put(Integer.valueOf(i), list);
                        }
                        list.add(faVar);
                    } catch (IOException e2) {
                        try {
                            q().v().a("Failed to merge filter. appId", av.a(str), e2);
                        } catch (SQLiteException e3) {
                            e = e3;
                        }
                    }
                } while (query.moveToNext());
                if (query != null) {
                    query.close();
                }
                return aVar;
            }
            Map<Integer, List<fa>> emptyMap = Collections.emptyMap();
            if (query == null) {
                return emptyMap;
            }
            query.close();
            return emptyMap;
            try {
                q().v().a("Database error querying filters. appId", av.a(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
            query = null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    final Map<Integer, List<fd>> g(String str, String str2) {
        Throwable th;
        N();
        c();
        ab.a(str);
        ab.a(str2);
        Map<Integer, List<fd>> aVar = new a();
        Cursor query;
        Object e;
        try {
            query = x().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, null, null, null);
            if (query.moveToFirst()) {
                do {
                    byte[] blob = query.getBlob(1);
                    a a = a.a(blob, blob.length);
                    j fdVar = new fd();
                    try {
                        fdVar.a(a);
                        int i = query.getInt(0);
                        List list = (List) aVar.get(Integer.valueOf(i));
                        if (list == null) {
                            list = new ArrayList();
                            aVar.put(Integer.valueOf(i), list);
                        }
                        list.add(fdVar);
                    } catch (IOException e2) {
                        try {
                            q().v().a("Failed to merge filter", av.a(str), e2);
                        } catch (SQLiteException e3) {
                            e = e3;
                        }
                    }
                } while (query.moveToNext());
                if (query != null) {
                    query.close();
                }
                return aVar;
            }
            Map<Integer, List<fd>> emptyMap = Collections.emptyMap();
            if (query == null) {
                return emptyMap;
            }
            query.close();
            return emptyMap;
            try {
                q().v().a("Database error querying filters. appId", av.a(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
            query = null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    @VisibleForTesting
    protected final long h(String str, String str2) {
        Object e;
        ab.a(str);
        ab.a(str2);
        c();
        N();
        SQLiteDatabase x = x();
        x.beginTransaction();
        long a;
        try {
            a = a(new StringBuilder(String.valueOf(str2).length() + 32).append("select ").append(str2).append(" from app2 where app_id=?").toString(), new String[]{str}, -1);
            if (a == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", Integer.valueOf(0));
                contentValues.put("previous_install_count", Integer.valueOf(0));
                if (x.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                    q().v().a("Failed to insert column (got -1). appId", av.a(str), str2);
                    x.endTransaction();
                    return -1;
                }
                a = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + a));
                if (((long) x.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    q().v().a("Failed to update column (got 0). appId", av.a(str), str2);
                    x.endTransaction();
                    return -1;
                }
                x.setTransactionSuccessful();
                x.endTransaction();
                return a;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    q().v().a("Error inserting column. appId", av.a(str), str2, e);
                    return a;
                } finally {
                    x.endTransaction();
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            a = 0;
            q().v().a("Error inserting column. appId", av.a(str), str2, e);
            return a;
        }
    }

    protected final boolean t() {
        return false;
    }

    @WorkerThread
    public final void u() {
        N();
        x().beginTransaction();
    }

    @WorkerThread
    public final void v() {
        N();
        x().setTransactionSuccessful();
    }

    @WorkerThread
    public final void w() {
        N();
        x().endTransaction();
    }

    @WorkerThread
    @VisibleForTesting
    final SQLiteDatabase x() {
        c();
        try {
            return this.h.getWritableDatabase();
        } catch (SQLiteException e) {
            q().y().a("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public final String y() {
        Object e;
        Throwable th;
        String str = null;
        Cursor rawQuery;
        try {
            rawQuery = x().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
            try {
                if (rawQuery.moveToFirst()) {
                    str = rawQuery.getString(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    q().v().a("Database error getting next bundle app id", e);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            rawQuery = null;
        } catch (Throwable th3) {
            rawQuery = null;
            th = th3;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return str;
    }

    public final boolean z() {
        return b("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }
}
