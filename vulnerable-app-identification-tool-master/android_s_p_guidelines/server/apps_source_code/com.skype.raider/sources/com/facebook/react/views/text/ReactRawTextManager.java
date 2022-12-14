package com.facebook.react.views.text;

import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ae;

@ReactModule(name = "RCTRawText")
public class ReactRawTextManager extends ReactTextViewManager {
    @VisibleForTesting
    public static final String REACT_CLASS = "RCTRawText";

    public String getName() {
        return REACT_CLASS;
    }

    public ReactTextView createViewInstance(ae context) {
        throw new IllegalStateException("RKRawText doesn't map into a native view");
    }

    public void updateExtraData(ReactTextView view, Object extraData) {
    }

    public g createShadowNodeInstance() {
        return new i();
    }
}
