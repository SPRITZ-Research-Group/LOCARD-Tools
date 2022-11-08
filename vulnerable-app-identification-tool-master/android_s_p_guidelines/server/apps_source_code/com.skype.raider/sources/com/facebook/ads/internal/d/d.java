package com.facebook.ads.internal.d;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.p.b.f;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class d {
    private static final String a = d.class.getSimpleName();
    private static d b;
    private final Future<f> c;

    private d(final Context context) {
        this.c = Executors.newSingleThreadExecutor().submit(new Callable<f>(this) {
            final /* synthetic */ d b;

            public final /* synthetic */ Object call() {
                return new f(context);
            }
        });
    }

    public static d a(Context context) {
        if (b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (d.class) {
                if (b == null) {
                    b = new d(applicationContext);
                }
            }
        }
        return b;
    }

    @Nullable
    private f a() {
        try {
            return (f) this.c.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
        } catch (ExecutionException e2) {
        } catch (TimeoutException e3) {
        }
        return null;
    }

    public final boolean a(String str) {
        f a = a();
        return a != null && a.a(str);
    }

    @Nullable
    public final String b(String str) {
        f a = a();
        return a == null ? null : a.b(str);
    }
}
