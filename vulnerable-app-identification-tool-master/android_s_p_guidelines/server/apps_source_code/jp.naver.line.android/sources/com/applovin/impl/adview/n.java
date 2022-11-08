package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import com.applovin.impl.a.a;
import com.applovin.impl.a.f;
import com.applovin.impl.a.i;
import com.applovin.impl.a.j;
import com.applovin.impl.sdk.ab;
import com.applovin.impl.sdk.an;
import com.applovin.impl.sdk.ar;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.g;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.ge;
import com.applovin.impl.sdk.m;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

class n extends WebView {
    private final AppLovinLogger a;
    private final AppLovinSdk b;
    private g c;
    private AppLovinAd d = null;
    private String e = null;
    private boolean f = false;

    n(ah ahVar, AppLovinSdk appLovinSdk, Context context) {
        super(context);
        if (appLovinSdk != null) {
            this.b = appLovinSdk;
            this.a = appLovinSdk.getLogger();
            setBackgroundColor(0);
            WebSettings settings = getSettings();
            settings.setSupportMultipleWindows(false);
            settings.setJavaScriptEnabled(true);
            setWebViewClient(ahVar);
            setWebChromeClient(new m(appLovinSdk));
            setVerticalScrollBarEnabled(false);
            setHorizontalScrollBarEnabled(false);
            setScrollBarStyle(33554432);
            setOnTouchListener(new o(this));
            setOnLongClickListener(new z(this));
            return;
        }
        throw new IllegalArgumentException("No sdk specified.");
    }

    private String a(String str, String str2, String str3) {
        return AppLovinSdkUtils.isValidString(str) ? gd.a(str3, str).replace("{SOURCE}", str2) : null;
    }

    private void a(an anVar) {
        try {
            if (new ee(this.b).I() || anVar.ah()) {
                a(new aa(this));
            }
            if (ab.e()) {
                a(new ab(this, anVar));
            }
            if (ab.f() && anVar.aj()) {
                a(new ac(this));
            }
            ge ak = anVar.ak();
            if (ak != null) {
                WebSettings settings = getSettings();
                PluginState b = ak.b();
                if (b != null) {
                    a(new ad(this, settings, b));
                }
                Boolean c = ak.c();
                if (c != null) {
                    a(new ae(this, settings, c));
                }
                c = ak.d();
                if (c != null) {
                    a(new af(this, settings, c));
                }
                c = ak.e();
                if (c != null) {
                    a(new ag(this, settings, c));
                }
                c = ak.f();
                if (c != null) {
                    a(new p(this, settings, c));
                }
                c = ak.g();
                if (c != null) {
                    a(new q(this, settings, c));
                }
                c = ak.h();
                if (c != null) {
                    a(new r(this, settings, c));
                }
                c = ak.i();
                if (c != null) {
                    a(new s(this, settings, c));
                }
                c = ak.j();
                if (c != null) {
                    a(new t(this, settings, c));
                }
                c = ak.k();
                if (c != null) {
                    a(new u(this, settings, c));
                }
                if (ab.d()) {
                    c = ak.l();
                    if (c != null) {
                        a(new v(this, settings, c));
                    }
                    c = ak.m();
                    if (c != null) {
                        a(new w(this, settings, c));
                    }
                }
                if (ab.g()) {
                    Integer a = ak.a();
                    if (a != null) {
                        a(new x(this, settings, a));
                    }
                }
                if (ab.h()) {
                    Boolean n = ak.n();
                    if (n != null) {
                        a(new y(this, settings, n));
                    }
                }
            }
        } catch (Throwable th) {
            this.a.e("AdWebView", "Unable to apply WebView settings", th);
        }
    }

