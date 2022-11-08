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
public class ReactRawTextManager$$PropsSetter implements e<ReactRawTextManager, ReactTextView> {
    private final HashMap<String, e<ReactRawTextManager, ReactTextView>> setters = new HashMap(42);

    public ReactRawTextManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setAccessibilityComponentType((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setAccessibilityLabel((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setAccessibilityLiveRegion((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBackgroundColor((ReactTextView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomColor", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactRawTextManager reactRawTextManager = (ReactRawTextManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactRawTextManager.setBorderColor(reactTextView, 4, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomLeftRadius", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderRadius((ReactTextView) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomRightRadius", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderRadius((ReactTextView) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomWidth", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderWidth((ReactTextView) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderColor", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactRawTextManager reactRawTextManager = (ReactRawTextManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactRawTextManager.setBorderColor(reactTextView, 0, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftColor", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactRawTextManager reactRawTextManager = (ReactRawTextManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactRawTextManager.setBorderColor(reactTextView, 1, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderLeftWidth", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderWidth((ReactTextView) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRadius", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderRadius((ReactTextView) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightColor", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactRawTextManager reactRawTextManager = (ReactRawTextManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactRawTextManager.setBorderColor(reactTextView, 2, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRightWidth", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderWidth((ReactTextView) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderStyle", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderStyle((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopColor", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactRawTextManager reactRawTextManager = (ReactRawTextManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactRawTextManager.setBorderColor(reactTextView, 3, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopLeftRadius", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderRadius((ReactTextView) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopRightRadius", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderRadius((ReactTextView) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopWidth", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderWidth((ReactTextView) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setBorderWidth((ReactTextView) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("disabled", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setDisabled((ReactTextView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setElevation((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("ellipsizeMode", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setEllipsizeMode((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setImportantForAccessibility((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("includeFontPadding", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setIncludeFontPadding((ReactTextView) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setNativeId((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("numberOfLines", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setNumberOfLines((ReactTextView) view, xVar.a(str, Integer.MAX_VALUE));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onInlineViewLayout", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setNotifyOnInlineViewLayout((ReactTextView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setOpacity((ReactTextView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setRenderToHardwareTexture((ReactTextView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setRotation((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setScaleX((ReactTextView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setScaleY((ReactTextView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selectable", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setSelectable((ReactTextView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selectionColor", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactRawTextManager reactRawTextManager = (ReactRawTextManager) viewManager;
                ReactTextView reactTextView = (ReactTextView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactRawTextManager.setSelectionColor(reactTextView, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setTestId((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("textAlignVertical", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setTextAlignVertical((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setTransform((ReactTextView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setTranslateX((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setTranslateY((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setViewLayerType((ReactTextView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactRawTextManager, ReactTextView>(this) {
            final /* synthetic */ ReactRawTextManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactRawTextManager) viewManager).setZIndex((ReactTextView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactRawTextManager manager, ReactTextView view, String name, x props) {
        e<ReactRawTextManager, ReactTextView> setter = (e) this.setters.get(name);
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
