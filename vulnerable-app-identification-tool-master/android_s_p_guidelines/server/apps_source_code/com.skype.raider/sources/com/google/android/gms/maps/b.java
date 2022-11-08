package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.maps.a.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.g;

public final class b {
    private static a a;

    private static a a() {
        return (a) ab.a(a, (Object) "CameraUpdateFactory is not initialized");
    }

    public static a a(LatLng latLng) {
        try {
            return new a(a().a(latLng));
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public static a a(LatLngBounds latLngBounds, int i) {
        try {
            return new a(a().a(latLngBounds, i));
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public static a a(LatLngBounds latLngBounds, int i, int i2) {
        try {
            return new a(a().a(latLngBounds, i, i2));
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public static void a(a aVar) {
        a = (a) ab.a((Object) aVar);
    }

    public static a b(LatLng latLng) {
        try {
            return new a(a().b(latLng));
        } catch (RemoteException e) {
            throw new g(e);
        }
    }
}
