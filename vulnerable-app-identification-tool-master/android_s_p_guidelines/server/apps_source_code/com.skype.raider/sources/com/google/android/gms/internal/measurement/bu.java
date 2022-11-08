package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.ab;
import java.lang.Thread.UncaughtExceptionHandler;

final class bu implements UncaughtExceptionHandler {
    private final String a;
    private final /* synthetic */ bs b;

    public bu(bs bsVar, String str) {
        this.b = bsVar;
        ab.a((Object) str);
        this.a = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.b.q().v().a(this.a, th);
    }
}
