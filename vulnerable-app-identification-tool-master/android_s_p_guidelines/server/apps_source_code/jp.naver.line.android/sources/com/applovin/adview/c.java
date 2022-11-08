package com.applovin.adview;

import android.app.Activity;
import com.applovin.impl.adview.MediatedInterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

final class c implements Runnable {
    final /* synthetic */ AppLovinSdk a;
    final /* synthetic */ Activity b;

    c(AppLovinSdk appLovinSdk, Activity activity) {
        this.a = appLovinSdk;
        this.b = activity;
    }

    public final void run() {
        new MediatedInterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.a, this.b).show();
    }
}
