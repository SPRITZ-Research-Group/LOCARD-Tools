package com.applovin.adview;

import android.app.Activity;
import android.util.AttributeSet;
import android.view.View;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.impl.adview.MediatedInterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;

public class AppLovinMediatedInterstitialAd extends View {
    private AppLovinInterstitialAdDialog a;

    public AppLovinMediatedInterstitialAd(Activity activity, AttributeSet attributeSet) {
        this(activity, attributeSet, 0);
    }

    public AppLovinMediatedInterstitialAd(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
        this.a = null;
        AppLovinSdk instance = AppLovinSdk.getInstance(activity);
        if (!(instance == null || instance.hasCriticalErrors())) {
            this.a = new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(instance, activity);
        }
        setVisibility(8);
    }

    public static AppLovinInterstitialAdDialog create(AppLovinSdk appLovinSdk, Activity activity) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity != null) {
            return new MediatedInterstitialAdDialogCreatorImpl().createInterstitialAdDialog(appLovinSdk, activity);
        } else {
            throw new IllegalArgumentException("No activity specified");
        }
    }

    public static boolean isAdReadyToDisplay(Activity activity) {
        return AppLovinSdk.getInstance(activity).getAdService().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
    }

    public static void show(Activity activity) {
        if (activity != null) {
            AppLovinSdk instance = AppLovinSdk.getInstance(activity);
            if (instance != null && !instance.hasCriticalErrors()) {
                show(instance, activity);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("No activity specified");
    }

    @Deprecated
    public static void show(AppLovinSdk appLovinSdk, Activity activity) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity != null) {
            activity.runOnUiThread(new c(appLovinSdk, activity));
        } else {
            throw new IllegalArgumentException("No activity specified");
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            this.a.show();
        }
    }
}
