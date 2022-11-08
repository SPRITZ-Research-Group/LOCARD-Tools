package com.google.android.gms.internal.measurement;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.ab;
import java.util.List;
import java.util.Map;

@WorkerThread
final class bb implements Runnable {
    private final ba a;
    private final int b;
    private final Throwable c;
    private final byte[] d;
    private final String e;
    private final Map<String, List<String>> f;

    private bb(String str, ba baVar, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        ab.a((Object) baVar);
        this.a = baVar;
        this.b = i;
        this.c = th;
        this.d = bArr;
        this.e = str;
        this.f = map;
    }

    /* synthetic */ bb(String str, ba baVar, int i, Throwable th, byte[] bArr, Map map, byte b) {
        this(str, baVar, i, th, bArr, map);
    }

    public final void run() {
        this.a.a(this.e, this.b, this.c, this.d, this.f);
    }
}
