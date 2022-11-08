package com.facebook.react.views.drawer.a;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class c extends b<c> {
    private final float a;

    public c(int viewId, float offset) {
        super(viewId);
        this.a = offset;
    }

    public final String a() {
        return "topDrawerSlide";
    }

    public final short f() {
        return (short) 0;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putDouble("offset", (double) this.a);
        rctEventEmitter.receiveEvent(d(), "topDrawerSlide", writableNativeMap);
    }
}
