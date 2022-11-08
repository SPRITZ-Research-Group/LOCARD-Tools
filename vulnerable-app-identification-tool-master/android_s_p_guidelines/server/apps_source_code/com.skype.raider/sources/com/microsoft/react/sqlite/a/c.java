package com.microsoft.react.sqlite.a;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ae;
import com.microsoft.b.a.a;
import com.microsoft.react.sqlite.SQLiteStorageModule;
import com.microsoft.react.sqlite.b.d;

public abstract class c {
    protected final ae a;
    protected final h b;

    public abstract Object a(a aVar);

    public c(ae promise, h transactionContext) {
        this.a = promise;
        this.b = transactionContext;
    }

    public final i b(a db) {
        try {
            Object result = a(db);
            if (this.a != null) {
                this.a.a(result);
            }
            return a();
        } catch (Throwable th) {
            FLog.e(SQLiteStorageModule.TAG, "Command " + getClass().getSimpleName() + " failed with context" + c(), th);
            if (this.a != null) {
                this.a.a(d.a(th), th);
            }
            return i.ABORTED;
        }
    }

    public final void b() {
        if (this.a != null) {
            this.a.a("0", "Transaction aborted");
        }
    }

    protected i a() {
        return i.IN_PROGRESS;
    }

    public String c() {
        return getClass().getSimpleName();
    }
}
