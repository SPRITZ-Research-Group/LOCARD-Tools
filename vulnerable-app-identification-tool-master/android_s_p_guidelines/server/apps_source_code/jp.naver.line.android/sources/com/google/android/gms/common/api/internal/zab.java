package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager.zaa;
import com.google.android.gms.common.util.PlatformVersion;

public abstract class zab {
    private final int type;

    public zab(int i) {
        this.type = i;
    }

    public abstract void zaa(Status status);

    public abstract void zaa(zaa<?> zaa) throws DeadObjectException;

    public abstract void zaa(zaab zaab, boolean z);

    public abstract void zaa(RuntimeException runtimeException);

    private static Status zaa(RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && (remoteException instanceof TransactionTooLargeException)) {
            stringBuilder.append("TransactionTooLargeException: ");
        }
        stringBuilder.append(remoteException.getLocalizedMessage());
        return new Status(8, stringBuilder.toString());
    }
}
