package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.f;
import java.util.ArrayList;
import java.util.List;

public class AirMapPolyline extends AirMapFeature {
    private PolylineOptions a;
    private f b;
    private List<LatLng> c;
    private int d;
    private float e;
    private boolean f;
    private float g;

    public AirMapPolyline(Context context) {
        super(context);
    }

    public void setCoordinates(al coordinates) {
        this.c = new ArrayList(coordinates.size());
        for (int i = 0; i < coordinates.size(); i++) {
            am coordinate = coordinates.getMap(i);
            this.c.add(i, new LatLng(coordinate.getDouble("latitude"), coordinate.getDouble("longitude")));
        }
        if (this.b != null) {
            this.b.a(this.c);
        }
    }

    public void setColor(int color) {
        this.d = color;
        if (this.b != null) {
            this.b.a(color);
        }
    }

    public void setWidth(float width) {
        this.e = width;
        if (this.b != null) {
            this.b.a(width);
        }
    }

    public void setZIndex(float zIndex) {
        this.g = zIndex;
        if (this.b != null) {
            this.b.b(zIndex);
        }
    }

    public void setGeodesic(boolean geodesic) {
        this.f = geodesic;
        if (this.b != null) {
            this.b.a(geodesic);
        }
    }

    public final Object a() {
        return this.b;
    }

    public final void b() {
        this.b.a();
    }

    public final void a(c map) {
        if (this.a == null) {
            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.a(this.c);
            polylineOptions.a(this.d);
            polylineOptions.a(this.e);
            polylineOptions.a(this.f);
            polylineOptions.b(this.g);
            this.a = polylineOptions;
        }
        this.b = map.a(this.a);
        this.b.c();
    }
}
