package com.google.android.gms.c;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.ab;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class j {

    interface b extends b, d, e<Object> {
    }

    private static final class a implements b {
        private final CountDownLatch a;

        private a() {
            this.a = new CountDownLatch(1);
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void a() {
            this.a.countDown();
        }

        public final void a(@NonNull Exception exception) {
            this.a.countDown();
        }

        public final boolean a(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.a.await(j, timeUnit);
        }

        public final void b() {
            this.a.countDown();
        }

        public final void c() throws InterruptedException {
            this.a.await();
        }
    }

    public static <TResult> g<TResult> a() {
        g xVar = new x();
        xVar.a(null);
        return xVar;
    }

    private static void a(g<?> gVar, b bVar) {
        gVar.a(i.b, (e) bVar);
        gVar.a(i.b, (d) bVar);
        gVar.a(i.b, (b) bVar);
    }

    private static <TResult> TResult b(g<TResult> gVar) throws ExecutionException {
        if (gVar.b()) {
            return gVar.d();
        }
        if (gVar.c()) {
            throw new CancellationException("Task is already canceled");
        }
        throw new ExecutionException(gVar.e());
    }

    public static <TResult> TResult a(@NonNull g<TResult> gVar) throws ExecutionException, InterruptedException {
        ab.c("Must not be called on the main application thread");
        ab.a((Object) gVar, (Object) "Task must not be null");
        if (gVar.a()) {
            return b(gVar);
        }
        Object aVar = new a();
        a(gVar, aVar);
        aVar.c();
        return b(gVar);
    }

    public static <TResult> TResult a(@NonNull g<TResult> gVar, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        ab.c("Must not be called on the main application thread");
        ab.a((Object) gVar, (Object) "Task must not be null");
        ab.a((Object) timeUnit, (Object) "TimeUnit must not be null");
        if (gVar.a()) {
            return b(gVar);
        }
        Object aVar = new a();
        a(gVar, aVar);
        if (aVar.a(j, timeUnit)) {
            return b(gVar);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }
}
