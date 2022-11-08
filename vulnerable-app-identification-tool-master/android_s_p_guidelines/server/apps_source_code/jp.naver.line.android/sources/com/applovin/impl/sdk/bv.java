package com.applovin.impl.sdk;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

public class bv {
    public static void a(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cg(appLovinAdViewEventListener, appLovinAd, appLovinAdView, appLovinSdk));
        }
    }

    public static void a(AppLovinAdClickListener appLovinAdClickListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdClickListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cd(appLovinAdClickListener, appLovinAd, appLovinSdk));
        }
    }

    public static void a(AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdDisplayListener != null) {
            AppLovinSdkUtils.runOnUiThread(new bw(appLovinAdDisplayListener, appLovinAd, appLovinSdk));
        }
    }

    public static void a(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, int i, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ca(appLovinAdRewardListener, appLovinAd, i, appLovinSdk));
        }
    }

    public static void a(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cb(appLovinAdRewardListener, appLovinAd, appLovinSdk));
        }
    }

    public static void a(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map<String, String> map, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new bx(appLovinAdRewardListener, appLovinAd, map, appLovinSdk));
        }
    }

    public static void a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAd appLovinAd, double d, boolean z, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdVideoPlaybackListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cf(appLovinAdVideoPlaybackListener, appLovinAd, d, z, appLovinSdk));
        }
    }

    public static void a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdVideoPlaybackListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ce(appLovinAdVideoPlaybackListener, appLovinAd, appLovinSdk));
        }
    }

    private static AppLovinAd b(AppLovinAd appLovinAd) {
        q qVar = (q) appLovinAd;
        return qVar.u() != null ? qVar.u() : appLovinAd;
    }

    public static void b(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ch(appLovinAdViewEventListener, appLovinAd, appLovinAdView, appLovinSdk));
        }
    }

    public static void b(AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdDisplayListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cc(appLovinAdDisplayListener, appLovinAd, appLovinSdk));
        }
    }

    public static void b(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map<String, String> map, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new by(appLovinAdRewardListener, appLovinAd, map, appLovinSdk));
        }
    }

    public static void c(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ci(appLovinAdViewEventListener, appLovinAd, appLovinAdView, appLovinSdk));
        }
    }

    public static void c(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map<String, String> map, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new bz(appLovinAdRewardListener, appLovinAd, map, appLovinSdk));
        }
    }
}
