package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.f.a;

final class m implements a {
    private final /* synthetic */ BasePendingResult a;
    private final /* synthetic */ l b;

    m(l lVar, BasePendingResult basePendingResult) {
        this.b = lVar;
        this.a = basePendingResult;
    }

    public final void a(Status status) {
        this.b.a.remove(this.a);
    }
}
