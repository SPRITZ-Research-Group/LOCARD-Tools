package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.internal.c.v;
import java.util.List;

public final class e {
    private final v a;

    public e(v vVar) {
        this.a = (v) ab.a((Object) vVar);
    }

    public final void a() {
        try {
            this.a.q_();
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
            return this.a.b();
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

    public final void c() {
        try {
            this.a.d();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        try {
            return this.a.a(((e) obj).a);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final int hashCode() {
        try {
            return this.a.c();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }
}
