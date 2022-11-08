package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.b.a;

final class s implements a {
    private final /* synthetic */ d a;

    s(d dVar) {
        this.a = dVar;
    }

    public final void a(boolean z) {
        this.a.q.sendMessage(this.a.q.obtainMessage(1, Boolean.valueOf(z)));
    }
}
