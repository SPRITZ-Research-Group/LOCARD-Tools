package com.facebook.react.bridge.queue;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public final class a extends Handler {
    private final c a;

    public a(Looper looper, c exceptionHandler) {
        super(looper);
        this.a = exceptionHandler;
    }

    public final void dispatchMessage(Message msg) {
        try {
            super.dispatchMessage(msg);
        } catch (Exception e) {
            this.a.a(e);
        }
    }
}
