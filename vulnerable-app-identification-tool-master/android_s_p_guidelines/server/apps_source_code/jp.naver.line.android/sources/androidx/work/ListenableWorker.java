package androidx.work;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Keep;
import defpackage.bon;
import java.util.UUID;
import java.util.concurrent.Executor;

public abstract class ListenableWorker {
    private Context a;
    private WorkerParameters b;
    private volatile boolean c;
    private boolean d;

    public abstract bon<l> d();

    public void f() {
    }

    @SuppressLint({"BanKeepAnnotation"})
    @Keep
    public ListenableWorker(Context context, WorkerParameters workerParameters) {
        if (context == null) {
            throw new IllegalArgumentException("Application Context is null");
        } else if (workerParameters != null) {
            this.a = context;
            this.b = workerParameters;
        } else {
            throw new IllegalArgumentException("WorkerParameters is null");
        }
    }

    public final Context a() {
        return this.a;
    }

    public final UUID b() {
        return this.b.a();
    }

    public final h c() {
        return this.b.b();
    }

    public final void e() {
        this.c = true;
        f();
    }

    public final boolean g() {
        return this.d;
    }

    public final void h() {
        this.d = true;
    }

    public final Executor i() {
        return this.b.c();
    }
}
