package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.ab;

public final class bh {
    private final String a;
    private final long b;
    private boolean c;
    private long d;
    private final /* synthetic */ bf e;

    public bh(bf bfVar, String str, long j) {
        this.e = bfVar;
        ab.a(str);
        this.a = str;
        this.b = j;
    }

    @WorkerThread
    public final long a() {
        if (!this.c) {
            this.c = true;
            this.d = this.e.B().getLong(this.a, this.b);
        }
        return this.d;
    }

    @WorkerThread
    public final void a(long j) {
        Editor edit = this.e.B().edit();
        edit.putLong(this.a, j);
        edit.apply();
        this.d = j;
    }
}
