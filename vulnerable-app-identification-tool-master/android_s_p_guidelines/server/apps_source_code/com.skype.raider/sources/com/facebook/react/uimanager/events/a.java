package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.p;

public final class a extends b<a> {
    private final int a;
    private final int b;

    public a(int viewTag, int width, int height) {
        super(viewTag);
        this.a = width;
        this.b = height;
    }

    public final String a() {
        return "topContentSizeChange";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar data = new WritableNativeMap();
        data.putDouble("width", (double) p.b((float) this.a));
        data.putDouble("height", (double) p.b((float) this.b));
        rctEventEmitter.receiveEvent(d(), "topContentSizeChange", data);
    }
}
