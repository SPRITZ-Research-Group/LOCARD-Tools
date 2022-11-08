package com.facebook.ads.internal.view.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.q.a.u;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class a extends com.facebook.ads.internal.q.c.a {
    private static final String a = a.class.getSimpleName();
    private final WeakReference<b> b;
    private final AtomicBoolean c = new AtomicBoolean();
    private final AtomicBoolean d = new AtomicBoolean(true);
    @Nullable
    private WeakReference<d> e;
    @Nullable
    private com.facebook.ads.internal.s.a f;
    private s g = new s();
    private com.facebook.ads.internal.s.a.a h;
    private boolean i = true;
    private boolean j;

    public interface b {
        void a();

        void a(int i);

        void a(String str, Map<String, String> map);

        void b();

        void c();
    }

    public static class c implements b {
        public void a() {
        }

        public void a(int i) {
        }

        public void a(String str, Map<String, String> map) {
        }

        public void b() {
        }

        public void c() {
        }
    }

    static class a {
        private final String a = a.class.getSimpleName();
        private final WeakReference<a> b;
        private final WeakReference<b> c;
        private final WeakReference<com.facebook.ads.internal.s.a> d;
        private final WeakReference<AtomicBoolean> e;
        private final WeakReference<AtomicBoolean> f;
        private final boolean g;

        a(a aVar, b bVar, com.facebook.ads.internal.s.a aVar2, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, boolean z) {
            this.b = new WeakReference(aVar);
            this.c = new WeakReference(bVar);
            this.d = new WeakReference(aVar2);
            this.e = new WeakReference(atomicBoolean);
            this.f = new WeakReference(atomicBoolean2);
            this.g = z;
        }

        @JavascriptInterface
        public void alert(String str) {
        }

        @JavascriptInterface
        public String getAnalogInfo() {
            return k.a(com.facebook.ads.internal.g.a.a());
        }

        @JavascriptInterface
        public void onMainAssetLoaded() {
            if (this.b.get() != null && this.e.get() != null && this.f.get() != null && this.g && ((AtomicBoolean) this.f.get()).get()) {
                ((AtomicBoolean) this.e.get()).set(true);
                if (((a) this.b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new e(this.d));
                }
            }
        }

        @JavascriptInterface
        public void onPageInitialized() {
            a aVar = (a) this.b.get();
            if (aVar != null && !aVar.c()) {
                b bVar = (b) this.c.get();
                if (bVar != null) {
                    bVar.a();
                }
                if (!this.g && ((a) this.b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new e(this.d));
                }
            }
        }
    }

    public interface d {
        void b();
    }

    static class e implements Runnable {
        private final WeakReference<com.facebook.ads.internal.s.a> a;

        e(com.facebook.ads.internal.s.a aVar) {
            this.a = new WeakReference(aVar);
        }

        e(WeakReference<com.facebook.ads.internal.s.a> weakReference) {
            this.a = weakReference;
        }

        public final void run() {
            com.facebook.ads.internal.s.a aVar = (com.facebook.ads.internal.s.a) this.a.get();
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    static class f extends WebChromeClient {
        f() {
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }
    }

    static class g extends WebViewClient {
        private final WeakReference<b> a;
        private final WeakReference<com.facebook.ads.internal.s.a> b;
        private final WeakReference<s> c;
        private final WeakReference<AtomicBoolean> d;
        private final WeakReference<a> e;
        private boolean f = false;

        g(WeakReference<b> weakReference, WeakReference<com.facebook.ads.internal.s.a> weakReference2, WeakReference<s> weakReference3, WeakReference<AtomicBoolean> weakReference4, WeakReference<a> weakReference5) {
            this.a = weakReference;
            this.b = weakReference2;
            this.c = weakReference3;
            this.d = weakReference4;
            this.e = weakReference5;
        }

        private void a() {
            if (this.a.get() != null) {
                ((b) this.a.get()).c();
            }
        }

        public final void onPageFinished(WebView webView, String str) {
            if (!(this.e.get() == null || this.d.get() == null || ((AtomicBoolean) this.d.get()).get())) {
                a.d((a) this.e.get());
            }
            this.f = true;
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public final void run() {
                    if (!this.a.f) {
                        this.a.a();
                    }
                }
            }, 2000);
        }

        public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            this.f = true;
            a();
        }

        public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Map hashMap = new HashMap();
            if (this.b.get() != null) {
                ((com.facebook.ads.internal.s.a) this.b.get()).a(hashMap);
            }
            if (this.c.get() != null) {
                hashMap.put("touch", k.a(((s) this.c.get()).e()));
            }
            if (this.a.get() != null) {
                ((b) this.a.get()).a(str, hashMap);
            }
            return true;
        }
    }

    public a(Context context, WeakReference<b> weakReference, int i) {
        super(context);
        this.j = com.facebook.ads.internal.l.a.v(context);
        this.b = weakReference;
        this.h = new com.facebook.ads.internal.s.a.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void a() {
                if (this.a.i || !this.a.g.b()) {
                    this.a.g.a();
                }
                if (this.a.b.get() != null) {
                    ((b) this.a.b.get()).b();
                }
            }
        };
        this.f = new com.facebook.ads.internal.s.a(this, i, this.h);
        setWebChromeClient(new f());
        setWebViewClient(b());
        getSettings().setSupportZoom(false);
        getSettings().setCacheMode(1);
        addJavascriptInterface(new a(this, (b) weakReference.get(), this.f, this.c, this.d, this.j), "AdControl");
    }

    protected final WebChromeClient a() {
        return new f();
    }

    public final void a(int i, int i2) {
        if (this.f != null) {
            this.f.a(i);
            this.f.b(i2);
        }
    }

    protected final WebViewClient b() {
        return new g(this.b, new WeakReference(this.f), new WeakReference(this.g), new WeakReference(this.d), new WeakReference(this));
    }

    public final com.facebook.ads.internal.s.a d() {
        return this.f;
    }

    public void destroy() {
        if (this.f != null) {
            this.f.c();
            this.f = null;
        }
        u.b(this);
        this.h = null;
        this.g = null;
        com.facebook.ads.internal.q.c.b.a(this);
        super.destroy();
    }

    public final s e() {
        return this.g;
    }

    public final Map<String, String> f() {
        return this.g.e();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.g.a(motionEvent, this, this);
        return super.onTouchEvent(motionEvent);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.b.get() != null) {
            ((b) this.b.get()).a(i);
        }
        if (this.f != null) {
            if (i == 0) {
                Object obj = (!this.j || this.c.get()) ? 1 : null;
                if (obj != null) {
                    this.f.a();
                    return;
                }
            }
            if (i == 8) {
                this.f.c();
            }
        }
    }

    public void setCheckAssetsByJavascriptBridge(boolean z) {
        this.d.set(z);
    }

    public void setLogMultipleImpressions(boolean z) {
        this.i = z;
    }

    public void setOnAssetsLoadedListener(d dVar) {
        this.e = new WeakReference(dVar);
    }

    public void setWaitForAssetsToLoad(boolean z) {
        this.j = z;
    }

    static /* synthetic */ void d(a aVar) {
        aVar.c.set(true);
        new Handler(Looper.getMainLooper()).post(new e(aVar.f));
        if (aVar.e != null && aVar.e.get() != null) {
            ((d) aVar.e.get()).b();
        }
    }
}
