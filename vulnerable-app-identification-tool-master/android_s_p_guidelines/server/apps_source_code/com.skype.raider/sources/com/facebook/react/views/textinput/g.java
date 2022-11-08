package com.facebook.react.views.textinput;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class g extends b<g> {
    private String a;
    private String b;
    private int c;
    private int d;

    public g(int viewId, String text, String previousText, int rangeStart, int rangeEnd) {
        super(viewId);
        this.a = text;
        this.b = previousText;
        this.c = rangeStart;
        this.d = rangeEnd;
    }

    public final String a() {
        return "topTextInput";
    }

    public final boolean b() {
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        ar writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putDouble("start", (double) this.c);
        writableNativeMap2.putDouble("end", (double) this.d);
        writableNativeMap.putString("text", this.a);
        writableNativeMap.putString("previousText", this.b);
        writableNativeMap.putMap("range", writableNativeMap2);
        writableNativeMap.putInt("target", d());
        rctEventEmitter.receiveEvent(d(), "topTextInput", writableNativeMap);
    }
}
