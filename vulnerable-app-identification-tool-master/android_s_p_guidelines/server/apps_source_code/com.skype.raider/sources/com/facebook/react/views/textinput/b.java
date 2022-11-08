package com.facebook.react.views.textinput;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public final class b extends com.facebook.react.uimanager.events.b<d> {
    private float a;
    private float b;

    public b(int viewId, float contentSizeWidth, float contentSizeHeight) {
        super(viewId);
        this.a = contentSizeWidth;
        this.b = contentSizeHeight;
    }

    public final String a() {
        return "topContentSizeChange";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        ar writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putDouble("width", (double) this.a);
        writableNativeMap2.putDouble("height", (double) this.b);
        writableNativeMap.putMap("contentSize", writableNativeMap2);
        writableNativeMap.putInt("target", d());
        rctEventEmitter.receiveEvent(d(), "topContentSizeChange", writableNativeMap);
    }
}
