package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.e;
import java.util.ArrayList;
import java.util.List;

public class AirMapPolygon extends AirMapFeature {
    private PolygonOptions a;
    private e b;
    private List<LatLng> c;
    private int d;
    private int e;
    private float f;
    private boolean g;
    private float h;

    public AirMapPolygon(Context context) {
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

    public void setFillColor(int color) {
        this.e = color;
        if (this.b != null) {
            this.b.b(color);
        }
    }

    public void setStrokeColor(int color) {
        this.d = color;
        if (this.b != null) {
            this.b.a(color);
        }
    }

    public void setStrokeWidth(float width) {
        this.f = width;
        if (this.b != null) {
            this.b.a(width);
        }
    }

    public void setGeodesic(boolean geodesic) {
        this.g = geodesic;
        if (this.b != null) {
            this.b.a(geodesic);
        }
    }

    public void setZIndex(float zIndex) {
        this.h = zIndex;
        if (this.b != null) {
            this.b.b(zIndex);
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
            PolygonOptions polygonOptions = new PolygonOptions();
            polygonOptions.a(this.c);
            polygonOptions.b(this.e);
            polygonOptions.a(this.d);
            polygonOptions.a(this.f);
            polygonOptions.a(this.g);
            polygonOptions.b(this.h);
            this.a = polygonOptions;
        }
        this.b = map.a(this.a);
        this.b.c();
    }
}
