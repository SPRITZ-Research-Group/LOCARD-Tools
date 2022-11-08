package com.airbnb.android.react.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class a {
    public static boolean a(LatLngBounds a, LatLngBounds b) {
        LatLng centerA = a.a();
        double latA = centerA.a;
        double lngA = centerA.b;
        double latDeltaA = a.b.a - a.a.a;
        double lngDeltaA = a.b.b - a.a.b;
        LatLng centerB = b.a();
        double latEps = Math.min(Math.abs(a.b.a - a.a.a), Math.abs(b.b.a - b.a.a)) / 2560.0d;
        double lngEps = Math.min(Math.abs(a.b.b - a.a.b), Math.abs(b.b.b - b.a.b)) / 2560.0d;
        return a(latA, centerB.a, latEps) || a(lngA, centerB.b, lngEps) || a(latDeltaA, b.b.a - b.a.a, latEps) || a(lngDeltaA, b.b.b - b.a.b, lngEps);
    }

    private static boolean a(double a, double b, double epsilon) {
        return Math.abs(a - b) > epsilon;
    }
}
