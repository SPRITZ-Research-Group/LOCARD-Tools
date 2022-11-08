package com.microsoft.react.sqlite;

import android.database.sqlite.SQLiteDatabase;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.microsoft.b.a.b;
import com.microsoft.react.sqlite.a.a;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SQLiteStorageModule extends ReactContextBaseJavaModule {
    public static final String TAG = "SQLiteStorage";
    private final b mConnectionProvider;
    private Map<String, a> mDatabaseManagerMap = new ConcurrentHashMap();
    private AtomicBoolean mDestroyed = new AtomicBoolean(false);

    public SQLiteStorageModule(ag reactContext, b connectionProvider) {
        super(reactContext);
        this.mConnectionProvider = connectionProvider;
    }

    public String getName() {
        return "RNStorage";
    }

    @ReactMethod
    public void open(am options, ae promise) {
        ae loggingPromise = createPromise(promise);
        if (this.mDestroyed.get()) {
            FLog.i(TAG, "Open failed. Module is already destroyed");
            return;
        }
        b databaseOptions = b.b(options);
        String databaseName = databaseOptions.a();
        if (((a) this.mDatabaseManagerMap.get(databaseName)) != null) {
            loggingPromise.a("0", "Database is already open");
            return;
        }
        a manager = new a(getReactApplicationContext(), this.mConnectionProvider, databaseOptions);
        if (manager.a(loggingPromise)) {
            this.mDatabaseManagerMap.put(databaseName, manager);
        }
    }

    @ReactMethod
    public void close(am options, ae promise) {
        final ae loggingPromise = createPromise(promise);
        if (this.mDestroyed.get()) {
            FLog.i(TAG, "Close failed. Module is already destroyed");
            return;
        }
        final String databaseName = b.a(options).a();
        a manager = (a) this.mDatabaseManagerMap.get(databaseName);
        if (manager == null) {
            loggingPromise.a("0", "Database is not open");
            return;
        }
        FLog.i(TAG, "Closing database: " + databaseName);
        manager.a(new a<Void, String>(this) {
            final /* synthetic */ SQLiteStorageModule c;

            public final /* bridge */ /* synthetic */ void a(Object obj) {
                loggingPromise.a("0", (String) obj);
            }

            public final /* synthetic */ void a() {
                this.c.mDatabaseManagerMap.remove(databaseName);
                loggingPromise.a(null);
            }
        });
    }

    @ReactMethod
    public void delete(am options, ae promise) {
        ae loggingPromise = createPromise(promise);
        if (this.mDestroyed.get()) {
            FLog.i(TAG, "Delete failed. Module is already destroyed");
            return;
        }
        String databaseName = b.a(options).a();
        a manager = (a) this.mDatabaseManagerMap.get(databaseName);
        if (manager != null) {
            manager.a(null);
            this.mDatabaseManagerMap.remove(databaseName);
        }
        if (SQLiteDatabase.deleteDatabase(getReactApplicationContext().getDatabasePath(databaseName))) {
            loggingPromise.a(null);
        } else {
            loggingPromise.a("0", "File is not deleted");
        }
    }

    @ReactMethod
    public void transaction(String databaseName, int transactionId, boolean readOnly, ae promise) {
        ae loggingPromise = createPromise(promise);
        if (this.mDestroyed.get()) {
            FLog.i(TAG, "Transaction failed. Module is already destroyed");
            return;
        }
        a manager = (a) this.mDatabaseManagerMap.get(databaseName);
        if (manager == null) {
            loggingPromise.a("0", "Database is not open");
        } else {
            manager.a(transactionId, readOnly, loggingPromise);
        }
    }

    @ReactMethod
    public void commit(String databaseName, int transactionId, ae promise) {
        ae loggingPromise = createPromise(promise);
        if (this.mDestroyed.get()) {
            FLog.i(TAG, "Commit failed. Module is already destroyed");
            return;
        }
        a manager = (a) this.mDatabaseManagerMap.get(databaseName);
        if (manager == null) {
            loggingPromise.a("0", "Database is not open");
        } else {
            manager.a(transactionId, loggingPromise);
        }
    }

    @ReactMethod
    public void abort(String databaseName, int transactionId, ae promise) {
        ae loggingPromise = createPromise(promise);
        if (this.mDestroyed.get()) {
            FLog.i(TAG, "Abort failed. Module is already destroyed");
            return;
        }
        a manager = (a) this.mDatabaseManagerMap.get(databaseName);
        if (manager == null) {
            loggingPromise.a("0", "Database is not open");
        } else {
            manager.b(transactionId, loggingPromise);
        }
    }

    @ReactMethod
    public void executeSql(String databaseName, int transactionId, String sql, al parameters, ae promise) {
        ae loggingPromise = createPromise(promise);
        if (this.mDestroyed.get()) {
            FLog.i(TAG, "Query failed: " + sql + " Module is already destroyed");
            return;
        }
        a manager = (a) this.mDatabaseManagerMap.get(databaseName);
        if (manager == null) {
            loggingPromise.a("0", "Database is not open");
        } else {
            manager.a(transactionId, sql, parameters, loggingPromise);
        }
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mDestroyed.set(true);
        for (Entry<String, a> entry : this.mDatabaseManagerMap.entrySet()) {
            FLog.i(TAG, "Shutdown database: " + ((String) entry.getKey()));
            ((a) entry.getValue()).a();
        }
        this.mDatabaseManagerMap.clear();
    }

    private ae createPromise(ae promise) {
        return new c(promise, this.mDestroyed);
    }
}
