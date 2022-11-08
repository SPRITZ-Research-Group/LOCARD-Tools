package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

final class d extends b<d> {
    protected d(int viewTag) {
        super(viewTag);
    }

    public final String a() {
        return "topShow";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(d(), "topShow", null);
    }
}
