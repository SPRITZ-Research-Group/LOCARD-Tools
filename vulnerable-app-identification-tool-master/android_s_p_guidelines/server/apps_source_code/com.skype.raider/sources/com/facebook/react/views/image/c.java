package com.facebook.react.views.image;

import com.facebook.drawee.c.q.b;
import com.facebook.react.bridge.n;
import javax.annotation.Nullable;

public final class c {
    public static b a(@Nullable String resizeModeValue) {
        if ("contain".equals(resizeModeValue)) {
            return b.c;
        }
        if ("cover".equals(resizeModeValue)) {
            return b.g;
        }
        if ("stretch".equals(resizeModeValue)) {
            return b.a;
        }
        if ("center".equals(resizeModeValue)) {
            return b.f;
        }
        if (resizeModeValue == null) {
            return b.g;
        }
        throw new n("Invalid resize mode: '" + resizeModeValue + "'");
    }
}
