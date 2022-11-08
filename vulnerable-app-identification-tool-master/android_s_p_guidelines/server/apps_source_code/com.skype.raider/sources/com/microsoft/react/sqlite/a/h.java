package com.microsoft.react.sqlite.a;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.microsoft.react.sqlite.SQLiteStorageModule;
import com.microsoft.react.sqlite.a.g.a;
import com.microsoft.react.sqlite.b;
import com.microsoft.react.sqlite.d;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class h {
    private final ExecutorService a;
    private final b b;
    private final d c;
    private final Lock d;
    private final boolean e;
    private final int f;
    private final RCTDeviceEventEmitter g;
    private volatile Future<?> h;
    private boolean i;
    private a j;

    public h(ExecutorService executor, b databaseOptions, d connectionPool, Lock lock, int transactionId, boolean readOnly, RCTDeviceEventEmitter eventEmitter, a transactionListener) {
        this.a = executor;
        this.b = databaseOptions;
        this.c = connectionPool;
        this.d = lock;
        this.e = readOnly;
        this.f = transactionId;
        this.g = eventEmitter;
        this.j = transactionListener;
    }

    public final void a(g transaction) {
        if (this.h != null) {
            throw new IllegalStateException("Transaction is already submited: " + this.f);
        }
        this.h = this.a.submit(transaction);
    }

    public final Future<?> a() {
        if (this.h != null) {
            return this.h;
        }
        throw new IllegalStateException("Transaction is not started yet.");
    }

    public final void b() {
        this.d.lock();
    }

    public final void c() {
        this.d.unlock();
    }

    public final com.microsoft.b.a.a d() {
        return this.c.a();
    }

    public final void a(com.microsoft.b.a.a connection) {
        if (connection != null) {
            try {
                this.c.a(connection);
            } catch (Throwable th) {
                this.j.a();
            }
        }
        this.j.a();
    }

    public final void b(com.microsoft.b.a.a db) {
        if (this.b.c()) {
            new StringBuilder("abortTransaction ").append(this.f);
        }
        if (!this.e && this.i) {
            db.a("ROLLBACK;");
        }
        this.i = false;
    }

    public final void a(String message, c command, Throwable th) {
        FLog.e(SQLiteStorageModule.TAG, message + (command != null ? " " + command.c() : ""), th);
        ar map = new WritableNativeMap();
        map.putString("databaseName", this.b.a());
        map.putInt("transactionId", this.f);
        map.putString("message", message);
        this.g.emit("SQLiteStorage.onTransactionFailed", map);
    }

    public final void c(com.microsoft.b.a.a db) {
        if (this.b.c()) {
            new StringBuilder("beginTransaction ").append(this.f).append(" ").append(this.e ? "readonly" : "exclusive");
        }
        if (!this.e) {
            db.a("BEGIN;");
        }
        this.i = true;
    }

    public final void d(com.microsoft.b.a.a db) {
        if (this.b.c()) {
            new StringBuilder("commitTransaction ").append(this.f);
        }
        if (!this.e && this.i) {
            db.a("COMMIT;");
        }
        this.i = false;
    }
}
