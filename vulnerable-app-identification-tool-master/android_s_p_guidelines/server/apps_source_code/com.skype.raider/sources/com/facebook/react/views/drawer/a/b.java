package com.facebook.react.views.drawer.a;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public final class b extends com.facebook.react.uimanager.events.b<b> {
    public b(int viewId) {
        super(viewId);
    }

    public final String a() {
        return "topDrawerOpened";
    }

    public final short f() {
        return (short) 0;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(d(), "topDrawerOpened", new WritableNativeMap());
    }
}
