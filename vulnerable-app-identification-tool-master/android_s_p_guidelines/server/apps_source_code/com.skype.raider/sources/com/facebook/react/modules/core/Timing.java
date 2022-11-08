package com.facebook.react.modules.core;

import android.os.SystemClock;
import android.util.SparseArray;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

@ReactModule(name = "Timing")
public final class Timing extends ReactContextBaseJavaModule implements com.facebook.react.b.b, v {
    private static final float FRAME_DURATION_MS = 16.666666f;
    private static final float IDLE_CALLBACK_FRAME_DEADLINE_MS = 1.0f;
    protected static final String NAME = "Timing";
    private final AtomicBoolean isPaused = new AtomicBoolean(true);
    private final AtomicBoolean isRunningTasks = new AtomicBoolean(false);
    @Nullable
    private a mCurrentIdleCallbackRunnable;
    private final com.facebook.react.devsupport.a.a mDevSupportManager;
    private boolean mFrameCallbackPosted = false;
    private boolean mFrameIdleCallbackPosted = false;
    private final Object mIdleCallbackGuard = new Object();
    private final b mIdleFrameCallback = new b();
    private final e mReactChoreographer;
    private boolean mSendIdleEvents = false;
    private final d mTimerFrameCallback = new d();
    private final Object mTimerGuard = new Object();
    private final SparseArray<c> mTimerIdsToTimers;
    private final PriorityQueue<c> mTimers;

    private class a implements Runnable {
        final /* synthetic */ Timing a;
        private volatile boolean b = false;
        private final long c;

        public a(Timing timing, long frameStartTime) {
            this.a = timing;
            this.c = frameStartTime;
        }

        public final void run() {
            if (!this.b) {
                long frameTimeElapsed = SystemClock.uptimeMillis() - (this.c / 1000000);
                long absoluteFrameStartTime = System.currentTimeMillis() - frameTimeElapsed;
                if (Timing.FRAME_DURATION_MS - ((float) frameTimeElapsed) >= Timing.IDLE_CALLBACK_FRAME_DEADLINE_MS) {
                    boolean sendIdleEvents;
                    synchronized (this.a.mIdleCallbackGuard) {
                        sendIdleEvents = this.a.mSendIdleEvents;
                    }
                    if (sendIdleEvents) {
                        ((JSTimersExecution) this.a.getReactApplicationContext().a(JSTimersExecution.class)).callIdleCallbacks((double) absoluteFrameStartTime);
                    }
                    this.a.mCurrentIdleCallbackRunnable = null;
                }
            }
        }

        public final void a() {
            this.b = true;
        }
    }

    private class b extends com.facebook.react.modules.core.a.a {
        final /* synthetic */ Timing a;

        private b(Timing timing) {
            this.a = timing;
        }

        /* synthetic */ b(Timing x0, byte b) {
            this(x0);
        }

        public final void b(long frameTimeNanos) {
            if (!this.a.isPaused.get() || this.a.isRunningTasks.get()) {
                if (this.a.mCurrentIdleCallbackRunnable != null) {
                    this.a.mCurrentIdleCallbackRunnable.a();
                }
                this.a.mCurrentIdleCallbackRunnable = new a(this.a, frameTimeNanos);
                this.a.getReactApplicationContext().d(this.a.mCurrentIdleCallbackRunnable);
                this.a.mReactChoreographer.a(com.facebook.react.modules.core.e.a.IDLE_EVENT, this);
            }
        }
    }

    private static class c {
        private final int a;
        private final boolean b;
        private final int c;
        private long d;

        /* synthetic */ c(int x0, long x1, int x2, boolean x3, byte b) {
            this(x0, x1, x2, x3);
        }

        private c(int callbackID, long initialTargetTime, int duration, boolean repeat) {
            this.a = callbackID;
            this.d = initialTargetTime;
            this.c = duration;
            this.b = repeat;
        }
    }

    private class d extends com.facebook.react.modules.core.a.a {
        final /* synthetic */ Timing a;
        @Nullable
        private aq b;

        private d(Timing timing) {
            this.a = timing;
            this.b = null;
        }

        /* synthetic */ d(Timing x0, byte b) {
            this(x0);
        }

        public final void b(long frameTimeNanos) {
            if (!this.a.isPaused.get() || this.a.isRunningTasks.get()) {
                long frameTimeMillis = frameTimeNanos / 1000000;
                synchronized (this.a.mTimerGuard) {
                    while (!this.a.mTimers.isEmpty() && ((c) this.a.mTimers.peek()).d < frameTimeMillis) {
                        c timer = (c) this.a.mTimers.poll();
                        if (this.b == null) {
                            this.b = new WritableNativeArray();
                        }
                        this.b.pushInt(timer.a);
                        if (timer.b) {
                            timer.d = ((long) timer.c) + frameTimeMillis;
                            this.a.mTimers.add(timer);
                        } else {
                            this.a.mTimerIdsToTimers.remove(timer.a);
                        }
                    }
                }
                if (this.b != null) {
                    ((JSTimersExecution) this.a.getReactApplicationContext().a(JSTimersExecution.class)).callTimers(this.b);
                    this.b = null;
                }
                this.a.mReactChoreographer.a(com.facebook.react.modules.core.e.a.TIMERS_EVENTS, this);
            }
        }
    }

