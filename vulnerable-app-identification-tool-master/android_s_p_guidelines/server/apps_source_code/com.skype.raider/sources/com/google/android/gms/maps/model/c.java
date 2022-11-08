package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.internal.c.p;

public final class c {
    private final p a;

    public c(p pVar) {
        this.a = (p) ab.a((Object) pVar);
    }

    public final void a() {
        try {
            this.a.o_();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void a(double d) {
        try {
            this.a.a(d);
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

    public final void a(LatLng latLng) {
        try {
            this.a.a(latLng);
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

    public final void b(int i) {
        try {
            this.a.b(i);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        try {
            return this.a.a(((c) obj).a);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final int hashCode() {
        try {
            return this.a.b();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }
}
