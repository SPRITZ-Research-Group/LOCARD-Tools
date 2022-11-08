package com.facebook.ads.internal.e;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.WorkerThread;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public final class c extends g {
    public static final b a = new b(0, "event_id", "TEXT PRIMARY KEY");
    public static final b b = new b(1, "token_id", "TEXT REFERENCES tokens ON UPDATE CASCADE ON DELETE RESTRICT");
    public static final b c = new b(2, EventsEntry.COLUMN_NAME_PRIORITY, "INTEGER");
    public static final b d = new b(3, "type", "TEXT");
    public static final b e = new b(4, "time", "REAL");
    public static final b f = new b(5, "session_time", "REAL");
    public static final b g = new b(6, "session_id", "TEXT");
    public static final b h = new b(7, "data", "TEXT");
    public static final b i = new b(8, "attempt", "INTEGER");
    public static final b[] j = new b[]{a, b, c, d, e, f, g, h, i};
    private static final String l = g.a(EventsEntry.TABLE_NAME, j);

    public c(d dVar) {
        super(dVar);
    }

    public final String a() {
        return EventsEntry.TABLE_NAME;
    }

    @WorkerThread
    final String a(String str, int i, String str2, double d, double d2, String str3, Map<String, String> map) {
        String uuid = UUID.randomUUID().toString();
        ContentValues contentValues = new ContentValues(9);
        contentValues.put(a.b, uuid);
        contentValues.put(b.b, str);
        contentValues.put(c.b, Integer.valueOf(i));
        contentValues.put(d.b, str2);
        contentValues.put(e.b, Double.valueOf(d));
        contentValues.put(f.b, Double.valueOf(d2));
        contentValues.put(g.b, str3);
        contentValues.put(h.b, map != null ? new JSONObject(map).toString() : null);
        contentValues.put(i.b, Integer.valueOf(0));
        e().insertOrThrow(EventsEntry.TABLE_NAME, null, contentValues);
        return uuid;
    }

    final boolean a(String str) {
        return e().delete(EventsEntry.TABLE_NAME, new StringBuilder().append(a.b).append(" = ?").toString(), new String[]{str}) > 0;
    }

    public final b[] b() {
        return j;
    }

    @WorkerThread
    final Cursor c() {
        return e().rawQuery("SELECT count(*) FROM events", null);
    }

    @WorkerThread
    final Cursor d() {
        return e().rawQuery(l, null);
    }
}
