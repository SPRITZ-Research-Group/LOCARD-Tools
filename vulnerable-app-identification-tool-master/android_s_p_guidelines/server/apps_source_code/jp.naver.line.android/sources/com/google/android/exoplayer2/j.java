package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Looper;
import com.google.android.exoplayer2.trackselection.m;
import defpackage.ark;
import defpackage.bbi;
import defpackage.bbv;
import defpackage.beg;

public final class j {
    private static bbi a;

    @Deprecated
    public static al a(Context context, m mVar, u uVar) {
        aj gVar = new g(context);
        Looper a = beg.a();
        ark ark = new ark();
        return new al(context, gVar, mVar, uVar, a(), a);
    }

    private static synchronized bbi a() {
        bbi bbi;
        synchronized (j.class) {
            if (a == null) {
                a = new bbv().a();
            }
            bbi = a;
        }
        return bbi;
    }
}
