package com.github.alinz.reactnativewebviewbridge;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.FrameLayout.LayoutParams;
import com.adjust.sdk.Constants;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.webview.ReactWebViewManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

public class WebViewBridgeManager extends ReactWebViewManager {
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN_KEY = "Access-Control-Allow-Origin";
    private static final Map<String, String> ACCESS_CONTROL_ALLOW_ORIGIN_MAP = Collections.singletonMap(ACCESS_CONTROL_ALLOW_ORIGIN_KEY, ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";
    public static final int COMMAND_CLEAR_FOCUS = 103;
    public static final int COMMAND_REQUEST_FOCUS = 102;
    public static final int COMMAND_SEND_TO_BRIDGE = 101;
    private static final String REACT_CLASS = "RCTWebViewBridge";
    private static final String TAG = "WebViewBridgeManager";
    private final WeakHashMap<WebView, b> attachListenerMap = new WeakHashMap();
    private a fullScreenWebChromeClient;

    private class a extends WebChromeClient {
        final /* synthetic */ WebViewBridgeManager a;
        private View b;
        private CustomViewCallback c;
        private WebView d;
        private int e;

        public a(WebViewBridgeManager webViewBridgeManager, WebView webView) {
            this.a = webViewBridgeManager;
            this.d = webView;
        }

        public final void a(int rgbFullscreenBackgroundColor) {
            this.e = rgbFullscreenBackgroundColor;
        }

        public final void onHideCustomView() {
            ViewGroup root = a(this.d.getRootView());
            if (root != null) {
                root.removeView(this.b);
            }
            this.b = null;
            this.c.onCustomViewHidden();
            this.c = null;
        }

        public final void onShowCustomView(View view, CustomViewCallback callback) {
            this.b = view;
            this.c = callback;
            ViewGroup root = a(this.d.getRootView());
            if (root != null) {
                if (this.e != 0) {
                    this.b.setBackgroundColor(this.e);
                }
                root.addView(this.b, new LayoutParams(-1, -1, 17));
                View view2 = this.b;
                if (view2 != null) {
                    view2.setSystemUiVisibility(5894);
                }
            }
        }

        private static ViewGroup a(View view) {
            if (view == null || !(view instanceof ViewGroup)) {
                return null;
            }
            return (ViewGroup) view;
        }
    }

    private static class b implements OnAttachStateChangeListener {
        private boolean a;

        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final void a(boolean showKeyboardOnMount) {
            this.a = showKeyboardOnMount;
        }

        public final void onViewAttachedToWindow(View v) {
            if (this.a) {
                v.requestFocus();
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService("input_method");
                if (imm != null) {
                    imm.showSoftInput(v, 1);
                }
            }
        }

        public final void onViewDetachedFromWindow(View v) {
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        Map<String, Integer> commandsMap = super.getCommandsMap();
        commandsMap.put("sendToBridge", Integer.valueOf(101));
        commandsMap.put("requestFocus", Integer.valueOf(102));
        commandsMap.put("clearFocus", Integer.valueOf(103));
        return commandsMap;
    }

    protected WebView createViewInstance(ae reactContext) {
        WebView root = super.createViewInstance(reactContext);
        root.addJavascriptInterface(new b(root), "WebViewBridge");
        this.fullScreenWebChromeClient = new a(this, root);
        root.setWebChromeClient(this.fullScreenWebChromeClient);
        b listener = new b();
        root.addOnAttachStateChangeListener(listener);
        this.attachListenerMap.put(root, listener);
        return root;
    }

    public void receiveCommand(WebView root, int commandId, @Nullable al args) {
        super.receiveCommand(root, commandId, args);
        switch (commandId) {
            case 101:
                sendToBridge(root, args.getString(0));
                return;
            case 102:
                root.requestFocus();
                return;
            case 103:
                root.clearFocus();
                return;
            default:
                return;
        }
    }

    private void sendToBridge(WebView root, String message) {
        evaluateJavascript(root, "WebViewBridge.onMessage('" + message + "');");
    }

    private static void evaluateJavascript(WebView root, String javascript) {
        if (VERSION.SDK_INT >= 19) {
            root.evaluateJavascript(javascript, null);
        } else {
            root.loadUrl("javascript:" + javascript);
        }
    }

    private void setAssetsCacheWebViewClient(final WebView root, @Nullable String assetsRootPath) {
        final a bundledWebResourceAssetProvider = new a(assetsRootPath);
        root.setWebViewClient(new a(this) {
            final /* synthetic */ WebViewBridgeManager c;

            @TargetApi(21)
            public final WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                WebResourceResponse webResourceResponse = null;
                String url = request.getUrl().toString();
                try {
                    InputStream asset = bundledWebResourceAssetProvider.a(url, root.getContext());
                    if (asset == null) {
                        return null;
                    }
                    FLog.e(WebViewBridgeManager.TAG, url + " exists in the local cache");
                    c mimeType = c.a(url);
                    if (mimeType == null) {
                        return null;
                    }
                    WebResourceResponse webResourceResponse2 = new WebResourceResponse(mimeType.toString(), Constants.ENCODING, asset);
                    try {
                        if (VERSION.SDK_INT >= 21 && mimeType == c.FONT) {
                            webResourceResponse2.setResponseHeaders(WebViewBridgeManager.ACCESS_CONTROL_ALLOW_ORIGIN_MAP);
                        }
                        return webResourceResponse2;
                    } catch (IOException e) {
                        webResourceResponse = webResourceResponse2;
                        FLog.e(WebViewBridgeManager.TAG, url + " does not exist in the local cache");
                        return webResourceResponse;
                    }
                } catch (IOException e2) {
                    FLog.e(WebViewBridgeManager.TAG, url + " does not exist in the local cache");
                    return webResourceResponse;
                }
            }
        });
    }

    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(WebView root, boolean allows) {
        root.getSettings().setAllowFileAccess(allows);
    }

    @ReactProp(name = "allowFileAccessFromFileURLs")
    public void setAllowFileAccessFromFileURLs(WebView root, boolean allows) {
        root.getSettings().setAllowFileAccessFromFileURLs(allows);
    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(WebView root, boolean allows) {
        root.getSettings().setAllowUniversalAccessFromFileURLs(allows);
    }

    @ReactProp(name = "javaScriptCanOpenWindowsAutomatically")
    public void setJavaScriptCanOpenWindowsAutomatically(WebView root, boolean allows) {
        root.getSettings().setJavaScriptCanOpenWindowsAutomatically(allows);
    }

    @ReactProp(name = "showKeyboardOnMount")
    public void setShowKeyboardOnMount(WebView root, boolean showKeyboardOnMount) {
        b listener = (b) this.attachListenerMap.get(root);
        if (listener != null) {
            listener.a(showKeyboardOnMount);
        }
    }

    @ReactProp(name = "cachedAssetsRootPath")
    public void setCachedAssetsRootPath(WebView root, @Nullable String assetsRootPath) {
        if (assetsRootPath != null && !assetsRootPath.isEmpty()) {
            setAssetsCacheWebViewClient(root, assetsRootPath);
        }
    }

    @ReactProp(name = "fullScreenBackgroundColor")
    public void setFullscreenBackgroundColor(WebView root, @Nullable am rgbValues) {
        if (rgbValues != null) {
            int red = rgbValues.getInt("red");
            int blue = rgbValues.getInt("blue");
            this.fullScreenWebChromeClient.a(Color.rgb(red, rgbValues.getInt("green"), blue));
        }
    }
}
