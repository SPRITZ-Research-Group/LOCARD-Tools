package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.internal.c.d;

public final class h {
    private final d a;

    public h(d dVar) {
        this.a = (d) ab.a((Object) dVar);
    }

    public final void a() {
        try {
            this.a.n_();
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

    public final void b() {
        try {
            this.a.b();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        try {
            return this.a.a(((h) obj).a);
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
