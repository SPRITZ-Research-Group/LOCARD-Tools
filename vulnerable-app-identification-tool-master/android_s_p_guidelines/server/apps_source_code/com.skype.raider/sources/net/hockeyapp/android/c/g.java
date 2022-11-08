package net.hockeyapp.android.c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import net.hockeyapp.android.c.a.a;
import net.hockeyapp.android.c.a.d;
import net.hockeyapp.android.c.a.i;
import net.hockeyapp.android.c.a.j;
import net.hockeyapp.android.c.a.n;
import net.hockeyapp.android.f.e;

final class g {
    final d a;
    final j b;
    final n c;
    final i d;
    final a e;
    private final Object f;
    private WeakReference<Context> g;
    private String h;
    private String i;

    private g() {
        this.f = new Object();
        this.a = new d();
        this.b = new j();
        this.c = new n();
        this.e = new a();
        this.d = new i();
    }

    @SuppressLint({"StaticFieldLeak"})
    g(Context context, String appIdentifier) {
        this();
        this.g = new WeakReference(context);
        this.h = net.hockeyapp.android.f.j.d(appIdentifier);
        e.b("HockeyApp-Metrics");
        String str = VERSION.RELEASE;
        synchronized (this.a) {
            this.a.g(str);
        }
        str = "Android";
        synchronized (this.a) {
            this.a.f(str);
        }
        d(Build.MODEL);
        str = Build.MANUFACTURER;
        synchronized (this.a) {
            this.a.e(str);
        }
        str = Locale.getDefault().toString();
        synchronized (this.a) {
            this.a.c(str);
        }
        str = Locale.getDefault().getLanguage();
        synchronized (this.a) {
            this.a.b(str);
        }
        a();
        Context d = d();
        TelephonyManager telephonyManager = d != null ? (TelephonyManager) d.getSystemService("phone") : null;
        if (telephonyManager == null || telephonyManager.getPhoneType() != 0) {
            e("Phone");
        } else {
            e("Tablet");
        }
        if (net.hockeyapp.android.f.j.a()) {
            d("[Emulator]" + this.a.a());
        }
        b("android:" + "5.1.0");
        e.b("HockeyApp-Metrics");
        this.i = "";
        if (net.hockeyapp.android.a.c != null) {
            this.i = net.hockeyapp.android.a.c;
        }
        str = String.format("%s (%S)", new Object[]{net.hockeyapp.android.a.b, net.hockeyapp.android.a.a});
        synchronized (this.e) {
            this.e.a(str);
        }
        b("android:" + "5.1.0");
        net.hockeyapp.android.f.a.a(new AsyncTask<Void, Object, Object>(this) {
            final /* synthetic */ g a;

            {
                this.a = this$0;
            }

            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                return a();
            }

            private Object a() {
                try {
                    String deviceId = (String) net.hockeyapp.android.a.a().get();
                    g gVar = this.a;
                    synchronized (gVar.a) {
                        gVar.a.a(deviceId);
                    }
                    gVar = this.a;
                    synchronized (gVar.c) {
                        gVar.c.a(deviceId);
                    }
                } catch (InterruptedException e) {
                } catch (ExecutionException e2) {
                }
                return null;
                e.c();
                return null;
            }
        });
    }

    final void a(String sessionId) {
        e.b("HockeyApp-Metrics");
        synchronized (this.b) {
            this.b.a(sessionId);
        }
        e.b("HockeyApp-Metrics");
        String str = "true";
        synchronized (this.b) {
            this.b.c(str);
        }
        Context d = d();
        if (d == null) {
            e.d("HockeyApp-Metrics");
            return;
        }
        SharedPreferences sharedPreferences = d.getSharedPreferences("HOCKEY_APP_TELEMETRY_CONTEXT", 0);
        if (sharedPreferences.getBoolean("SESSION_IS_FIRST", false)) {
            c("false");
            e.b("HockeyApp-Metrics");
            return;
        }
        Editor edit = sharedPreferences.edit();
        edit.putBoolean("SESSION_IS_FIRST", true);
        edit.apply();
        c("true");
        e.b("HockeyApp-Metrics");
    }

    final void a() {
        Context context = d();
        if (context != null) {
            int width;
            int height;
            WindowManager wm = (WindowManager) context.getSystemService("window");
            Point size;
            Display d;
            if (VERSION.SDK_INT >= 17) {
                size = new Point();
                d = wm.getDefaultDisplay();
                if (d != null) {
                    d.getRealSize(size);
                    width = size.x;
                    height = size.y;
                } else {
                    width = 0;
                    height = 0;
                }
            } else {
                try {
                    Method mGetRawW = Display.class.getMethod("getRawWidth", new Class[0]);
                    Method mGetRawH = Display.class.getMethod("getRawHeight", new Class[0]);
                    Display display = wm.getDefaultDisplay();
                    width = ((Integer) mGetRawW.invoke(display, new Object[0])).intValue();
                    height = ((Integer) mGetRawH.invoke(display, new Object[0])).intValue();
                } catch (Exception ex) {
                    size = new Point();
                    d = wm.getDefaultDisplay();
                    if (d != null) {
                        d.getSize(size);
                        width = size.x;
                        height = size.y;
                    } else {
                        width = 0;
                        height = 0;
                    }
                    new StringBuilder("Couldn't determine screen resolution: ").append(ex.toString());
                    e.b("HockeyApp-Metrics");
                }
            }
            String resolutionString = String.valueOf(height) + "x" + String.valueOf(width);
            synchronized (this.a) {
                this.a.h(resolutionString);
            }
        }
    }

    private Context d() {
        return this.g != null ? (Context) this.g.get() : null;
    }

    protected final Map<String, String> b() {
        Map contextTags = new LinkedHashMap();
        synchronized (this.e) {
            this.e.a(contextTags);
        }
        synchronized (this.a) {
            this.a.a(contextTags);
        }
        synchronized (this.b) {
            this.b.a(contextTags);
        }
        synchronized (this.c) {
            this.c.a(contextTags);
        }
        synchronized (this.d) {
            this.d.a(contextTags);
        }
        return contextTags;
    }

    public final String c() {
        String str;
        synchronized (this.f) {
            str = this.h;
        }
        return str;
    }

    private void b(String sdkVersion) {
        synchronized (this.d) {
            this.d.a(sdkVersion);
        }
    }

    private void c(String isFirst) {
        synchronized (this.b) {
            this.b.b(isFirst);
        }
    }

    private void d(String deviceModel) {
        synchronized (this.a) {
            this.a.d(deviceModel);
        }
    }

    private void e(String deviceType) {
        synchronized (this.a) {
            this.a.i(deviceType);
        }
    }
}
