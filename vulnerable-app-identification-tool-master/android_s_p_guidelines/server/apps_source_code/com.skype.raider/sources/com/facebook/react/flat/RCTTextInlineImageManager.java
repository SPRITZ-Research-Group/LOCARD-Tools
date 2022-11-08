package com.facebook.react.flat;

import android.view.View;

public final class RCTTextInlineImageManager extends VirtualViewManager<ac> {
    static final String REACT_CLASS = "RCTTextInlineImage";

    public final /* bridge */ /* synthetic */ void updateExtraData(View view, Object obj) {
        super.updateExtraData(view, obj);
    }

    public final String getName() {
        return REACT_CLASS;
    }

    public final ac createShadowNodeInstance() {
        return new ac();
    }

    public final Class<ac> getShadowNodeClass() {
        return ac.class;
    }
}
