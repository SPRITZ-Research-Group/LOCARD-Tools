package com.microsoft.react.sqlite.b;

import java.util.concurrent.atomic.AtomicBoolean;

public final class b {
    AtomicBoolean a = new AtomicBoolean(false);

    public final void a() {
        while (true) {
            if (!this.a.get() && this.a.compareAndSet(false, true)) {
                return;
            }
        }
    }

    public final void b() {
        this.a.compareAndSet(true, false);
    }
}
