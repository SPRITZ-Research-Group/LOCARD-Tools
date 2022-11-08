package jp.naver.line.android.util;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class bq<V> extends FutureTask<V> {
    public final String a;
    public final boolean b;
    public final boolean c;
    public final String d;
    public final boolean e;
    private final aq f;

    public bq(Runnable runnable, V v, boolean z) {
        super(runnable, v);
        this.a = runnable instanceof dp ? ((dp) runnable).a() : runnable.getClass().getName();
        this.b = runnable instanceof bz;
        this.c = runnable instanceof cx;
        this.d = runnable.getClass().getName();
        this.e = z;
        this.f = runnable instanceof aq ? (aq) runnable : null;
    }

    public bq(Callable<V> callable) {
        super(callable);
        this.a = callable instanceof dp ? ((dp) callable).a() : callable.getClass().getName();
        this.b = callable instanceof bz;
        this.c = callable instanceof cx;
        this.d = callable.getClass().getName();
        this.e = true;
        this.f = callable instanceof aq ? (aq) callable : null;
    }

    public final boolean cancel(boolean z) {
        return super.cancel(z);
    }
}
