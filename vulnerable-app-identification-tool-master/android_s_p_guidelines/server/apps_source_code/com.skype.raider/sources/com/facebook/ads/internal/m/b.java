package com.facebook.ads.internal.m;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.ads.internal.p.a.n;
import com.facebook.ads.internal.p.a.p;
import com.facebook.ads.internal.q.c.d;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class b {
    private static final String a = b.class.getSimpleName();
    private final a b;
    private final Context c;
    private final ThreadPoolExecutor d;
    private final ConnectivityManager e;
    private final com.facebook.ads.internal.p.a.a f;
    private final Handler g;
    private final long h;
    private final long i;
    private final Runnable j = new Runnable(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void run() {
            b.a(this.a);
            if (this.a.n > 0) {
                try {
                    Thread.sleep(this.a.n);
                } catch (InterruptedException e) {
                }
            }
            b.c(this.a);
        }
    };
    private final Runnable k = new Runnable(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.l = false;
            if (this.a.d.getQueue().isEmpty()) {
                this.a.d.execute(this.a.j);
            }
        }
    };
    private volatile boolean l;
    private int m;
    private long n;

    interface a {
        JSONObject a();

        boolean a(JSONArray jSONArray);

        void b();

        void b(JSONArray jSONArray);

        boolean c();
    }

    b(Context context, a aVar) {
        this.b = aVar;
        this.c = context;
        this.d = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.e = (ConnectivityManager) context.getSystemService("connectivity");
        this.f = d.b(context);
        this.g = new Handler(Looper.getMainLooper());
        this.h = com.facebook.ads.internal.l.a.l(context);
        this.i = com.facebook.ads.internal.l.a.m(context);
    }

    static /* synthetic */ int a(b bVar) {
        int i = bVar.m + 1;
        bVar.m = i;
        return i;
    }

    private void a(long j) {
        this.g.postDelayed(this.k, j);
    }

    private void c() {
        if (this.m >= 5) {
            d();
            b();
            return;
        }
        if (this.m == 1) {
            this.n = 2000;
        } else {
            this.n *= 2;
        }
        a();
    }

    private void d() {
        this.m = 0;
        this.n = 0;
        if (this.d.getQueue().size() == 0) {
            this.b.b();
        }
    }

    final void a() {
        this.l = true;
        this.g.removeCallbacks(this.k);
        a(this.h);
    }

    final void b() {
        if (!this.l) {
            this.l = true;
            this.g.removeCallbacks(this.k);
            a(this.i);
        }
    }

    static /* synthetic */ void c(b bVar) {
        try {
            NetworkInfo activeNetworkInfo = bVar.e.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                bVar.a(bVar.i);
                return;
            }
            JSONObject a = bVar.b.a();
            if (a == null) {
                bVar.d();
                return;
            }
            String str;
            Object e;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("attempt", String.valueOf(bVar.m));
            a.put("data", jSONObject);
            p pVar = new p();
            pVar.a("payload", a.toString());
            com.facebook.ads.internal.p.a.a aVar = bVar.f;
            Context context = bVar.c;
            if (TextUtils.isEmpty(com.facebook.ads.internal.t.a.a())) {
                str = "https://www.facebook.com/adnw_logging/";
            } else {
                str = String.format(Locale.US, "https://www.%s.facebook.com/adnw_logging/", new Object[]{com.facebook.ads.internal.t.a.a()});
            }
            CharSequence u = com.facebook.ads.internal.l.a.u(context);
            if (!TextUtils.isEmpty(u)) {
                str = str.replace("www", u);
            }
            n a2 = aVar.a(str, pVar);
            if (a2 != null) {
                e = a2.e();
            } else {
                e = null;
            }
            if (TextUtils.isEmpty(e)) {
                if (a.has(EventsEntry.TABLE_NAME)) {
                    bVar.b.b(a.getJSONArray(EventsEntry.TABLE_NAME));
                }
                bVar.c();
            } else if (a2.a() != 200) {
                if (a.has(EventsEntry.TABLE_NAME)) {
                    bVar.b.b(a.getJSONArray(EventsEntry.TABLE_NAME));
                }
                bVar.c();
            } else if (!bVar.b.a(new JSONArray(e))) {
                bVar.c();
            } else if (bVar.b.c()) {
                bVar.c();
            } else {
                bVar.d();
            }
        } catch (Exception e2) {
            bVar.c();
        }
    }
}
