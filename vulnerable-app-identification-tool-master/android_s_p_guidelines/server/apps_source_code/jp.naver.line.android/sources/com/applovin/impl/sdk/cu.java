package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.mediation.AppLovinMediationDisplayListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;

class cu implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ ck b;
    final /* synthetic */ Activity c;
    final /* synthetic */ cp d;

    cu(cp cpVar, h hVar, ck ckVar, Activity activity) {
        this.d = cpVar;
        this.a = hVar;
        this.b = ckVar;
        this.c = activity;
    }

    public void run() {
        AppLovinMediationDisplayListener cvVar = new cv(this);
        AppLovinLogger d;
        StringBuilder stringBuilder;
        if (this.b.getType() == AppLovinAdType.REGULAR) {
            if (this.b.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.d.b.showInterstitialAd(this.b.d(), this.d.e, this.c, cvVar);
                return;
            }
            d = this.d.d;
            stringBuilder = new StringBuilder("Failed to display ");
            stringBuilder.append(this.b);
            stringBuilder.append(": ");
            stringBuilder.append(this.b.getSize());
            stringBuilder.append(" is not a supported ad size");
            d.e("MediationAdapterWrapper", stringBuilder.toString());
            throw new IllegalArgumentException("Unsupported ad size");
        } else if (this.b.getType() == AppLovinAdType.INCENTIVIZED) {
            this.d.b.showIncentivizedAd(this.b.d(), this.d.e, this.c, cvVar);
        } else {
            d = this.d.d;
            stringBuilder = new StringBuilder("Failed to display ");
            stringBuilder.append(this.b);
            stringBuilder.append(": ");
            stringBuilder.append(this.b.getType());
            stringBuilder.append(" is not a supported ad type");
            d.e("MediationAdapterWrapper", stringBuilder.toString());
            throw new IllegalArgumentException("Unsupported ad type");
        }
    }
}
