package com.google.firebase.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class n extends Handler {
    private final /* synthetic */ m a;

    n(m mVar, Looper looper) {
        this.a = mVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        m.a(this.a, message);
    }
}
