package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.maps.a.d;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public final class g {
    private final d a;

    g(d dVar) {
        this.a = dVar;
    }

    public final Point a(LatLng latLng) {
        try {
            return (Point) com.google.android.gms.b.d.a(this.a.a(latLng));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final LatLng a(Point point) {
        try {
            return this.a.a(com.google.android.gms.b.d.a((Object) point));
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }

    public final VisibleRegion a() {
        try {
            return this.a.a();
        } catch (RemoteException e) {
            throw new com.google.android.gms.maps.model.g(e);
        }
    }
}
