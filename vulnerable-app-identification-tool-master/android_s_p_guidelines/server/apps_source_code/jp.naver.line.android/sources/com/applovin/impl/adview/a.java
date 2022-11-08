package com.applovin.impl.adview;

class a implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    a(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void run() {
        if (this.a.s != null) {
            StringBuilder stringBuilder = new StringBuilder("Detaching expanded ad: ");
            stringBuilder.append(this.a.s.a());
            this.a.e.d("AppLovinAdView", stringBuilder.toString());
            this.a.t = this.a.s;
            this.a.s = null;
            this.a.a(this.a.f);
        }
    }
}
