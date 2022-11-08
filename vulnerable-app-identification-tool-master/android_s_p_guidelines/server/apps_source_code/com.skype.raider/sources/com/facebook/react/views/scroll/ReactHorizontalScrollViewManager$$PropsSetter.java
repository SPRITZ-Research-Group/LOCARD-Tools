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
public class ReactHorizontalScrollViewManager$$PropsSetter implements e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView> {
    private final HashMap<String, e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>> setters = new HashMap(42);

    public ReactHorizontalScrollViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setAccessibilityComponentType((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setAccessibilityLabel((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setAccessibilityLiveRegion((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBackgroundColor((ReactHorizontalScrollView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomColor", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactHorizontalScrollViewManager reactHorizontalScrollViewManager = (ReactHorizontalScrollViewManager) viewManager;
                ReactHorizontalScrollView reactHorizontalScrollView = (ReactHorizontalScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactHorizontalScrollViewManager.setBorderColor(reactHorizontalScrollView, 4, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomLeftRadius", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderRadius((ReactHorizontalScrollView) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomRightRadius", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderRadius((ReactHorizontalScrollView) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderWidth((ReactHorizontalScrollView) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderColor", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactHorizontalScrollViewManager reactHorizontalScrollViewManager = (ReactHorizontalScrollViewManager) viewManager;
                ReactHorizontalScrollView reactHorizontalScrollView = (ReactHorizontalScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactHorizontalScrollViewManager.setBorderColor(reactHorizontalScrollView, 0, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftColor", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactHorizontalScrollViewManager reactHorizontalScrollViewManager = (ReactHorizontalScrollViewManager) viewManager;
                ReactHorizontalScrollView reactHorizontalScrollView = (ReactHorizontalScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactHorizontalScrollViewManager.setBorderColor(reactHorizontalScrollView, 1, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderWidth((ReactHorizontalScrollView) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRadius", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderRadius((ReactHorizontalScrollView) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightColor", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactHorizontalScrollViewManager reactHorizontalScrollViewManager = (ReactHorizontalScrollViewManager) viewManager;
                ReactHorizontalScrollView reactHorizontalScrollView = (ReactHorizontalScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactHorizontalScrollViewManager.setBorderColor(reactHorizontalScrollView, 2, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderWidth((ReactHorizontalScrollView) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderStyle", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderStyle((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopColor", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactHorizontalScrollViewManager reactHorizontalScrollViewManager = (ReactHorizontalScrollViewManager) viewManager;
                ReactHorizontalScrollView reactHorizontalScrollView = (ReactHorizontalScrollView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactHorizontalScrollViewManager.setBorderColor(reactHorizontalScrollView, 3, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopLeftRadius", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderRadius((ReactHorizontalScrollView) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopRightRadius", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderRadius((ReactHorizontalScrollView) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderWidth((ReactHorizontalScrollView) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBorderWidth((ReactHorizontalScrollView) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setElevation((ReactHorizontalScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("endFillColor", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setBottomFillColor((ReactHorizontalScrollView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setImportantForAccessibility((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setNativeId((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setOpacity((ReactHorizontalScrollView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("overScrollMode", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setOverScrollMode((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("pagingEnabled", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setPagingEnabled((ReactHorizontalScrollView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("removeClippedSubviews", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setRemoveClippedSubviews((ReactHorizontalScrollView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setRenderToHardwareTexture((ReactHorizontalScrollView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setRotation((ReactHorizontalScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setScaleX((ReactHorizontalScrollView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setScaleY((ReactHorizontalScrollView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scrollEnabled", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setScrollEnabled((ReactHorizontalScrollView) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scrollPerfTag", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setScrollPerfTag((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("sendMomentumEvents", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setSendMomentumEvents((ReactHorizontalScrollView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("showsHorizontalScrollIndicator", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setShowsHorizontalScrollIndicator((ReactHorizontalScrollView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setTestId((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setTransform((ReactHorizontalScrollView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setTranslateX((ReactHorizontalScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setTranslateY((ReactHorizontalScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setViewLayerType((ReactHorizontalScrollView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView>(this) {
            final /* synthetic */ ReactHorizontalScrollViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactHorizontalScrollViewManager) viewManager).setZIndex((ReactHorizontalScrollView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactHorizontalScrollViewManager manager, ReactHorizontalScrollView view, String name, x props) {
        e<ReactHorizontalScrollViewManager, ReactHorizontalScrollView> setter = (e) this.setters.get(name);
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
        props.put("pagingEnabled", "boolean");
        props.put("removeClippedSubviews", "boolean");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("scrollEnabled", "boolean");
        props.put("scrollPerfTag", "String");
        props.put("sendMomentumEvents", "boolean");
        props.put("showsHorizontalScrollIndicator", "boolean");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
