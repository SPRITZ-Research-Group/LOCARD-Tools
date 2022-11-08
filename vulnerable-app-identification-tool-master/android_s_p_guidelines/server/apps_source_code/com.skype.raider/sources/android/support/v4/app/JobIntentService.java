package android.support.v4.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    static final Object h = new Object();
    static final HashMap<ComponentName, h> i = new HashMap();
    b a;
    h b;
    a c;
    boolean d = false;
    boolean e = false;
    boolean f = false;
    final ArrayList<d> g;

    final class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ JobIntentService a;

        a(JobIntentService this$0) {
            this.a = this$0;
        }

        protected final /* synthetic */ void onCancelled(Object obj) {
            this.a.a();
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            this.a.a();
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            while (true) {
                e b = this.a.b();
                if (b == null) {
                    return null;
                }
                this.a.a(b.a());
                b.b();
            }
        }
    }

    interface b {
        IBinder a();

        e b();
    }

    static abstract class h {
        final ComponentName c;
        boolean d;
        int e;

        abstract void a(Intent intent);

        h(ComponentName cn) {
            this.c = cn;
        }

        final void a(int jobId) {
            if (!this.d) {
                this.d = true;
                this.e = jobId;
            } else if (this.e != jobId) {
                throw new IllegalArgumentException("Given job ID " + jobId + " is different than previous " + this.e);
            }
        }

        public void a() {
        }

        public void b() {
        }

        public void c() {
        }
    }

    static final class c extends h {
        boolean a;
        boolean b;
        private final Context f;
        private final WakeLock g;
        private final WakeLock h;

        c(Context context, ComponentName cn) {
            super(cn);
            this.f = context.getApplicationContext();
            PowerManager pm = (PowerManager) context.getSystemService("power");
            this.g = pm.newWakeLock(1, cn.getClassName() + ":launch");
            this.g.setReferenceCounted(false);
            this.h = pm.newWakeLock(1, cn.getClassName() + ":run");
            this.h.setReferenceCounted(false);
        }

        final void a(Intent work) {
            Intent intent = new Intent(work);
            intent.setComponent(this.c);
            if (this.f.startService(intent) != null) {
                synchronized (this) {
                    if (!this.a) {
                        this.a = true;
                        if (!this.b) {
                            this.g.acquire(60000);
                        }
                    }
                }
            }
        }

        public final void a() {
            synchronized (this) {
                this.a = false;
            }
        }

        public final void b() {
            synchronized (this) {
                if (!this.b) {
                    this.b = true;
                    this.h.acquire(600000);
                    this.g.release();
                }
            }
        }

        public final void c() {
            synchronized (this) {
                if (this.b) {
                    if (this.a) {
                        this.g.acquire(60000);
                    }
                    this.b = false;
                    this.h.release();
                }
            }
        }
    }

    interface e {
        Intent a();

        void b();
    }

    final class d implements e {
        final Intent a;
        final int b;
        final /* synthetic */ JobIntentService c;

        d(JobIntentService this$0, Intent intent, int startId) {
            this.c = this$0;
            this.a = intent;
            this.b = startId;
        }

        public final Intent a() {
            return this.a;
        }

        public final void b() {
            this.c.stopSelf(this.b);
        }
    }

    @RequiresApi(26)
    static final class f extends JobServiceEngine implements b {
        final JobIntentService a;
        final Object b = new Object();
        JobParameters c;

        final class a implements e {
            final JobWorkItem a;
            final /* synthetic */ f b;

            a(f this$0, JobWorkItem jobWork) {
                this.b = this$0;
                this.a = jobWork;
            }

            public final Intent a() {
                return this.a.getIntent();
            }

            public final void b() {
                synchronized (this.b.b) {
                    if (this.b.c != null) {
                        this.b.c.completeWork(this.a);
                    }
                }
            }
        }

        f(JobIntentService service) {
            super(service);
            this.a = service;
        }

        public final IBinder a() {
            return getBinder();
        }

        public final boolean onStartJob(JobParameters params) {
            this.c = params;
            this.a.a(false);
            return true;
        }

        public final boolean onStopJob(JobParameters params) {
            JobIntentService jobIntentService = this.a;
            if (jobIntentService.c != null) {
                jobIntentService.c.cancel(jobIntentService.d);
            }
            jobIntentService.e = true;
            synchronized (this.b) {
                this.c = null;
            }
            return true;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final e b() {
            synchronized (this.b) {
                if (this.c == null) {
                    return null;
                }
                JobWorkItem work = this.c.dequeueWork();
            }
        }
    }

    @RequiresApi(26)
    static final class g extends h {
        private final JobInfo a;
        private final JobScheduler b;

        g(Context context, ComponentName cn, int jobId) {
            super(cn);
            a(jobId);
            this.a = new Builder(jobId, this.c).setOverrideDeadline(0).build();
            this.b = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        final void a(Intent work) {
            this.b.enqueue(this.a, new JobWorkItem(work));
        }
    }

    protected abstract void a(@NonNull Intent intent);

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
            this.a = new f(this);
            this.b = null;
            return;
        }
        this.a = null;
        this.b = a((Context) this, new ComponentName(this, getClass()), false, 0);
    }

    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (this.g == null) {
            return 2;
        }
        this.b.a();
        synchronized (this.g) {
            ArrayList arrayList = this.g;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(this, intent, startId));
            a(true);
        }
        return 3;
    }

    public IBinder onBind(@NonNull Intent intent) {
        if (this.a != null) {
            return this.a.a();
        }
        return null;
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

    public static void a(@NonNull Context context, @NonNull Class cls, int jobId, @NonNull Intent work) {
        ComponentName componentName = new ComponentName(context, cls);
        if (work == null) {
            throw new IllegalArgumentException("work must not be null");
        }
        synchronized (h) {
            h a = a(context, componentName, true, jobId);
            a.a(jobId);
            a.a(work);
        }
    }

    private static h a(Context context, ComponentName cn, boolean hasJobId, int jobId) {
        h we = (h) i.get(cn);
        if (we == null) {
            if (VERSION.SDK_INT < 26) {
                we = new c(context, cn);
            } else if (hasJobId) {
                we = new g(context, cn, jobId);
            } else {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            i.put(cn, we);
        }
        return we;
    }

    final void a(boolean reportStarted) {
        if (this.c == null) {
            this.c = new a(this);
            if (this.b != null && reportStarted) {
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

    final e b() {
        if (this.a != null) {
            return this.a.b();
        }
        synchronized (this.g) {
            if (this.g.size() > 0) {
                e eVar = (e) this.g.remove(0);
                return eVar;
            }
            return null;
        }
    }
}
