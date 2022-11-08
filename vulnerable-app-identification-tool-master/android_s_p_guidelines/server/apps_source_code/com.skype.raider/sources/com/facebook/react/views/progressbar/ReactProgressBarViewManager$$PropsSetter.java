package com.facebook.react.views.progressbar;

import android.view.View;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import com.microsoft.react.videofxp.VideoFXPModule;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactProgressBarViewManager$$PropsSetter implements e<ReactProgressBarViewManager, a> {
    private final HashMap<String, e<ReactProgressBarViewManager, a>> setters = new HashMap(23);

    public ReactProgressBarViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setAccessibilityComponentType((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setAccessibilityLabel((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setAccessibilityLiveRegion((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("animating", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setAnimating((a) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setBackgroundColor((a) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("color", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactProgressBarViewManager reactProgressBarViewManager = (ReactProgressBarViewManager) viewManager;
                a aVar = (a) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactProgressBarViewManager.setColor(aVar, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setElevation((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setImportantForAccessibility((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("indeterminate", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setIndeterminate((a) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setNativeId((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setOpacity((a) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY, new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setProgress((a) view, xVar.a(str, 0.0d));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setRenderToHardwareTexture((a) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setRotation((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setScaleX((a) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setScaleY((a) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("styleAttr", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setStyle((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setTestId((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setTransform((a) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setTranslateX((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setTranslateY((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setViewLayerType((a) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactProgressBarViewManager, a>(this) {
            final /* synthetic */ ReactProgressBarViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactProgressBarViewManager) viewManager).setZIndex((a) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactProgressBarViewManager manager, a view, String name, x props) {
        e<ReactProgressBarViewManager, a> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("animating", "boolean");
        props.put("backgroundColor", "Color");
        props.put("color", "Color");
        props.put("elevation", "number");
        props.put("importantForAccessibility", "String");
        props.put("indeterminate", "boolean");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("opacity", "number");
        props.put(VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY, "number");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("styleAttr", "String");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
