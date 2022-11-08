package com.microsoft.tokenshare;

import android.util.Log;

public final class h {
    private int a;
    private a b;
    private boolean c;

    public interface a {
    }

    private static final class b {
        private static h a = new h();
    }

    /* synthetic */ h(byte b) {
        this();
    }

    private h() {
        this.a = 3;
        this.c = true;
    }

    static void a(String tag) {
        b.a.a(3, tag);
    }

    static void b(String tag) {
        b.a.a(5, tag);
    }

    static void c(String tag) {
        b.a.a(6, tag);
    }

    static void a(String tag, String message, Throwable err) {
        h a = b.a;
        new StringBuilder().append(message).append(10).append(Log.getStackTraceString(err));
        a.a(6, tag);
    }

    private void a(int r3, java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r2 = this;
        r0 = r2.a;
        if (r0 <= r3) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0 = r0.append(r4);
        r1 = "_v1.4.7";
        r0.append(r1);
        r0 = r2.b;
        if (r0 == 0) goto L_0x0004;
    L_0x0017:
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.tokenshare.h.a(int, java.lang.String):void");
    }
}
