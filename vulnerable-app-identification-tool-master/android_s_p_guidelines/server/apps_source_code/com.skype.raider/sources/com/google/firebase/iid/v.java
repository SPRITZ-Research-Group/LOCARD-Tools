package com.google.firebase.iid;

import android.content.Intent;

final class v implements Runnable {
    private final /* synthetic */ Intent a;
    private final /* synthetic */ u b;

    v(u uVar, Intent intent) {
        this.b = uVar;
        this.a = intent;
    }

    public final void run() {
        String action = this.a.getAction();
        new StringBuilder(String.valueOf(action).length() + 61).append("Service took too long to process intent: ").append(action).append(" App may get closed.");
        this.b.a();
    }
}
