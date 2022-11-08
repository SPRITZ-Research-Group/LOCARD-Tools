package com.facebook.react.views.slider;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;

public final class b extends com.facebook.react.uimanager.events.b<b> {
    private final double a;

    public b(int viewId, double value) {
        super(viewId);
        this.a = value;
    }

    public final String a() {
        return "topSlidingComplete";
    }

    public final short f() {
        return (short) 0;
    }

    public final boolean b() {
        return false;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("target", d());
        writableNativeMap.putDouble(PropertiesEntry.COLUMN_NAME_VALUE, this.a);
        rctEventEmitter.receiveEvent(d(), "topSlidingComplete", writableNativeMap);
    }
}
