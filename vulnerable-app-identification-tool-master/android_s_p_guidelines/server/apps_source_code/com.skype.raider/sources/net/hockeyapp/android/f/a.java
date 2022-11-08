package net.hockeyapp.android.f;

import android.os.AsyncTask;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public final class a {
    private static Executor a;

    public static void a(AsyncTask<Void, ?, ?> asyncTask) {
        asyncTask.executeOnExecutor(a != null ? a : AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static <T> FutureTask<T> a(Callable<T> callable) {
        Executor executor = a != null ? a : AsyncTask.THREAD_POOL_EXECUTOR;
        FutureTask<T> futureTask = new FutureTask(callable);
        executor.execute(futureTask);
        return futureTask;
    }
}
