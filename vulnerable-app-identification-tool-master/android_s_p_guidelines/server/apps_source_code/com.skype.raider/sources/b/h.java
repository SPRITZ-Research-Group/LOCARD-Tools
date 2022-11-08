package b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public final class h<TResult> {
    public static final ExecutorService a = b.a();
    public static final Executor b = a.b();
    private static final Executor c = b.b();
    private static volatile a d;
    private static h<?> m = new h(null);
    private static h<Boolean> n = new h(Boolean.valueOf(true));
    private static h<Boolean> o = new h(Boolean.valueOf(false));
    private static h<?> p = new h((byte) 0);
    private final Object e = new Object();
    private boolean f;
    private boolean g;
    private TResult h;
    private Exception i;
    private boolean j;
    private j k;
    private List<f<TResult, Void>> l = new ArrayList();

    public interface a {
    }

    public static a a() {
        return d;
    }

    h() {
    }

    private h(TResult result) {
        b((Object) result);
    }

    private h(byte b) {
        f();
    }

    private boolean g() {
        boolean z;
        synchronized (this.e) {
            z = this.f;
        }
        return z;
    }

    public final boolean b() {
        boolean z;
        synchronized (this.e) {
            z = this.g;
        }
        return z;
    }

    public final boolean c() {
        boolean z;
        synchronized (this.e) {
            z = e() != null;
        }
        return z;
    }

    public final TResult d() {
        TResult tResult;
        synchronized (this.e) {
            tResult = this.h;
        }
        return tResult;
    }

    public final Exception e() {
        Exception exception;
        synchronized (this.e) {
            if (this.i != null) {
                this.j = true;
                if (this.k != null) {
                    this.k.a();
                    this.k = null;
                }
            }
            exception = this.i;
        }
        return exception;
    }

    public static <TResult> h<TResult> a(TResult value) {
        if (value == null) {
            return m;
        }
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue() ? n : o;
        } else {
            i<TResult> tcs = new i();
            tcs.a((Object) value);
            return tcs.a();
        }
    }

    public static <TResult> h<TResult> a(Exception error) {
        i<TResult> tcs = new i();
        tcs.a(error);
        return tcs.a();
    }

    public static <TResult> h<TResult> a(Callable<TResult> callable, Executor executor) {
        return b((Callable) callable, executor);
    }

    private static <TResult> h<TResult> b(final Callable<TResult> callable, Executor executor) {
        final i<TResult> tcs = new i();
        try {
            executor.execute(new Runnable() {
                final /* synthetic */ c a = null;

                public final void run() {
                    if (this.a == null || !this.a.a()) {
                        try {
                            tcs.a(callable.call());
                            return;
                        } catch (CancellationException e) {
                            tcs.b();
                            return;
                        } catch (Exception e2) {
                            tcs.a(e2);
                            return;
                        }
                    }
                    tcs.b();
                }
            });
        } catch (Exception e) {
            tcs.a(new g(e));
        }
        return tcs.a();
    }

    private <TContinuationResult> h<TContinuationResult> a(final f<TResult, TContinuationResult> continuation, final Executor executor) {
        boolean completed;
        final i<TContinuationResult> tcs = new i();
        synchronized (this.e) {
            completed = g();
            if (!completed) {
                this.l.add(new f<TResult, Void>(this) {
                    final /* synthetic */ c d = null;
                    final /* synthetic */ h e;

                    public final /* bridge */ /* synthetic */ Object a(h x0) throws Exception {
                        h.c(tcs, continuation, x0, executor, this.d);
                        return null;
                    }
                });
            }
        }
        if (completed) {
            c(tcs, continuation, this, executor, null);
        }
        return tcs.a();
    }

    public final <TContinuationResult> h<TContinuationResult> a(f<TResult, TContinuationResult> continuation) {
        return a((f) continuation, c);
    }

    private <TContinuationResult> h<TContinuationResult> b(final f<TResult, h<TContinuationResult>> continuation, final Executor executor) {
        boolean completed;
        final i<TContinuationResult> tcs = new i();
        synchronized (this.e) {
            completed = g();
            if (!completed) {
                this.l.add(new f<TResult, Void>(this) {
                    final /* synthetic */ c d = null;
                    final /* synthetic */ h e;

                    public final /* synthetic */ Object a(h x0) throws Exception {
                        h.d(tcs, continuation, x0, executor, this.d);
                        return null;
                    }
                });
            }
        }
        if (completed) {
            d(tcs, continuation, this, executor, null);
        }
        return tcs.a();
    }

    public final <TContinuationResult> h<TContinuationResult> b(f<TResult, h<TContinuationResult>> continuation) {
        return b((f) continuation, c);
    }

    private static <TContinuationResult, TResult> void c(final i<TContinuationResult> tcs, final f<TResult, TContinuationResult> continuation, final h<TResult> task, Executor executor, final c ct) {
        try {
            executor.execute(new Runnable() {
                public final void run() {
                    if (ct == null || !ct.a()) {
                        try {
                            tcs.a(continuation.a(task));
                            return;
                        } catch (CancellationException e) {
                            tcs.b();
                            return;
                        } catch (Exception e2) {
                            tcs.a(e2);
                            return;
                        }
                    }
                    tcs.b();
                }
            });
        } catch (Exception e) {
            tcs.a(new g(e));
        }
    }

    private static <TContinuationResult, TResult> void d(final i<TContinuationResult> tcs, final f<TResult, h<TContinuationResult>> continuation, final h<TResult> task, Executor executor, final c ct) {
        try {
            executor.execute(new Runnable() {
                public final void run() {
                    if (ct == null || !ct.a()) {
                        try {
                            h<TContinuationResult> result = (h) continuation.a(task);
                            if (result == null) {
                                tcs.a(null);
                                return;
                            } else {
                                result.a(new f<TContinuationResult, Void>(this) {
                                    final /* synthetic */ AnonymousClass4 a;

                                    {
                                        this.a = r1;
                                    }

                                    public final /* synthetic */ Object a(h x0) throws Exception {
                                        if (ct != null && ct.a()) {
                                            tcs.b();
                                        } else if (x0.b()) {
                                            tcs.b();
                                        } else if (x0.c()) {
                                            tcs.a(x0.e());
                                        } else {
                                            tcs.a(x0.d());
                                        }
                                        return null;
                                    }
                                });
                                return;
                            }
                        } catch (CancellationException e) {
                            tcs.b();
                            return;
                        } catch (Exception e2) {
                            tcs.a(e2);
                            return;
                        }
                    }
                    tcs.b();
                }
            });
        } catch (Exception e) {
            tcs.a(new g(e));
        }
    }

    private void h() {
        synchronized (this.e) {
            for (f<TResult, ?> continuation : this.l) {
                try {
                    continuation.a(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.l = null;
        }
    }

    final boolean f() {
        boolean z = true;
        synchronized (this.e) {
            if (this.f) {
                z = false;
            } else {
                this.f = true;
                this.g = true;
                this.e.notifyAll();
                h();
            }
        }
        return z;
    }

    final boolean b(TResult result) {
        boolean z = true;
        synchronized (this.e) {
            if (this.f) {
                z = false;
            } else {
                this.f = true;
                this.h = result;
                this.e.notifyAll();
                h();
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final boolean b(Exception error) {
        synchronized (this.e) {
            if (this.f) {
                return false;
            }
            this.f = true;
            this.i = error;
            this.j = false;
            this.e.notifyAll();
            h();
            if (!(this.j || d == null)) {
                this.k = new j(this);
            }
        }
    }
}
