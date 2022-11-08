package com.facebook.react.uimanager;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.v;
import javax.annotation.Nullable;

public final class ae extends ai {
    private final ag a;

    public ae(ag reactApplicationContext, Context base) {
        super(base);
        a(reactApplicationContext.a());
        this.a = reactApplicationContext;
    }

    public final void a(v listener) {
        this.a.a(listener);
    }

    public final void b(v listener) {
        this.a.b(listener);
    }

    public final boolean i() {
        return this.a.i();
    }

    @Nullable
    public final Activity j() {
        return this.a.j();
    }
}
