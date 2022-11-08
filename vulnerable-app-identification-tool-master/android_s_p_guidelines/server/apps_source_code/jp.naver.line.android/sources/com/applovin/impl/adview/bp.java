package com.applovin.impl.adview;

class bp implements Runnable {
    final /* synthetic */ az a;

    bp(az azVar) {
        this.a = azVar;
    }

    public void run() {
        n adWebView = ((AdViewControllerImpl) this.a.a.getAdViewController()).getAdWebView();
        if (adWebView != null) {
            adWebView.a("javascript:al_onPoststitialShow();");
        }
    }
}
