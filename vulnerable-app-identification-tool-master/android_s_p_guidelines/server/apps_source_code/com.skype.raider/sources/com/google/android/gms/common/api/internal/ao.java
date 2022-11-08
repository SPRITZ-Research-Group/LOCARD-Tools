package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.c.h;
import com.google.android.gms.common.api.internal.h.a;

public final class ao extends af<Boolean> {
    private final a<?> b;

    public ao(a<?> aVar, h<Boolean> hVar) {
        super(hVar);
        this.b = aVar;
    }

    public final /* bridge */ /* synthetic */ void a(@NonNull l lVar, boolean z) {
    }

    public final void b(d.a<?> aVar) throws RemoteException {
        aa aaVar = (aa) aVar.e().remove(this.b);
        if (aaVar != null) {
            aaVar.a.a();
        } else {
            this.a.b(Boolean.valueOf(false));
        }
    }
}
