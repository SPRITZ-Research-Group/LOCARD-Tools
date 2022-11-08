package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.internal.c.y;
import java.util.List;

public final class f {
    private final y a;

    public f(y yVar) {
        this.a = (y) ab.a((Object) yVar);
    }

    public final void a() {
        try {
            this.a.b();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void a(float f) {
        try {
            this.a.a(f);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void a(int i) {
        try {
            this.a.a(i);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void a(List<LatLng> list) {
        try {
            this.a.a((List) list);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void a(boolean z) {
        try {
            this.a.a(z);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final List<LatLng> b() {
        try {
            return this.a.c();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void b(float f) {
        try {
            this.a.b(f);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void c() {
        try {
            this.a.e();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        try {
            return this.a.a(((f) obj).a);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final int hashCode() {
        try {
            return this.a.d();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }
}
