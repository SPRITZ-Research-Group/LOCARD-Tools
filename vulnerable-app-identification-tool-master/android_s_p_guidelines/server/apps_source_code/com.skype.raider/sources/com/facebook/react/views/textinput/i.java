package com.facebook.react.views.textinput;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

final class i extends b<i> {
    private int a;
    private int b;

    public i(int viewId, int selectionStart, int selectionEnd) {
        super(viewId);
        this.a = selectionStart;
        this.b = selectionEnd;
    }

    public final String a() {
        return "topSelectionChange";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        ar writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putInt("end", this.b);
        writableNativeMap2.putInt("start", this.a);
        writableNativeMap.putMap("selection", writableNativeMap2);
        rctEventEmitter.receiveEvent(d(), "topSelectionChange", writableNativeMap);
    }
}
