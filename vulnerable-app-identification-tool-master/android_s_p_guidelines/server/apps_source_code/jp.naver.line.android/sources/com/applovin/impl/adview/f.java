package com.applovin.impl.adview;

class f implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ AdViewControllerImpl b;

    f(AdViewControllerImpl adViewControllerImpl, int i) {
        this.b = adViewControllerImpl;
        this.a = i;
    }

    public void run() {
        try {
            if (this.b.z != null) {
                this.b.z.failedToReceiveAd(this.a);
            }
        } catch (Throwable th) {
            this.b.e.userError("AppLovinAdView", "Exception while running app load  callback", th);
        }
    }
}
