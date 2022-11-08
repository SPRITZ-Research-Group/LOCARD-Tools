package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.common.util.a.a;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
    @VisibleForTesting
    final ExecutorService a;
    private Binder b;
    private final Object c;
    private int d;
    private int e;

    public zzb() {
        String str = "Firebase-";
        String valueOf = String.valueOf(getClass().getSimpleName());
        this.a = Executors.newSingleThreadExecutor(new a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)));
        this.c = new Object();
        this.e = 0;
    }

    private final void d(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.a(intent);
        }
        synchronized (this.c) {
            this.e--;
            if (this.e == 0) {
                stopSelfResult(this.d);
            }
        }
    }

    protected Intent a(Intent intent) {
        return intent;
    }

    public abstract void b(Intent intent);

    public boolean c(Intent intent) {
        return false;
    }

    public final synchronized IBinder onBind(Intent intent) {
        if (this.b == null) {
            this.b = new w(this);
        }
        return this.b;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.c) {
            this.d = i2;
            this.e++;
        }
        Intent a = a(intent);
        if (a == null) {
            d(intent);
            return 2;
        } else if (c(a)) {
            d(intent);
            return 2;
        } else {
            this.a.execute(new t(this, a, intent));
            return 3;
        }
    }
}
