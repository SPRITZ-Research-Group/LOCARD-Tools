package com.facebook.react.flat;

import com.facebook.react.uimanager.h;
import com.facebook.react.views.modal.ReactModalHostManager;

public class RCTModalHostManager extends ReactModalHostManager {
    static final String REACT_CLASS = "RCTModalHostView";

    public h createShadowNodeInstance() {
        return new p();
    }

    public Class<? extends h> getShadowNodeClass() {
        return p.class;
    }
}
