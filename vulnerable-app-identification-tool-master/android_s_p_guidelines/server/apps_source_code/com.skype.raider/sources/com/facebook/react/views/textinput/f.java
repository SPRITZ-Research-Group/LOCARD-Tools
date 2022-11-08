package com.facebook.react.views.textinput;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

final class f extends b<f> {
    private String a;

    public f(int viewId, String text) {
        super(viewId);
        this.a = text;
    }

    public final String a() {
        return "topEndEditing";
    }

    public final boolean b() {
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("target", d());
        writableNativeMap.putString("text", this.a);
        rctEventEmitter.receiveEvent(d(), "topEndEditing", writableNativeMap);
    }
}
