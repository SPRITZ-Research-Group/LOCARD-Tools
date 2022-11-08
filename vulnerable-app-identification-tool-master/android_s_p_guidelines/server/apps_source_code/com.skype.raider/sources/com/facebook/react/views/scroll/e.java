package com.facebook.react.views.scroll;

import android.support.v4.util.j.c;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;
import com.facebook.react.uimanager.p;
import javax.annotation.Nullable;

public final class e extends b<e> {
    private static final c<e> a = new c(3);
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    @Nullable
    private f h;

    public static e a(int viewTag, f scrollEventType, int scrollX, int scrollY, int contentWidth, int contentHeight, int scrollViewWidth, int scrollViewHeight) {
        e event = (e) a.a();
        if (event == null) {
            event = new e();
        }
        super.a(viewTag);
        event.h = scrollEventType;
        event.b = scrollX;
        event.c = scrollY;
        event.d = contentWidth;
        event.e = contentHeight;
        event.f = scrollViewWidth;
        event.g = scrollViewHeight;
        return event;
    }

    public final void c() {
        a.a(this);
    }

    private e() {
    }

    public final String a() {
        return ((f) a.a(this.h)).a();
    }

    public final short f() {
        return (short) 0;
    }

    public final boolean b() {
        if (this.h == f.SCROLL) {
            return true;
        }
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        int d = d();
        String a = a();
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putDouble("top", 0.0d);
        writableNativeMap.putDouble("bottom", 0.0d);
        writableNativeMap.putDouble("left", 0.0d);
        writableNativeMap.putDouble("right", 0.0d);
        ar writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putDouble("x", (double) p.b((float) this.b));
        writableNativeMap2.putDouble("y", (double) p.b((float) this.c));
        ar writableNativeMap3 = new WritableNativeMap();
        writableNativeMap3.putDouble("width", (double) p.b((float) this.d));
        writableNativeMap3.putDouble("height", (double) p.b((float) this.e));
        ar writableNativeMap4 = new WritableNativeMap();
        writableNativeMap4.putDouble("width", (double) p.b((float) this.f));
        writableNativeMap4.putDouble("height", (double) p.b((float) this.g));
        ar writableNativeMap5 = new WritableNativeMap();
        writableNativeMap5.putMap("contentInset", writableNativeMap);
        writableNativeMap5.putMap("contentOffset", writableNativeMap2);
        writableNativeMap5.putMap("contentSize", writableNativeMap3);
        writableNativeMap5.putMap("layoutMeasurement", writableNativeMap4);
        writableNativeMap5.putInt("target", d());
        writableNativeMap5.putBoolean("responderIgnoreScroll", true);
        rctEventEmitter.receiveEvent(d, a, writableNativeMap5);
    }
}
