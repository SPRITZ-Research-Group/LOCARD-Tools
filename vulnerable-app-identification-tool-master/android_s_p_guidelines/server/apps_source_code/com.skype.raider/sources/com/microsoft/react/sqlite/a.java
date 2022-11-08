package com.microsoft.react.sqlite;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.microsoft.react.sqlite.a.g;
import com.microsoft.react.sqlite.a.h;
import com.microsoft.react.sqlite.b.b;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class a {
    private final d a;
    private final b b;
    private final RCTDeviceEventEmitter c;
    private final ExecutorService d;
    private final Map<Integer, g> e;
    private final ReentrantReadWriteLock f;
    private final b g = new b();
    private boolean h;

    public interface a<R, E> {
        void a();

        void a(E e);
    }

    public a(ag reactContext, com.microsoft.b.a.b connectionProvider, b options) {
        this.b = options;
        this.f = new ReentrantReadWriteLock(true);
        this.d = Executors.newFixedThreadPool(options.b());
        this.c = (RCTDeviceEventEmitter) reactContext.a(RCTDeviceEventEmitter.class);
        this.a = new d(reactContext.getApplicationContext(), connectionProvider, options);
        this.e = new ConcurrentSkipListMap();
    }

    public final boolean a(ae promise) {
        boolean result = false;
        try {
            this.g.a();
            if (b(promise)) {
                this.a.a(this.a.a());
                promise.a(null);
                result = true;
            }
        } catch (Throwable th) {
            this.g.b();
        }
        this.g.b();
        return result;
    }

    public final void a(a<Void, String> callback) {
        try {
            this.g.a();
            a((a) callback, false);
        } finally {
            this.g.b();
        }
    }

    public final void a() {
        try {
            this.g.a();
            a(null, true);
        } finally {
            this.g.b();
        }
    }

    public final void a(final int transactionId, boolean readOnly, ae promise) {
        try {
            this.g.a();
            if (b(promise)) {
                Lock lock;
                if (readOnly) {
                    lock = this.f.readLock();
                } else {
                    lock = this.f.writeLock();
                }
                g transaction = new g(new h(this.d, this.b, this.a, lock, transactionId, readOnly, this.c, new com.microsoft.react.sqlite.a.g.a(this) {
                    final /* synthetic */ a b;

                    public final void a() {
                        this.b.e.remove(Integer.valueOf(transactionId));
                    }
                }), promise);
                this.e.put(Integer.valueOf(transactionId), transaction);
                transaction.a();
            }
            this.g.b();
        } catch (Throwable th) {
            this.g.b();
        }
    }

    public final void a(int transactionId, ae promise) {
        try {
            this.g.a();
            if (b(promise)) {
                g transaction = (g) this.e.get(Integer.valueOf(transactionId));
                if (transaction == null) {
                    c(transactionId, promise);
                } else {
                    transaction.b(promise);
                }
            }
            this.g.b();
        } catch (Throwable th) {
            this.g.b();
        }
    }

    public final void b(int transactionId, ae promise) {
        try {
            this.g.a();
            if (b(promise)) {
                g transaction = (g) this.e.get(Integer.valueOf(transactionId));
                if (transaction == null) {
                    c(transactionId, promise);
                } else {
                    transaction.a(promise);
                }
            }
            this.g.b();
        } catch (Throwable th) {
            this.g.b();
        }
    }

    public final void a(int transactionId, String sql, al parameters, ae promise) {
        try {
            this.g.a();
            if (b(promise)) {
                g transaction = (g) this.e.get(Integer.valueOf(transactionId));
                if (transaction == null) {
                    c(transactionId, promise);
                } else {
                    transaction.a(sql, parameters, promise);
                }
            }
            this.g.b();
        } catch (Throwable th) {
            this.g.b();
        }
    }

    private boolean b(ae promise) {
        if (this.h) {
            promise.a("0", "Database already closed");
        }
        return !this.h;
    }

    private void a(a<Void, String> callback, boolean terminate) {
        if (!this.h) {
            this.h = true;
            Collection<g> transactions = this.e.values();
            if (terminate) {
                for (g c : transactions) {
                    c.c();
                }
            }
            for (g transaction : transactions) {
                try {
                    transaction.b().get();
                } catch (Throwable e) {
                    FLog.w(SQLiteStorageModule.TAG, "Interrupted exception. Shouldn't happen.", e);
                }
            }
            FLog.i(SQLiteStorageModule.TAG, "Closing connection pool");
            this.a.b();
            FLog.i(SQLiteStorageModule.TAG, "Shutting down database executor");
            this.d.shutdown();
            if (callback != null) {
                callback.a();
            }
        } else if (callback != null) {
            callback.a("Database is already closed");
        }
    }

    private static void c(int id, ae promise) {
        promise.a("0", "Transaction not found: " + id);
    }
}
