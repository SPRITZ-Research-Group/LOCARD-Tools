package com.facebook.ads.internal.e;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.q.d.b;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class d {
    private static final String a = ("SELECT tokens." + h.a.b + ", tokens." + h.b.b + ", events." + c.a.b + ", events." + c.c.b + ", events." + c.d.b + ", events." + c.e.b + ", events." + c.f.b + ", events." + c.g.b + ", events." + c.h.b + ", events." + c.i.b + " FROM events JOIN tokens ON events." + c.b.b + " = tokens." + h.a.b + " ORDER BY events." + c.e.b + " ASC");
    private static final int b = Runtime.getRuntime().availableProcessors();
    private static final int c = Math.max(2, Math.min(b - 1, 4));
    private static final int d = ((b * 2) + 1);
    private static final ThreadFactory e = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "DatabaseTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> f = new LinkedBlockingQueue(128);
    private static final Executor g;
    private static final ReentrantReadWriteLock h;
    private static final Lock i;
    private static final Lock j = h.writeLock();
    private final Context k;
    private final h l = new h(this);
    private final c m = new c(this);
    private SQLiteOpenHelper n;

    private static class a<T> extends AsyncTask<Void, Void, T> {
        private final f<T> a;
        private final a<T> b;
        private final Context c;
        private com.facebook.ads.internal.e.f.a d;

        a(Context context, f<T> fVar, a<T> aVar) {
            this.a = fVar;
            this.b = aVar;
            this.c = context;
        }

        private T a() {
            T a;
            Exception e;
            try {
                a = this.a.a();
                try {
                    this.d = this.a.b();
                } catch (Exception e2) {
                    e = e2;
                    com.facebook.ads.internal.q.d.a.a(this.c, "database", b.l, e);
                    this.d = com.facebook.ads.internal.e.f.a.UNKNOWN;
                    return a;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                a = null;
                e = exception;
            }
            return a;
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        protected final void onPostExecute(T t) {
            if (this.d == null) {
                this.b.a(t);
                return;
            }
            this.d.a();
            this.d.b();
        }
    }

    static {
        Executor threadPoolExecutor = new ThreadPoolExecutor(c, d, 30, TimeUnit.SECONDS, f, e);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        g = threadPoolExecutor;
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        h = reentrantReadWriteLock;
        i = reentrantReadWriteLock.readLock();
    }

    public d(Context context) {
        this.k = context;
    }

    private synchronized SQLiteDatabase i() {
        if (this.n == null) {
            this.n = new e(this.k, this);
        }
        return this.n.getWritableDatabase();
    }

    @WorkerThread
    public final Cursor a(int i) {
        i.lock();
        try {
            Cursor rawQuery = a().rawQuery(a + " LIMIT " + String.valueOf(i), null);
            return rawQuery;
        } finally {
            i.unlock();
        }
    }

    public final SQLiteDatabase a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return i();
        }
        throw new IllegalStateException("Cannot call getDatabase from the UI thread!");
    }

    public final AsyncTask a(String str, int i, String str2, double d, double d2, String str3, Map<String, String> map, a<String> aVar) {
        final String str4 = str;
        final int i2 = i;
        final String str5 = str2;
        final double d3 = d;
        final double d4 = d2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        f anonymousClass2 = new i<String>(this) {
            final /* synthetic */ d h;

            @Nullable
            private String c() {
                Exception e;
                SQLiteDatabase sQLiteDatabase;
                Throwable th;
                if (TextUtils.isEmpty(str4)) {
                    return null;
                }
                d.j.lock();
                SQLiteDatabase a;
                try {
                    a = this.h.a();
                    try {
                        a.beginTransaction();
                        String a2 = this.h.m.a(this.h.l.a(str4), i2, str5, d3, d4, str6, map2);
                        a.setTransactionSuccessful();
                        if (a != null && a.isOpen()) {
                            try {
                                if (a.inTransaction()) {
                                    a.endTransaction();
                                }
                            } catch (Exception e2) {
                                com.facebook.ads.internal.q.d.a.a(this.h.k, "database", b.k, e2);
                            }
                        }
                        d.j.unlock();
                        return a2;
                    } catch (Exception e3) {
                        e = e3;
                        sQLiteDatabase = a;
                        try {
                            a(com.facebook.ads.internal.e.f.a.DATABASE_INSERT);
                            com.facebook.ads.internal.q.d.a.a(this.h.k, "database", b.i, e);
                            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                try {
                                    if (sQLiteDatabase.inTransaction()) {
                                        sQLiteDatabase.endTransaction();
                                    }
                                } catch (Exception e4) {
                                    com.facebook.ads.internal.q.d.a.a(this.h.k, "database", b.k, e4);
                                }
                            }
                            d.j.unlock();
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            a = sQLiteDatabase;
                            if (a != null && a.isOpen()) {
                                try {
                                    if (a.inTransaction()) {
                                        a.endTransaction();
                                    }
                                } catch (Exception e22) {
                                    com.facebook.ads.internal.q.d.a.a(this.h.k, "database", b.k, e22);
                                }
                            }
                            d.j.unlock();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (a.inTransaction()) {
                            a.endTransaction();
                        }
                        d.j.unlock();
                        throw th;
                    }
                } catch (Exception e5) {
                    e4 = e5;
                    sQLiteDatabase = null;
                } catch (Throwable th4) {
                    th = th4;
                    a = null;
                    if (a.inTransaction()) {
                        a.endTransaction();
                    }
                    d.j.unlock();
                    throw th;
                }
            }

            @Nullable
            public final /* synthetic */ Object a() {
                return c();
            }
        };
        Executor executor = g;
        AsyncTask aVar2 = new a(this.k.getApplicationContext(), anonymousClass2, aVar);
        Void[] voidArr = new Void[0];
        if (VERSION.SDK_INT >= 11) {
            aVar2.executeOnExecutor(executor, voidArr);
        } else {
            aVar2.execute(voidArr);
        }
        return aVar2;
    }

    @WorkerThread
    public final boolean a(String str) {
        boolean z = true;
        j.lock();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE events SET ").append(c.i.b).append("=").append(c.i.b).append("+1 WHERE ").append(c.a.b).append("=?");
            a().execSQL(stringBuilder.toString(), new String[]{str});
        } catch (SQLiteException e) {
            z = false;
        }
        j.unlock();
        return z;
    }

    public final synchronized void b() {
        c();
        if (this.n != null) {
            this.n.close();
            this.n = null;
        }
    }

    @WorkerThread
    public final boolean b(String str) {
        j.lock();
        try {
            boolean a = this.m.a(str);
            return a;
        } finally {
            j.unlock();
        }
    }

    public final g[] c() {
        return new g[]{this.l, this.m};
    }

    public final Cursor d() {
        i.lock();
        try {
            Cursor c = this.m.c();
            return c;
        } finally {
            i.unlock();
        }
    }

    @WorkerThread
    public final Cursor e() {
        i.lock();
        try {
            Cursor d = this.m.d();
            return d;
        } finally {
            i.unlock();
        }
    }

    @WorkerThread
    public final Cursor f() {
        i.lock();
        try {
            Cursor c = this.l.c();
            return c;
        } finally {
            i.unlock();
        }
    }

    @WorkerThread
    public final void g() {
        j.lock();
        try {
            this.l.d();
        } finally {
            j.unlock();
        }
    }
}
