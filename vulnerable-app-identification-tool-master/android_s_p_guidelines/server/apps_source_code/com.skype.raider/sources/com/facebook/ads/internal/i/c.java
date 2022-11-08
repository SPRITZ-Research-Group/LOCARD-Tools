package com.facebook.ads.internal.i;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64OutputStream;
import com.facebook.ads.internal.f.e;
import com.facebook.ads.internal.g.b;
import com.facebook.ads.internal.q.a.g;
import com.facebook.ads.internal.q.a.g.a;
import com.facebook.ads.internal.q.a.h;
import com.facebook.ads.internal.q.a.i;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.n;
import com.facebook.ads.internal.q.a.q;
import com.facebook.ads.internal.q.a.r;
import com.facebook.ads.internal.q.a.u;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.DeflaterOutputStream;
import org.json.JSONObject;

public final class c {
    public static int a = 1303;
    private static final AtomicInteger b = new AtomicInteger(0);
    private static String c = null;
    private static final a d = g.a();
    private final Context e;
    private final b f;

    public c(Context context, boolean z) {
        this.e = context;
        this.f = new b(context);
        a(context, z);
    }

    private static void a(final Context context, boolean z) {
        if (b.compareAndSet(0, 1)) {
            try {
                n.a();
                final SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
                final String str = "AFP;" + new b(context).g();
                c = sharedPreferences.getString(str, null);
                Object futureTask = new FutureTask(new Callable<Boolean>() {
                    public final /* synthetic */ Object call() {
                        c.c = c.b(context, context.getPackageName());
                        sharedPreferences.edit().putString(str, c.c).apply();
                        c.b.set(2);
                        return Boolean.valueOf(true);
                    }
                });
                Executors.newSingleThreadExecutor().submit(futureTask);
                if (z) {
                    futureTask.get();
                }
            } catch (Exception e) {
                b.set(0);
            }
        }
    }

    @Nullable
    private static String b(Context context, String str) {
        try {
            return i.a(new File(context.getPackageManager().getApplicationInfo(str, 0).sourceDir));
        } catch (Exception e) {
            Map b = new c(context, false).b();
            b.put("subtype", "generic");
            b.put("subtype_code", String.valueOf(a));
            e.a(e, context, b);
            return null;
        }
    }

