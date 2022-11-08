package com.microsoft.react.sqlite.a;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.al;
import com.microsoft.react.sqlite.SQLiteStorageModule;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

public final class g implements Runnable {
    private final Object a = new Object();
    private h b;
    private final LinkedBlockingDeque<c> c;
    private i d = i.IN_PROGRESS;

    public interface a {
        void a();
    }

    public g(h transactionContext, ae promise) {
        this.b = transactionContext;
        this.c = new LinkedBlockingDeque();
        this.c.addLast(new b(promise, this.b));
    }

    public final void a() {
        this.b.a(this);
    }

    public final Future<?> b() {
        return this.b.a();
    }

    public final void run() {
        try {
            this.b.b();
            com.microsoft.b.a.a db = this.b.d();
            if (db != null) {
                while (true) {
                    a(((c) this.c.takeFirst()).b(db));
                    if (!e()) {
                        break;
                    }
                }
            }
            this.b.c();
            try {
                d();
                return;
            } finally {
                this.b.a(db);
            }
        } catch (Throwable th) {
            this.b.a(null);
        }
        a(i.ABORTED);
        this.b.a("Transaction Unhandled Exception", null, th);
        this.b.c();
        d();
        this.b.a(null);
    }

    public final void c() {
        a(true, new f(this.b));
    }

    public final void a(ae promise) {
        a(true, new a(promise, this.b));
    }

    public final void b(ae promise) {
        a(false, new d(promise, this.b));
    }

    public final void a(String sql, al parameters, ae promise) {
        a(false, new e(promise, this.b, sql, parameters));
    }

    private void a(boolean first, c command) {
        if (e()) {
            if (first) {
                this.c.addFirst(command);
            } else {
                this.c.addLast(command);
            }
            if (f()) {
                d();
                return;
            }
            return;
        }
        FLog.i(SQLiteStorageModule.TAG, "Transaction in state: " + this.d + " aborting command " + command.getClass().getSimpleName());
        command.b();
    }

    private void d() {
        synchronized (this.a) {
            if (this.d == i.ABORTED) {
                List<c> commands = new ArrayList();
                this.c.drainTo(commands);
                for (c b : commands) {
                    b.b();
                }
            }
        }
    }

    private void a(i state) {
        synchronized (this.a) {
            this.d = state;
        }
    }

    private boolean e() {
        boolean z;
        synchronized (this.a) {
            z = this.d == i.IN_PROGRESS;
        }
        return z;
    }

    private boolean f() {
        boolean z;
        synchronized (this.a) {
            z = this.d == i.ABORTED;
        }
        return z;
    }
}
