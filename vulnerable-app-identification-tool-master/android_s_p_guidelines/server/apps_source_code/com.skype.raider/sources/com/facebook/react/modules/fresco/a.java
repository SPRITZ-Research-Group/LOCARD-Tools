package com.facebook.react.modules.fresco;

import com.facebook.imagepipeline.k.b;
import com.facebook.imagepipeline.k.c;
import com.facebook.react.bridge.am;

public final class a extends b {
    private final am a;

    public static a a(c builder, am headers) {
        return new a(builder, headers);
    }

    private a(c builder, am headers) {
        super(builder);
        this.a = headers;
    }

    public final am s() {
        return this.a;
    }
}
