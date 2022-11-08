package com.facebook.react.uimanager;

import android.view.View;

public abstract class SimpleViewManager<T extends View> extends BaseViewManager<T, h> {
    public h createShadowNodeInstance() {
        return new h();
    }

    public Class<h> getShadowNodeClass() {
        return h.class;
    }

    public void updateExtraData(T t, Object extraData) {
    }
}
