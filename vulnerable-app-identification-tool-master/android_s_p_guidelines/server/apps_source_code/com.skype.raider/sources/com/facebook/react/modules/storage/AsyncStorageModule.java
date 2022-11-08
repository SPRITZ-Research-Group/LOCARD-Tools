package com.facebook.react.modules.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.j;
import com.facebook.react.module.annotations.ReactModule;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.HashSet;
import java.util.Iterator;

@ReactModule(name = "AsyncSQLiteDBStorage")
public final class AsyncStorageModule extends ReactContextBaseJavaModule {
    private static final int MAX_SQL_KEYS = 999;
    protected static final String NAME = "AsyncSQLiteDBStorage";
    private c mReactDatabaseSupplier;
    private boolean mShuttingDown = false;

    public AsyncStorageModule(ag reactContext) {
        super(reactContext);
        this.mReactDatabaseSupplier = c.a(reactContext);
    }

    public final String getName() {
        return NAME;
    }

    public final void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }

    public final void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
    }

    public final void clearSensitiveData() {
        this.mReactDatabaseSupplier.c();
    }

    @ReactMethod
    public final void multiGet(final al keys, final d callback) {
        if (keys == null) {
            callback.invoke(b.a("Invalid key"), null);
            return;
        }
        new j<Void, Void>(this, getReactApplicationContext()) {
            final /* synthetic */ AsyncStorageModule c;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            protected final /* synthetic */ void a() {
                if (this.c.ensureDatabase()) {
                    String[] strArr = new String[]{PropertiesEntry.COLUMN_NAME_KEY, PropertiesEntry.COLUMN_NAME_VALUE};
                    HashSet hashSet = new HashSet();
                    aq writableNativeArray = new WritableNativeArray();
                    int i = 0;
                    while (i < keys.size()) {
                        int min = Math.min(keys.size() - i, AsyncStorageModule.MAX_SQL_KEYS);
                        Cursor query = this.c.mReactDatabaseSupplier.b().query("catalystLocalStorage", strArr, a.a(min), a.a(keys, i, min), null, null, null);
                        hashSet.clear();
                        try {
                            if (query.getCount() != keys.size()) {
                                for (int i2 = i; i2 < i + min; i2++) {
                                    hashSet.add(keys.getString(i2));
                                }
                            }
                            if (query.moveToFirst()) {
                                do {
                                    aq writableNativeArray2 = new WritableNativeArray();
                                    writableNativeArray2.pushString(query.getString(0));
                                    writableNativeArray2.pushString(query.getString(1));
                                    writableNativeArray.pushArray(writableNativeArray2);
                                    hashSet.remove(query.getString(0));
                                } while (query.moveToNext());
                            }
                            query.close();
                            Iterator it = hashSet.iterator();
                            while (it.hasNext()) {
                                String str = (String) it.next();
                                aq writableNativeArray3 = new WritableNativeArray();
                                writableNativeArray3.pushString(str);
                                writableNativeArray3.pushNull();
                                writableNativeArray.pushArray(writableNativeArray3);
                            }
                            hashSet.clear();
                            i += AsyncStorageModule.MAX_SQL_KEYS;
                        } catch (Throwable e) {
                            FLog.w("React", e.getMessage(), e);
                            callback.invoke(b.a(e.getMessage()), null);
                            return;
                        } catch (Throwable th) {
                            query.close();
                        }
                    }
                    callback.invoke(null, writableNativeArray);
                    return;
                }
                callback.invoke(b.a("Database Error"), null);
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public final void multiSet(final al keyValueArray, final d callback) {
        if (keyValueArray.size() == 0) {
            callback.invoke(b.a("Invalid key"));
            return;
        }
        new j<Void, Void>(this, getReactApplicationContext()) {
            final /* synthetic */ AsyncStorageModule c;

            protected final /* synthetic */ void a() {
                if (this.c.ensureDatabase()) {
                    SQLiteStatement compileStatement = this.c.mReactDatabaseSupplier.b().compileStatement("INSERT OR REPLACE INTO catalystLocalStorage VALUES (?, ?);");
                    ar arVar = null;
                    this.c.mReactDatabaseSupplier.b().beginTransaction();
                    int i = 0;
                    while (i < keyValueArray.size()) {
                        if (keyValueArray.getArray(i).size() != 2) {
                            b.a("Invalid Value");
                            try {
                                this.c.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Throwable e) {
                                FLog.w("React", e.getMessage(), e);
                                return;
                            }
                        } else if (keyValueArray.getArray(i).getString(0) == null) {
                            b.a("Invalid key");
                            try {
                                this.c.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Throwable e2) {
                                FLog.w("React", e2.getMessage(), e2);
                                return;
                            }
                        } else if (keyValueArray.getArray(i).getString(1) == null) {
                            b.a("Invalid Value");
                            try {
                                this.c.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Throwable e22) {
                                FLog.w("React", e22.getMessage(), e22);
                                return;
                            }
                        } else {
                            try {
                                compileStatement.clearBindings();
                                compileStatement.bindString(1, keyValueArray.getArray(i).getString(0));
                                compileStatement.bindString(2, keyValueArray.getArray(i).getString(1));
                                compileStatement.execute();
                                i++;
                            } catch (Throwable e222) {
                                FLog.w("React", e222.getMessage(), e222);
                                ar a = b.a(e222.getMessage());
                                try {
                                    this.c.mReactDatabaseSupplier.b().endTransaction();
                                    arVar = a;
                                } catch (Throwable e2222) {
                                    FLog.w("React", e2222.getMessage(), e2222);
                                    arVar = a;
                                }
                            } catch (Throwable e22222) {
                                try {
                                    this.c.mReactDatabaseSupplier.b().endTransaction();
                                } catch (Throwable e3) {
                                    FLog.w("React", e3.getMessage(), e3);
                                    b.a(e3.getMessage());
                                }
                                throw e22222;
                            }
                        }
                    }
                    this.c.mReactDatabaseSupplier.b().setTransactionSuccessful();
                    try {
                        this.c.mReactDatabaseSupplier.b().endTransaction();
                    } catch (Throwable e222222) {
                        FLog.w("React", e222222.getMessage(), e222222);
                        arVar = b.a(e222222.getMessage());
                    }
                    if (arVar != null) {
                        callback.invoke(arVar);
                        return;
                    }
                    callback.invoke(new Object[0]);
                    return;
                }
                callback.invoke(b.a("Database Error"));
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public final void multiRemove(final al keys, final d callback) {
        if (keys.size() == 0) {
            callback.invoke(b.a("Invalid key"));
            return;
        }
        new j<Void, Void>(this, getReactApplicationContext()) {
            final /* synthetic */ AsyncStorageModule c;

            protected final /* synthetic */ void a() {
                if (this.c.ensureDatabase()) {
                    ar arVar = null;
                    try {
                        this.c.mReactDatabaseSupplier.b().beginTransaction();
                        for (int i = 0; i < keys.size(); i += AsyncStorageModule.MAX_SQL_KEYS) {
                            int min = Math.min(keys.size() - i, AsyncStorageModule.MAX_SQL_KEYS);
                            this.c.mReactDatabaseSupplier.b().delete("catalystLocalStorage", a.a(min), a.a(keys, i, min));
                        }
                        this.c.mReactDatabaseSupplier.b().setTransactionSuccessful();
                        try {
                            this.c.mReactDatabaseSupplier.b().endTransaction();
                        } catch (Throwable e) {
                            FLog.w("React", e.getMessage(), e);
                            arVar = b.a(e.getMessage());
                        }
                    } catch (Throwable e2) {
                        FLog.w("React", e2.getMessage(), e2);
                        ar a = b.a(e2.getMessage());
                        try {
                            this.c.mReactDatabaseSupplier.b().endTransaction();
                            arVar = a;
                        } catch (Throwable e22) {
                            FLog.w("React", e22.getMessage(), e22);
                            arVar = a;
                        }
                    } catch (Throwable e222) {
                        try {
                            this.c.mReactDatabaseSupplier.b().endTransaction();
                        } catch (Throwable e3) {
                            FLog.w("React", e3.getMessage(), e3);
                            b.a(e3.getMessage());
                        }
                        throw e222;
                    }
                    if (arVar != null) {
                        callback.invoke(arVar);
                        return;
                    }
                    callback.invoke(new Object[0]);
                    return;
                }
                callback.invoke(b.a("Database Error"));
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public final void multiMerge(final al keyValueArray, final d callback) {
        new j<Void, Void>(this, getReactApplicationContext()) {
            final /* synthetic */ AsyncStorageModule c;

            protected final /* synthetic */ void a() {
                if (this.c.ensureDatabase()) {
                    ar arVar = null;
                    this.c.mReactDatabaseSupplier.b().beginTransaction();
                    int i = 0;
                    while (i < keyValueArray.size()) {
                        if (keyValueArray.getArray(i).size() != 2) {
                            b.a("Invalid Value");
                            try {
                                this.c.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Throwable e) {
                                FLog.w("React", e.getMessage(), e);
                                return;
                            }
                        } else if (keyValueArray.getArray(i).getString(0) == null) {
                            b.a("Invalid key");
                            try {
                                this.c.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Throwable e2) {
                                FLog.w("React", e2.getMessage(), e2);
                                return;
                            }
                        } else if (keyValueArray.getArray(i).getString(1) == null) {
                            b.a("Invalid Value");
                            try {
                                this.c.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Throwable e22) {
                                FLog.w("React", e22.getMessage(), e22);
                                return;
                            }
                        } else {
                            try {
                                if (a.a(this.c.mReactDatabaseSupplier.b(), keyValueArray.getArray(i).getString(0), keyValueArray.getArray(i).getString(1))) {
                                    i++;
                                } else {
                                    b.a("Database Error");
                                    try {
                                        this.c.mReactDatabaseSupplier.b().endTransaction();
                                        return;
                                    } catch (Throwable e222) {
                                        FLog.w("React", e222.getMessage(), e222);
                                        return;
                                    }
                                }
                            } catch (Throwable e2222) {
                                FLog.w("React", e2222.getMessage(), e2222);
                                ar a = b.a(e2222.getMessage());
                                try {
                                    this.c.mReactDatabaseSupplier.b().endTransaction();
                                    arVar = a;
                                } catch (Throwable e22222) {
                                    FLog.w("React", e22222.getMessage(), e22222);
                                    arVar = a;
                                }
                            } catch (Throwable e222222) {
                                try {
                                    this.c.mReactDatabaseSupplier.b().endTransaction();
                                } catch (Throwable e3) {
                                    FLog.w("React", e3.getMessage(), e3);
                                    b.a(e3.getMessage());
                                }
                                throw e222222;
                            }
                        }
                    }
                    this.c.mReactDatabaseSupplier.b().setTransactionSuccessful();
                    try {
                        this.c.mReactDatabaseSupplier.b().endTransaction();
                    } catch (Throwable e2222222) {
                        FLog.w("React", e2222222.getMessage(), e2222222);
                        arVar = b.a(e2222222.getMessage());
                    }
                    if (arVar != null) {
                        callback.invoke(arVar);
                        return;
                    }
                    callback.invoke(new Object[0]);
                    return;
                }
                callback.invoke(b.a("Database Error"));
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public final void clear(final d callback) {
        new j<Void, Void>(this, getReactApplicationContext()) {
            final /* synthetic */ AsyncStorageModule b;

            protected final /* synthetic */ void a() {
                this.b.mReactDatabaseSupplier.a();
                try {
                    this.b.mReactDatabaseSupplier.d();
                    callback.invoke(new Object[0]);
                } catch (Throwable e) {
                    FLog.w("React", e.getMessage(), e);
                    callback.invoke(b.a(e.getMessage()));
                }
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public final void getAllKeys(final d callback) {
        new j<Void, Void>(this, getReactApplicationContext()) {
            final /* synthetic */ AsyncStorageModule b;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            protected final /* synthetic */ void a() {
                if (this.b.ensureDatabase()) {
                    aq writableNativeArray = new WritableNativeArray();
                    Cursor query = this.b.mReactDatabaseSupplier.b().query("catalystLocalStorage", new String[]{PropertiesEntry.COLUMN_NAME_KEY}, null, null, null, null, null);
                    try {
                        if (query.moveToFirst()) {
                            do {
                                writableNativeArray.pushString(query.getString(0));
                            } while (query.moveToNext());
                        }
                        query.close();
                        callback.invoke(null, writableNativeArray);
                    } catch (Throwable e) {
                        FLog.w("React", e.getMessage(), e);
                        callback.invoke(b.a(e.getMessage()), null);
                    } catch (Throwable th) {
                        query.close();
                    }
                } else {
                    callback.invoke(b.a("Database Error"), null);
                }
            }
        }.execute(new Void[0]);
    }

    private boolean ensureDatabase() {
        if (this.mShuttingDown) {
            return false;
        }
        this.mReactDatabaseSupplier.a();
        return true;
    }
}
