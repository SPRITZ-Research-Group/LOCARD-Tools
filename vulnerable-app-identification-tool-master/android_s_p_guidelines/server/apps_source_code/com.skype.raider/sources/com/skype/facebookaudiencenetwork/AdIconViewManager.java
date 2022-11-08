package com.skype.facebookaudiencenetwork;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;

public class AdIconViewManager extends SimpleViewManager<AdIconView> {
    private static final String RN_CLASS = "RNAdIconView";

    public String getName() {
        return RN_CLASS;
    }

    protected AdIconView createViewInstance(ae reactContext) {
        AdIconView adIconView = new AdIconView(reactContext);
        adIconView.setFocusable(false);
        return adIconView;
    }
}
