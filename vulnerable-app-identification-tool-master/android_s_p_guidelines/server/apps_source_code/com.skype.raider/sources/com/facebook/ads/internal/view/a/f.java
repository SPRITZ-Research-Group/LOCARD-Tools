package com.facebook.ads.internal.view.a;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.adjust.sdk.Constants;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

@TargetApi(19)
public class f extends com.facebook.ads.internal.q.c.a {
    private static final String a = f.class.getSimpleName();
    private static final Set<String> b;
    private a c;
    private d d;
    private long e = -1;
    private long f = -1;
    private long g = -1;
    private long h = -1;

    public interface a {
        void a();

        void a(int i);

        void a(String str);

        void b(String str);
    }

    static class b extends WebChromeClient {
        private final WeakReference<a> a;
        private final WeakReference<d> b;

        b(WeakReference<a> weakReference, WeakReference<d> weakReference2) {
            this.a = weakReference;
            this.b = weakReference2;
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Object message = consoleMessage.message();
            if (!(TextUtils.isEmpty(message) || consoleMessage.messageLevel() != MessageLevel.LOG || this.b.get() == null)) {
                ((d) this.b.get()).a(message);
            }
            return true;
        }

        public final void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (this.b.get() != null) {
                ((d) this.b.get()).b();
            }
            if (this.a.get() != null) {
                ((a) this.a.get()).a(i);
            }
        }

        public final void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (this.a.get() != null) {
                ((a) this.a.get()).b(str);
            }
        }
    }

    static class c extends WebViewClient {
        private final WeakReference<a> a;
        private final WeakReference<Context> b;

        c(WeakReference<a> weakReference, WeakReference<Context> weakReference2) {
            this.a = weakReference;
            this.b = weakReference2;
        }

        public final void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.a.get() != null) {
                ((a) this.a.get()).a();
            }
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.a.get() != null) {
                ((a) this.a.get()).a(str);
            }
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            if (!(f.b.contains(parse.getScheme()) || this.b.get() == null)) {
                try {
                    ((Context) this.b.get()).startActivity(new Intent("android.intent.action.VIEW", parse));
                    return true;
                } catch (ActivityNotFoundException e) {
                    f.a;
                } catch (Exception e2) {
                    f.a;
                }
            }
            return false;
        }
    }

    static {
        Set hashSet = new HashSet(2);
        b = hashSet;
        hashSet.add("http");
        b.add(Constants.SCHEME);
    }

    public f(Context context) {
        super(context);
        WebSettings settings = getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setAllowFileAccess(false);
        this.d = new d(this);
    }

    private void j() {
        if (this.f > -1 && this.g > -1 && this.h > -1) {
            this.d.a();
        }
    }

    protected final WebChromeClient a() {
        return new b(new WeakReference(this.c), new WeakReference(this.d));
    }

    public final void a(long j) {
        if (this.e < 0) {
            this.e = j;
        }
    }

    protected final WebViewClient b() {
        return new c(new WeakReference(this.c), new WeakReference(getContext()));
    }

    public final void b(long j) {
        if (this.f < 0) {
            this.f = j;
        }
        j();
    }

    public final void c(long j) {
        if (this.h < 0) {
            this.h = j;
        }
        j();
    }

    public final long d() {
        return this.e;
    }

    public void destroy() {
        this.c = null;
        com.facebook.ads.internal.q.c.b.a(this);
        super.destroy();
    }

    public final long e() {
        return this.f;
    }

    public final long f() {
        return this.g;
    }

    public final long g() {
        return this.h;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.g < 0 && computeVerticalScrollRange() > getHeight()) {
            this.g = System.currentTimeMillis();
            j();
        }
    }

    public void setListener(a aVar) {
        this.c = aVar;
    }
}
