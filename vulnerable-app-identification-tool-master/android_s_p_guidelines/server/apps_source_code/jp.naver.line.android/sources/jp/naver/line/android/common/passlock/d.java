package jp.naver.line.android.common.passlock;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.util.Log;
import com.google.android.exoplayer.hls.HlsChunkSource;
import defpackage.lj;
import defpackage.tfa;
import java.util.ArrayList;
import java.util.List;
import jp.naver.line.android.common.e;

public final class d {
    private static final d a = new d();
    private boolean b = true;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private long g = Long.MAX_VALUE;
    private boolean h;

    private d() {
    }

    public static d a() {
        return a;
    }

    public final void a(Context context) {
        if (e.a()) {
            if (this.d) {
                this.d = false;
                return;
            }
            Object obj;
            if (this.e || (tfa.b() && this.b && (!this.f ? this.c : System.currentTimeMillis() - this.g < HlsChunkSource.DEFAULT_PLAYLIST_BLACKLIST_MS))) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                this.e = true;
                if (context instanceof Activity) {
                    context.startActivity(new Intent(context, InputPassActivity.class));
                } else {
                    Intent intent = new Intent(context, InputPassActivityForNewTask.class);
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                }
            }
            this.b = false;
            this.c = false;
            this.d = false;
            this.f = false;
            this.g = Long.MAX_VALUE;
        } else if (jp.naver.line.android.common.access.remote.e.a().g() && (context instanceof Activity)) {
            ((Activity) context).moveTaskToBack(true);
        } else {
            jp.naver.line.android.common.access.remote.e.a().b();
        }
    }

    public final void b(Context context) {
        if (e.a()) {
            List a = a(context, 1);
            if (!(a.isEmpty() || a(context, ((RunningTaskInfo) a.get(0)).topActivity.getPackageName()))) {
                this.b = true;
                this.g = System.currentTimeMillis();
            }
            return;
        }
        jp.naver.line.android.common.access.remote.e.a().c();
    }

    public final void c(Context context) {
        if (e.a()) {
            List a = a(context, 2);
            if (VERSION.SDK_INT >= 23) {
                if (a.size() > 0 && !a(context, ((RunningTaskInfo) a.get(0)).baseActivity.getPackageName())) {
                    this.b = true;
                    this.g = System.currentTimeMillis();
                }
                return;
            } else if (a.size() >= 2) {
                RunningTaskInfo runningTaskInfo = (RunningTaskInfo) a.get(0);
                RunningTaskInfo runningTaskInfo2 = (RunningTaskInfo) a.get(1);
                ComponentName componentName = runningTaskInfo.baseActivity;
                if (componentName.equals(runningTaskInfo.topActivity) && !runningTaskInfo2.baseActivity.getPackageName().equals(componentName.getPackageName())) {
                    this.b = true;
                    this.g = System.currentTimeMillis();
                }
            }
            return;
        }
        jp.naver.line.android.common.access.remote.e.a().d();
    }

    private static List<RunningTaskInfo> a(Context context, int i) {
        ArrayList arrayList = new ArrayList();
        try {
            return ((ActivityManager) context.getSystemService("activity")).getRunningTasks(i);
        } catch (Throwable e) {
            Log.w("PassLockManager", e);
            return arrayList;
        }
    }

    public final void d(Context context) {
        this.e = false;
        this.b = false;
        lj.a(context).a(new Intent("jp.naver.line.android.activity.passlock.PassLockManager.ACTION_UNLOCK"));
    }

    public final boolean b() {
        return this.e;
    }

    public final void c() {
        if (e.a()) {
            this.c = true;
        } else {
            jp.naver.line.android.common.access.remote.e.a().e();
        }
    }

    public final void d() {
        this.d = true;
    }

    public final void e() {
        this.b = true;
        this.c = false;
        this.f = false;
    }

    public final boolean f() {
        boolean z = this.h;
        this.h = false;
        return z;
    }

    public final void g() {
        this.h = true;
    }

    private static boolean a(Context context, String str) {
        return context.getPackageName().equals(str) || "com.linecorp.linepay".equals(str);
    }
}
