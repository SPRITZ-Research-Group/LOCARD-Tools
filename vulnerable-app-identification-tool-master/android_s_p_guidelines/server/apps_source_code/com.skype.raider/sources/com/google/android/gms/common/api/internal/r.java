package com.google.android.gms.common.api.internal;

import android.os.Build.VERSION;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.d.a;

public abstract class r {
    private final int a;

    public r(int i) {
        this.a = i;
    }

    public abstract void a(@NonNull Status status);

    public abstract void a(a<?> aVar) throws DeadObjectException;

    public abstract void a(@NonNull l lVar, boolean z);

    public abstract void a(@NonNull RuntimeException runtimeException);

    static /* synthetic */ Status a(RemoteException remoteException) {
        Object obj;
        StringBuilder stringBuilder = new StringBuilder();
        if (VERSION.SDK_INT >= 15) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null && (remoteException instanceof TransactionTooLargeException)) {
            stringBuilder.append("TransactionTooLargeException: ");
        }
        stringBuilder.append(remoteException.getLocalizedMessage());
        return new Status(8, stringBuilder.toString());
    }
}
