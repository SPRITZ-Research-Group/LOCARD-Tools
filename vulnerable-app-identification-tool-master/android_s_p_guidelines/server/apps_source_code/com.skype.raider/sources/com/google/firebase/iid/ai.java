package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.c.g;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

public final class ai {
    @GuardedBy("MessengerIpcClient.class")
    private static ai a;
    private final Context b;
    private final ScheduledExecutorService c;
    @GuardedBy("this")
    private aj d = new aj();
    @GuardedBy("this")
    private int e = 1;

    @VisibleForTesting
    private ai(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.c = scheduledExecutorService;
        this.b = context.getApplicationContext();
    }

    private final synchronized int a() {
        int i;
        i = this.e;
        this.e = i + 1;
        return i;
    }

    private final synchronized <T> g<T> a(c<T> cVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(cVar);
            new StringBuilder(String.valueOf(valueOf).length() + 9).append("Queueing ").append(valueOf);
        }
        if (!this.d.a((c) cVar)) {
            this.d = new aj();
            this.d.a((c) cVar);
        }
        return cVar.b.a();
    }

    public static synchronized ai a(Context context) {
        ai aiVar;
        synchronized (ai.class) {
            if (a == null) {
                a = new ai(context, Executors.newSingleThreadScheduledExecutor());
            }
            aiVar = a;
        }
        return aiVar;
    }

    public final g<Void> a(Bundle bundle) {
        return a(new b(a(), bundle));
    }

    public final g<Bundle> b(Bundle bundle) {
        return a(new e(a(), bundle));
    }
}
