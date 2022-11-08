package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

final class a extends b<a> {
    private final int a;
    private final float b;

    protected a(int viewTag, int position, float offset) {
        super(viewTag);
        this.a = position;
        if (Float.isInfinite(offset) || Float.isNaN(offset)) {
            offset = 0.0f;
        }
        this.b = offset;
    }

    public final String a() {
        return "topPageScroll";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("position", this.a);
        writableNativeMap.putDouble("offset", (double) this.b);
        rctEventEmitter.receiveEvent(d(), "topPageScroll", writableNativeMap);
    }
}
