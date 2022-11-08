package com.skype.facebookaudiencenetwork;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;

public abstract class AbstractNativeAdWrapperManager<T extends AbstractNativeAdWrapper> extends ViewGroupManager<T> {
    public static final int FETCH_AD = 4;
    public static final String ON_AD_FAILED = "onAdFailed";
    public static final String ON_AD_LOADED = "onAdLoaded";
    public static final String ON_AD_REGISTERED = "onAdRegistered";
    public static final int PERFORM_ACTION = 3;
    public static final int REGISTER_VIEW = 1;
    private static final String TAG = "AbstractNativeAdWrapperManager";
    public static final int UNREGISTER_VIEW = 2;
    protected final ag reactAppContext;

    public AbstractNativeAdWrapperManager(ag reactAppContext) {
        this.reactAppContext = reactAppContext;
    }

    @ReactProp(name = "placementId")
    public void setPlacementId(T view, String placementId) {
        view.setPlacementId(placementId);
    }

    @ReactProp(name = "adId")
    public void setAdId(T view, String adId) {
        view.setAdId(adId);
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return e.a(ON_AD_LOADED, e.a("registrationName", ON_AD_LOADED), ON_AD_REGISTERED, e.a("registrationName", ON_AD_REGISTERED), ON_AD_FAILED, e.a("registrationName", ON_AD_FAILED));
    }

    public Map<String, Integer> getCommandsMap() {
        return e.a("registerView", Integer.valueOf(1), "unregisterView", Integer.valueOf(2), "performAction", Integer.valueOf(3), "fetchAd", Integer.valueOf(4));
    }

    public void receiveCommand(T nativeAdWrapper, int commandType, al args) {
        FLog.d(TAG, String.format("Command %d received by %s.", new Object[]{Integer.valueOf(commandType), getClass().getSimpleName()}));
        switch (commandType) {
            case 1:
                nativeAdWrapper.e();
                return;
            case 2:
                nativeAdWrapper.f();
                return;
            case 3:
                nativeAdWrapper.performClick();
                return;
            case 4:
                nativeAdWrapper.a(this.reactAppContext);
                return;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), getClass().getSimpleName()}));
        }
    }
}