    public final String a() {
        Base64OutputStream base64OutputStream;
        Throwable e;
        DeflaterOutputStream deflaterOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Base64OutputStream base64OutputStream2 = null;
        a(this.e, true);
        ByteArrayOutputStream byteArrayOutputStream2;
        try {
            byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                base64OutputStream = new Base64OutputStream(byteArrayOutputStream2, 0);
            } catch (IOException e2) {
                e = e2;
                deflaterOutputStream = null;
                byteArrayOutputStream = byteArrayOutputStream2;
                try {
                    throw new RuntimeException("Failed to build user token", e);
                } catch (Throwable th) {
                    e = th;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    base64OutputStream = base64OutputStream2;
                    if (deflaterOutputStream != null) {
                        try {
                            deflaterOutputStream.close();
                        } catch (IOException e3) {
                            throw e;
                        }
                    }
                    if (base64OutputStream != null) {
                        base64OutputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                deflaterOutputStream = null;
                base64OutputStream = null;
                if (deflaterOutputStream != null) {
                    deflaterOutputStream.close();
                }
                if (base64OutputStream != null) {
                    base64OutputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                throw e;
            }
            try {
                deflaterOutputStream = new DeflaterOutputStream(base64OutputStream);
                try {
                    Map b = b();
                    if (TextUtils.isEmpty(com.facebook.ads.internal.c.b.b)) {
                        com.facebook.ads.internal.c.b.a(this.e);
                    }
                    b.put("IDFA", com.facebook.ads.internal.c.b.b);
                    deflaterOutputStream.write(new JSONObject(b).toString().getBytes());
                    deflaterOutputStream.close();
                    String replaceAll = byteArrayOutputStream2.toString().replaceAll("\n", "");
                    try {
                        deflaterOutputStream.close();
                        base64OutputStream.close();
                        byteArrayOutputStream2.close();
                    } catch (IOException e4) {
                    }
                    return replaceAll;
                } catch (IOException e5) {
                    e = e5;
                    base64OutputStream2 = base64OutputStream;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    throw new RuntimeException("Failed to build user token", e);
                } catch (Throwable th3) {
                    e = th3;
                    if (deflaterOutputStream != null) {
                        deflaterOutputStream.close();
                    }
                    if (base64OutputStream != null) {
                        base64OutputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    throw e;
                }
            } catch (IOException e6) {
                e = e6;
                deflaterOutputStream = null;
                base64OutputStream2 = base64OutputStream;
                byteArrayOutputStream = byteArrayOutputStream2;
                throw new RuntimeException("Failed to build user token", e);
            } catch (Throwable th4) {
                e = th4;
                deflaterOutputStream = null;
                if (deflaterOutputStream != null) {
                    deflaterOutputStream.close();
                }
                if (base64OutputStream != null) {
                    base64OutputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                throw e;
            }
        } catch (IOException e7) {
            e = e7;
            deflaterOutputStream = null;
            byteArrayOutputStream = null;
            throw new RuntimeException("Failed to build user token", e);
        } catch (Throwable th5) {
            e = th5;
            deflaterOutputStream = null;
            base64OutputStream = null;
            byteArrayOutputStream2 = null;
            if (deflaterOutputStream != null) {
                deflaterOutputStream.close();
            }
            if (base64OutputStream != null) {
                base64OutputStream.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            throw e;
        }
    }

    public final Map<String, String> b() {
        a(this.e, false);
        com.facebook.ads.internal.g.a.a(this.e);
        Map<String, String> hashMap = new HashMap();
        hashMap.put("SDK", "android");
        hashMap.put("SDK_VERSION", "4.99.1");
        hashMap.put("LOCALE", Locale.getDefault().toString());
        float f = u.b;
        int i = this.e.getResources().getDisplayMetrics().widthPixels;
        int i2 = this.e.getResources().getDisplayMetrics().heightPixels;
        hashMap.put("DENSITY", String.valueOf(f));
        hashMap.put("SCREEN_WIDTH", String.valueOf((int) (((float) i) / f)));
        hashMap.put("SCREEN_HEIGHT", String.valueOf((int) (((float) i2) / f)));
        hashMap.put("ATTRIBUTION_ID", com.facebook.ads.internal.c.b.a);
        hashMap.put("ID_SOURCE", com.facebook.ads.internal.c.b.d);
        hashMap.put("OS", "Android");
        hashMap.put("OSVERS", b.a);
        hashMap.put("BUNDLE", this.f.f());
        hashMap.put("APPNAME", this.f.d());
        hashMap.put("APPVERS", this.f.g());
        hashMap.put("APPBUILD", String.valueOf(this.f.h()));
        hashMap.put("CARRIER", this.f.c());
        hashMap.put("MAKE", b.a());
        hashMap.put("MODEL", b.b());
        hashMap.put("ROOTED", String.valueOf(d.d));
        hashMap.put("INSTALLER", this.f.e());
        hashMap.put("SDK_CAPABILITY", com.facebook.ads.internal.q.a.c.a());
        hashMap.put("NETWORK_TYPE", String.valueOf(q.a(this.e).g));
        hashMap.put("SESSION_TIME", r.a(n.b()));
        hashMap.put("SESSION_ID", n.c());
        if (c != null) {
            hashMap.put("AFP", c);
        }
        String a = g.a(this.e);
        if (a != null) {
            hashMap.put("ASHAS", a);
        }
        hashMap.put("UNITY", String.valueOf(h.a(this.e)));
        a = com.facebook.ads.internal.t.a.b();
        if (a != null) {
            hashMap.put("MEDIATION_SERVICE", a);
        }
        hashMap.put("ACCESSIBILITY_ENABLED", String.valueOf(this.f.i()));
        if (this.f.j() != -1) {
            hashMap.put("APP_MIN_SDK_VERSION", String.valueOf(this.f.j()));
        }
        hashMap.put("VALPARAMS", b.a());
        hashMap.put("ANALOG", k.a(com.facebook.ads.internal.g.a.a()));
        return hashMap;
    }
}
