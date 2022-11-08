package com.skype.facebookaudiencenetwork;

import com.facebook.react.bridge.ag;
import com.facebook.react.uimanager.ae;

public class NativeAdWrapperManager extends AbstractNativeAdWrapperManager<NativeAdWrapper> {
    private static final String RN_CLASS = "RNNativeAdWrapper";

    protected NativeAdWrapper createViewInstance(ae themedReactContext) {
        return new NativeAdWrapper(themedReactContext);
    }

    public NativeAdWrapperManager(ag reactAppContext) {
        super(reactAppContext);
    }

    public String getName() {
        return RN_CLASS;
    }
}
