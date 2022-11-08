package com.google.android.exoplayer2.upstream;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.adjust.sdk.Constants;
import com.google.android.exoplayer2.d.r;
import com.google.android.exoplayer2.d.s;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public final class o {
    private final ExecutorService a;
    private b<? extends c> b;
    private IOException c;

    public interface c {
        void a();

        boolean b();

        void c() throws IOException, InterruptedException;
    }

    public interface a<T extends c> {
        int a(T t, IOException iOException);

        void a(T t);

        void a(T t, boolean z);
    }

    @SuppressLint({"HandlerLeak"})
    private final class b<T extends c> extends Handler implements Runnable {
        public final int a;
        final /* synthetic */ o b;
        private final T c;
        private final a<T> d;
        private final long e;
        private IOException f;
        private int g;
        private volatile Thread h;
        private volatile boolean i;

        public b(o oVar, Looper looper, T loadable, a<T> callback, int defaultMinRetryCount, long startTimeMs) {
            this.b = oVar;
            super(looper);
            this.c = loadable;
            this.d = callback;
            this.a = defaultMinRetryCount;
            this.e = startTimeMs;
        }

        public final void a(int minRetryCount) throws IOException {
            if (this.f != null && this.g > minRetryCount) {
                throw this.f;
            }
        }

        public final void a(long delayMillis) {
            com.google.android.exoplayer2.d.a.b(this.b.b == null);
            this.b.b = this;
            if (delayMillis > 0) {
                sendEmptyMessageDelayed(0, delayMillis);
            } else {
                a();
            }
        }

        public final void a(boolean released) {
            this.i = released;
            this.f = null;
            if (hasMessages(0)) {
                removeMessages(0);
                if (!released) {
                    sendEmptyMessage(1);
                }
            } else {
                this.c.a();
                if (this.h != null) {
                    this.h.interrupt();
                }
            }
            if (released) {
                b();
                SystemClock.elapsedRealtime();
                this.d.a(this.c, true);
            }
        }

        public final void run() {
            try {
                this.h = Thread.currentThread();
                if (!this.c.b()) {
                    r.a("load:" + this.c.getClass().getSimpleName());
                    this.c.c();
                    r.a();
                }
                if (!this.i) {
                    sendEmptyMessage(2);
                }
            } catch (IOException e) {
                if (!this.i) {
                    obtainMessage(3, e).sendToTarget();
                }
            } catch (InterruptedException e2) {
                com.google.android.exoplayer2.d.a.b(this.c.b());
                if (!this.i) {
                    sendEmptyMessage(2);
                }
            } catch (Exception e3) {
                if (!this.i) {
                    obtainMessage(3, new d(e3)).sendToTarget();
                }
            } catch (OutOfMemoryError e4) {
                if (!this.i) {
                    obtainMessage(3, new d(e4)).sendToTarget();
                }
            } catch (Error e5) {
                if (!this.i) {
                    obtainMessage(4, e5).sendToTarget();
                }
                throw e5;
            } catch (Throwable th) {
                r.a();
            }
        }

        public final void handleMessage(Message msg) {
            if (!this.i) {
                if (msg.what == 0) {
                    a();
                } else if (msg.what == 4) {
                    throw ((Error) msg.obj);
                } else {
                    b();
                    SystemClock.elapsedRealtime();
                    if (this.c.b()) {
                        this.d.a(this.c, false);
                        return;
                    }
                    switch (msg.what) {
                        case 1:
                            this.d.a(this.c, false);
                            return;
                        case 2:
                            this.d.a(this.c);
                            return;
                        case 3:
                            this.f = (IOException) msg.obj;
                            int retryAction = this.d.a(this.c, this.f);
                            if (retryAction == 3) {
                                this.b.c = this.f;
                                return;
                            } else if (retryAction != 2) {
                                int i;
                                if (retryAction == 1) {
                                    i = 1;
                                } else {
                                    i = this.g + 1;
                                }
                                this.g = i;
                                a((long) Math.min((this.g - 1) * Constants.ONE_SECOND, 5000));
                                return;
                            } else {
                                return;
                            }
                        default:
                            return;
                    }
                }
            }
        }

        private void a() {
            this.f = null;
            this.b.a.execute(this.b.b);
        }

        private void b() {
            this.b.b = null;
        }
    }

    public static final class d extends IOException {
        public d(Throwable cause) {
            super("Unexpected " + cause.getClass().getSimpleName() + ": " + cause.getMessage(), cause);
        }
    }

    public o(String threadName) {
        this.a = s.a(threadName);
    }

    public final <T extends c> long a(T loadable, a<T> callback, int defaultMinRetryCount) {
        Looper looper = Looper.myLooper();
        com.google.android.exoplayer2.d.a.b(looper != null);
        long startTimeMs = SystemClock.elapsedRealtime();
        new b(this, looper, loadable, callback, defaultMinRetryCount, startTimeMs).a(0);
        return startTimeMs;
    }

    public final boolean a() {
        return this.b != null;
    }

    public final void b() {
        this.b.a(false);
    }

    public final void a(Runnable postLoadAction) {
        if (this.b != null) {
            this.b.a(true);
        }
        this.a.execute(postLoadAction);
        this.a.shutdown();
    }

    public final void c() throws IOException {
        if (this.c != null) {
            throw this.c;
        } else if (this.b != null) {
            this.b.a(this.b.a);
        }
    }
}
