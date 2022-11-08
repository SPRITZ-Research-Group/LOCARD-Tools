package com.microsoft.react.clippedview;

import com.facebook.react.bridge.al;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public class ClippedViewManager extends ViewGroupManager<ClippedView> {
    public static final String REACT_CLASS = "ClippedView";

    public String getName() {
        return REACT_CLASS;
    }

    public ClippedView createViewInstance(ae reactContext) {
        return new ClippedView(reactContext);
    }

    @ReactProp(name = "path")
    public void setSrc(ClippedView view, @Nullable al shapePath) {
        view.setShapePath(shapePath);
    }
}
