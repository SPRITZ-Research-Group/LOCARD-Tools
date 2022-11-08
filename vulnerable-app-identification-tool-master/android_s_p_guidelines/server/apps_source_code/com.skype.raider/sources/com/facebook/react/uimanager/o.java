package com.facebook.react.uimanager;

import android.support.v4.util.j.c;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;

public final class o extends b<o> {
    private static final c<o> a = new c(20);
    private int b;
    private int c;
    private int d;
    private int e;

    public static o a(int viewTag, int x, int y, int width, int height) {
        o event = (o) a.a();
        if (event == null) {
            event = new o();
        }
        super.a(viewTag);
        event.b = x;
        event.c = y;
        event.d = width;
        event.e = height;
        return event;
    }

    public final void c() {
        a.a(this);
    }

    private o() {
    }

    public final String a() {
        return "topLayout";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar layout = new WritableNativeMap();
        layout.putDouble("x", (double) p.b((float) this.b));
        layout.putDouble("y", (double) p.b((float) this.c));
        layout.putDouble("width", (double) p.b((float) this.d));
        layout.putDouble("height", (double) p.b((float) this.e));
        ar event = new WritableNativeMap();
        event.putMap("layout", layout);
        event.putInt("target", d());
        rctEventEmitter.receiveEvent(d(), "topLayout", event);
    }
}
