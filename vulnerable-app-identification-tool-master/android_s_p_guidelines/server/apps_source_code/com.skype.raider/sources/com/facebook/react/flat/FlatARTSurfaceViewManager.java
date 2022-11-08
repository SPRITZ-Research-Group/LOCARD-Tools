package com.facebook.react.flat;

import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.views.art.ARTSurfaceView;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;

public class FlatARTSurfaceViewManager extends BaseViewManager<ARTSurfaceView, o> {
    private static final YogaMeasureFunction MEASURE_FUNCTION = new YogaMeasureFunction() {
        public final long measure(YogaNode node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
            throw new IllegalStateException("SurfaceView should have explicit width and height set");
        }
    };
    static final String REACT_CLASS = "ARTSurfaceView";

    public String getName() {
        return REACT_CLASS;
    }

    public o createShadowNodeInstance() {
        o node = new o();
        node.a(MEASURE_FUNCTION);
        return node;
    }

    public Class<o> getShadowNodeClass() {
        return o.class;
    }

    protected ARTSurfaceView createViewInstance(ae reactContext) {
        return new ARTSurfaceView(reactContext);
    }

    public void updateExtraData(ARTSurfaceView root, Object extraData) {
        root.setSurfaceTextureListener((o) extraData);
    }
}
