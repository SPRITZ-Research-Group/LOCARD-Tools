package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;

final class b extends com.facebook.react.uimanager.events.b<b> {
    private final String a;

    protected b(int viewTag, String pageScrollState) {
        super(viewTag);
        this.a = pageScrollState;
    }

    public final String a() {
        return "topPageScrollStateChanged";
    }

    public final void a(RCTEventEmitter rctEventEmitter) {
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("pageScrollState", this.a);
        rctEventEmitter.receiveEvent(d(), "topPageScrollStateChanged", writableNativeMap);
    }
}
