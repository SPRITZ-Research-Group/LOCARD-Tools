package com.facebook.react.views.drawer.a;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class d extends b<d> {
    private final int a;

    public d(int viewId, int drawerState) {
        super(viewId);
        this.a = drawerState;
    }

    public final String a() {
        return "topDrawerStateChanged";
    }

    public final short f() {
        return (short) 0;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putDouble("drawerState", (double) this.a);
        rctEventEmitter.receiveEvent(d(), "topDrawerStateChanged", writableNativeMap);
    }
}
