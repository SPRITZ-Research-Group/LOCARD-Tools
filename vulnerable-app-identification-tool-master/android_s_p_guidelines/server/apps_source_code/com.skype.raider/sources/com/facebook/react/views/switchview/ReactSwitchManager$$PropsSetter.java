package com.facebook.react.views.switchview;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactSwitchManager$$PropsSetter implements e<ReactSwitchManager, a> {
    private final HashMap<String, e<ReactSwitchManager, a>> setters = new HashMap(22);

    public ReactSwitchManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setAccessibilityComponentType((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setAccessibilityLabel((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setAccessibilityLiveRegion((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setBackgroundColor((a) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setElevation((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("enabled", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setEnabled((a) view, xVar.a(str, true));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setImportantForAccessibility((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setNativeId((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("on", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setOn((a) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setOpacity((a) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setRenderToHardwareTexture((a) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setRotation((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setScaleX((a) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setScaleY((a) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setTestId((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("thumbTintColor", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactSwitchManager reactSwitchManager = (ReactSwitchManager) viewManager;
                a aVar = (a) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactSwitchManager.setThumbTintColor(aVar, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("trackTintColor", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactSwitchManager reactSwitchManager = (ReactSwitchManager) viewManager;
                a aVar = (a) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactSwitchManager.setTrackTintColor(aVar, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setTransform((a) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setTranslateX((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setTranslateY((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setViewLayerType((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactSwitchManager, a>(this) {
            final /* synthetic */ ReactSwitchManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactSwitchManager) viewManager).setZIndex((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactSwitchManager manager, a view, String name, x props) {
        e<ReactSwitchManager, a> setter = (e) this.setters.get(name);
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
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("on", "boolean");
        props.put("opacity", "number");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("thumbTintColor", "Color");
        props.put("trackTintColor", "Color");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
