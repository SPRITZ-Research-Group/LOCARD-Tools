package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.mediation.AppLovinMediationAdapterConfig;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

class cp {
    private final String a;
    private final AppLovinMediationAdapter b;
    private final AppLovinSdkImpl c;
    private final AppLovinLogger d;
    private cm e;
    private final AtomicBoolean f;

    cp(String str, AppLovinMediationAdapter appLovinMediationAdapter, AppLovinSdkImpl appLovinSdkImpl) {
        if (str == null) {
            throw new IllegalArgumentException("No implementation name specified");
        } else if (appLovinMediationAdapter == null) {
            throw new IllegalArgumentException("No implementation specified");
        } else if (appLovinSdkImpl != null) {
            this.a = str;
            this.b = appLovinMediationAdapter;
            this.c = appLovinSdkImpl;
            this.d = appLovinSdkImpl.getLogger();
            this.e = new cm(str, appLovinSdkImpl);
            this.f = new AtomicBoolean(true);
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    private void a(int i, cw cwVar) {
        if (cwVar.c.compareAndSet(false, true) && cwVar.b != null) {
            cwVar.b.failedToReceiveAd(i);
        }
    }

    private void a(AppLovinMediatedAdInfo appLovinMediatedAdInfo, cw cwVar) {
        if (cwVar.c.compareAndSet(false, true) && cwVar.b != null) {
            cwVar.b.adReceived(new ck(cwVar.a, true, appLovinMediatedAdInfo));
        }
    }

    private void a(String str, Runnable runnable) {
        if (runnable != null) {
            StringBuilder stringBuilder;
            try {
                stringBuilder = new StringBuilder("Running implementation operation '");
                stringBuilder.append(str);
                stringBuilder.append("'...");
                this.d.d("MediationAdapterWrapper", stringBuilder.toString());
                runnable.run();
                return;
            } catch (Throwable th) {
                stringBuilder = new StringBuilder("Unable to implementation operation run ");
                stringBuilder.append(str);
                stringBuilder.append(", marking ");
                stringBuilder.append(this);
                stringBuilder.append(" as disabled");
                this.d.e("MediationAdapterWrapper", stringBuilder.toString(), th);
                a("fail_".concat(String.valueOf(str)));
                return;
            }
        }
        throw new IllegalArgumentException("No operation specified");
    }

    private void b(ck ckVar) {
        Map e = ckVar.e();
        if (e != null) {
            this.e.b(e);
        }
    }

    public String a() {
        return this.a;
    }

    void a(ck ckVar) {
        StringBuilder stringBuilder;
        if (ckVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!this.f.get()) {
            stringBuilder = new StringBuilder("Mediation implementation '");
            stringBuilder.append(f());
            stringBuilder.append("' was disabled due to earlier failures. Preparing ads with this implementation is disabled.");
            this.d.w("MediationAdapterWrapper", stringBuilder.toString());
        } else if (this.b.isReady()) {
            a("ad_prepare", new ct(this, ckVar));
        } else {
            stringBuilder = new StringBuilder("Mediation implementation '");
            stringBuilder.append(f());
            stringBuilder.append("' is not ready.");
            this.d.userError("MediationAdapterWrapper", stringBuilder.toString());
        }
    }

    void a(ck ckVar, Activity activity, h hVar) {
        if (ckVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!ckVar.a()) {
            throw new IllegalArgumentException("Mediated ad is not ready");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (hVar != null) {
            a("ad_render", new cu(this, hVar, ckVar, activity));
        } else {
            throw new IllegalArgumentException("No listeners specified");
        }
    }

    void a(ck ckVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        StringBuilder stringBuilder;
        if (ckVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!this.f.get()) {
            stringBuilder = new StringBuilder("Mediation implementation '");
            stringBuilder.append(f());
            stringBuilder.append("' was disabled due to earlier failures. Loading ads with this implementation is disabled.");
            this.d.userError("MediationAdapterWrapper", stringBuilder.toString());
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.MEDIATION_ADAPTER_DISABLED);
            }
        } else if (this.b.isReady()) {
            a("ad_load", new cr(this, ckVar, new cw(ckVar, appLovinAdLoadListener)));
        } else {
            stringBuilder = new StringBuilder("Mediation implementation '");
            stringBuilder.append(f());
            stringBuilder.append("' is not ready.");
            this.d.userError("MediationAdapterWrapper", stringBuilder.toString());
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.MEDIATION_ADAPTER_READY_AD);
            }
        }
    }

    void a(String str) {
        StringBuilder stringBuilder = new StringBuilder("Marking ");
        stringBuilder.append(f());
        stringBuilder.append(" as disabled due to: ");
        stringBuilder.append(str);
        this.d.i("MediationAdapterWrapper", stringBuilder.toString());
        this.f.set(false);
    }

    void a(Map<String, String> map) {
        a("init", new cq(this, map));
    }

    boolean b() {
        return this.f.get();
    }

    boolean c() {
        return b() && this.b.isReady();
    }

    AppLovinMediationAdapter d() {
        return this.b;
    }

    String e() {
        if (this.b != null) {
            try {
                return this.b.getVersion();
            } catch (Throwable th) {
                StringBuilder stringBuilder = new StringBuilder("Unable to get implementation version, marking ");
                stringBuilder.append(this);
                stringBuilder.append(" as disabled");
                this.d.e("MediationAdapterWrapper", stringBuilder.toString(), th);
                a("fail_version");
            }
        }
        return null;
    }

    String f() {
        return this.b.getClass().getName();
    }

    AppLovinMediationAdapterConfig g() {
        return this.e;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[MediationAdapterWrapper implementation: ");
        stringBuilder.append(f());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
