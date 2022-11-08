package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;

final class fx extends ContentObserver {
    fx() {
        super(null);
    }

    public final void onChange(boolean z) {
        fw.e.set(true);
    }
}
