package com.facebook.react.flat;

import android.view.View;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ae;

abstract class VirtualViewManager<C extends r> extends ViewManager<View, C> {
    VirtualViewManager() {
    }

    protected View createViewInstance(ae reactContext) {
        throw new RuntimeException(getName() + " doesn't map to a View");
    }

    public void updateExtraData(View root, Object extraData) {
    }
}
