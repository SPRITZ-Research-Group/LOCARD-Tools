package com.google.ads.mediation.customevent;

import com.google.ads.mediation.i;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzane;

@VisibleForTesting
final class a {
    private final CustomEventAdapter a;
    private final i b;

    public a(CustomEventAdapter customEventAdapter, i iVar) {
        this.a = customEventAdapter;
        this.b = iVar;
    }

    public final void onClick() {
        zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
        this.b.onClick(this.a);
    }
}
