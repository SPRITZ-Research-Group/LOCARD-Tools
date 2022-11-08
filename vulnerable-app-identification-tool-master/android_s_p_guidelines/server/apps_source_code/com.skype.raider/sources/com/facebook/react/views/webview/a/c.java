package com.facebook.react.views.webview.a;

import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class c extends b<c> {
    private ar a;

    public c(int viewId, ar eventData) {
        super(viewId);
        this.a = eventData;
    }

    public final String a() {
        return "topLoadingStart";
    }

    public final boolean b() {
        return false;
    }

    public final short f() {
        return (short) 0;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(d(), "topLoadingStart", this.a);
    }
}
