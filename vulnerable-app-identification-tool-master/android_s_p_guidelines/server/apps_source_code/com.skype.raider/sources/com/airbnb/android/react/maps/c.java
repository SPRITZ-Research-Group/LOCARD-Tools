package com.airbnb.android.react.maps;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class c extends b<c> {
    private final LatLngBounds a;
    private final LatLng b;
    private final boolean c;

    public c(int id, LatLngBounds bounds, LatLng center, boolean continuous) {
        super(id);
        this.a = bounds;
        this.b = center;
        this.c = continuous;
    }

    public final String a() {
        return "topChange";
    }

    public final boolean b() {
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar event = new WritableNativeMap();
        event.putBoolean("continuous", this.c);
        ar region = new WritableNativeMap();
        region.putDouble("latitude", this.b.a);
        region.putDouble("longitude", this.b.b);
        region.putDouble("latitudeDelta", this.a.b.a - this.a.a.a);
        region.putDouble("longitudeDelta", this.a.b.b - this.a.a.b);
        event.putMap("region", region);
        rctEventEmitter.receiveEvent(d(), "topChange", event);
    }
}
