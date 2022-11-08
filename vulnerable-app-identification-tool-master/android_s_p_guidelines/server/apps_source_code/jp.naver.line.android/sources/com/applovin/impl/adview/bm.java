package com.applovin.impl.adview;

import com.applovin.impl.sdk.fe;
import com.applovin.impl.sdk.fu;

class bm implements Runnable {
    final /* synthetic */ az a;

    bm(az azVar) {
        this.a = azVar;
    }

    public void run() {
        if (this.a.currentAd != null && !this.a.currentAd.ae().getAndSet(true)) {
            this.a.sdk.getTaskManager().a(new fu(this.a.currentAd, this.a.sdk), fe.BACKGROUND);
        }
    }
}
