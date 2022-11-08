package com.facebook.ads.internal.e;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import java.util.UUID;

public class h extends g {
    public static final b a = new b(0, "token_id", "TEXT PRIMARY KEY");
    public static final b b = new b(1, "token", "TEXT");
    public static final b[] c = new b[]{a, b};
    private static final String d = h.class.getSimpleName();
    private static final String e = g.a("tokens", c);
    private static final String f;
    private static final String g = ("DELETE FROM tokens WHERE NOT EXISTS (SELECT 1 FROM events WHERE tokens." + a.b + " = events." + c.b.b + ")");

    static {
        b[] bVarArr = c;
        b bVar = b;
        StringBuilder stringBuilder = new StringBuilder(g.a("tokens", bVarArr));
        stringBuilder.append(" WHERE ");
        stringBuilder.append(bVar.b);
        stringBuilder.append(" = ?");
        f = stringBuilder.toString();
    }

    public h(d dVar) {
        super(dVar);
    }

    public final String a() {
        return "tokens";
    }

    @WorkerThread
    final String a(String str) {
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Invalid token.");
        }
        Cursor rawQuery;
        try {
            rawQuery = e().rawQuery(f, new String[]{str});
            try {
                String string = rawQuery.moveToNext() ? rawQuery.getString(a.a) : null;
                if (TextUtils.isEmpty(string)) {
                    string = UUID.randomUUID().toString();
                    ContentValues contentValues = new ContentValues(2);
                    contentValues.put(a.b, string);
                    contentValues.put(b.b, str);
                    e().insertOrThrow("tokens", null, contentValues);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else if (rawQuery != null) {
                    rawQuery.close();
                }
                return string;
            } catch (Throwable th2) {
                th = th2;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
        }
    }

    public final b[] b() {
        return c;
    }

    @WorkerThread
    final Cursor c() {
        return e().rawQuery(e, null);
    }

    @WorkerThread
    public final void d() {
        try {
            e().execSQL(g);
        } catch (SQLException e) {
        }
    }
}
