package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.extractor.mp4.g;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.UUID;

@TargetApi(18)
public final class DefaultDrmSessionManager<T extends c> implements a<T>, b<T> {
    final g a;
    final UUID b;
    b c;
    d d;
    private final Handler e;
    private final a f;
    private final d<T> g;
    private Looper h;
    private HandlerThread i;
    private Handler j;
    private int k;
    private int l;
    private boolean m;
    private int n;
    private T o;
    private com.google.android.exoplayer2.drm.a.a p;
    private byte[] q;
    private String r;
    private byte[] s;
    private byte[] t;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface a {
    }

    @SuppressLint({"HandlerLeak"})
    private class b extends Handler {
        final /* synthetic */ DefaultDrmSessionManager a;

        public b(DefaultDrmSessionManager defaultDrmSessionManager, Looper looper) {
            this.a = defaultDrmSessionManager;
            super(looper);
        }

        public final void handleMessage(Message msg) {
            if (this.a.l == 0) {
                return;
            }
            if (this.a.n == 3 || this.a.n == 4) {
                switch (msg.what) {
                    case 1:
                        this.a.n = 3;
                        this.a.f();
                        return;
                    case 2:
                        this.a.g();
                        return;
                    case 3:
                        if (this.a.n == 4) {
                            this.a.n = 3;
                            this.a.b(new f());
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class c extends Handler {
        final /* synthetic */ DefaultDrmSessionManager a;

        public c(DefaultDrmSessionManager defaultDrmSessionManager, Looper backgroundLooper) {
            this.a = defaultDrmSessionManager;
            super(backgroundLooper);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void handleMessage(Message msg) {
            Object response;
            try {
                g gVar;
                Object obj;
                switch (msg.what) {
                    case 0:
                        gVar = this.a.a;
                        obj = msg.obj;
                        response = gVar.a();
                        break;
                    case 1:
                        gVar = this.a.a;
                        obj = msg.obj;
                        response = gVar.b();
                        break;
                    default:
                        throw new RuntimeException();
                }
            } catch (Exception e) {
                response = e;
            }
            this.a.d.obtainMessage(msg.what, response).sendToTarget();
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class d extends Handler {
        final /* synthetic */ DefaultDrmSessionManager a;

        public d(DefaultDrmSessionManager defaultDrmSessionManager, Looper looper) {
            this.a = defaultDrmSessionManager;
            super(looper);
        }

        public final void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    DefaultDrmSessionManager.a(this.a, msg.obj);
                    return;
                case 1:
                    DefaultDrmSessionManager.b(this.a, msg.obj);
                    return;
                default:
                    return;
            }
        }
    }

    static /* synthetic */ void a(DefaultDrmSessionManager x0, Object x1) {
        x0.m = false;
        if (x0.n != 2 && x0.n != 3 && x0.n != 4) {
            return;
        }
        if (x1 instanceof Exception) {
            x0.b((Exception) x1);
            return;
        }
        try {
            if (x0.n == 2) {
                x0.a(false);
            } else {
                x0.g();
            }
        } catch (Exception e) {
            x0.b(e);
        }
    }

    static /* synthetic */ void b(DefaultDrmSessionManager x0, Object x1) {
        if (x0.n != 3 && x0.n != 4) {
            return;
        }
        if (x1 instanceof Exception) {
            x0.a((Exception) x1);
            return;
        }
        try {
            if (x0.k != 3) {
                byte[] c = x0.g.c();
                if (!((x0.k != 2 && (x0.k != 0 || x0.t == null)) || c == null || c.length == 0)) {
                    x0.t = c;
                }
                x0.n = 4;
                if (x0.e != null && x0.f != null) {
                    x0.e.post(new Runnable(x0) {
                        final /* synthetic */ DefaultDrmSessionManager a;

                        {
                            this.a = this$0;
                        }

                        public final void run() {
                        }
                    });
                }
            } else if (x0.e != null && x0.f != null) {
                x0.e.post(new Runnable(x0) {
                    final /* synthetic */ DefaultDrmSessionManager a;

                    {
                        this.a = this$0;
                    }

                    public final void run() {
                    }
                });
            }
        } catch (Exception e) {
            x0.a(e);
        }
    }

    public final a<T> a(Looper playbackLooper, DrmInitData drmInitData) {
        boolean z = this.h == null || this.h == playbackLooper;
        com.google.android.exoplayer2.d.a.b(z);
        int i = this.l + 1;
        this.l = i;
        if (i == 1) {
            if (this.h == null) {
                this.h = playbackLooper;
                this.c = new b(this, playbackLooper);
                this.d = new d(this, playbackLooper);
            }
            this.i = new HandlerThread("DrmRequestHandler");
            this.i.start();
            this.j = new c(this, this.i.getLooper());
            if (this.t == null) {
                SchemeData schemeData = drmInitData.a(this.b);
                if (schemeData == null) {
                    b(new IllegalStateException("Media does not support uuid: " + this.b));
                } else {
                    this.q = schemeData.b;
                    this.r = schemeData.a;
                    if (s.a < 21) {
                        byte[] psshData = g.a(this.q, C.d);
                        if (psshData != null) {
                            this.q = psshData;
                        }
                    }
                    if (s.a < 26 && C.c.equals(this.b) && ("video/mp4".equals(this.r) || "audio/mp4".equals(this.r))) {
                        this.r = "cenc";
                    }
                }
            }
            this.n = 2;
            a(true);
        }
        return this;
    }

    public final void a() {
        int i = this.l - 1;
        this.l = i;
        if (i == 0) {
            this.n = 1;
            this.m = false;
            this.c.removeCallbacksAndMessages(null);
            this.d.removeCallbacksAndMessages(null);
            this.j.removeCallbacksAndMessages(null);
            this.j = null;
            this.i.quit();
            this.i = null;
            this.q = null;
            this.r = null;
            this.o = null;
            this.p = null;
            if (this.s != null) {
                this.s = null;
            }
        }
    }

    public final int b() {
        return this.n;
    }

    public final T c() {
        if (this.n == 3 || this.n == 4) {
            return this.o;
        }
        throw new IllegalStateException();
    }

    public final boolean a(String mimeType) {
        if (this.n == 3 || this.n == 4) {
            return this.o.a(mimeType);
        }
        throw new IllegalStateException();
    }

    public final com.google.android.exoplayer2.drm.a.a d() {
        return this.n == 0 ? this.p : null;
    }

    public final Map<String, String> e() {
        if (this.s != null) {
            return this.g.e();
        }
        throw new IllegalStateException();
    }

    private void a(boolean allowProvisioning) {
        try {
            this.s = this.g.a();
            this.o = this.g.f();
            this.n = 3;
            g();
        } catch (Exception e) {
            if (allowProvisioning) {
                f();
            } else {
                b(e);
            }
        } catch (Exception e2) {
            b(e2);
        }
    }

    private void f() {
        if (!this.m) {
            this.m = true;
            this.j.obtainMessage(0, this.g.d()).sendToTarget();
        }
    }

    private void g() {
        switch (this.k) {
            case 0:
            case 1:
                if (this.t == null) {
                    h();
                    return;
                }
                long licenseDurationRemainingSec;
                if (C.d.equals(this.b)) {
                    Pair a = h.a(this);
                    licenseDurationRemainingSec = Math.min(((Long) a.first).longValue(), ((Long) a.second).longValue());
                } else {
                    licenseDurationRemainingSec = Long.MAX_VALUE;
                }
                if (this.k != 0 || licenseDurationRemainingSec > 60) {
                    if (licenseDurationRemainingSec <= 0) {
                        b(new f());
                        return;
                    }
                    this.n = 4;
                    if (this.e != null && this.f != null) {
                        this.e.post(new Runnable(this) {
                            final /* synthetic */ DefaultDrmSessionManager a;

                            {
                                this.a = this$0;
                            }

                            public final void run() {
                            }
                        });
                        return;
                    }
                    return;
                }
                break;
            case 2:
                if (this.t == null) {
                    h();
                    return;
                } else {
                    h();
                    return;
                }
            case 3:
                break;
            default:
                return;
        }
        h();
    }

    private void h() {
        try {
            this.j.obtainMessage(1, this.g.b()).sendToTarget();
        } catch (Exception e) {
            a(e);
        }
    }

    private void a(Exception e) {
        if (e instanceof NotProvisionedException) {
            f();
        } else {
            b(e);
        }
    }

    private void b(final Exception e) {
        this.p = new com.google.android.exoplayer2.drm.a.a(e);
        if (!(this.e == null || this.f == null)) {
            this.e.post(new Runnable(this) {
                final /* synthetic */ DefaultDrmSessionManager b;

                public final void run() {
                }
            });
        }
        if (this.n != 4) {
            this.n = 0;
        }
    }
}
