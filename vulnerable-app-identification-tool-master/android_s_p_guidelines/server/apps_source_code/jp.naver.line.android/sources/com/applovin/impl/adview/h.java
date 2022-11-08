package com.applovin.impl.adview;

class h implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    private h(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    /* synthetic */ h(AdViewControllerImpl adViewControllerImpl, a aVar) {
        this(adViewControllerImpl);
    }

    public void run() {
        if (this.a.l != null) {
            this.a.l.setVisibility(8);
        }
    }
}
