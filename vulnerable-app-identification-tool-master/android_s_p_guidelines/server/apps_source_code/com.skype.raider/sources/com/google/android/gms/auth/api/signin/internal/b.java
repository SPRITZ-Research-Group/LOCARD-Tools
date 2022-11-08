package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.util.VisibleForTesting;

public final class b {
    @VisibleForTesting
    private static int a = 31;
    private int b = 1;

    public final int a() {
        return this.b;
    }

    public final b a(Object obj) {
        this.b = (obj == null ? 0 : obj.hashCode()) + (this.b * a);
        return this;
    }

    public final b a(boolean z) {
        this.b = (z ? 1 : 0) + (this.b * a);
        return this;
    }
}
