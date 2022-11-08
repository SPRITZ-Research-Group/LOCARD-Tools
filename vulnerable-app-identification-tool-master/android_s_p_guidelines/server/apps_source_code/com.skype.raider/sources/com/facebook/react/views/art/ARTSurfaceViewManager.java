package com.facebook.react.views.art;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;

@ReactModule(name = "ARTSurfaceView")
public class ARTSurfaceViewManager extends BaseViewManager<ARTSurfaceView, c> {
    private static final YogaMeasureFunction MEASURE_FUNCTION = new YogaMeasureFunction() {
        public final long measure(YogaNode node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
            throw new IllegalStateException("SurfaceView should have explicit width and height set");
        }
    };
    protected static final String REACT_CLASS = "ARTSurfaceView";

    public String getName() {
        return REACT_CLASS;
    }

    public c createShadowNodeInstance() {
        c node = new c();
        node.a(MEASURE_FUNCTION);
        return node;
    }

    public Class<c> getShadowNodeClass() {
        return c.class;
    }

    protected ARTSurfaceView createViewInstance(ae reactContext) {
        return new ARTSurfaceView(reactContext);
    }

    public void updateExtraData(ARTSurfaceView root, Object extraData) {
        root.setSurfaceTextureListener((c) extraData);
    }

    public void setBackgroundColor(ARTSurfaceView view, int backgroundColor) {
    }
}
