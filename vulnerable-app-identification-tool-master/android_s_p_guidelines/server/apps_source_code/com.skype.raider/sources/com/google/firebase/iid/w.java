package com.google.firebase.iid;

import android.os.Binder;
import android.os.Process;

public final class w extends Binder {
    private final zzb a;

    w(zzb zzb) {
        this.a = zzb;
    }

    public final void a(u uVar) {
        if (Binder.getCallingUid() != Process.myUid()) {
            throw new SecurityException("Binding only allowed within app");
        } else if (this.a.c(uVar.a)) {
            uVar.a();
        } else {
            this.a.a.execute(new x(this, uVar));
        }
    }
}
