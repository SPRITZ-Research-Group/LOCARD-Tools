package com.facebook.react.views.textinput;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

final class k extends b<k> {
    private String a;

    public k(int viewId, String text) {
        super(viewId);
        this.a = text;
    }

    public final String a() {
        return "topSubmitEditing";
    }

    public final boolean b() {
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("target", d());
        writableNativeMap.putString("text", this.a);
        rctEventEmitter.receiveEvent(d(), "topSubmitEditing", writableNativeMap);
    }
}
