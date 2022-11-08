package com.facebook.react.flat;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class RCTViewManager$$PropsSetter implements e<RCTViewManager, t> {
    private final HashMap<String, e<RCTViewManager, t>> setters = new HashMap(23);

    public RCTViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setAccessibilityComponentType((t) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setAccessibilityLabel((t) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setAccessibilityLiveRegion((t) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setBackgroundColor((t) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setElevation((t) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("hitSlop", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setHitSlop((t) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setImportantForAccessibility((t) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("nativeBackgroundAndroid", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setHotspot((t) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setNativeId((t) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("needsOffscreenAlphaCompositing", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setNeedsOffscreenAlphaCompositing((t) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setOpacity((t) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("pointerEvents", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setPointerEvents((t) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("removeClippedSubviews", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setRemoveClippedSubviews((t) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setRenderToHardwareTexture((t) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setRotation((t) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setScaleX((t) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setScaleY((t) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setTestId((t) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setTransform((t) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setTranslateX((t) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setTranslateY((t) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setViewLayerType((t) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<RCTViewManager, t>(this) {
            final /* synthetic */ RCTViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTViewManager) viewManager).setZIndex((t) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(RCTViewManager manager, t view, String name, x props) {
        e<RCTViewManager, t> setter = (e) this.setters.get(name);
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
        props.put("hitSlop", "Map");
        props.put("importantForAccessibility", "String");
        props.put("nativeBackgroundAndroid", "Map");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("needsOffscreenAlphaCompositing", "boolean");
        props.put("opacity", "number");
        props.put("pointerEvents", "String");
        props.put("removeClippedSubviews", "boolean");
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
