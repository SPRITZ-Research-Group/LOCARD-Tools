package com.applovin.adview;

import android.content.Context;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

final class b implements Runnable {
    final /* synthetic */ AppLovinSdk a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;

    b(AppLovinSdk appLovinSdk, Context context, String str) {
        this.a = appLovinSdk;
        this.b = context;
        this.c = str;
    }

    public final void run() {
        new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.a, this.b).show(this.c);
    }
}
