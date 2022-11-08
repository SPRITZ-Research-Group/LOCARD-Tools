package com.applovin.impl.adview;

class d implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    d(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void run() {
        this.a.d();
        if (this.a.b != null && this.a.l != null && this.a.l.getParent() == null) {
            this.a.b.addView(this.a.l);
            AdViewControllerImpl.b(this.a.l, this.a.p.getSize());
        }
    }
}
