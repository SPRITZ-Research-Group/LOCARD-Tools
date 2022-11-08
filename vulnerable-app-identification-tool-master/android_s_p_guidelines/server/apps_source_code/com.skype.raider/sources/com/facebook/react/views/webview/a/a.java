package com.facebook.react.views.webview.a;

import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class a extends b<a> {
    private ar a;

    public a(int viewId, ar eventData) {
        super(viewId);
        this.a = eventData;
    }

    public final String a() {
        return "topLoadingError";
    }

    public final boolean b() {
        return false;
    }

    public final short f() {
        return (short) 0;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(d(), "topLoadingError", this.a);
    }
}
