package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.SoftReference;

public class ax {
    protected final AppLovinSdkImpl a;
    protected final AppLovinAdServiceImpl b;
    private AppLovinAd c;
    private String d;
    private String e;
    private SoftReference<AppLovinAdLoadListener> f;
    private final Object g = new Object();
    private volatile String h;
    private fy i;
    private volatile boolean j = false;
    private SoftReference<AppLovinInterstitialAdDialog> k;

    public ax(String str, AppLovinSdk appLovinSdk) {
        this.a = (AppLovinSdkImpl) appLovinSdk;
        this.b = (AppLovinAdServiceImpl) appLovinSdk.getAdService();
        this.e = str;
    }

    private void a(an anVar, AppLovinAdRewardListener appLovinAdRewardListener) {
        this.i = new fy(anVar, appLovinAdRewardListener, this.a);
        this.a.getTaskManager().a(this.i, fe.BACKGROUND);
    }

    private void a(ck ckVar, Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        h hVar = new h();
        hVar.a(appLovinAdClickListener);
        hVar.a(appLovinAdDisplayListener);
        hVar.a(appLovinAdRewardListener);
        this.a.getMediationService().showAd(ckVar, this.d, activity, hVar);
        a((AppLovinAd) ckVar);
    }

    private void a(q qVar, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (!qVar.getType().equals(AppLovinAdType.INCENTIVIZED)) {
            StringBuilder stringBuilder = new StringBuilder("Failed to render an ad of type ");
            stringBuilder.append(qVar.getType());
            stringBuilder.append(" in an Incentivized Ad interstitial.");
            this.a.getLogger().e("IncentivizedAdController", stringBuilder.toString());
            a((AppLovinAd) qVar, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
        } else if (gd.a((AppLovinAd) qVar, this.a)) {
            if (qVar.m() == o.DIRECT) {
                if (!gd.a(qVar instanceof aq ? (an) this.a.c().c(qVar.t()) : (an) qVar, context, this.a)) {
                    this.a.getLogger().userError("IncentivizedAdController", "Failed to render an ad: video cache has been removed.");
                    a((AppLovinAd) qVar, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
                    return;
                }
            }
            Runnable ayVar = new ay(this, qVar, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
            boolean booleanValue = ((Boolean) this.a.get(ea.ap)).booleanValue();
            if (booleanValue && (context instanceof Activity)) {
                Activity activity = (Activity) context;
                if (!activity.isFinishing()) {
                    bg.a().a(this.a).a(activity).a(this).a(appLovinAdRewardListener).a(ayVar).a().a((AppLovinAd) qVar);
                    return;
                }
            }
            if (booleanValue) {
                this.a.getLogger().userError("IncentivizedAdController", "Unable to show Incentivized Ad prompt. Must pass in an active Activity context.");
            }
            ayVar.run();
        } else {
            a((AppLovinAd) qVar, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
        }
    }

    private void a(AppLovinAd appLovinAd) {
        if (this.c != null && ((this.c instanceof aq) ? appLovinAd != ((aq) this.c).a() : appLovinAd != this.c)) {
            this.c = null;
        }
        this.d = null;
    }

    private void a(AppLovinAd appLovinAd, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (appLovinAd == null) {
            appLovinAd = this.c;
        }
        q qVar = (q) appLovinAd;
        if (qVar != null) {
            if ((qVar.m() == o.INDIRECT ? 1 : null) != null) {
                appLovinAd = gd.a((AppLovinAd) qVar, this.a);
                if (!(appLovinAd instanceof ck)) {
                    StringBuilder stringBuilder = new StringBuilder("Skipping mediated incentivized video playback: an unknown ad was pre-loaded: '");
                    stringBuilder.append(appLovinAd);
                    stringBuilder.append("'");
                    this.a.getLogger().userError("IncentivizedAdController", stringBuilder.toString());
                    e();
                    return;
                } else if (context instanceof Activity) {
                    a((ck) appLovinAd, (Activity) context, appLovinAdRewardListener, appLovinAdDisplayListener, appLovinAdClickListener);
                    return;
                } else {
                    this.a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: Activity required.");
                    a((AppLovinAd) qVar, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
                    return;
                }
            }
            a(qVar, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
            return;
        }
        this.a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
        e();
    }

    private void a(AppLovinAd appLovinAd, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener) {
        bv.a(appLovinAdVideoPlaybackListener, appLovinAd, 0.0d, false, this.a);
        bv.b(appLovinAdDisplayListener, appLovinAd, this.a);
    }

    private void e() {
        if (this.f != null) {
            AppLovinAdLoadListener appLovinAdLoadListener = (AppLovinAdLoadListener) this.f.get();
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED);
            }
        }
    }

    private AppLovinAdRewardListener f() {
        return new az(this);
    }

    public void a(AppLovinAd appLovinAd, Context context, String str, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (appLovinAdRewardListener == null) {
            appLovinAdRewardListener = f();
        }
        AppLovinAdRewardListener appLovinAdRewardListener2 = appLovinAdRewardListener;
        this.d = str;
        a(appLovinAd, context, appLovinAdRewardListener2, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    void a(AppLovinAd appLovinAd, AppLovinAdRewardListener appLovinAdRewardListener) {
        bv.a(appLovinAdRewardListener, appLovinAd, this.a);
    }

    public void a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a.getLogger().d("IncentivizedAdController", "User requested preload of incentivized ad...");
        this.f = new SoftReference(appLovinAdLoadListener);
        if (a()) {
            this.a.getLogger().userError("IncentivizedAdController", "Attempted to call preloadAndNotify: while an ad was already loaded or currently being played. Do not call preloadAndNotify: again until the last ad has been closed (adHidden).");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.adReceived(this.c);
            }
            return;
        }
        b(new ba(this, appLovinAdLoadListener));
    }

    void a(String str) {
        synchronized (this.g) {
            this.h = str;
        }
    }

    void a(String str, Context context) {
        if (str != null && ((Boolean) this.a.get(ea.aq)).booleanValue()) {
            new be(this.a, context, str).a();
        }
    }

    public boolean a() {
        return this.c != null;
    }

    String b() {
        String str;
        synchronized (this.g) {
            str = this.h;
        }
        return str;
    }

    void b(AppLovinAdLoadListener appLovinAdLoadListener) {
        if (TextUtils.isEmpty(this.e)) {
            this.b.a(appLovinAdLoadListener);
        } else {
            this.b.loadNextAdForZoneId(this.e, appLovinAdLoadListener);
        }
    }

    public String c() {
        return this.e;
    }

    public void d() {
        if (this.k != null) {
            AppLovinInterstitialAdDialog appLovinInterstitialAdDialog = (AppLovinInterstitialAdDialog) this.k.get();
            if (appLovinInterstitialAdDialog != null) {
                appLovinInterstitialAdDialog.dismiss();
            }
        }
    }
}