    public Timing(ag reactContext, com.facebook.react.devsupport.a.a devSupportManager) {
        super(reactContext);
        this.mDevSupportManager = devSupportManager;
        this.mTimers = new PriorityQueue(11, new Comparator<c>(this) {
            final /* synthetic */ Timing a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ int compare(Object obj, Object obj2) {
                long a = ((c) obj).d - ((c) obj2).d;
                if (a == 0) {
                    return 0;
                }
                if (a < 0) {
                    return -1;
                }
                return 1;
            }
        });
        this.mTimerIdsToTimers = new SparseArray();
        this.mReactChoreographer = e.b();
    }

    public final void initialize() {
        getReactApplicationContext().a((v) this);
        com.facebook.react.b.a.a(getReactApplicationContext()).a((com.facebook.react.b.b) this);
    }

    public final void onHostPause() {
        this.isPaused.set(true);
        clearFrameCallback();
        maybeIdleCallback();
    }

    public final void onHostDestroy() {
        clearFrameCallback();
        maybeIdleCallback();
    }

    public final void onHostResume() {
        this.isPaused.set(false);
        setChoreographerCallback();
        maybeSetChoreographerIdleCallback();
    }

    public final void onHeadlessJsTaskStart(int taskId) {
        if (!this.isRunningTasks.getAndSet(true)) {
            setChoreographerCallback();
            maybeSetChoreographerIdleCallback();
        }
    }

    public final void onHeadlessJsTaskFinish(int taskId) {
        if (!com.facebook.react.b.a.a(getReactApplicationContext()).a()) {
            this.isRunningTasks.set(false);
            clearFrameCallback();
            maybeIdleCallback();
        }
    }

    public final void onCatalystInstanceDestroy() {
        clearFrameCallback();
        clearChoreographerIdleCallback();
        com.facebook.react.b.a.a(getReactApplicationContext()).b((com.facebook.react.b.b) this);
    }

    private void maybeSetChoreographerIdleCallback() {
        synchronized (this.mIdleCallbackGuard) {
            if (this.mSendIdleEvents) {
                setChoreographerIdleCallback();
            }
        }
    }

    private void maybeIdleCallback() {
        if (this.isPaused.get() && !this.isRunningTasks.get()) {
            clearFrameCallback();
        }
    }

    private void setChoreographerCallback() {
        if (!this.mFrameCallbackPosted) {
            this.mReactChoreographer.a(com.facebook.react.modules.core.e.a.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = true;
        }
    }

    private void clearFrameCallback() {
        com.facebook.react.b.a headlessJsTaskContext = com.facebook.react.b.a.a(getReactApplicationContext());
        if (this.mFrameCallbackPosted && this.isPaused.get() && !headlessJsTaskContext.a()) {
            this.mReactChoreographer.b(com.facebook.react.modules.core.e.a.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = false;
        }
    }

    private void setChoreographerIdleCallback() {
        if (!this.mFrameIdleCallbackPosted) {
            this.mReactChoreographer.a(com.facebook.react.modules.core.e.a.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = true;
        }
    }

    private void clearChoreographerIdleCallback() {
        if (this.mFrameIdleCallbackPosted) {
            this.mReactChoreographer.b(com.facebook.react.modules.core.e.a.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = false;
        }
    }

    public final String getName() {
        return NAME;
    }

    @ReactMethod
    public final void deleteTimer(int timerId) {
        synchronized (this.mTimerGuard) {
            c timer = (c) this.mTimerIdsToTimers.get(timerId);
            if (timer == null) {
                return;
            }
            this.mTimerIdsToTimers.remove(timerId);
            this.mTimers.remove(timer);
        }
    }

    @ReactMethod
    public final void setSendIdleEvents(final boolean sendIdleEvents) {
        synchronized (this.mIdleCallbackGuard) {
            this.mSendIdleEvents = sendIdleEvents;
        }
        ap.a(new Runnable(this) {
            final /* synthetic */ Timing b;

            public final void run() {
                synchronized (this.b.mIdleCallbackGuard) {
                    if (sendIdleEvents) {
                        this.b.setChoreographerIdleCallback();
                    } else {
                        this.b.clearChoreographerIdleCallback();
                    }
                }
            }
        });
    }

    @ReactMethod
    public final void createTimer(int callbackID, int duration, double jsSchedulingTime, boolean repeat) {
        long adjustedDuration = Math.max(0, (((long) jsSchedulingTime) - System.currentTimeMillis()) + ((long) duration));
        if (duration != 0 || repeat) {
            c timer = new c(callbackID, (System.nanoTime() / 1000000) + adjustedDuration, duration, repeat, (byte) 0);
            synchronized (this.mTimerGuard) {
                this.mTimers.add(timer);
                this.mTimerIdsToTimers.put(callbackID, timer);
            }
            return;
        }
        aq timerToCall = new WritableNativeArray();
        timerToCall.pushInt(callbackID);
        ((JSTimersExecution) getReactApplicationContext().a(JSTimersExecution.class)).callTimers(timerToCall);
    }
}
