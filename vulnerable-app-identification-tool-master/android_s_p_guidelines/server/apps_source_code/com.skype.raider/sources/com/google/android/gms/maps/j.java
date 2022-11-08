package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.a.g;

public final class j {
    private final g a;

    j(g gVar) {
        this.a = gVar;
    }

    public final void a(boolean z) {
        try {
            this.a.a(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final boolean a() {
        try {
            return this.a.a();
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void b(boolean z) {
        try {
            this.a.b(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void c(boolean z) {
        try {
            this.a.g(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void d(boolean z) {
        try {
            this.a.c(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void e(boolean z) {
        try {
            this.a.d(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void f(boolean z) {
        try {
            this.a.e(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void g(boolean z) {
        try {
            this.a.f(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final void h(boolean z) {
        try {
            this.a.h(z);
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }
}
