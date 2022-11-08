package com.applovin.sdk;

import android.content.Context;
import android.util.Log;
import com.applovin.impl.sdk.gd;

public class AppLovinSdkSettings {
    private boolean a;
    private boolean b;
    private long c;
    private String d;
    private String e;
    private boolean f;

    public AppLovinSdkSettings() {
        this(null);
    }

    protected AppLovinSdkSettings(Context context) {
        this.b = gd.c(context);
        this.a = gd.b(context);
        this.c = -1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppLovinAdSize.INTERSTITIAL.getLabel());
        stringBuilder.append(",");
        stringBuilder.append(AppLovinAdSize.BANNER.getLabel());
        stringBuilder.append(",");
        stringBuilder.append(AppLovinAdSize.MREC.getLabel());
        this.d = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(AppLovinAdType.INCENTIVIZED.getLabel());
        stringBuilder.append(",");
        stringBuilder.append(AppLovinAdType.REGULAR.getLabel());
        stringBuilder.append(",");
        stringBuilder.append(AppLovinAdType.NATIVE.getLabel());
        this.e = stringBuilder.toString();
    }

    @Deprecated
    public String getAutoPreloadSizes() {
        return this.d;
    }

    @Deprecated
    public String getAutoPreloadTypes() {
        return this.e;
    }

    public long getBannerAdRefreshSeconds() {
        return this.c;
    }

    public boolean isMuted() {
        return this.f;
    }

    public boolean isTestAdsEnabled() {
        return this.a;
    }

    public boolean isVerboseLoggingEnabled() {
        return this.b;
    }

    @Deprecated
    public void setAutoPreloadSizes(String str) {
        this.d = str;
    }

    @Deprecated
    public void setAutoPreloadTypes(String str) {
        this.e = str;
    }

    public void setBannerAdRefreshSeconds(long j) {
        this.c = j;
    }

    public void setMuted(boolean z) {
        this.f = z;
    }

    public void setTestAdsEnabled(boolean z) {
        this.a = z;
    }

    public void setVerboseLogging(boolean z) {
        if (gd.a()) {
            Log.e(AppLovinLogger.SDK_TAG, "[AppLovinSdkSettings] Ignoring setting of verbose logging - it is configured from Android manifest already or AppLovinSdkSettings was initialized without a context.");
        } else {
            this.b = z;
        }
    }
}
