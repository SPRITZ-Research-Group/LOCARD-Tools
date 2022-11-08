package com.facebook.react.views.drawer;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactDrawerLayoutManager$$PropsSetter implements e<ReactDrawerLayoutManager, a> {
    private final HashMap<String, e<ReactDrawerLayoutManager, a>> setters = new HashMap(21);

    public ReactDrawerLayoutManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setAccessibilityComponentType((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setAccessibilityLabel((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setAccessibilityLiveRegion((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setBackgroundColor((a) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("drawerLockMode", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setDrawerLockMode((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("drawerPosition", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setDrawerPosition((a) view, xVar.a(str, 8388611));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("drawerWidth", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).getDrawerWidth((a) view, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setElevation((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setImportantForAccessibility((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setNativeId((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setOpacity((a) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setRenderToHardwareTexture((a) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setRotation((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setScaleX((a) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setScaleY((a) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setTestId((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setTransform((a) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setTranslateX((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setTranslateY((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setViewLayerType((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactDrawerLayoutManager, a>(this) {
            final /* synthetic */ ReactDrawerLayoutManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDrawerLayoutManager) viewManager).setZIndex((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactDrawerLayoutManager manager, a view, String name, x props) {
        e<ReactDrawerLayoutManager, a> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("backgroundColor", "Color");
        props.put("drawerLockMode", "String");
        props.put("drawerPosition", "number");
        props.put("drawerWidth", "number");
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
