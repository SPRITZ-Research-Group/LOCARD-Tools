package com.facebook.react.views.slider;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.b;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;

public final class a extends b<a> {
    private final double a;
    private final boolean b;

    public a(int viewId, double value, boolean fromUser) {
        super(viewId);
        this.a = value;
        this.b = fromUser;
    }

    public final String a() {
        return "topChange";
    }

    public final short f() {
        return (short) 0;
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("target", d());
        writableNativeMap.putDouble(PropertiesEntry.COLUMN_NAME_VALUE, this.a);
        writableNativeMap.putBoolean("fromUser", this.b);
        rctEventEmitter.receiveEvent(d(), "topChange", writableNativeMap);
    }
}
