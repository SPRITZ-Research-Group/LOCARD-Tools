package com.facebook.react.common;

import com.facebook.infer.annotation.a;
import javax.annotation.Nullable;

public final class g {
    @Nullable
    private Thread a = null;

    public final void a() {
        Thread current = Thread.currentThread();
        if (this.a == null) {
            this.a = current;
        }
        a.a(this.a == current);
    }
}
