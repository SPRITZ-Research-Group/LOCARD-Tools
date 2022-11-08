package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.c;

public class AirMapCircle extends AirMapFeature {
    private CircleOptions a;
    private c b;
    private LatLng c;
    private double d;
    private int e;
    private int f;
    private float g;
    private float h;

    public AirMapCircle(Context context) {
        super(context);
    }

    public void setCenter(LatLng center) {
        this.c = center;
        if (this.b != null) {
            this.b.a(this.c);
        }
    }

    public void setRadius(double radius) {
        this.d = radius;
        if (this.b != null) {
            this.b.a(this.d);
        }
    }

    public void setFillColor(int color) {
        this.f = color;
        if (this.b != null) {
            this.b.b(color);
        }
    }

    public void setStrokeColor(int color) {
        this.e = color;
        if (this.b != null) {
            this.b.a(color);
        }
    }

    public void setStrokeWidth(float width) {
        this.g = width;
        if (this.b != null) {
            this.b.a(width);
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

    public final void a(com.google.android.gms.maps.c map) {
        if (this.a == null) {
            CircleOptions circleOptions = new CircleOptions();
            circleOptions.a(this.c);
            circleOptions.a(this.d);
            circleOptions.b(this.f);
            circleOptions.a(this.e);
            circleOptions.a(this.g);
            circleOptions.b(this.h);
            this.a = circleOptions;
        }
        this.b = map.a(this.a);
    }
}
