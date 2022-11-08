package com.facebook.react.bridge.queue;

import android.os.Looper;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class e implements d {
    private final MessageQueueThreadImpl a;
    @Nullable
    private final MessageQueueThreadImpl b;
    private final MessageQueueThreadImpl c;
    private final MessageQueueThreadImpl d;

    private e(MessageQueueThreadImpl uiQueueThread, @Nullable MessageQueueThreadImpl uiBackgroundQueueThread, MessageQueueThreadImpl nativeModulesQueueThread, MessageQueueThreadImpl jsQueueThread) {
        this.a = uiQueueThread;
        this.b = uiBackgroundQueueThread;
        this.c = nativeModulesQueueThread;
        this.d = jsQueueThread;
    }

    public final MessageQueueThread a() {
        return this.a;
    }

    @Nullable
    public final MessageQueueThread b() {
        return this.b;
    }

    public final MessageQueueThread c() {
        return this.c;
    }

    public final MessageQueueThread d() {
        return this.d;
    }

    public final void e() {
        if (!(this.b == null || this.b.getLooper() == Looper.getMainLooper())) {
            this.b.quitSynchronous();
        }
        if (this.c.getLooper() != Looper.getMainLooper()) {
            this.c.quitSynchronous();
        }
        if (this.d.getLooper() != Looper.getMainLooper()) {
            this.d.quitSynchronous();
        }
    }

    public static e a(f spec, c exceptionHandler) {
        Map<b, MessageQueueThreadImpl> specsToThreads = new HashMap();
        b uiThreadSpec = b.a();
        MessageQueueThreadImpl uiThread = MessageQueueThreadImpl.create(uiThreadSpec, exceptionHandler);
        specsToThreads.put(uiThreadSpec, uiThread);
        MessageQueueThreadImpl jsThread = (MessageQueueThreadImpl) specsToThreads.get(spec.c());
        if (jsThread == null) {
            jsThread = MessageQueueThreadImpl.create(spec.c(), exceptionHandler);
        }
        MessageQueueThreadImpl nativeModulesThread = (MessageQueueThreadImpl) specsToThreads.get(spec.b());
        if (nativeModulesThread == null) {
            nativeModulesThread = MessageQueueThreadImpl.create(spec.b(), exceptionHandler);
        }
        MessageQueueThreadImpl uiBackgroundThread = (MessageQueueThreadImpl) specsToThreads.get(spec.a());
        if (uiBackgroundThread == null && spec.a() != null) {
            uiBackgroundThread = MessageQueueThreadImpl.create(spec.a(), exceptionHandler);
        }
        return new e(uiThread, uiBackgroundThread, nativeModulesThread, jsThread);
    }
}
