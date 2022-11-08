package com.facebook.ads.internal.f;

import android.content.Context;
import android.os.Process;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.q.a.n;
import com.facebook.ads.internal.q.a.o;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;

public final class c implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler a;
    private final Context b;
    private final Map<String, String> c;

    public c(@Nullable UncaughtExceptionHandler uncaughtExceptionHandler, Context context, Map<String, String> map) {
        this.a = uncaughtExceptionHandler;
        if (context == null) {
            throw new IllegalArgumentException("Missing Context");
        }
        this.b = context.getApplicationContext();
        this.c = map;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            String a = o.a(th);
            if (a != null && a.contains("com.facebook.ads")) {
                Map a2 = new b(a, this.c).a();
                a2.put("subtype", "crash");
                a2.put("subtype_code", "0");
                e.a(new d(n.b(), n.c(), a2), this.b);
            }
        } catch (Exception e) {
        }
        if (this.a != null) {
            this.a.uncaughtException(thread, th);
            return;
        }
        try {
            Process.killProcess(Process.myPid());
        } catch (Throwable th2) {
        }
        try {
            System.exit(10);
        } catch (Throwable th3) {
        }
    }
}
