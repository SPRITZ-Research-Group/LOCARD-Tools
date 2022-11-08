package com.facebook.react.views.view;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactViewManager$$PropsSetter implements e<ReactViewManager, ReactViewGroup> {
    private final HashMap<String, e<ReactViewManager, ReactViewGroup>> setters = new HashMap(44);

    public ReactViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setAccessibilityComponentType((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setAccessibilityLabel((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setAccessibilityLiveRegion((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessible", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setAccessible((ReactViewGroup) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBackgroundColor((ReactViewGroup) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomColor", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactViewManager reactViewManager = (ReactViewManager) viewManager;
                ReactViewGroup reactViewGroup = (ReactViewGroup) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactViewManager.setBorderColor(reactViewGroup, 4, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomLeftRadius", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderRadius((ReactViewGroup) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomRightRadius", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderRadius((ReactViewGroup) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderWidth((ReactViewGroup) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderColor", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactViewManager reactViewManager = (ReactViewManager) viewManager;
                ReactViewGroup reactViewGroup = (ReactViewGroup) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactViewManager.setBorderColor(reactViewGroup, 0, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftColor", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactViewManager reactViewManager = (ReactViewManager) viewManager;
                ReactViewGroup reactViewGroup = (ReactViewGroup) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactViewManager.setBorderColor(reactViewGroup, 1, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderWidth((ReactViewGroup) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRadius", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderRadius((ReactViewGroup) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightColor", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactViewManager reactViewManager = (ReactViewManager) viewManager;
                ReactViewGroup reactViewGroup = (ReactViewGroup) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactViewManager.setBorderColor(reactViewGroup, 2, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderWidth((ReactViewGroup) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderStyle", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderStyle((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopColor", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactViewManager reactViewManager = (ReactViewManager) viewManager;
                ReactViewGroup reactViewGroup = (ReactViewGroup) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactViewManager.setBorderColor(reactViewGroup, 3, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopLeftRadius", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderRadius((ReactViewGroup) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopRightRadius", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderRadius((ReactViewGroup) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderWidth((ReactViewGroup) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setBorderWidth((ReactViewGroup) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("collapsable", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setCollapsable((ReactViewGroup) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setElevation((ReactViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("hitSlop", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setHitSlop((ReactViewGroup) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setImportantForAccessibility((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("nativeBackgroundAndroid", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setNativeBackground((ReactViewGroup) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("nativeForegroundAndroid", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setNativeForeground((ReactViewGroup) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setNativeId((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("needsOffscreenAlphaCompositing", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setNeedsOffscreenAlphaCompositing((ReactViewGroup) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setOpacity((ReactViewGroup) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("overflow", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setOverflow((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("pointerEvents", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setPointerEvents((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("removeClippedSubviews", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setRemoveClippedSubviews((ReactViewGroup) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setRenderToHardwareTexture((ReactViewGroup) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("restrictFocusWithin", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setRestrictFocusWithin((ReactViewGroup) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setRotation((ReactViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setScaleX((ReactViewGroup) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setScaleY((ReactViewGroup) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setTestId((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setTransform((ReactViewGroup) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setTranslateX((ReactViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setTranslateY((ReactViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setViewLayerType((ReactViewGroup) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactViewManager, ReactViewGroup>(this) {
            final /* synthetic */ ReactViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactViewManager) viewManager).setZIndex((ReactViewGroup) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactViewManager manager, ReactViewGroup view, String name, x props) {
        e<ReactViewManager, ReactViewGroup> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("accessible", "boolean");
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
        props.put("collapsable", "boolean");
        props.put("elevation", "number");
        props.put("hitSlop", "Map");
        props.put("importantForAccessibility", "String");
        props.put("nativeBackgroundAndroid", "Map");
        props.put("nativeForegroundAndroid", "Map");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("needsOffscreenAlphaCompositing", "boolean");
        props.put("opacity", "number");
        props.put("overflow", "String");
        props.put("pointerEvents", "String");
        props.put("removeClippedSubviews", "boolean");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("restrictFocusWithin", "boolean");
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
