package com.facebook.react.views.text;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactVirtualTextViewManager$$PropsSetter implements e<ReactVirtualTextViewManager, ReactTextView> {
    private final HashMap<String, e<ReactVirtualTextViewManager, ReactTextView>> setters = new HashMap(42);

    public ReactVirtualTextViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setAccessibilityComponentType((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setAccessibilityLabel((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setAccessibilityLiveRegion((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBackgroundColor((ReactTextView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomColor", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactVirtualTextViewManager reactVirtualTextViewManager = (ReactVirtualTextViewManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactVirtualTextViewManager.setBorderColor(reactTextView, 4, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomLeftRadius", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderRadius((ReactTextView) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomRightRadius", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderRadius((ReactTextView) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderWidth((ReactTextView) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderColor", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactVirtualTextViewManager reactVirtualTextViewManager = (ReactVirtualTextViewManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactVirtualTextViewManager.setBorderColor(reactTextView, 0, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftColor", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactVirtualTextViewManager reactVirtualTextViewManager = (ReactVirtualTextViewManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactVirtualTextViewManager.setBorderColor(reactTextView, 1, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderWidth((ReactTextView) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRadius", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderRadius((ReactTextView) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightColor", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactVirtualTextViewManager reactVirtualTextViewManager = (ReactVirtualTextViewManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactVirtualTextViewManager.setBorderColor(reactTextView, 2, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderWidth((ReactTextView) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderStyle", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderStyle((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopColor", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactVirtualTextViewManager reactVirtualTextViewManager = (ReactVirtualTextViewManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactVirtualTextViewManager.setBorderColor(reactTextView, 3, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopLeftRadius", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderRadius((ReactTextView) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopRightRadius", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderRadius((ReactTextView) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderWidth((ReactTextView) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setBorderWidth((ReactTextView) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("disabled", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setDisabled((ReactTextView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setElevation((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("ellipsizeMode", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setEllipsizeMode((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setImportantForAccessibility((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("includeFontPadding", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setIncludeFontPadding((ReactTextView) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setNativeId((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("numberOfLines", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setNumberOfLines((ReactTextView) view, xVar.a(str, Integer.MAX_VALUE));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onInlineViewLayout", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setNotifyOnInlineViewLayout((ReactTextView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setOpacity((ReactTextView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setRenderToHardwareTexture((ReactTextView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setRotation((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setScaleX((ReactTextView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setScaleY((ReactTextView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selectable", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setSelectable((ReactTextView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selectionColor", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactVirtualTextViewManager reactVirtualTextViewManager = (ReactVirtualTextViewManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactVirtualTextViewManager.setSelectionColor(reactTextView, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setTestId((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textAlignVertical", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setTextAlignVertical((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setTransform((ReactTextView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setTranslateX((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setTranslateY((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setViewLayerType((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactVirtualTextViewManager, ReactTextView>(this) {
            final /* synthetic */ ReactVirtualTextViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactVirtualTextViewManager) viewManager).setZIndex((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactVirtualTextViewManager manager, ReactTextView view, String name, x props) {
        e<ReactVirtualTextViewManager, ReactTextView> setter = (e) this.setters.get(name);
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
        props.put("disabled", "boolean");
        props.put("elevation", "number");
        props.put("ellipsizeMode", "String");
        props.put("importantForAccessibility", "String");
        props.put("includeFontPadding", "boolean");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("numberOfLines", "number");
        props.put("onInlineViewLayout", "boolean");
        props.put("opacity", "number");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("selectable", "boolean");
        props.put("selectionColor", "Color");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("textAlignVertical", "String");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
