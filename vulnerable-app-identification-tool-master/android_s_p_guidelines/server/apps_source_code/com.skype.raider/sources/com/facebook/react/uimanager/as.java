package com.facebook.react.uimanager;

import com.facebook.react.common.a;
import com.facebook.yoga.YogaNode;

public final class as {
    private static final Object a = new Object();
    private static a<YogaNode> b;

    public static a<YogaNode> a() {
        if (b != null) {
            return b;
        }
        a<YogaNode> aVar;
        synchronized (a) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }
}
