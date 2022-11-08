package com.facebook.react.views.art;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ARTSurfaceViewManager$$PropsSetter implements e<ARTSurfaceViewManager, ARTSurfaceView> {
    private final HashMap<String, e<ARTSurfaceViewManager, ARTSurfaceView>> setters = new HashMap(18);

    public ARTSurfaceViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setAccessibilityComponentType((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setAccessibilityLabel((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setAccessibilityLiveRegion((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setBackgroundColor((ARTSurfaceView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setElevation((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setImportantForAccessibility((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setNativeId((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setOpacity((ARTSurfaceView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setRenderToHardwareTexture((ARTSurfaceView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setRotation((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setScaleX((ARTSurfaceView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setScaleY((ARTSurfaceView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setTestId((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setTransform((ARTSurfaceView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setTranslateX((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setTranslateY((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setViewLayerType((ARTSurfaceView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ARTSurfaceViewManager, ARTSurfaceView>(this) {
            final /* synthetic */ ARTSurfaceViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ARTSurfaceViewManager) viewManager).setZIndex((ARTSurfaceView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ARTSurfaceViewManager manager, ARTSurfaceView view, String name, x props) {
        e<ARTSurfaceViewManager, ARTSurfaceView> setter = (e) this.setters.get(name);
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
