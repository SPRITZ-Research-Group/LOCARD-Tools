package com.airbnb.android.react.maps;

import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.h;
import java.util.HashMap;

public final class d extends h {
    public final void a(al uiViewOperationQueue) {
        super.a(uiViewOperationQueue);
        Object data = new HashMap();
        data.put("width", Float.valueOf(P()));
        data.put("height", Float.valueOf(Q()));
        uiViewOperationQueue.a(A(), data);
    }
}
