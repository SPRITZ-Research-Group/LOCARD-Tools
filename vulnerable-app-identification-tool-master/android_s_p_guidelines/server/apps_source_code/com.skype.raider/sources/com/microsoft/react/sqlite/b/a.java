package com.microsoft.react.sqlite.b;

import android.os.SystemClock;
import com.facebook.common.logging.FLog;
import com.microsoft.react.sqlite.SQLiteStorageModule;
import java.util.ArrayList;
import java.util.List;

public final class a {
    private final String a;
    private final long b = 100;
    private final List<String> c = new ArrayList();
    private final List<Long> d = new ArrayList();
    private long e;

    public a(String message) {
        this.a = message;
    }

    public final void a() {
        this.e = SystemClock.uptimeMillis();
    }

    public final void a(String name) {
        a(name, false);
    }

    public final void a(String name, boolean last) {
        this.c.add(name);
        this.d.add(Long.valueOf(SystemClock.uptimeMillis()));
        if (last) {
            long uptimeMillis = SystemClock.uptimeMillis() - this.e;
            if (uptimeMillis > this.b) {
                FLog.w(SQLiteStorageModule.TAG, this.a + " took " + uptimeMillis + "ms");
                long j = this.e;
                int i = 0;
                while (i < this.c.size()) {
                    String str = (String) this.c.get(i);
                    long longValue = ((Long) this.d.get(i)).longValue();
                    FLog.w(SQLiteStorageModule.TAG, str + "took: " + (longValue - j) + "ms");
                    i++;
                    j = longValue;
                }
            }
        }
    }
}
