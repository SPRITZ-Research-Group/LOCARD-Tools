package android.support.design.widget;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

final class p {
    private static p a;
    private final Object b = new Object();
    private final Handler c = new Handler(Looper.getMainLooper(), new Callback(this) {
        final /* synthetic */ p a;

        {
            this.a = r1;
        }

        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    p.a(this.a, (b) message.obj);
                    return true;
                default:
                    return false;
            }
        }
    });
    private b d;
    private b e;

    interface a {
    }

    private static class b {
        private final WeakReference<a> a;
        private int b;

        final boolean a(a callback) {
            return callback != null && this.a.get() == callback;
        }
    }

    static p a() {
        if (a == null) {
            a = new p();
        }
        return a;
    }

    private p() {
    }

    public final void a(a callback) {
        synchronized (this.b) {
            if (g(callback)) {
                a(this.d);
            } else if (h(callback)) {
                a(this.e);
            }
        }
    }

    public final void b(a callback) {
        synchronized (this.b) {
            if (g(callback)) {
                this.d = null;
                if (!(this.e == null || this.e == null)) {
                    this.d = this.e;
                    this.e = null;
                    if (((a) this.d.a.get()) == null) {
                        this.d = null;
                    }
                }
            }
        }
    }

    public final void c(a callback) {
        synchronized (this.b) {
            if (g(callback)) {
                b(this.d);
            }
        }
    }

    public final void d(a callback) {
        synchronized (this.b) {
            if (g(callback)) {
                this.c.removeCallbacksAndMessages(this.d);
            }
        }
    }

    public final void e(a callback) {
        synchronized (this.b) {
            if (g(callback)) {
                b(this.d);
            }
        }
    }

    public final boolean f(a callback) {
        boolean z;
        synchronized (this.b) {
            z = g(callback) || h(callback);
        }
        return z;
    }

    private static boolean a(b record) {
        if (((a) record.a.get()) != null) {
            return true;
        }
        return false;
    }

    private boolean g(a callback) {
        return this.d != null && this.d.a(callback);
    }

    private boolean h(a callback) {
        return this.e != null && this.e.a(callback);
    }

    private void b(b r) {
        if (r.b != -2) {
            int durationMs = 2750;
            if (r.b > 0) {
                durationMs = r.b;
            } else if (r.b == -1) {
                durationMs = 1500;
            }
            this.c.removeCallbacksAndMessages(r);
            this.c.sendMessageDelayed(Message.obtain(this.c, 0, r), (long) durationMs);
        }
    }

    static /* synthetic */ void a(p x0, b x1) {
        synchronized (x0.b) {
            if (x0.d == x1 || x0.e == x1) {
                a(x1);
            }
        }
    }
}
