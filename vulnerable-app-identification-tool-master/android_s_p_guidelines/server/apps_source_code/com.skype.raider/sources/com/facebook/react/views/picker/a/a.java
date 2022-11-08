package com.facebook.react.views.picker.a;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class a extends b<a> {
    private final int a;

    public a(int id, int position) {
        super(id);
        this.a = position;
    }

    public final String a() {
        return "topSelect";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("position", this.a);
        rctEventEmitter.receiveEvent(d(), "topSelect", writableNativeMap);
    }
}
