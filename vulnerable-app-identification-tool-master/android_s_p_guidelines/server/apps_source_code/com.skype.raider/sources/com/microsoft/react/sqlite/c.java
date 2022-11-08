package com.microsoft.react.sqlite;

import android.support.annotation.NonNull;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ae;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public final class c implements ae {
    private final ae a;
    private final AtomicBoolean b;

    public c(@NonNull ae promise, @NonNull AtomicBoolean destroyed) {
        this.a = promise;
        this.b = destroyed;
    }

    public final void a(@Nullable Object value) {
        if (this.b.get()) {
            FLog.i(SQLiteStorageModule.TAG, "Module already destroyed. Resolve is ignored: " + value.toString());
        } else {
            this.a.a(value);
        }
    }

    public final void a(String code, String message) {
        if (this.b.get()) {
            FLog.i(SQLiteStorageModule.TAG, "Module already destroyed. Ignore " + code + ": " + message);
            return;
        }
        FLog.i(SQLiteStorageModule.TAG, code + ": " + message);
        this.a.a(code, message);
    }

    public final void a(String code, Throwable e) {
        if (this.b.get()) {
            FLog.i(SQLiteStorageModule.TAG, "Module already destroyed. Ignore " + code + " " + e.getMessage());
            return;
        }
        FLog.i(SQLiteStorageModule.TAG, code, e);
        this.a.a(code, e);
    }

    public final void a(String code, String message, Throwable e) {
        if (this.b.get()) {
            FLog.i(SQLiteStorageModule.TAG, "Module already destroyed. Ignore " + code + ": " + message);
            return;
        }
        FLog.i(SQLiteStorageModule.TAG, code + ": " + message, e);
        this.a.a(code, message, e);
    }

    public final void a(String message) {
        if (this.b.get()) {
            FLog.i(SQLiteStorageModule.TAG, "Module already destroyed. Ignore " + message);
            return;
        }
        FLog.i(SQLiteStorageModule.TAG, "message");
        this.a.a(message);
    }

    public final void a(Throwable reason) {
        if (this.b.get()) {
            FLog.i(SQLiteStorageModule.TAG, "Module already destroyed. Ignore Unexpected error " + reason.getMessage());
            return;
        }
        FLog.i(SQLiteStorageModule.TAG, "Unexpected error", reason);
        this.a.a(reason);
    }
}
