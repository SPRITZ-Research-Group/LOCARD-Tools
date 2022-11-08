package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver.a;

final class au extends a {
    private final /* synthetic */ Dialog a;
    private final /* synthetic */ at b;

    au(at atVar, Dialog dialog) {
        this.b = atVar;
        this.a = dialog;
    }

    public final void a() {
        this.b.a.c();
        if (this.a.isShowing()) {
            this.a.dismiss();
        }
    }
}
