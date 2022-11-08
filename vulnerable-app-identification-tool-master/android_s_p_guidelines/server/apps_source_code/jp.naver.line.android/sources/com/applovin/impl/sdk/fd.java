package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.linecorp.linelive.player.component.videoplayer.LiveVideoPlayerService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class fd {
    private final String a = "TaskManager";
    private final AppLovinSdkImpl b;
    private final AppLovinLogger c;
    private final ScheduledThreadPoolExecutor d;
    private final ScheduledThreadPoolExecutor e;
    private final ScheduledThreadPoolExecutor f;
    private final List<fh> g = new ArrayList(5);
    private final Object h = new Object();
    private boolean i;

    fd(AppLovinSdkImpl appLovinSdkImpl) {
        this.b = appLovinSdkImpl;
        this.c = appLovinSdkImpl.getLogger();
        this.d = a(LiveVideoPlayerService.TAG_MAIN);
        this.e = a("back");
        this.f = a("postbacks");
    }

    private long a(fe feVar) {
        long taskCount;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        if (feVar == fe.MAIN) {
            taskCount = this.d.getTaskCount();
            scheduledThreadPoolExecutor = this.d;
        } else if (feVar == fe.BACKGROUND) {
            taskCount = this.e.getTaskCount();
            scheduledThreadPoolExecutor = this.e;
        } else if (feVar != fe.POSTBACKS) {
            return 0;
        } else {
            taskCount = this.f.getTaskCount();
            scheduledThreadPoolExecutor = this.f;
        }
        return taskCount - scheduledThreadPoolExecutor.getCompletedTaskCount();
    }

    private ScheduledThreadPoolExecutor a(String str) {
        return new ScheduledThreadPoolExecutor(1, new ff(this, str));
    }

    private static void a(Runnable runnable, long j, ScheduledExecutorService scheduledExecutorService) {
        if (j > 0) {
            scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } else {
            scheduledExecutorService.submit(runnable);
        }
    }

    private boolean a(fh fhVar) {
        if (fhVar.c.g) {
            return false;
        }
        synchronized (this.h) {
            if (this.i) {
                return false;
            }
            this.g.add(fhVar);
            return true;
        }
    }

    void a() {
        synchronized (this.h) {
            this.i = false;
        }
    }

    void a(dx dxVar) {
        if (dxVar != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder("Executing ");
                stringBuilder.append(dxVar.a());
                stringBuilder.append(" immediately...");
                this.c.i("TaskManager", stringBuilder.toString());
                dxVar.run();
                stringBuilder = new StringBuilder();
                stringBuilder.append(dxVar.a());
                stringBuilder.append(" finished executing...");
                this.c.i("TaskManager", stringBuilder.toString());
                return;
            } catch (Throwable th) {
                this.c.e("TaskManager", "Task failed execution", th);
                return;
            }
        }
        this.c.e("TaskManager", "Attempted to execute null task immediately");
    }

    public void a(dx dxVar, fe feVar) {
        a(dxVar, feVar, 0);
    }

    void a(dx dxVar, fe feVar, long j) {
        if (dxVar == null) {
            throw new IllegalArgumentException("No task specified");
        } else if (j < 0) {
            throw new IllegalArgumentException("Invalid delay specified: ".concat(String.valueOf(j)));
        } else if (feVar == fe.MAIN || feVar == fe.BACKGROUND || feVar == fe.POSTBACKS) {
            Runnable fhVar = new fh(this, dxVar, feVar);
            if (a((fh) fhVar)) {
                AppLovinLogger appLovinLogger = this.c;
                String a = dxVar.a();
                StringBuilder stringBuilder = new StringBuilder("Task ");
                stringBuilder.append(dxVar.a());
                stringBuilder.append(" execution delayed until after init");
                appLovinLogger.i(a, stringBuilder.toString());
                return;
            }
            long a2 = a(feVar) + 1;
            StringBuilder stringBuilder2 = new StringBuilder("Scheduling ");
            stringBuilder2.append(dxVar.c);
            stringBuilder2.append(" on ");
            stringBuilder2.append(feVar);
            stringBuilder2.append(" queue in ");
            stringBuilder2.append(j);
            stringBuilder2.append("ms with new queue size ");
            stringBuilder2.append(a2);
            this.c.d("TaskManager", stringBuilder2.toString());
            if (feVar == fe.MAIN) {
                a(fhVar, j, this.d);
            } else if (feVar == fe.BACKGROUND) {
                a(fhVar, j, this.e);
            } else {
                if (feVar == fe.POSTBACKS) {
                    a(fhVar, j, this.f);
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid queue specified");
        }
    }

    void b() {
        synchronized (this.h) {
            this.i = true;
            for (fh fhVar : this.g) {
                a(fhVar.c, fhVar.d);
            }
            this.g.clear();
        }
    }
}
