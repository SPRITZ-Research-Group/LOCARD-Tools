package com.facebook.react.views.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebView.PictureListener;
import android.webkit.WebViewClient;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.webview.a.b;
import com.facebook.react.views.webview.a.c;
import com.facebook.react.views.webview.a.d;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import net.hockeyapp.android.j;
import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = "RCTWebView")
public class ReactWebViewManager extends SimpleViewManager<WebView> {
    private static final String BLANK_URL = "about:blank";
    private static final String BRIDGE_NAME = "__REACT_WEB_VIEW_BRIDGE";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    private static final String HTML_ENCODING = "UTF-8";
    private static final String HTML_MIME_TYPE = "text/html; charset=utf-8";
    private static final String HTTP_METHOD_POST = "POST";
    protected static final String REACT_CLASS = "RCTWebView";
    @Nullable
    private PictureListener mPictureListener;
    private a mWebViewConfig;

    protected static class ReactWebView extends WebView implements v {
        @Nullable
        private String a;
        private boolean b = false;

        private class a {
            ReactWebView a;
            final /* synthetic */ ReactWebView b;

            a(ReactWebView reactWebView, ReactWebView c) {
                this.b = reactWebView;
                this.a = c;
            }

            @JavascriptInterface
            public final void postMessage(String message) {
                WebView webView = this.a;
                ReactWebViewManager.dispatchEvent(webView, new d(webView.getId(), message));
            }
        }

        public ReactWebView(ae reactContext) {
            super(reactContext);
        }

        public void onHostResume() {
        }

        public void onHostPause() {
        }

        public void onHostDestroy() {
            c();
        }

        public void setInjectedJavaScript(@Nullable String js) {
            this.a = js;
        }

        public void setMessagingEnabled(boolean enabled) {
            if (this.b != enabled) {
                this.b = enabled;
                if (enabled) {
                    addJavascriptInterface(new a(this, this), ReactWebViewManager.BRIDGE_NAME);
                    b();
                    return;
                }
                removeJavascriptInterface(ReactWebViewManager.BRIDGE_NAME);
            }
        }

        public final void a() {
            if (getSettings().getJavaScriptEnabled() && this.a != null && !TextUtils.isEmpty(this.a)) {
                loadUrl("javascript:(function() {\n" + this.a + ";\n})();");
            }
        }

        public final void b() {
            if (this.b) {
                loadUrl("javascript:(window.originalPostMessage = window.postMessage,window.postMessage = function(data) {__REACT_WEB_VIEW_BRIDGE.postMessage(String(data));})");
            }
        }

        private void c() {
            setWebViewClient(null);
            destroy();
        }
    }

    protected static class a extends WebViewClient {
        private boolean a = false;

        protected a() {
        }

        public void onPageFinished(WebView webView, String url) {
            super.onPageFinished(webView, url);
            if (!this.a) {
                ReactWebView reactWebView = (ReactWebView) webView;
                reactWebView.a();
                reactWebView.b();
                a(webView, url);
            }
        }

