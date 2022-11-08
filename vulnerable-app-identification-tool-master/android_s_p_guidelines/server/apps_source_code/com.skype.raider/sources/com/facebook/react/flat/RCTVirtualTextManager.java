package com.facebook.react.flat;

import android.view.View;

public final class RCTVirtualTextManager extends VirtualViewManager<af> {
    static final String REACT_CLASS = "RCTVirtualText";

    public final /* bridge */ /* synthetic */ void updateExtraData(View view, Object obj) {
        super.updateExtraData(view, obj);
    }

    public final String getName() {
        return "RCTVirtualText";
    }

    public final af createShadowNodeInstance() {
        return new af();
    }

    public final Class<af> getShadowNodeClass() {
        return af.class;
    }
}
