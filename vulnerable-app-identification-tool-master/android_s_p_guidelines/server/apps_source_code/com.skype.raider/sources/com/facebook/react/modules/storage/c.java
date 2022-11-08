package com.facebook.react.modules.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.facebook.common.logging.FLog;
import javax.annotation.Nullable;

public final class c extends SQLiteOpenHelper {
    @Nullable
    private static c a;
    private Context b;
    @Nullable
    private SQLiteDatabase c;
    private long d = 6291456;

    private c(Context context) {
        super(context, "RKStorage", null, 1);
        this.b = context;
    }

    public static c a(Context context) {
        if (a == null) {
            a = new c(context.getApplicationContext());
        }
        return a;
    }

    public final void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE catalystLocalStorage (key TEXT PRIMARY KEY, value TEXT NOT NULL)");
    }

    public final void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            e();
            onCreate(db);
        }
    }

    final synchronized boolean a() {
        if (this.c == null || !this.c.isOpen()) {
            SQLiteException lastSQLiteException = null;
            int tries = 0;
            while (tries < 2) {
                if (tries > 0) {
                    try {
                        e();
                    } catch (SQLiteException e) {
                        lastSQLiteException = e;
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e2) {
                            Thread.currentThread().interrupt();
                        }
                        tries++;
                    }
                }
                this.c = getWritableDatabase();
            }
            if (this.c == null) {
                throw lastSQLiteException;
            }
            this.c.setMaximumSize(this.d);
        }
        return true;
    }

    public final synchronized SQLiteDatabase b() {
        a();
        return this.c;
    }

    public final synchronized void c() throws RuntimeException {
        try {
            d();
            f();
            FLog.d("React", "Cleaned RKStorage");
        } catch (Exception e) {
            if (e()) {
                FLog.d("React", "Deleted Local Database RKStorage");
            } else {
                throw new RuntimeException("Clearing and deleting database RKStorage failed");
            }
        }
    }

    final synchronized void d() {
        b().delete("catalystLocalStorage", null, null);
    }

    private synchronized boolean e() {
        f();
        return this.b.deleteDatabase("RKStorage");
    }

    private synchronized void f() {
        if (this.c != null && this.c.isOpen()) {
            this.c.close();
            this.c = null;
        }
    }
}