        public void onPageStarted(WebView webView, String url, Bitmap favicon) {
            super.onPageStarted(webView, url, favicon);
            this.a = false;
            ReactWebViewManager.dispatchEvent(webView, new c(webView.getId(), b(webView, url)));
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("file://") || url.equals(ReactWebViewManager.BLANK_URL)) {
                return false;
            }
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                intent.setFlags(ErrorDialogData.BINDER_CRASH);
                view.getContext().startActivity(intent);
            } catch (Throwable e) {
                FLog.w("React", "activity not found to handle uri scheme for: " + url, e);
            }
            return true;
        }

        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            super.onReceivedError(webView, errorCode, description, failingUrl);
            this.a = true;
            a(webView, failingUrl);
            ar eventData = b(webView, failingUrl);
            eventData.putDouble("code", (double) errorCode);
            eventData.putString("description", description);
            ReactWebViewManager.dispatchEvent(webView, new com.facebook.react.views.webview.a.a(webView.getId(), eventData));
        }

        private void a(WebView webView, String url) {
            ReactWebViewManager.dispatchEvent(webView, new b(webView.getId(), b(webView, url)));
        }

        private ar b(WebView webView, String url) {
            ar event = new WritableNativeMap();
            event.putDouble("target", (double) webView.getId());
            event.putString(j.FRAGMENT_URL, url);
            String str = "loading";
            boolean z = (this.a || webView.getProgress() == 100) ? false : true;
            event.putBoolean(str, z);
            event.putString("title", webView.getTitle());
            event.putBoolean("canGoBack", webView.canGoBack());
            event.putBoolean("canGoForward", webView.canGoForward());
            return event;
        }
    }

    public ReactWebViewManager() {
        this.mWebViewConfig = new a(this) {
            final /* synthetic */ ReactWebViewManager a;

            {
                this.a = this$0;
            }
        };
    }

    public ReactWebViewManager(a webViewConfig) {
        this.mWebViewConfig = webViewConfig;
    }

    public String getName() {
        return REACT_CLASS;
    }

    protected WebView createViewInstance(ae reactContext) {
        ReactWebView webView = new ReactWebView(reactContext);
        webView.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ ReactWebViewManager a;

            {
                this.a = this$0;
            }

            public final boolean onConsoleMessage(ConsoleMessage message) {
                return true;
            }

            public final void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
                callback.invoke(origin, true, false);
            }
        });
        reactContext.a(webView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setLayoutParams(new LayoutParams(-1, -1));
        return webView;
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(WebView view, boolean enabled) {
        view.getSettings().setJavaScriptEnabled(enabled);
    }

    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(WebView view, boolean enabled) {
        if (VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(view, enabled);
        }
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(WebView view, boolean enabled) {
        view.getSettings().setUseWideViewPort(!enabled);
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(WebView view, boolean enabled) {
        view.getSettings().setDomStorageEnabled(enabled);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(WebView view, @Nullable String userAgent) {
        if (userAgent != null) {
            view.getSettings().setUserAgentString(userAgent);
        }
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(WebView view, boolean requires) {
        view.getSettings().setMediaPlaybackRequiresUserGesture(requires);
    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(WebView view, boolean allow) {
        view.getSettings().setAllowUniversalAccessFromFileURLs(allow);
    }

    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(WebView view, boolean disable) {
        view.getSettings().setSaveFormData(!disable);
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(WebView view, @Nullable String injectedJavaScript) {
        ((ReactWebView) view).setInjectedJavaScript(injectedJavaScript);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(WebView view, boolean enabled) {
        ((ReactWebView) view).setMessagingEnabled(enabled);
    }

    @ReactProp(name = "source")
    public void setSource(WebView view, @Nullable am source) {
        if (source != null) {
            if (source.hasKey("html")) {
                String html = source.getString("html");
                if (source.hasKey("baseUrl")) {
                    WebView webView = view;
                    webView.loadDataWithBaseURL(source.getString("baseUrl"), html, HTML_MIME_TYPE, "UTF-8", null);
                    return;
                }
                view.loadData(html, HTML_MIME_TYPE, "UTF-8");
                return;
            }
            if (source.hasKey(ReactVideoViewManager.PROP_SRC_URI)) {
                String url = source.getString(ReactVideoViewManager.PROP_SRC_URI);
                String previousUrl = view.getUrl();
                if (previousUrl == null || !previousUrl.equals(url)) {
                    if (source.hasKey("method")) {
                        if (source.getString("method").equals(HTTP_METHOD_POST)) {
                            byte[] postData = null;
                            if (source.hasKey("body")) {
                                String body = source.getString("body");
                                try {
                                    postData = body.getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    postData = body.getBytes();
                                }
                            }
                            if (postData == null) {
                                postData = new byte[0];
                            }
                            view.postUrl(url, postData);
                            return;
                        }
                    }
                    HashMap<String, String> headerMap = new HashMap();
                    if (source.hasKey("headers")) {
                        am headers = source.getMap("headers");
                        ReadableMapKeySetIterator iter = headers.keySetIterator();
                        while (iter.hasNextKey()) {
                            String key = iter.nextKey();
                            if (!"user-agent".equals(key.toLowerCase(Locale.ENGLISH))) {
                                headerMap.put(key, headers.getString(key));
                            } else if (view.getSettings() != null) {
                                view.getSettings().setUserAgentString(headers.getString(key));
                            }
                        }
                    }
                    view.loadUrl(url, headerMap);
                    return;
                }
                return;
            }
        }
        view.loadUrl(BLANK_URL);
    }

    @ReactProp(name = "onContentSizeChange")
    public void setOnContentSizeChange(WebView view, boolean sendContentSizeChangeEvents) {
        if (sendContentSizeChangeEvents) {
            view.setPictureListener(getPictureListener());
        } else {
            view.setPictureListener(null);
        }
    }

    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(WebView view, @Nullable String mixedContentMode) {
        if (VERSION.SDK_INT < 21) {
            return;
        }
        if (mixedContentMode == null || "never".equals(mixedContentMode)) {
            view.getSettings().setMixedContentMode(1);
        } else if ("always".equals(mixedContentMode)) {
            view.getSettings().setMixedContentMode(0);
        } else if ("compatibility".equals(mixedContentMode)) {
            view.getSettings().setMixedContentMode(2);
        }
    }

    protected void addEventEmitters(ae reactContext, WebView view) {
        view.setWebViewClient(new a());
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        Integer valueOf = Integer.valueOf(1);
        Integer valueOf2 = Integer.valueOf(2);
        Integer valueOf3 = Integer.valueOf(3);
        Integer valueOf4 = Integer.valueOf(4);
        Integer valueOf5 = Integer.valueOf(5);
        Integer valueOf6 = Integer.valueOf(6);
        Map<String, Integer> hashMap = new HashMap();
        hashMap.put("goBack", valueOf);
        hashMap.put("goForward", valueOf2);
        hashMap.put("reload", valueOf3);
        hashMap.put("stopLoading", valueOf4);
        hashMap.put("postMessage", valueOf5);
        hashMap.put("injectJavaScript", valueOf6);
        return hashMap;
    }

    public void receiveCommand(WebView root, int commandId, @Nullable al args) {
        switch (commandId) {
            case 1:
                root.goBack();
                return;
            case 2:
                root.goForward();
                return;
            case 3:
                root.reload();
                return;
            case 4:
                root.stopLoading();
                return;
            case 5:
                try {
                    JSONObject eventInitDict = new JSONObject();
                    eventInitDict.put("data", args.getString(0));
                    root.loadUrl("javascript:(function () {var event;var data = " + eventInitDict.toString() + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();");
                    return;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            case 6:
                root.loadUrl("javascript:" + args.getString(0));
                return;
            default:
                return;
        }
    }

    public void onDropViewInstance(WebView webView) {
        super.onDropViewInstance(webView);
        ((ae) webView.getContext()).b((ReactWebView) webView);
        ((ReactWebView) webView).c();
    }

    private PictureListener getPictureListener() {
        if (this.mPictureListener == null) {
            this.mPictureListener = new PictureListener(this) {
                final /* synthetic */ ReactWebViewManager a;

                {
                    this.a = this$0;
                }

                public final void onNewPicture(WebView webView, Picture picture) {
                    ReactWebViewManager.dispatchEvent(webView, new com.facebook.react.uimanager.events.a(webView.getId(), webView.getWidth(), webView.getContentHeight()));
                }
            };
        }
        return this.mPictureListener;
    }

    private static void dispatchEvent(WebView webView, com.facebook.react.uimanager.events.b event) {
        ((UIManagerModule) ((ai) webView.getContext()).b(UIManagerModule.class)).getEventDispatcher().a(event);
    }
}
