package com.facebook.react.views.scroll;

import android.support.v4.util.j.c;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class g extends b<g> {
    private static final c<g> a = new c(3);
    private int b;

    private g() {
    }

    public static g a(int viewTag, int id) {
        g event = (g) a.a();
        if (event == null) {
            event = new g();
        }
        super.a(viewTag);
        event.b = id;
        return event;
    }

    public final void c() {
        a.a(this);
    }

    public final String a() {
        return f.SCROLL_POSITION_SET.a();
    }

    public final boolean b() {
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        int d = d();
        String a = f.SCROLL_POSITION_SET.a();
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putDouble("id", (double) this.b);
        rctEventEmitter.receiveEvent(d, a, writableNativeMap);
    }
}
