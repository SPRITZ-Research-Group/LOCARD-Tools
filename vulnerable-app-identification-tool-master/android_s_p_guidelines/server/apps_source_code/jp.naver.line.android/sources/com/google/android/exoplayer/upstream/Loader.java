package com.google.android.exoplayer.upstream;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.TraceUtil;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public final class Loader {
    private static final int MSG_END_OF_SOURCE = 0;
    private static final int MSG_FATAL_ERROR = 2;
    private static final int MSG_IO_EXCEPTION = 1;
    private LoadTask currentTask;
    private final ExecutorService downloadExecutorService;
    private boolean loading;

    public interface Callback {
        void onLoadCanceled(Loadable loadable);

        void onLoadCompleted(Loadable loadable);

        void onLoadError(Loadable loadable, IOException iOException);
    }

    @SuppressLint({"HandlerLeak"})
    final class LoadTask extends Handler implements Runnable {
        private static final String TAG = "LoadTask";
        private final Callback callback;
        private volatile Thread executorThread;
        private final Loadable loadable;

        public LoadTask(Looper looper, Loadable loadable, Callback callback) {
            super(looper);
            this.loadable = loadable;
            this.callback = callback;
        }

        public final void quit() {
            this.loadable.cancelLoad();
            if (this.executorThread != null) {
                this.executorThread.interrupt();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            try {
                this.executorThread = Thread.currentThread();
                if (!this.loadable.isLoadCanceled()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(this.loadable.getClass().getSimpleName());
                    stringBuilder.append(".load()");
                    TraceUtil.beginSection(stringBuilder.toString());
                    this.loadable.load();
                    TraceUtil.endSection();
                }
                sendEmptyMessage(0);
            } catch (IOException e) {
                obtainMessage(1, e).sendToTarget();
            } catch (Throwable e2) {
                Log.e(TAG, "Unexpected exception loading stream", e2);
                obtainMessage(1, new UnexpectedLoaderException(e2)).sendToTarget();
            } catch (Throwable e22) {
                Log.e(TAG, "Unexpected error loading stream", e22);
                obtainMessage(2, e22).sendToTarget();
                throw e22;
            }
        }

        public final void handleMessage(Message message) {
            if (message.what != 2) {
                onFinished();
                if (this.loadable.isLoadCanceled()) {
                    this.callback.onLoadCanceled(this.loadable);
                    return;
                }
                switch (message.what) {
                    case 0:
                        this.callback.onLoadCompleted(this.loadable);
                        return;
                    case 1:
                        this.callback.onLoadError(this.loadable, (IOException) message.obj);
                        break;
                }
                return;
            }
            throw ((Error) message.obj);
        }

        private void onFinished() {
            Loader.this.loading = false;
            Loader.this.currentTask = null;
        }
    }

    public interface Loadable {
        void cancelLoad();

        boolean isLoadCanceled();

        void load() throws IOException, InterruptedException;
    }

    public final class UnexpectedLoaderException extends IOException {
        public UnexpectedLoaderException(Exception exception) {
            StringBuilder stringBuilder = new StringBuilder("Unexpected ");
            stringBuilder.append(exception.getClass().getSimpleName());
            stringBuilder.append(": ");
            stringBuilder.append(exception.getMessage());
            super(stringBuilder.toString(), exception);
        }
    }

    public Loader(String str) {
        this.downloadExecutorService = Util.newSingleThreadExecutor(str);
    }

    public final void startLoading(Loadable loadable, Callback callback) {
        Looper myLooper = Looper.myLooper();
        Assertions.checkState(myLooper != null);
        startLoading(myLooper, loadable, callback);
    }

    public final void startLoading(Looper looper, Loadable loadable, Callback callback) {
        Assertions.checkState(this.loading ^ true);
        this.loading = true;
        this.currentTask = new LoadTask(looper, loadable, callback);
        this.downloadExecutorService.submit(this.currentTask);
    }

    public final boolean isLoading() {
        return this.loading;
    }

    public final void cancelLoading() {
        Assertions.checkState(this.loading);
        this.currentTask.quit();
    }

    public final void release() {
        if (this.loading) {
            cancelLoading();
        }
        this.downloadExecutorService.shutdown();
    }
}
