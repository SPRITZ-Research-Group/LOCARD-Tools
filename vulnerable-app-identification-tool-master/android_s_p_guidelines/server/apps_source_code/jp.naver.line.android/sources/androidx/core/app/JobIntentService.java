package androidx.core.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    static final Object h = new Object();
    static final HashMap<ComponentName, t> i = new HashMap();
    m a;
    t b;
    l c;
    boolean d = false;
    boolean e = false;
    boolean f = false;
    final ArrayList<o> g;

    protected abstract void a(Intent intent);

    public JobIntentService() {
        if (VERSION.SDK_INT >= 26) {
            this.g = null;
        } else {
            this.g = new ArrayList();
        }
    }

    public void onCreate() {
        super.onCreate();
        if (VERSION.SDK_INT >= 26) {
            this.a = new q(this);
            this.b = null;
            return;
        }
        this.a = null;
        this.b = a((Context) this, new ComponentName(this, getClass()), false, 0);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (this.g == null) {
            return 2;
        }
        this.b.a();
        synchronized (this.g) {
            ArrayList arrayList = this.g;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new o(this, intent, i2));
            a(true);
        }
        return 3;
    }

    public IBinder onBind(Intent intent) {
        return this.a != null ? this.a.a() : null;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            synchronized (this.g) {
                this.f = true;
                this.b.c();
            }
        }
    }

    public static void a(Context context, Class cls, int i, Intent intent) {
        ComponentName componentName = new ComponentName(context, cls);
        if (intent != null) {
            synchronized (h) {
                t a = a(context, componentName, true, i);
                a.a(i);
                a.a(intent);
            }
            return;
        }
        throw new IllegalArgumentException("work must not be null");
    }

    private static t a(Context context, ComponentName componentName, boolean z, int i) {
        t tVar = (t) i.get(componentName);
        if (tVar != null) {
            return tVar;
        }
        t nVar;
        if (VERSION.SDK_INT < 26) {
            nVar = new n(context, componentName);
        } else if (z) {
            nVar = new s(context, componentName, i);
        } else {
            throw new IllegalArgumentException("Can't be here without a job id");
        }
        tVar = nVar;
        i.put(componentName, tVar);
        return tVar;
    }

    final void a(boolean z) {
        if (this.c == null) {
            this.c = new l(this);
            if (this.b != null && z) {
                this.b.b();
            }
            this.c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    final void a() {
        if (this.g != null) {
            synchronized (this.g) {
                this.c = null;
                if (this.g != null && this.g.size() > 0) {
                    a(false);
                } else if (!this.f) {
                    this.b.c();
                }
            }
        }
    }

    final p b() {
        if (this.a != null) {
            return this.a.b();
        }
        synchronized (this.g) {
            if (this.g.size() > 0) {
                p pVar = (p) this.g.remove(0);
                return pVar;
            }
            return null;
        }
    }
}
