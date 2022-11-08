package com.facebook.react.flat;

import android.view.View;

public final class RCTRawTextManager extends VirtualViewManager<aa> {
    static final String REACT_CLASS = "RCTRawText";

    public final /* bridge */ /* synthetic */ void updateExtraData(View view, Object obj) {
        super.updateExtraData(view, obj);
    }

    public final String getName() {
        return "RCTRawText";
    }

    public final aa createShadowNodeInstance() {
        return new aa();
    }

    public final Class<aa> getShadowNodeClass() {
        return aa.class;
    }
}
