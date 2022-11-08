package com.skype.facebookaudiencenetwork;

import android.widget.LinearLayout;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;

public class AdChoicesLayoutManager extends SimpleViewManager<LinearLayout> {
    private static final String RN_CLASS = "RNAdChoicesView";

    public String getName() {
        return RN_CLASS;
    }

    protected LinearLayout createViewInstance(ae reactContext) {
        return new LinearLayout(reactContext);
    }
}
