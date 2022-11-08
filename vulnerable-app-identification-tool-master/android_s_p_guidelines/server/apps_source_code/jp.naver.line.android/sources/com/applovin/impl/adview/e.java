package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinLogger;

class e implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ AdViewControllerImpl b;

    e(AdViewControllerImpl adViewControllerImpl, AppLovinAd appLovinAd) {
        this.b = adViewControllerImpl;
        this.a = appLovinAd;
    }

    public void run() {
        try {
            if (this.b.z != null) {
                this.b.z.adReceived(this.a);
            }
        } catch (Throwable th) {
            AppLovinLogger b = this.b.e;
            String str = AppLovinLogger.SDK_TAG;
            StringBuilder stringBuilder = new StringBuilder("Exception while running ad load callback: ");
            stringBuilder.append(th.getMessage());
            b.userError(str, stringBuilder.toString());
        }
    }
}
