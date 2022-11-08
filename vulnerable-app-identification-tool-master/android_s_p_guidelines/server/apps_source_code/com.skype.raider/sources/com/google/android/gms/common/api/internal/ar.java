package com.google.android.gms.common.api.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

public abstract class ar extends LifecycleCallback implements OnCancelListener {
    protected volatile boolean b;
    protected final AtomicReference<as> c;
    protected final GoogleApiAvailability d;
    private final Handler e;

    protected abstract void a(ConnectionResult connectionResult, int i);

    protected abstract void b();

    public final void b(ConnectionResult connectionResult, int i) {
        as asVar = new as(connectionResult, i);
        if (this.c.compareAndSet(null, asVar)) {
            this.e.post(new at(this, asVar));
        }
    }

    protected final void c() {
        this.c.set(null);
        b();
    }

    public void onCancel(DialogInterface dialogInterface) {
        as asVar = (as) this.c.get();
        a(new ConnectionResult(13, null), asVar == null ? -1 : asVar.a());
        c();
    }
}
