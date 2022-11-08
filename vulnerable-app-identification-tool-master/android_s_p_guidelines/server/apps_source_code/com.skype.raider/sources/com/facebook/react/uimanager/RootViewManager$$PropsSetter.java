package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.ao.e;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class RootViewManager$$PropsSetter implements e<RootViewManager, ViewGroup> {
    private final HashMap<String, e<RootViewManager, ViewGroup>> setters = new HashMap(18);

    public RootViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setAccessibilityComponentType((ViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setAccessibilityLabel((ViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setAccessibilityLiveRegion((ViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setBackgroundColor((ViewGroup) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setElevation((ViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setImportantForAccessibility((ViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setNativeId((ViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setOpacity((ViewGroup) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setRenderToHardwareTexture((ViewGroup) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setRotation((ViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setScaleX((ViewGroup) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setScaleY((ViewGroup) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setTestId((ViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setTransform((ViewGroup) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setTranslateX((ViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setTranslateY((ViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setViewLayerType((ViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<RootViewManager, ViewGroup>(this) {
            final /* synthetic */ RootViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((RootViewManager) viewManager).setZIndex((ViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(RootViewManager manager, ViewGroup view, String name, x props) {
        e<RootViewManager, ViewGroup> setter = (e) this.setters.get(name);
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
