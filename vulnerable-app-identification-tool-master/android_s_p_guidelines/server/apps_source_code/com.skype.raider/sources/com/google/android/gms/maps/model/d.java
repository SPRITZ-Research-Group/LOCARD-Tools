package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.internal.c.s;

public final class d {
    private final s a;

    public d(s sVar) {
        this.a = (s) ab.a((Object) sVar);
    }

    public final void a() {
        try {
            this.a.p_();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void a(float f) {
        try {
            this.a.c(f);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void a(float f, float f2) {
        try {
            this.a.a(f, f2);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void a(@NonNull LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("latlng cannot be null - a position is required.");
        }
        try {
            this.a.a(latLng);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void a(@Nullable a aVar) {
        if (aVar == null) {
            try {
                this.a.a(null);
                return;
            } catch (RemoteException e) {
                throw new g(e);
            }
        }
        this.a.a(aVar.a());
    }

    public final void a(@Nullable String str) {
        try {
            this.a.a(str);
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

    public final LatLng b() {
        try {
            return this.a.b();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void b(float f) {
        try {
            this.a.a(f);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void b(float f, float f2) {
        try {
            this.a.b(f, f2);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void b(@Nullable String str) {
        try {
            this.a.b(str);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void b(boolean z) {
        try {
            this.a.b(z);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void c() {
        try {
            this.a.c();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void c(float f) {
        try {
            this.a.b(f);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final void d() {
        try {
            this.a.d();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        try {
            return this.a.a(((d) obj).a);
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public final int hashCode() {
        try {
            return this.a.e();
        } catch (RemoteException e) {
            throw new g(e);
        }
    }
}
