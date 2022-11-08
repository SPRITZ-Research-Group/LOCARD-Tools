package com.facebook.react.views.swiperefresh;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class a extends b<a> {
    protected a(int viewTag) {
        super(viewTag);
    }

    public final String a() {
        return "topRefresh";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(d(), "topRefresh", null);
    }
}
