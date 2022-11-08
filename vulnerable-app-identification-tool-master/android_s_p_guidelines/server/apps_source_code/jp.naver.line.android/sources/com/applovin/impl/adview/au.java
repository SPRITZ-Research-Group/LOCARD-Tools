package com.applovin.impl.adview;

class au implements Runnable {
    final /* synthetic */ ar a;

    au(ar arVar) {
        this.a = arVar;
    }

    public void run() {
        this.a.g.removeView(this.a.d);
        super.dismiss();
    }
}
