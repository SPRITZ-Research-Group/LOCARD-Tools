package com.microsoft.react.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.facebook.common.logging.FLog;
import com.microsoft.b.a.a;
import com.microsoft.b.a.b;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public final class d {
    private final Object a = new Object();
    private final Context b;
    private final b c;
    private final b d;
    private final Set<a> e;
    private final a[] f;
    private int g;
    private boolean h;

    public d(Context context, b connectionProvider, b options) {
        this.b = context;
        this.c = connectionProvider;
        this.d = options;
        this.e = new HashSet();
        this.g = 0;
        this.f = new a[options.b()];
    }

    public final a a() {
        a result;
        synchronized (this.a) {
            result = null;
            if (this.h) {
                FLog.i(SQLiteStorageModule.TAG, "Can't acquire connection. ConnectionPool is closed");
            } else if (this.g > 0) {
                int lastPooledIndex = this.g - 1;
                result = this.f[lastPooledIndex];
                this.f[lastPooledIndex] = null;
                this.g--;
            } else {
                File dbfile = this.b.getDatabasePath(this.d.a());
                if (!dbfile.exists()) {
                    dbfile.getParentFile().mkdirs();
                }
                try {
                    result = this.c.a(dbfile.getAbsolutePath());
                } catch (Throwable e) {
                    FLog.w(SQLiteStorageModule.TAG, "Can't open database. Wiping it out", e);
                    SQLiteDatabase.deleteDatabase(dbfile);
                    result = this.c.a(dbfile.getAbsolutePath());
                }
                this.e.add(result);
            }
        }
        return result;
    }

    public final boolean a(a instance) {
        synchronized (this.a) {
            if (!this.e.contains(instance)) {
                throw new IllegalStateException("Can't release instance not allocated by this pool");
            } else if (b(instance)) {
                throw new IllegalStateException("Already in the pool!");
            } else if (this.g < this.f.length) {
                this.f[this.g] = instance;
                this.g++;
                return true;
            } else {
                return false;
            }
        }
    }

    public final void b() {
        synchronized (this.a) {
            this.h = true;
            for (a connection : this.e) {
                try {
                    connection.close();
                } catch (Throwable e) {
                    FLog.e(SQLiteStorageModule.TAG, "database close failed.", e);
                }
            }
        }
    }

    private boolean b(a instance) {
        for (int i = 0; i < this.g; i++) {
            if (this.f[i] == instance) {
                return true;
            }
        }
        return false;
    }
}
