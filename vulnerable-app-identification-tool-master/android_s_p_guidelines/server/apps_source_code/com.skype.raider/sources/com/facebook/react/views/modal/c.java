package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

final class c extends b<c> {
    protected c(int viewTag) {
        super(viewTag);
    }

    public final String a() {
        return "topRequestClose";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(d(), "topRequestClose", null);
    }
}
