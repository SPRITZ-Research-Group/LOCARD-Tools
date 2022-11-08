package com.facebook.react.views.textinput;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

final class e extends b<e> {
    public e(int viewId) {
        super(viewId);
    }

    public final String a() {
        return "topBlur";
    }

    public final boolean b() {
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("target", d());
        rctEventEmitter.receiveEvent(d(), "topBlur", writableNativeMap);
    }
}
