package com.facebook.react.views.modal;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactModalHostManager$$PropsSetter implements e<ReactModalHostManager, ReactModalHostView> {
    private final HashMap<String, e<ReactModalHostManager, ReactModalHostView>> setters = new HashMap(21);

    public ReactModalHostManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setAccessibilityComponentType((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setAccessibilityLabel((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setAccessibilityLiveRegion((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("animationType", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setAnimationType((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setBackgroundColor((ReactModalHostView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setElevation((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("hardwareAccelerated", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setHardwareAccelerated((ReactModalHostView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setImportantForAccessibility((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setNativeId((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setOpacity((ReactModalHostView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setRenderToHardwareTexture((ReactModalHostView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setRotation((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setScaleX((ReactModalHostView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setScaleY((ReactModalHostView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setTestId((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setTransform((ReactModalHostView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setTranslateX((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setTranslateY((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transparent", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setTransparent((ReactModalHostView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setViewLayerType((ReactModalHostView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactModalHostManager, ReactModalHostView>(this) {
            final /* synthetic */ ReactModalHostManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactModalHostManager) viewManager).setZIndex((ReactModalHostView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactModalHostManager manager, ReactModalHostView view, String name, x props) {
        e<ReactModalHostManager, ReactModalHostView> setter = (e) this.setters.get(name);
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
