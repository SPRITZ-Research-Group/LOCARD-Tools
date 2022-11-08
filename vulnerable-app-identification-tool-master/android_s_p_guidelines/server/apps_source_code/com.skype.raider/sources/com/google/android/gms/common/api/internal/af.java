package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.c.h;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.api.internal.d.a;

abstract class af<T> extends r {
    protected final h<T> a;

    public af(h<T> hVar) {
        super(4);
        this.a = hVar;
    }

    public void a(@NonNull Status status) {
        this.a.b(new b(status));
    }

    public final void a(a<?> aVar) throws DeadObjectException {
        try {
            b(aVar);
        } catch (RemoteException e) {
            a(r.a(e));
            throw e;
        } catch (RemoteException e2) {
            a(r.a(e2));
        } catch (RuntimeException e3) {
            a(e3);
        }
    }

    public void a(@NonNull l lVar, boolean z) {
    }

    public void a(@NonNull RuntimeException runtimeException) {
        this.a.b((Exception) runtimeException);
    }

    protected abstract void b(a<?> aVar) throws RemoteException;
}
