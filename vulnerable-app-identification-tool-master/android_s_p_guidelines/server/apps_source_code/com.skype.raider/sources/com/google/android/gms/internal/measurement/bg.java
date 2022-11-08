package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.ab;

public final class bg {
    private final String a;
    private final boolean b = true;
    private boolean c;
    private boolean d;
    private final /* synthetic */ bf e;

    public bg(bf bfVar, String str) {
        this.e = bfVar;
        ab.a(str);
        this.a = str;
    }

    @WorkerThread
    public final void a(boolean z) {
        Editor edit = this.e.B().edit();
        edit.putBoolean(this.a, z);
        edit.apply();
        this.d = z;
    }

    @WorkerThread
    public final boolean a() {
        if (!this.c) {
            this.c = true;
            this.d = this.e.B().getBoolean(this.a, this.b);
        }
        return this.d;
    }
}
