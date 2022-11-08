package com.facebook.react.views.scroll;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactScrollViewManager$$PropsSetter implements e<ReactScrollViewManager, ReactScrollView> {
    private final HashMap<String, e<ReactScrollViewManager, ReactScrollView>> setters = new HashMap(41);

    public ReactScrollViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setAccessibilityComponentType((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setAccessibilityLabel((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setAccessibilityLiveRegion((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBackgroundColor((ReactScrollView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomColor", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactScrollViewManager reactScrollViewManager = (ReactScrollViewManager) viewManager;
                ReactScrollView reactScrollView = (ReactScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactScrollViewManager.setBorderColor(reactScrollView, 4, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomLeftRadius", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderRadius((ReactScrollView) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomRightRadius", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderRadius((ReactScrollView) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderWidth((ReactScrollView) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderColor", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactScrollViewManager reactScrollViewManager = (ReactScrollViewManager) viewManager;
                ReactScrollView reactScrollView = (ReactScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactScrollViewManager.setBorderColor(reactScrollView, 0, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftColor", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactScrollViewManager reactScrollViewManager = (ReactScrollViewManager) viewManager;
                ReactScrollView reactScrollView = (ReactScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactScrollViewManager.setBorderColor(reactScrollView, 1, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderWidth((ReactScrollView) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRadius", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderRadius((ReactScrollView) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightColor", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactScrollViewManager reactScrollViewManager = (ReactScrollViewManager) viewManager;
                ReactScrollView reactScrollView = (ReactScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactScrollViewManager.setBorderColor(reactScrollView, 2, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderWidth((ReactScrollView) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderStyle", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderStyle((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopColor", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactScrollViewManager reactScrollViewManager = (ReactScrollViewManager) viewManager;
                ReactScrollView reactScrollView = (ReactScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactScrollViewManager.setBorderColor(reactScrollView, 3, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopLeftRadius", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderRadius((ReactScrollView) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopRightRadius", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderRadius((ReactScrollView) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderWidth((ReactScrollView) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBorderWidth((ReactScrollView) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setElevation((ReactScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("endFillColor", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setBottomFillColor((ReactScrollView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setImportantForAccessibility((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setNativeId((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setOpacity((ReactScrollView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("overScrollMode", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setOverScrollMode((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("removeClippedSubviews", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setRemoveClippedSubviews((ReactScrollView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setRenderToHardwareTexture((ReactScrollView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setRotation((ReactScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setScaleX((ReactScrollView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setScaleY((ReactScrollView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scrollEnabled", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setScrollEnabled((ReactScrollView) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scrollPerfTag", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setScrollPerfTag((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("sendMomentumEvents", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setSendMomentumEvents((ReactScrollView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("showsVerticalScrollIndicator", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setShowsVerticalScrollIndicator((ReactScrollView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setTestId((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setTransform((ReactScrollView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setTranslateX((ReactScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setTranslateY((ReactScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setViewLayerType((ReactScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactScrollViewManager, ReactScrollView>(this) {
            final /* synthetic */ ReactScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactScrollViewManager) viewManager).setZIndex((ReactScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactScrollViewManager manager, ReactScrollView view, String name, x props) {
        e<ReactScrollViewManager, ReactScrollView> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("backgroundColor", "Color");
        props.put("borderBottomColor", "Color");
        props.put("borderBottomLeftRadius", "number");
        props.put("borderBottomRightRadius", "number");
        props.put("borderBottomWidth", "number");
        props.put("borderColor", "Color");
        props.put("borderLeftColor", "Color");
        props.put("borderLeftWidth", "number");
        props.put("borderRadius", "number");
        props.put("borderRightColor", "Color");
        props.put("borderRightWidth", "number");
        props.put("borderStyle", "String");
        props.put("borderTopColor", "Color");
        props.put("borderTopLeftRadius", "number");
        props.put("borderTopRightRadius", "number");
        props.put("borderTopWidth", "number");
        props.put("borderWidth", "number");
        props.put("elevation", "number");
        props.put("endFillColor", "Color");
        props.put("importantForAccessibility", "String");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("opacity", "number");
        props.put("overScrollMode", "String");
        props.put("removeClippedSubviews", "boolean");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("scrollEnabled", "boolean");
        props.put("scrollPerfTag", "String");
        props.put("sendMomentumEvents", "boolean");
        props.put("showsVerticalScrollIndicator", "boolean");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
