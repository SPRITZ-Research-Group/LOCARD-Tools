package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.ab;

public final class bj {
    private final String a;
    private final String b = null;
    private boolean c;
    private String d;
    private final /* synthetic */ bf e;

    public bj(bf bfVar, String str) {
        this.e = bfVar;
        ab.a(str);
        this.a = str;
    }

    @WorkerThread
    public final String a() {
        if (!this.c) {
            this.c = true;
            this.d = this.e.B().getString(this.a, null);
        }
        return this.d;
    }

    @WorkerThread
    public final void a(String str) {
        if (!ew.b(str, this.d)) {
            Editor edit = this.e.B().edit();
            edit.putString(this.a, str);
            edit.apply();
            this.d = str;
        }
    }
}
