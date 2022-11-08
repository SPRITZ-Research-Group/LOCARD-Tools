package com.applovin.impl.adview;

import com.applovin.impl.sdk.an;

class ab implements Runnable {
    final /* synthetic */ an a;
    final /* synthetic */ n b;

    ab(n nVar, an anVar) {
        this.b = nVar;
        this.a = anVar;
    }

    public void run() {
        this.b.getSettings().setMediaPlaybackRequiresUserGesture(this.a.ag());
    }
}
