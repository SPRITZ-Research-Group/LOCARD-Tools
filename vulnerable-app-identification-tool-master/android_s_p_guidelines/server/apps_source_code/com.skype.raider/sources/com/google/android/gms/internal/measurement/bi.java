package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import android.util.Pair;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;

public final class bi {
    @VisibleForTesting
    private final String a;
    private final String b;
    private final String c;
    private final long d;
    private final /* synthetic */ bf e;

    private bi(bf bfVar, String str, long j) {
        this.e = bfVar;
        ab.a(str);
        ab.b(j > 0);
        this.a = String.valueOf(str).concat(":start");
        this.b = String.valueOf(str).concat(":count");
        this.c = String.valueOf(str).concat(":value");
        this.d = j;
    }

    /* synthetic */ bi(bf bfVar, String str, long j, byte b) {
        this(bfVar, str, j);
    }

    @WorkerThread
    private final void b() {
        this.e.c();
        long a = this.e.j().a();
        Editor edit = this.e.B().edit();
        edit.remove(this.b);
        edit.remove(this.c);
        edit.putLong(this.a, a);
        edit.apply();
    }

    @WorkerThread
    private final long c() {
        return this.e.B().getLong(this.a, 0);
    }

    @WorkerThread
    public final Pair<String, Long> a() {
        this.e.c();
        this.e.c();
        long c = c();
        if (c == 0) {
            b();
            c = 0;
        } else {
            c = Math.abs(c - this.e.j().a());
        }
        if (c < this.d) {
            return null;
        }
        if (c > (this.d << 1)) {
            b();
            return null;
        }
        String string = this.e.B().getString(this.c, null);
        long j = this.e.B().getLong(this.b, 0);
        b();
        return (string == null || j <= 0) ? bf.a : new Pair(string, Long.valueOf(j));
    }

    @WorkerThread
    public final void a(String str) {
        this.e.c();
        if (c() == 0) {
            b();
        }
        if (str == null) {
            str = "";
        }
        long j = this.e.B().getLong(this.b, 0);
        if (j <= 0) {
            Editor edit = this.e.B().edit();
            edit.putString(this.c, str);
            edit.putLong(this.b, 1);
            edit.apply();
            return;
        }
        Object obj = (this.e.n().w().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / (j + 1) ? 1 : null;
        Editor edit2 = this.e.B().edit();
        if (obj != null) {
            edit2.putString(this.c, str);
        }
        edit2.putLong(this.b, j + 1);
        edit2.apply();
    }
}
