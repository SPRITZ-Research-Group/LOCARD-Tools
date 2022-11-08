package com.facebook.react.views.webview;

import android.view.View;
import android.webkit.WebView;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ao.e;
import com.facebook.react.uimanager.x;
import com.skype.camera.imagefilter.ImageFilterManager;
import java.util.HashMap;
import java.util.Map;

@DoNotStrip
public class ReactWebViewManager$$PropsSetter implements e<ReactWebViewManager, WebView> {
    private final HashMap<String, e<ReactWebViewManager, WebView>> setters = new HashMap(31);

    public ReactWebViewManager$$PropsSetter() {
        this.setters.put("accessibilityComponentType", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setAccessibilityComponentType((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLabel", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setAccessibilityLabel((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("accessibilityLiveRegion", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setAccessibilityLiveRegion((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("allowUniversalAccessFromFileURLs", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setAllowUniversalAccessFromFileURLs((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("backgroundColor", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setBackgroundColor((WebView) view, xVar.a(str, 0));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("domStorageEnabled", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setDomStorageEnabled((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("elevation", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setElevation((WebView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("importantForAccessibility", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setImportantForAccessibility((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("injectedJavaScript", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setInjectedJavaScript((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("javaScriptEnabled", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setJavaScriptEnabled((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("mediaPlaybackRequiresUserAction", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setMediaPlaybackRequiresUserAction((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("messagingEnabled", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setMessagingEnabled((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("mixedContentMode", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setMixedContentMode((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_NATIVE_ID, new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setNativeId((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("onContentSizeChange", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setOnContentSizeChange((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("opacity", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setOpacity((WebView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("renderToHardwareTextureAndroid", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setRenderToHardwareTexture((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("rotation", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setRotation((WebView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("saveFormDataDisabled", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setSaveFormDataDisabled((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleX", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setScaleX((WebView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scaleY", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setScaleY((WebView) view, xVar.a(str, 1.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("scalesPageToFit", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setScalesPageToFit((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(ImageFilterManager.PROP_SOURCE, new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setSource((WebView) view, xVar.e(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put(BaseViewManager.PROP_TEST_ID, new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setTestId((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("thirdPartyCookiesEnabled", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setThirdPartyCookiesEnabled((WebView) view, xVar.a(str, false));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("transform", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setTransform((WebView) view, xVar.d(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateX", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setTranslateX((WebView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("translateY", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setTranslateY((WebView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("userAgent", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setUserAgent((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("viewLayerTypeAndroid", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setViewLayerType((WebView) view, xVar.c(str));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
        this.setters.put("zIndex", new e<ReactWebViewManager, WebView>(this) {
            final /* synthetic */ ReactWebViewManager$$PropsSetter a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void setProperty(ViewManager viewManager, View view, String str, x xVar) {
                ((ReactWebViewManager) viewManager).setZIndex((WebView) view, xVar.a(str, 0.0f));
            }

            public final void getProperties(Map<String, String> map) {
            }
        });
    }

    public void setProperty(ReactWebViewManager manager, WebView view, String name, x props) {
        e<ReactWebViewManager, WebView> setter = (e) this.setters.get(name);
        if (setter != null) {
            setter.setProperty(manager, view, name, props);
        }
    }

    public void getProperties(Map<String, String> props) {
        props.put("accessibilityComponentType", "String");
        props.put("accessibilityLabel", "String");
        props.put("accessibilityLiveRegion", "String");
        props.put("allowUniversalAccessFromFileURLs", "boolean");
        props.put("backgroundColor", "Color");
        props.put("domStorageEnabled", "boolean");
        props.put("elevation", "number");
        props.put("importantForAccessibility", "String");
        props.put("injectedJavaScript", "String");
        props.put("javaScriptEnabled", "boolean");
        props.put("mediaPlaybackRequiresUserAction", "boolean");
        props.put("messagingEnabled", "boolean");
        props.put("mixedContentMode", "String");
        props.put(BaseViewManager.PROP_NATIVE_ID, "String");
        props.put("onContentSizeChange", "boolean");
        props.put("opacity", "number");
        props.put("renderToHardwareTextureAndroid", "boolean");
        props.put("rotation", "number");
        props.put("saveFormDataDisabled", "boolean");
        props.put("scaleX", "number");
        props.put("scaleY", "number");
        props.put("scalesPageToFit", "boolean");
        props.put(ImageFilterManager.PROP_SOURCE, "Map");
        props.put(BaseViewManager.PROP_TEST_ID, "String");
        props.put("thirdPartyCookiesEnabled", "boolean");
        props.put("transform", "Array");
        props.put("translateX", "number");
        props.put("translateY", "number");
        props.put("userAgent", "String");
        props.put("viewLayerTypeAndroid", "String");
        props.put("zIndex", "number");
    }
}
