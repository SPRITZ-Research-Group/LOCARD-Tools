package com.facebook.react.modules.network;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.j;
import com.facebook.react.bridge.k;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public final class b extends CookieHandler {
    private static final boolean a = (VERSION.SDK_INT < 21);
    private final a b = new a(this);
    private final ai c;
    @Nullable
    private CookieManager d;

    private class a {
        final /* synthetic */ b a;
        private final Handler b;

        public a(final b bVar) {
            this.a = bVar;
            this.b = new Handler(Looper.getMainLooper(), new Callback(this) {
                final /* synthetic */ a b;

                public final boolean handleMessage(Message msg) {
                    if (msg.what != 1) {
                        return false;
                    }
                    this.b.b();
                    return true;
                }
            });
        }

        public final void a() {
            if (b.a) {
                this.b.sendEmptyMessageDelayed(1, 30000);
            }
        }

        public final void b() {
            this.b.removeMessages(1);
            this.a.a(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    if (b.a) {
                        CookieSyncManager.getInstance().sync();
                    } else {
                        this.a.a.c().flush();
                    }
                }
            });
        }
    }

    public b(ai context) {
        this.c = context;
    }

    public final Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        String cookies = c().getCookie(uri.toString());
        if (TextUtils.isEmpty(cookies)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(cookies));
    }

    public final void put(URI uri, Map<String, List<String>> headers) throws IOException {
        final String url = uri.toString();
        for (Entry<String, List<String>> entry : headers.entrySet()) {
            String key = (String) entry.getKey();
            if (key != null) {
                Object obj;
                if (key.equalsIgnoreCase("Set-cookie") || key.equalsIgnoreCase("Set-cookie2")) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    final List<String> list = (List) entry.getValue();
                    if (a) {
                        a(new Runnable(this) {
                            final /* synthetic */ b c;

                            public final void run() {
                                for (String cookie : list) {
                                    this.c.c().setCookie(url, cookie);
                                }
                                this.c.b.a();
                            }
                        });
                    } else {
                        for (String cookie : list) {
                            c().setCookie(url, cookie, null);
                        }
                        this.b.a();
                    }
                }
            }
        }
    }

    public final void a(final d callback) {
        if (a) {
            new k<Boolean>(this, this.c) {
                final /* synthetic */ b b;

                protected final /* synthetic */ void a(Object obj) {
                    Boolean bool = (Boolean) obj;
                    callback.invoke(bool);
                }

                protected final /* synthetic */ Object a() {
                    this.b.c().removeAllCookie();
                    this.b.b.a();
                    return Boolean.valueOf(true);
                }
            }.execute(new Void[0]);
        } else {
            c().removeAllCookies(new ValueCallback<Boolean>(this) {
                final /* synthetic */ b b;

                public final /* synthetic */ void onReceiveValue(Object obj) {
                    Boolean bool = (Boolean) obj;
                    this.b.b.a();
                    callback.invoke(bool);
                }
            });
        }
    }

    public final void a() {
        if (a) {
            c().removeExpiredCookie();
            this.b.b();
        }
    }

    private void a(final Runnable runnable) {
        new j<Void, Void>(this, this.c) {
            final /* synthetic */ b b;

            protected final /* synthetic */ void a() {
                runnable.run();
            }
        }.execute(new Void[0]);
    }

    private CookieManager c() {
        if (this.d == null) {
            Context context = this.c;
            if (a) {
                CookieSyncManager.createInstance(context).sync();
            }
            this.d = CookieManager.getInstance();
            if (a) {
                this.d.removeExpiredCookie();
            }
        }
        return this.d;
    }
}
