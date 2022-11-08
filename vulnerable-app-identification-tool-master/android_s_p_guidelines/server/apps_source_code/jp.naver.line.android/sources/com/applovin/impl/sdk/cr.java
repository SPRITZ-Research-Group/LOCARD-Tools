package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationErrorCode;
import com.applovin.mediation.AppLovinMediationLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.concurrent.TimeUnit;

class cr implements Runnable {
    final /* synthetic */ ck a;
    final /* synthetic */ cw b;
    final /* synthetic */ cp c;

    cr(cp cpVar, ck ckVar, cw cwVar) {
        this.c = cpVar;
        this.a = ckVar;
        this.b = cwVar;
    }

    public void run() {
        StringBuilder stringBuilder;
        cp cpVar;
        AppLovinMediationErrorCode appLovinMediationErrorCode;
        this.c.b(this.a);
        AppLovinMediationLoadListener csVar = new cs(this);
        if (this.a.getType() == AppLovinAdType.REGULAR) {
            if (this.a.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.c.b.loadInterstitialAd(this.c.e, this.c.c.getApplicationContext(), csVar);
                if (!this.b.c.get()) {
                    if (this.a.g() != 0) {
                        stringBuilder = new StringBuilder("Failing ad ");
                        stringBuilder.append(this.a);
                        stringBuilder.append(" since it has 0 timeout");
                        this.c.d.d("MediationAdapterWrapper", stringBuilder.toString());
                        this.c.a((int) AppLovinErrorCodes.MEDIATION_ADAPTER_IMMEDIATE_TIMEOUT, this.b);
                    } else if (this.a.g() > 0) {
                        stringBuilder = new StringBuilder("Setting timeout ");
                        stringBuilder.append(this.a.g());
                        stringBuilder.append(" sec. for ");
                        stringBuilder.append(this.a);
                        this.c.d.d("MediationAdapterWrapper", stringBuilder.toString());
                        this.c.c.getTaskManager().a(new cx(this.c, this.b, null), fe.MAIN, TimeUnit.SECONDS.toMillis((long) this.a.g()));
                    } else {
                        stringBuilder = new StringBuilder("Negative timeout set for ");
                        stringBuilder.append(this.a);
                        stringBuilder.append(", not scheduling a timeout");
                        this.c.d.d("MediationAdapterWrapper", stringBuilder.toString());
                    }
                }
            }
            stringBuilder = new StringBuilder("Failed to load ");
            stringBuilder.append(this.a);
            stringBuilder.append(": ");
            stringBuilder.append(this.a.getSize());
            stringBuilder.append("> is not a supported ad size");
            this.c.d.e("MediationAdapterWrapper", stringBuilder.toString());
            cpVar = this.c;
            appLovinMediationErrorCode = AppLovinMediationErrorCode.INTERNAL_AD_SIZE_NOT_SUPPORTED;
        } else if (this.a.getType() == AppLovinAdType.INCENTIVIZED) {
            this.c.b.loadIncentivizedAd(this.c.e, this.c.c.getApplicationContext(), csVar);
            if (this.b.c.get()) {
                if (this.a.g() != 0) {
                    stringBuilder = new StringBuilder("Failing ad ");
                    stringBuilder.append(this.a);
                    stringBuilder.append(" since it has 0 timeout");
                    this.c.d.d("MediationAdapterWrapper", stringBuilder.toString());
                    this.c.a((int) AppLovinErrorCodes.MEDIATION_ADAPTER_IMMEDIATE_TIMEOUT, this.b);
                } else if (this.a.g() > 0) {
                    stringBuilder = new StringBuilder("Negative timeout set for ");
                    stringBuilder.append(this.a);
                    stringBuilder.append(", not scheduling a timeout");
                    this.c.d.d("MediationAdapterWrapper", stringBuilder.toString());
                } else {
                    stringBuilder = new StringBuilder("Setting timeout ");
                    stringBuilder.append(this.a.g());
                    stringBuilder.append(" sec. for ");
                    stringBuilder.append(this.a);
                    this.c.d.d("MediationAdapterWrapper", stringBuilder.toString());
                    this.c.c.getTaskManager().a(new cx(this.c, this.b, null), fe.MAIN, TimeUnit.SECONDS.toMillis((long) this.a.g()));
                }
            }
        } else {
            stringBuilder = new StringBuilder("Failed to load ");
            stringBuilder.append(this.a);
            stringBuilder.append(": ");
            stringBuilder.append(this.a.getType());
            stringBuilder.append(" is not a supported ad type");
            this.c.d.e("MediationAdapterWrapper", stringBuilder.toString());
            cpVar = this.c;
            appLovinMediationErrorCode = AppLovinMediationErrorCode.INTERNAL_AD_TYPE_NOT_SUPPORTED;
        }
        cpVar.a(appLovinMediationErrorCode.getErrorCode(), this.b);
        if (this.b.c.get()) {
            if (this.a.g() != 0) {
                stringBuilder = new StringBuilder("Failing ad ");
                stringBuilder.append(this.a);
                stringBuilder.append(" since it has 0 timeout");
                this.c.d.d("MediationAdapterWrapper", stringBuilder.toString());
                this.c.a((int) AppLovinErrorCodes.MEDIATION_ADAPTER_IMMEDIATE_TIMEOUT, this.b);
            } else if (this.a.g() > 0) {
                stringBuilder = new StringBuilder("Setting timeout ");
                stringBuilder.append(this.a.g());
                stringBuilder.append(" sec. for ");
                stringBuilder.append(this.a);
                this.c.d.d("MediationAdapterWrapper", stringBuilder.toString());
                this.c.c.getTaskManager().a(new cx(this.c, this.b, null), fe.MAIN, TimeUnit.SECONDS.toMillis((long) this.a.g()));
            } else {
                stringBuilder = new StringBuilder("Negative timeout set for ");
                stringBuilder.append(this.a);
                stringBuilder.append(", not scheduling a timeout");
                this.c.d.d("MediationAdapterWrapper", stringBuilder.toString());
            }
        }
    }
}
