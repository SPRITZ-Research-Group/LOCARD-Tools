package com.facebook.react.views.toolbar;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactToolbarManager$$PropsSetter implements e<ReactToolbarManager, ReactToolbar> {
    private final HashMap<String, e<ReactToolbarManager, ReactToolbar>> setters = new HashMap(29);

    public ReactToolbarManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setAccessibilityComponentType((ReactToolbar) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setAccessibilityLabel((ReactToolbar) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setAccessibilityLiveRegion((ReactToolbar) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setBackgroundColor((ReactToolbar) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("contentInsetEnd", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setContentInsetEnd((ReactToolbar) view, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("contentInsetStart", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setContentInsetStart((ReactToolbar) view, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setElevation((ReactToolbar) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setImportantForAccessibility((ReactToolbar) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("logo", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setLogo((ReactToolbar) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("nativeActions", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setActions((ReactToolbar) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setNativeId((ReactToolbar) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("navIcon", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setNavIcon((ReactToolbar) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setOpacity((ReactToolbar) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("overflowIcon", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setOverflowIcon((ReactToolbar) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setRenderToHardwareTexture((ReactToolbar) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setRotation((ReactToolbar) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rtl", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setRtl((ReactToolbar) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setScaleX((ReactToolbar) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setScaleY((ReactToolbar) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("subtitle", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setSubtitle((ReactToolbar) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("subtitleColor", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactToolbarManager reactToolbarManager = (ReactToolbarManager) viewManager;
                ReactToolbar reactToolbar = (ReactToolbar) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactToolbarManager.setSubtitleColor(reactToolbar, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setTestId((ReactToolbar) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("title", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setTitle((ReactToolbar) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("titleColor", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactToolbarManager reactToolbarManager = (ReactToolbarManager) viewManager;
                ReactToolbar reactToolbar = (ReactToolbar) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactToolbarManager.setTitleColor(reactToolbar, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setTransform((ReactToolbar) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setTranslateX((ReactToolbar) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setTranslateY((ReactToolbar) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setViewLayerType((ReactToolbar) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactToolbarManager, ReactToolbar>(this) {
            final /* synthetic */ ReactToolbarManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactToolbarManager) viewManager).setZIndex((ReactToolbar) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactToolbarManager manager, ReactToolbar view, String name, x props) {
        e<ReactToolbarManager, ReactToolbar> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("backgroundColor", "Color");
        props.put("contentInsetEnd", "number");
        props.put("contentInsetStart", "number");
        props.put("elevation", "number");
        props.put("importantForAccessibility", "String");
        props.put("logo", "Map");
        props.put("nativeActions", "Array");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("navIcon", "Map");
        props.put("opacity", "number");
        props.put("overflowIcon", "Map");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("rtl", "boolean");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("subtitle", "String");
        props.put("subtitleColor", "Color");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("title", "String");
        props.put("titleColor", "Color");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
