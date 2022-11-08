package com.facebook.react.views.webview.a;

import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public final class b extends com.facebook.react.uimanager.events.b<b> {
    private ar a;

    public b(int viewId, ar eventData) {
        super(viewId);
        this.a = eventData;
    }

    public final String a() {
        return "topLoadingFinish";
    }

    public final boolean b() {
        return false;
    }

    public final short f() {
        return (short) 0;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(d(), "topLoadingFinish", this.a);
    }
}
