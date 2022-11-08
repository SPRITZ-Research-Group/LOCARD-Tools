package com.facebook.react.flat;

import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;

abstract class FlatViewManager extends ViewGroupManager<t> {
    FlatViewManager() {
    }

    protected t createViewInstance(ae reactContext) {
        return new t(reactContext);
    }

    public void setBackgroundColor(t view, int backgroundColor) {
    }

    public void removeAllViews(t parent) {
        parent.removeAllViewsInLayout();
    }
}
