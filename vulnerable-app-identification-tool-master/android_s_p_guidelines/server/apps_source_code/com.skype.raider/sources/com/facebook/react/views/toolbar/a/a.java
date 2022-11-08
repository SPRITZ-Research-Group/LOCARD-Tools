package com.facebook.react.views.toolbar.a;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class a extends b<a> {
    private final int a;

    public a(int viewId, int position) {
        super(viewId);
        this.a = position;
    }

    public final String a() {
        return "topSelect";
    }

    public final boolean b() {
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar event = new WritableNativeMap();
        event.putInt("position", this.a);
        rctEventEmitter.receiveEvent(d(), "topSelect", event);
    }
}
