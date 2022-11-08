package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.b.d;
import com.google.android.gms.maps.a.ah;
import com.google.android.gms.maps.c.l;

final class v extends ah {
    private final /* synthetic */ l a;

    v(l lVar) {
        this.a = lVar;
    }

    public final void a(Bitmap bitmap) throws RemoteException {
        this.a.a(bitmap);
    }

    public final void a(b bVar) throws RemoteException {
        this.a.a((Bitmap) d.a(bVar));
    }
}