    private void a(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable th) {
            this.a.e("AdWebView", "Unable to apply WebView setting", th);
        }
    }

    private void a(String str, String str2, String str3, String str4, AppLovinSdk appLovinSdk) {
        String a = a(str3, str, str4);
        if (AppLovinSdkUtils.isValidString(a)) {
            this.a.d("AdWebView", "Rendering webview for VAST ad with resourceContents : ".concat(String.valueOf(a)));
            loadDataWithBaseURL(str2, a, "text/html", null, "");
            return;
        }
        a = a(new ee(appLovinSdk).X(), str, str4);
        if (AppLovinSdkUtils.isValidString(a)) {
            this.a.d("AdWebView", "Rendering webview for VAST ad with resourceContents : ".concat(String.valueOf(a)));
            loadDataWithBaseURL(str2, a, "text/html", null, "");
            return;
        }
        this.a.d("AdWebView", "Rendering webview for VAST ad with resourceURL : ".concat(String.valueOf(str)));
        loadUrl(str);
    }

    AppLovinAd a() {
        return this.d;
    }

    public void a(g gVar) {
        this.c = gVar;
    }

    public void a(AppLovinAd appLovinAd, String str) {
        if (this.f) {
            this.a.userError("AdWebView", "Ad can not be loaded in a destroyed webview");
            return;
        }
        this.d = appLovinAd;
        this.e = str;
        try {
            if (appLovinAd instanceof ar) {
                loadDataWithBaseURL("/", ((ar) appLovinAd).a(), "text/html", null, "");
                this.a.d("AdWebView", "Empty ad rendered");
                return;
            }
            an anVar = (an) appLovinAd;
            a(anVar);
            if (anVar.aa()) {
                setVisibility(0);
            }
            if (appLovinAd instanceof m) {
                loadDataWithBaseURL(anVar.ai(), gd.a(str, ((m) appLovinAd).a()), "text/html", null, "");
                this.a.d("AdWebView", "AppLovinAd rendered");
                return;
            }
            if (appLovinAd instanceof a) {
                a aVar = (a) appLovinAd;
                f e = aVar.e();
                if (e != null) {
                    i b = e.b();
                    Uri b2 = b.b();
                    String uri = b2 != null ? b2.toString() : "";
                    String c = b.c();
                    String j = aVar.j();
                    ee eeVar = new ee(this.b);
                    String a;
                    String str2;
                    if (!AppLovinSdkUtils.isValidString(uri) && !AppLovinSdkUtils.isValidString(c)) {
                        this.a.e("AdWebView", "Unable to load companion ad. No resources provided.");
                    } else if (b.a() == j.STATIC) {
                        this.a.d("AdWebView", "Rendering WebView for static VAST ad");
                        loadDataWithBaseURL(anVar.ai(), a(eeVar.W(), uri, str), "text/html", null, "");
                        return;
                    } else if (b.a() == j.HTML) {
                        if (AppLovinSdkUtils.isValidString(c)) {
                            a = a(j, c, str);
                            str2 = AppLovinSdkUtils.isValidString(a) ? a : c;
                            this.a.d("AdWebView", "Rendering WebView for HTML VAST ad with resourceContents: ".concat(String.valueOf(str2)));
                            loadDataWithBaseURL(anVar.ai(), str2, "text/html", null, "");
                            return;
                        } else if (AppLovinSdkUtils.isValidString(uri)) {
                            this.a.d("AdWebView", "Preparing to load HTML VAST ad resourceUri");
                            a(uri, anVar.ai(), j, str, this.b);
                            return;
                        }
                    } else if (b.a() != j.IFRAME) {
                        this.a.e("AdWebView", "Failed to render VAST companion ad of invalid type");
                        return;
                    } else if (AppLovinSdkUtils.isValidString(uri)) {
                        this.a.d("AdWebView", "Preparing to load iFrame VAST ad resourceUri");
                        a(uri, anVar.ai(), j, str, this.b);
                        return;
                    } else if (AppLovinSdkUtils.isValidString(c)) {
                        a = a(j, c, str);
                        str2 = AppLovinSdkUtils.isValidString(a) ? a : c;
                        this.a.d("AdWebView", "Rendering WebView for iFrame VAST ad with resourceContents: ".concat(String.valueOf(str2)));
                        loadDataWithBaseURL(anVar.ai(), str2, "text/html", null, "");
                    }
                    return;
                }
                this.a.d("AdWebView", "No companion ad provided.");
            }
        } catch (Throwable th) {
            StringBuilder stringBuilder = new StringBuilder("Unable to render AppLovinAd with placement = \"");
            stringBuilder.append(str);
            stringBuilder.append("\"");
            this.a.e("AdWebView", stringBuilder.toString(), th);
        }
    }

    public void a(String str) {
        a(str, null);
    }

    public void a(String str, Runnable runnable) {
        try {
            StringBuilder stringBuilder = new StringBuilder("Forwarding \"");
            stringBuilder.append(str);
            stringBuilder.append("\" to ad template");
            this.a.d("AdWebView", stringBuilder.toString());
            loadUrl(str);
        } catch (Throwable th) {
            this.a.e("AdWebView", "Unable to forward to template", th);
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    String b() {
        return this.e;
    }

    public g c() {
        return this.c;
    }

    public void computeScroll() {
    }

    public void destroy() {
        this.f = true;
        try {
            super.destroy();
            this.a.d("AdWebView", "Web view destroyed");
        } catch (Throwable th) {
            if (this.a != null) {
                this.a.e("AdWebView", "destroy() threw exception", th);
            }
        }
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        try {
            super.onFocusChanged(z, i, rect);
        } catch (Throwable e) {
            this.a.e("AdWebView", "onFocusChanged() threw exception", e);
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
    }

    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
        } catch (Throwable e) {
            this.a.e("AdWebView", "onWindowFocusChanged() threw exception", e);
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        try {
            super.onWindowVisibilityChanged(i);
        } catch (Throwable e) {
            this.a.e("AdWebView", "onWindowVisibilityChanged() threw exception", e);
        }
    }

    public boolean requestFocus(int i, Rect rect) {
        try {
            return super.requestFocus(i, rect);
        } catch (Throwable e) {
            this.a.e("AdWebView", "requestFocus() threw exception", e);
            return false;
        }
    }

    public void scrollTo(int i, int i2) {
    }
}
