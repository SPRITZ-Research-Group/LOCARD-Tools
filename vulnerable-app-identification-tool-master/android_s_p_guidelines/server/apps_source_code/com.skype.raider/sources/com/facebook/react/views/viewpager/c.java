package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

final class c extends b<c> {
    private final int a;

    protected c(int viewTag, int position) {
        super(viewTag);
        this.a = position;
    }

    public final String a() {
        return "topPageSelected";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("position", this.a);
        rctEventEmitter.receiveEvent(d(), "topPageSelected", writableNativeMap);
    }
}
