package com.facebook.react.views.drawer.a;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class a extends b<a> {
    public a(int viewId) {
        super(viewId);
    }

    public final String a() {
        return "topDrawerClosed";
    }

    public final short f() {
        return (short) 0;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(d(), "topDrawerClosed", new WritableNativeMap());
    }
}
