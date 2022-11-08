package com.facebook.react.views.picker;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactDropdownPickerManager$$PropsSetter implements e<ReactDropdownPickerManager, ReactPicker> {
    private final HashMap<String, e<ReactDropdownPickerManager, ReactPicker>> setters = new HashMap(23);

    public ReactDropdownPickerManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setAccessibilityComponentType((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setAccessibilityLabel((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setAccessibilityLiveRegion((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setBackgroundColor((ReactPicker) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("color", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactDropdownPickerManager reactDropdownPickerManager = (ReactDropdownPickerManager) viewManager;
                ReactPicker reactPicker = (ReactPicker) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactDropdownPickerManager.setColor(reactPicker, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setElevation((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("enabled", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setEnabled((ReactPicker) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setImportantForAccessibility((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("items", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setItems((ReactPicker) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setNativeId((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setOpacity((ReactPicker) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("prompt", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setPrompt((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setRenderToHardwareTexture((ReactPicker) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setRotation((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setScaleX((ReactPicker) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setScaleY((ReactPicker) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selected", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setSelected((ReactPicker) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setTestId((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setTransform((ReactPicker) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setTranslateX((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setTranslateY((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setViewLayerType((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactDropdownPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDropdownPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDropdownPickerManager) viewManager).setZIndex((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactDropdownPickerManager manager, ReactPicker view, String name, x props) {
        e<ReactDropdownPickerManager, ReactPicker> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("backgroundColor", "Color");
        props.put("color", "Color");
        props.put("elevation", "number");
        props.put("enabled", "boolean");
        props.put("importantForAccessibility", "String");
        props.put("items", "Array");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("opacity", "number");
        props.put("prompt", "String");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("selected", "number");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
