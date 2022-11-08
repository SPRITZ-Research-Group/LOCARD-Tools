package com.facebook.react.views.webview.a;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class d extends b<d> {
    private final String a;

    public d(int viewId, String data) {
        super(viewId);
        this.a = data;
    }

    public final String a() {
        return "topMessage";
    }

    public final boolean b() {
        return false;
    }

    public final short f() {
        return (short) 0;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar data = new WritableNativeMap();
        data.putString("data", this.a);
        rctEventEmitter.receiveEvent(d(), "topMessage", data);
    }
}
