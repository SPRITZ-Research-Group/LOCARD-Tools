package com.facebook.react.views.slider;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactSliderManager$$PropsSetter implements e<ReactSliderManager, ReactSlider> {
    private final HashMap<String, e<ReactSliderManager, ReactSlider>> setters = new HashMap(26);

    public ReactSliderManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setAccessibilityComponentType((ReactSlider) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setAccessibilityLabel((ReactSlider) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setAccessibilityLiveRegion((ReactSlider) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setBackgroundColor((ReactSlider) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setElevation((ReactSlider) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("enabled", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setEnabled((ReactSlider) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setImportantForAccessibility((ReactSlider) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maximumTrackTintColor", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactSliderManager reactSliderManager = (ReactSliderManager) viewManager;
                ReactSlider reactSlider = (ReactSlider) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactSliderManager.setMaximumTrackTintColor(reactSlider, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("maximumValue", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setMaximumValue((ReactSlider) view, xVar.a(str, 1.0d));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("minimumTrackTintColor", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactSliderManager reactSliderManager = (ReactSliderManager) viewManager;
                ReactSlider reactSlider = (ReactSlider) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactSliderManager.setMinimumTrackTintColor(reactSlider, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("minimumValue", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setMinimumValue((ReactSlider) view, xVar.a(str, 0.0d));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setNativeId((ReactSlider) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setOpacity((ReactSlider) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setRenderToHardwareTexture((ReactSlider) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setRotation((ReactSlider) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setScaleX((ReactSlider) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setScaleY((ReactSlider) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("step", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setStep((ReactSlider) view, xVar.a(str, 0.0d));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setTestId((ReactSlider) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("thumbTintColor", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactSliderManager reactSliderManager = (ReactSliderManager) viewManager;
                ReactSlider reactSlider = (ReactSlider) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactSliderManager.setThumbTintColor(reactSlider, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setTransform((ReactSlider) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setTranslateX((ReactSlider) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setTranslateY((ReactSlider) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(PropertiesEntry.COLUMN_NAME_VALUE, new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setValue((ReactSlider) view, xVar.a(str, 0.0d));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setViewLayerType((ReactSlider) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactSliderManager, ReactSlider>(this) {
            final /* synthetic */ ReactSliderManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSliderManager) viewManager).setZIndex((ReactSlider) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactSliderManager manager, ReactSlider view, String name, x props) {
        e<ReactSliderManager, ReactSlider> setter = (e) this.setters.get(name);
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
        props.put("enabled", "boolean");
        props.put("importantForAccessibility", "String");
        props.put("maximumTrackTintColor", "Color");
        props.put("maximumValue", "number");
        props.put("minimumTrackTintColor", "Color");
        props.put("minimumValue", "number");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("opacity", "number");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("step", "number");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("thumbTintColor", "Color");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put(PropertiesEntry.COLUMN_NAME_VALUE, "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
