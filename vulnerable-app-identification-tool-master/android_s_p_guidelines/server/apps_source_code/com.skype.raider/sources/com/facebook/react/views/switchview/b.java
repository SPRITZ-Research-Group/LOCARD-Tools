package com.facebook.react.views.switchview;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;

final class b extends com.facebook.react.uimanager.events.b<b> {
    private final boolean a;

    public b(int viewId, boolean isChecked) {
        super(viewId);
        this.a = isChecked;
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
        writableNativeMap.putBoolean(PropertiesEntry.COLUMN_NAME_VALUE, this.a);
        rctEventEmitter.receiveEvent(d(), "topChange", writableNativeMap);
    }
}
