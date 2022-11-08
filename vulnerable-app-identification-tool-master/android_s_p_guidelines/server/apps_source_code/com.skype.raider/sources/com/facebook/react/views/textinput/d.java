package com.facebook.react.views.textinput;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class d extends b<d> {
    private String a;
    private int b;

    public d(int viewId, String text, int eventCount) {
        super(viewId);
        this.a = text;
        this.b = eventCount;
    }

    public final String a() {
        return "topChange";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("text", this.a);
        writableNativeMap.putInt("eventCount", this.b);
        writableNativeMap.putInt("target", d());
        rctEventEmitter.receiveEvent(d(), "topChange", writableNativeMap);
    }
}
