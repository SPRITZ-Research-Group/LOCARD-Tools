package com.skype.facebookaudiencenetwork;

import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.g;
import com.facebook.ads.h;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.b.a;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

public abstract class AbstractNativeAdWrapper extends ReactViewGroup {
    protected final RCTEventEmitter a;
    protected g b;
    private String c;
    private String d;
    private AdChoicesView e;

    abstract void e();

    public AbstractNativeAdWrapper(ae context) {
        super(context);
        this.a = (RCTEventEmitter) context.a(RCTEventEmitter.class);
    }

    public void setPlacementId(String placementId) {
        if (this.c == null) {
            this.c = placementId;
        }
    }

    public void setAdId(String adId) {
        if (this.d == null) {
            this.d = adId;
        }
    }

    public void setNativeAd(ag reactContext) {
        if (this.b == null && this.d != null) {
            NativeAdsManager adsManager = ((NativeAdsManagersModule) reactContext.b(NativeAdsManagersModule.class)).getAdsManager(this.c);
            if (adsManager != null) {
                this.b = adsManager.a(this.d);
            }
        }
    }

    public final void a(ag reactContext) {
        FLog.d("AbstractNativeAdWrapper", "Fetching native ad from AdsManager");
        setNativeAd(reactContext);
        if (this.b == null) {
            this.a.receiveEvent(getId(), AbstractNativeAdWrapperManager.ON_AD_FAILED, a(NativeAdWrapperFailureType.NOT_FOUND));
            return;
        }
        h hVar = this.b;
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("title", hVar.h());
        writableNativeMap.putString("sponsoredLabel", hVar.l());
        writableNativeMap.putString("socialContext", hVar.k());
        writableNativeMap.putString("body", hVar.i());
        writableNativeMap.putString("callToAction", hVar.j());
        writableNativeMap.putString("adChoicesImageUrl", hVar.m());
        writableNativeMap.putString("adChoicesLinkUrl", hVar.n());
        writableNativeMap.putString("adChoicesText", hVar.o());
        this.a.receiveEvent(getId(), AbstractNativeAdWrapperManager.ON_AD_LOADED, writableNativeMap);
    }

    public final void f() {
        FLog.d("AbstractNativeAdWrapper", "Unregister view of native ad");
        if (this.b != null) {
            this.b.r();
        }
    }

    protected final LinearLayout g() {
        LinearLayout adChoiceContainer = (LinearLayout) a.a(this, "native-ad-choices-view");
        if (adChoiceContainer != null) {
            this.e = new AdChoicesView(getContext(), this.b);
            this.e.setLayoutParams(new LayoutParams(-1, -1));
            adChoiceContainer.addView(this.e);
        }
        return adChoiceContainer;
    }

    protected boolean k() {
        h nativeAd = this.b;
        if (nativeAd == null) {
            this.a.receiveEvent(getId(), AbstractNativeAdWrapperManager.ON_AD_FAILED, a(NativeAdWrapperFailureType.REGISTER_EMPTY_VIEW));
            return false;
        } else if (!nativeAd.f()) {
            this.a.receiveEvent(getId(), AbstractNativeAdWrapperManager.ON_AD_FAILED, a(NativeAdWrapperFailureType.AD_NOT_LOADED));
            return false;
        } else if (!nativeAd.e()) {
            return true;
        } else {
            this.a.receiveEvent(getId(), AbstractNativeAdWrapperManager.ON_AD_FAILED, a(NativeAdWrapperFailureType.AD_INVALIDATED));
            return false;
        }
    }

    protected static ar a(NativeAdWrapperFailureType exceptionType) {
        ar event = new WritableNativeMap();
        event.putInt("errorCode", exceptionType.a());
        event.putString("errorMessage", exceptionType.b());
        return event;
    }
}
