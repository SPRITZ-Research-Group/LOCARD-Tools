package com.facebook.ads.internal.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.Settings.System;
import android.support.v4.content.c;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.d.a;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.h.d;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class w extends h {
    private static final String c = w.class.getSimpleName();
    private static final int[] d = new int[]{-1, -6, -7, -8};
    private final String e = UUID.randomUUID().toString();
    private Context f;
    private i g;
    private boolean h = false;
    private String i;
    private String j;
    private long k;
    private k l;
    private ad m;

    private void a(Context context, final boolean z) {
        final b bVar = new b(context);
        bVar.a(this.l.e().a());
        bVar.a(this.l.e().f(), this.l.e().h(), this.l.e().g());
        bVar.a(this.l.a().b(), -1, -1);
        for (String a : this.l.f().d()) {
            bVar.a(a, -1, -1);
        }
        bVar.a(new a(this) {
            final /* synthetic */ w c;

            private void c() {
                this.c.h = true;
                this.c.g.a(this.c);
                this.c.l.b(bVar.b(this.c.l.e().a()));
            }

            public final void a() {
                c();
            }

            public final void b() {
                if (z) {
                    i a = this.c.g;
                    h hVar = this.c;
                    com.facebook.ads.a aVar = com.facebook.ads.a.f;
                    a.b(hVar);
                    return;
                }
                c();
            }
        });
    }

    static /* synthetic */ boolean a(WebResourceError webResourceError) {
        if (webResourceError == null || VERSION.SDK_INT < 23) {
            return true;
        }
        for (int i : d) {
            if (webResourceError.getErrorCode() == i) {
                return true;
            }
        }
        return false;
    }

    private boolean b() {
        return this.l.e().i() != null;
    }

    public final void a(Context context, i iVar, Map<String, Object> map, final boolean z) {
        this.f = context;
        this.g = iVar;
        this.h = false;
        this.j = (String) map.get("placementId");
        this.k = ((Long) map.get("requestTime")).longValue();
        int k = ((d) map.get("definition")).k();
        this.i = this.j != null ? this.j.split("_")[0] : "";
        this.l = k.a((JSONObject) map.get("data"));
        this.l.a(k);
        if (!TextUtils.isEmpty(this.l.e().a()) || b()) {
            this.m = new ad(this.e, this, iVar);
            c.a(this.f).a(this.m, this.m.a());
            if (!b()) {
                a(context, z);
                return;
            } else if (com.facebook.ads.internal.l.a.f(context)) {
                this.h = true;
                this.g.a(this);
                return;
            } else {
                WebView webView = new WebView(context);
                webView.getSettings().setCacheMode(1);
                webView.setWebViewClient(new WebViewClient(this) {
                    boolean a = false;
                    final /* synthetic */ w c;

                    private void a() {
                        this.c.h = true;
                        this.c.g.a(this.c);
                    }

                    private void a(WebResourceError webResourceError) {
                        if (z || !w.a(webResourceError)) {
                            i a = this.c.g;
                            h hVar = this.c;
                            com.facebook.ads.a aVar = com.facebook.ads.a.f;
                            a.b(hVar);
                            return;
                        }
                        a();
                    }

                    public final void onPageFinished(WebView webView, String str) {
                        this.a = true;
                        a();
                    }

                    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                        super.onPageStarted(webView, str, bitmap);
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public final void run() {
                                if (!this.a.a) {
                                    this.a.a(null);
                                }
                            }
                        }, 10000);
                    }

                    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                        this.a = true;
                        a(webResourceError);
                    }
                });
                webView.loadUrl(this.l.e().i().a());
                return;
            }
        }
        i iVar2 = this.g;
        com.facebook.ads.a aVar = com.facebook.ads.a.e;
        iVar2.b(this);
    }

    public final boolean a() {
        if (!this.h) {
            return false;
        }
        String b;
        if (this.a != null) {
            b = com.facebook.ads.c.b();
            if (b == null || b.isEmpty()) {
                b = "https://www.facebook.com/audience_network/server_side_reward";
            } else {
                b = String.format("https://www.%s.facebook.com/audience_network/server_side_reward", new Object[]{b});
            }
            Uri parse = Uri.parse(b);
            Builder builder = new Builder();
            builder.scheme(parse.getScheme());
            builder.authority(parse.getAuthority());
            builder.path(parse.getPath());
            builder.query(parse.getQuery());
            builder.fragment(parse.getFragment());
            builder.appendQueryParameter("puid", this.a.a());
            builder.appendQueryParameter("pc", this.a.b());
            builder.appendQueryParameter("ptid", this.e);
            builder.appendQueryParameter("appid", this.i);
            b = builder.build().toString();
        } else {
            b = null;
        }
        this.l.a(b);
        Intent intent = new Intent(this.f, AudienceNetworkActivity.class);
        intent.putExtra("viewType", com.facebook.ads.internal.t.b.a.REWARDED_VIDEO);
        intent.putExtra("rewardedVideoAdDataBundle", this.l);
        intent.putExtra("uniqueId", this.e);
        intent.putExtra("rewardServerURL", b);
        intent.putExtra("placementId", this.j);
        intent.putExtra("requestTime", this.k);
        if (this.b != -1 && System.getInt(this.f.getContentResolver(), "accelerometer_rotation", 0) != 1) {
            intent.putExtra("predefinedOrientationKey", this.b);
        } else if (!com.facebook.ads.internal.l.a.o(this.f)) {
            intent.putExtra("predefinedOrientationKey", 6);
        }
        if (!(this.f instanceof Activity)) {
            intent.setFlags(intent.getFlags() | ErrorDialogData.BINDER_CRASH);
        }
        this.f.startActivity(intent);
        return true;
    }

    public final void e() {
        if (this.m != null) {
            try {
                c.a(this.f).a(this.m);
            } catch (Exception e) {
            }
        }
    }
}
