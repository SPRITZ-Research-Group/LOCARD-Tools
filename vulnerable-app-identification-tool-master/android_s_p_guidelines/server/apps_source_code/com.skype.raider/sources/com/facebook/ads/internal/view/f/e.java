package com.facebook.ads.internal.view.f;

import android.database.ContentObserver;
import android.os.Handler;

final class e extends ContentObserver {
    private final c a;

    e(Handler handler, c cVar) {
        super(handler);
        this.a = cVar;
    }

    public final boolean deliverSelfNotifications() {
        return false;
    }

    public final void onChange(boolean z) {
        this.a.d();
    }
}
