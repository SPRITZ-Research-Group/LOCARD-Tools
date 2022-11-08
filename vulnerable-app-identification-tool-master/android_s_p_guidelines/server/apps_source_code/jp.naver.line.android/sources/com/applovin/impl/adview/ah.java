package com.applovin.impl.adview;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.view.ViewParent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.a.a;
import com.applovin.impl.a.f;
import com.applovin.impl.a.n;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.g;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.util.List;

class ah extends WebViewClient {
    private final AppLovinSdk a;
    private final AppLovinLogger b;
    private final AdViewControllerImpl c;

    public ah(AdViewControllerImpl adViewControllerImpl, AppLovinSdk appLovinSdk) {
        this.a = appLovinSdk;
        this.b = appLovinSdk.getLogger();
        this.c = adViewControllerImpl;
    }

    private void a(android.net.Uri r6, com.applovin.impl.adview.n r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.adview.ah.a(android.net.Uri, com.applovin.impl.adview.n):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        r0 = "n";	 Catch:{ Throwable -> 0x0096 }
        r0 = r6.getQueryParameter(r0);	 Catch:{ Throwable -> 0x0096 }
        r1 = com.applovin.sdk.AppLovinSdkUtils.isValidString(r0);	 Catch:{ Throwable -> 0x0096 }
        if (r1 == 0) goto L_0x008c;	 Catch:{ Throwable -> 0x0096 }
    L_0x000c:
        r1 = "load_type";	 Catch:{ Throwable -> 0x0096 }
        r1 = r6.getQueryParameter(r1);	 Catch:{ Throwable -> 0x0096 }
        r2 = "external";	 Catch:{ Throwable -> 0x0096 }
        r2 = r2.equalsIgnoreCase(r1);	 Catch:{ Throwable -> 0x0096 }
        if (r2 == 0) goto L_0x0052;	 Catch:{ Throwable -> 0x0096 }
    L_0x001a:
        r6 = r5.b;	 Catch:{ Throwable -> 0x0096 }
        r1 = "AdWebViewClient";	 Catch:{ Throwable -> 0x0096 }
        r2 = "Loading new page externally: ";	 Catch:{ Throwable -> 0x0096 }
        r3 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0096 }
        r2 = r2.concat(r3);	 Catch:{ Throwable -> 0x0096 }
        r6.d(r1, r2);	 Catch:{ Throwable -> 0x0096 }
        r6 = r7.getContext();	 Catch:{ Throwable -> 0x0096 }
        r7 = android.net.Uri.parse(r0);	 Catch:{ Throwable -> 0x0096 }
        r0 = r5.a;	 Catch:{ Throwable -> 0x0096 }
        r0 = (com.applovin.impl.sdk.AppLovinSdkImpl) r0;	 Catch:{ Throwable -> 0x0096 }
        com.applovin.sdk.AppLovinSdkUtils.openUri(r6, r7, r0);	 Catch:{ Throwable -> 0x0096 }
        r6 = r5.c;	 Catch:{ Throwable -> 0x0096 }
        r6 = r6.getAdViewEventListener();	 Catch:{ Throwable -> 0x0096 }
        r7 = r5.c;	 Catch:{ Throwable -> 0x0096 }
        r7 = r7.getCurrentAd();	 Catch:{ Throwable -> 0x0096 }
        r0 = r5.c;	 Catch:{ Throwable -> 0x0096 }
        r0 = r0.getParentView();	 Catch:{ Throwable -> 0x0096 }
        r1 = r5.a;	 Catch:{ Throwable -> 0x0096 }
        com.applovin.impl.sdk.bv.c(r6, r7, r0, r1);	 Catch:{ Throwable -> 0x0096 }
        return;	 Catch:{ Throwable -> 0x0096 }
    L_0x0052:
        r2 = "internal";	 Catch:{ Throwable -> 0x0096 }
        r1 = r2.equalsIgnoreCase(r1);	 Catch:{ Throwable -> 0x0096 }
        if (r1 == 0) goto L_0x0082;	 Catch:{ Throwable -> 0x0096 }
    L_0x005a:
        r1 = r5.b;	 Catch:{ Throwable -> 0x0096 }
        r2 = "AdWebViewClient";	 Catch:{ Throwable -> 0x0096 }
        r3 = "Loading new page in WebView: ";	 Catch:{ Throwable -> 0x0096 }
        r4 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0096 }
        r3 = r3.concat(r4);	 Catch:{ Throwable -> 0x0096 }
        r1.d(r2, r3);	 Catch:{ Throwable -> 0x0096 }
        r7.loadUrl(r0);	 Catch:{ Throwable -> 0x0096 }
        r0 = "bg_color";	 Catch:{ Throwable -> 0x0096 }
        r6 = r6.getQueryParameter(r0);	 Catch:{ Throwable -> 0x0096 }
        r0 = com.applovin.sdk.AppLovinSdkUtils.isValidString(r6);	 Catch:{ Throwable -> 0x0096 }
        if (r0 == 0) goto L_0x0081;	 Catch:{ Throwable -> 0x0096 }
    L_0x007a:
        r6 = android.graphics.Color.parseColor(r6);	 Catch:{ Throwable -> 0x0096 }
        r7.setBackgroundColor(r6);	 Catch:{ Throwable -> 0x0096 }
    L_0x0081:
        return;	 Catch:{ Throwable -> 0x0096 }
    L_0x0082:
        r6 = r5.b;	 Catch:{ Throwable -> 0x0096 }
        r7 = "AdWebViewClient";	 Catch:{ Throwable -> 0x0096 }
        r0 = "Could not find load type in original uri";	 Catch:{ Throwable -> 0x0096 }
        r6.e(r7, r0);	 Catch:{ Throwable -> 0x0096 }
        return;	 Catch:{ Throwable -> 0x0096 }
    L_0x008c:
        r6 = r5.b;	 Catch:{ Throwable -> 0x0096 }
        r7 = "AdWebViewClient";	 Catch:{ Throwable -> 0x0096 }
        r0 = "Could not find url to load from query in original uri";	 Catch:{ Throwable -> 0x0096 }
        r6.e(r7, r0);	 Catch:{ Throwable -> 0x0096 }
        return;
    L_0x0096:
        r6 = r5.b;
        r7 = "AdWebViewClient";
        r0 = "Failed to load new page from query in original uri";
        r6.e(r7, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.ah.a(android.net.Uri, com.applovin.impl.adview.n):void");
    }

    private void a(a aVar, n nVar) {
        f e = aVar.e();
        if (e != null) {
            n.a(e.c(), (AppLovinSdkImpl) this.c.getSdk());
            a(nVar, e.a());
        }
    }

    private void a(n nVar, Uri uri) {
        AppLovinAd a = nVar.a();
        String b = nVar.b();
        AppLovinAdView parentView = this.c.getParentView();
        if (parentView == null || a == null) {
            this.b.e("AdWebViewClient", "Attempting to track click that is null or not an ApplovinAdView instance for clickedUri = ".concat(String.valueOf(uri)));
            return;
        }
        g c = nVar.c();
        if (c != null) {
            c.b();
        }
        this.c.a(a, b, parentView, uri);
    }

    private void c(n nVar) {
        this.c.expandAd();
    }

    private void d(n nVar) {
        this.c.contractAd();
    }

    void a(n nVar) {
        ViewParent parent = nVar.getParent();
        if (parent instanceof AppLovinAdView) {
            ((AppLovinAdView) parent).loadNextAd();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean a(WebView webView, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder("Processing click on ad URL \"");
        stringBuilder.append(str);
        stringBuilder.append("\"");
        this.b.i("AdWebViewClient", stringBuilder.toString());
        if (str != null && (webView instanceof n)) {
            a aVar;
            Uri parse = Uri.parse(str);
            n nVar = (n) webView;
            String scheme = parse.getScheme();
            String host = parse.getHost();
            String path = parse.getPath();
            AppLovinAd currentAd = this.c.getCurrentAd();
            if (AppLovinSdk.URI_SCHEME.equals(scheme) && AppLovinSdk.URI_HOST.equals(host)) {
                if (AppLovinAdService.URI_NEXT_AD.equals(path)) {
                    a(nVar);
                } else if (AppLovinAdService.URI_CLOSE_AD.equals(path)) {
                    b(nVar);
                } else if (AppLovinAdService.URI_EXPAND_AD.equals(path)) {
                    c(nVar);
                } else if (AppLovinAdService.URI_CONTRACT_AD.equals(path)) {
                    d(nVar);
                } else if (AppLovinAdServiceImpl.URI_NO_OP.equals(path)) {
                    return true;
                } else {
                    if (AppLovinAdServiceImpl.URI_LOAD_URL.equals(path)) {
                        a(parse, nVar);
                    } else if (AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY.equals(path)) {
                        if (currentAd instanceof a) {
                            aVar = (a) currentAd;
                        } else {
                            a(nVar, Uri.parse(AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY));
                        }
                    } else if (path == null || !path.startsWith("/launch/")) {
                        this.b.w("AdWebViewClient", "Unknown URL: ".concat(String.valueOf(str)));
                        this.b.w("AdWebViewClient", "Path: ".concat(String.valueOf(path)));
                    } else {
                        List pathSegments = parse.getPathSegments();
                        if (pathSegments != null && pathSegments.size() > 1) {
                            str = (String) pathSegments.get(pathSegments.size() - 1);
                            try {
                                Context context = webView.getContext();
                                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
                                a(nVar, null);
                            } catch (Throwable e) {
                                this.b.e("AdWebViewClient", "Threw Exception Trying to Launch App for Package: ".concat(String.valueOf(str)), e);
                            }
                        }
                    }
                }
            } else if (!z) {
                return false;
            } else {
                if (currentAd instanceof a) {
                    aVar = (a) currentAd;
                }
                a(nVar, parse);
            }
            a(aVar, nVar);
        }
        return true;
    }

    void b(n nVar) {
        this.c.a();
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.c.onAdHtmlLoaded(webView);
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        boolean hasGesture = new ee(this.a).aj() ? webResourceRequest.hasGesture() : true;
        Uri url = webResourceRequest.getUrl();
        if (url != null) {
            return a(webView, url.toString(), hasGesture);
        }
        this.b.e("AdWebViewClient", "No url found for request");
        return false;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return a(webView, str, true);
    }
}
