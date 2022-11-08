package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.common.k;

public final class a extends com.google.android.gms.common.internal.q.a {
    private Account a;
    private Context b;
    private int c;

    public static Account a(q qVar) {
        Account account = null;
        if (qVar != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = qVar.a();
            } catch (RemoteException e) {
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
        return account;
    }

    public final Account a() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == this.c) {
            return this.a;
        }
        if (k.b(this.b, callingUid)) {
            this.c = callingUid;
            return this.a;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }

    public final boolean equals(Object obj) {
        return this == obj ? true : !(obj instanceof a) ? false : this.a.equals(((a) obj).a);
    }
}
