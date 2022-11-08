package com.facebook.react.flat;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import com.facebook.react.views.modal.ReactModalHostView;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class RCTModalHostManager$$PropsSetter implements e<RCTModalHostManager, ReactModalHostView> {
    private final HashMap<String, e<RCTModalHostManager, ReactModalHostView>> setters = new HashMap(21);

    public RCTModalHostManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setAccessibilityComponentType((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setAccessibilityLabel((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setAccessibilityLiveRegion((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("animationType", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setAnimationType((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setBackgroundColor((ReactModalHostView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setElevation((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("hardwareAccelerated", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setHardwareAccelerated((ReactModalHostView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setImportantForAccessibility((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setNativeId((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setOpacity((ReactModalHostView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setRenderToHardwareTexture((ReactModalHostView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setRotation((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setScaleX((ReactModalHostView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setScaleY((ReactModalHostView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setTestId((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setTransform((ReactModalHostView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setTranslateX((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setTranslateY((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transparent", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setTransparent((ReactModalHostView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setViewLayerType((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<RCTModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ RCTModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RCTModalHostManager) viewManager).setZIndex((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(RCTModalHostManager manager, ReactModalHostView view, String name, x props) {
        e<RCTModalHostManager, ReactModalHostView> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("animationType", "String");
        props.put("backgroundColor", "Color");
        props.put("elevation", "number");
        props.put("hardwareAccelerated", "boolean");
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
        props.put("transparent", "boolean");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
