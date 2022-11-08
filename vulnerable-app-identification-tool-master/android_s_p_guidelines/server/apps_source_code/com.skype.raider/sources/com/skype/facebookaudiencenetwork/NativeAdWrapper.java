package com.skype.facebookaudiencenetwork;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.b.a;
import java.util.Arrays;

public class NativeAdWrapper extends AbstractNativeAdWrapper {
    public NativeAdWrapper(ae context) {
        super(context);
    }

    protected final boolean k() {
        boolean isBaseValid = super.k();
        MediaView mediaView = (MediaView) a.a(this, "native-ad-media-view");
        if (!isBaseValid || mediaView != null) {
            return isBaseValid;
        }
        FLog.w("NativeAdWrapper", "The native ad does not have a children marked with 'native-ad-media-view'. FAN SDK require this view to be set.");
        this.a.receiveEvent(getId(), AbstractNativeAdWrapperManager.ON_AD_FAILED, AbstractNativeAdWrapper.a(NativeAdWrapperFailureType.AD_MEDIA_MISSING));
        return false;
    }

    public final void e() {
        f();
        FLog.d("NativeAdWrapper", "Register view of native ad");
        if (k()) {
            MediaView mediaView = (MediaView) a.a(this, "native-ad-media-view");
            g();
            AdIconView adIconView = (AdIconView) a.a(this, "native-ad-icon-view");
            if (adIconView == null) {
                this.b.a(this, mediaView, Arrays.asList(new View[]{this, mediaView}));
            } else {
                this.b.a(this, mediaView, adIconView, Arrays.asList(new View[]{this, mediaView, adIconView}));
            }
            this.a.receiveEvent(getId(), AbstractNativeAdWrapperManager.ON_AD_REGISTERED, null);
        }
    }
}
