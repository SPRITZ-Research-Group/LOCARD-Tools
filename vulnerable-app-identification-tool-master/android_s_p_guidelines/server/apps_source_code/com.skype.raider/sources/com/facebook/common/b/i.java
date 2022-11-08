package com.facebook.common.b;

import android.os.Handler;
import android.os.Looper;

public final class i extends e {
    private static i a = null;

    private i() {
        super(new Handler(Looper.getMainLooper()));
    }

    public static i b() {
        if (a == null) {
            a = new i();
        }
        return a;
    }

    public final void execute(Runnable command) {
        if (a()) {
            command.run();
        } else {
            super.execute(command);
        }
    }
}
