package com.facebook.react.flat;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import com.facebook.react.views.art.ARTSurfaceView;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class FlatARTSurfaceViewManager$$PropsSetter implements e<FlatARTSurfaceViewManager, ARTSurfaceView> {
    private final HashMap<String, e<FlatARTSurfaceViewManager, ARTSurfaceView>> setters = new HashMap(18);

    public FlatARTSurfaceViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setAccessibilityComponentType((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setAccessibilityLabel((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setAccessibilityLiveRegion((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setBackgroundColor((ARTSurfaceView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setElevation((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setImportantForAccessibility((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setNativeId((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setOpacity((ARTSurfaceView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setRenderToHardwareTexture((ARTSurfaceView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setRotation((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setScaleX((ARTSurfaceView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setScaleY((ARTSurfaceView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setTestId((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setTransform((ARTSurfaceView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setTranslateX((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setTranslateY((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setViewLayerType((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<FlatARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ FlatARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((FlatARTSurfaceViewManager) viewManager).setZIndex((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(FlatARTSurfaceViewManager manager, ARTSurfaceView view, String name, x props) {
        e<FlatARTSurfaceViewManager, ARTSurfaceView> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("backgroundColor", "Color");
        props.put("elevation", "number");
        props.put("importantForAccessibility", "String");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("opacity", "number");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
