package com.facebook.react.views.image;

import android.view.View;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactImageManager$$PropsSetter implements e<ReactImageManager, ReactImageView> {
    private final HashMap<String, e<ReactImageManager, ReactImageView>> setters = new HashMap(36);

    public ReactImageManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setAccessibilityComponentType((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setAccessibilityLabel((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setAccessibilityLiveRegion((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setBackgroundColor((ReactImageView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("blurRadius", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setBlurRadius((ReactImageView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomLeftRadius", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setBorderRadius((ReactImageView) view, 4, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderBottomRightRadius", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setBorderRadius((ReactImageView) view, 3, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderColor", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactImageManager reactImageManager = (ReactImageManager) viewManager;
                ReactImageView reactImageView = (ReactImageView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactImageManager.setBorderColor(reactImageView, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderRadius", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setBorderRadius((ReactImageView) view, 0, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopLeftRadius", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setBorderRadius((ReactImageView) view, 1, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderTopRightRadius", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setBorderRadius((ReactImageView) view, 2, xVar.a(str, Float.NaN));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("borderWidth", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setBorderWidth((ReactImageView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setElevation((ReactImageView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("fadeDuration", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setFadeDuration((ReactImageView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("headers", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setHeaders((ReactImageView) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setImportantForAccessibility((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("loadingIndicatorSrc", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setLoadingIndicatorSource((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setNativeId((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setOpacity((ReactImageView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("overlayColor", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactImageManager reactImageManager = (ReactImageManager) viewManager;
                ReactImageView reactImageView = (ReactImageView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactImageManager.setOverlayColor(reactImageView, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("progressiveRenderingEnabled", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setProgressiveRenderingEnabled((ReactImageView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setRenderToHardwareTexture((ReactImageView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("resizeMethod", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setResizeMethod((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(ReactVideoViewManager.PROP_RESIZE_MODE, new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setResizeMode((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setRotation((ReactImageView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setScaleX((ReactImageView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setScaleY((ReactImageView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("shouldNotifyLoadEvents", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setLoadHandlersRegistered((ReactImageView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(ReactVideoViewManager.PROP_SRC, new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setSource((ReactImageView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setTestId((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("tintColor", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                Integer num;
                ReactImageManager reactImageManager = (ReactImageManager) viewManager;
                ReactImageView reactImageView = (ReactImageView) view;
                if (xVar.b(str)) {
                    num = null;
                } else {
                    num = Integer.valueOf(xVar.a(str, 0));
                }
                reactImageManager.setTintColor(reactImageView, num);
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setTransform((ReactImageView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setTranslateX((ReactImageView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setTranslateY((ReactImageView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setViewLayerType((ReactImageView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactImageManager, ReactImageView>(this) {
            final /* synthetic */ ReactImageManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactImageManager) viewManager).setZIndex((ReactImageView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactImageManager manager, ReactImageView view, String name, x props) {
        e<ReactImageManager, ReactImageView> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("backgroundColor", "Color");
        props.put("blurRadius", "number");
        props.put("borderBottomLeftRadius", "number");
        props.put("borderBottomRightRadius", "number");
        props.put("borderColor", "Color");
        props.put("borderRadius", "number");
        props.put("borderTopLeftRadius", "number");
        props.put("borderTopRightRadius", "number");
        props.put("borderWidth", "number");
        props.put("elevation", "number");
        props.put("fadeDuration", "number");
        props.put("headers", "Map");
        props.put("importantForAccessibility", "String");
        props.put("loadingIndicatorSrc", "String");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("opacity", "number");
        props.put("overlayColor", "number");
        props.put("progressiveRenderingEnabled", "boolean");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("resizeMethod", "String");
        props.put(ReactVideoViewManager.PROP_RESIZE_MODE, "String");
        props.put("rotation", "number");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("shouldNotifyLoadEvents", "boolean");
        props.put(ReactVideoViewManager.PROP_SRC, "Array");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("tintColor", "Color");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
