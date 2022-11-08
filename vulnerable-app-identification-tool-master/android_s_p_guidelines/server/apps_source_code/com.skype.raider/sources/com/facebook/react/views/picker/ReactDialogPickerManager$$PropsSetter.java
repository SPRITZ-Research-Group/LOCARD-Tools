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
public class ReactDialogPickerManager$$PropsSetter implements e<ReactDialogPickerManager, ReactPicker> {
    private final HashMap<String, e<ReactDialogPickerManager, ReactPicker>> setters = new HashMap(23);

    public ReactDialogPickerManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setAccessibilityComponentType((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setAccessibilityLabel((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setAccessibilityLiveRegion((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setBackgroundColor((ReactPicker) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("color", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactDialogPickerManager reactDialogPickerManager = (ReactDialogPickerManager) viewManager;
                ReactPicker reactPicker = (ReactPicker) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactDialogPickerManager.setColor(reactPicker, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setElevation((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("enabled", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setEnabled((ReactPicker) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setImportantForAccessibility((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("items", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setItems((ReactPicker) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setNativeId((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setOpacity((ReactPicker) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("prompt", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setPrompt((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setRenderToHardwareTexture((ReactPicker) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setRotation((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setScaleX((ReactPicker) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setScaleY((ReactPicker) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("selected", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setSelected((ReactPicker) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setTestId((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setTransform((ReactPicker) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setTranslateX((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setTranslateY((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setViewLayerType((ReactPicker) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactDialogPickerManager, ReactPicker>(this) {
            final /* synthetic */ ReactDialogPickerManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactDialogPickerManager) viewManager).setZIndex((ReactPicker) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactDialogPickerManager manager, ReactPicker view, String name, x props) {
        e<ReactDialogPickerManager, ReactPicker> setter = (e) this.setters.get(name);
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
