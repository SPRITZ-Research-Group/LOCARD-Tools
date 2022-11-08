package com.google.firebase.iid;

import android.os.Handler.Callback;
import android.os.Message;

final /* synthetic */ class ak implements Callback {
    private final aj a;

    ak(aj ajVar) {
        this.a = ajVar;
    }

    public final boolean handleMessage(Message message) {
        return this.a.a(message);
    }
}
