package com.microsoft.react.timers;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BackgroundTimerModule extends ReactContextBaseJavaModule {
    private static final String BACKGROUD_TIMER_EVENT = "BackgroundTimer.timeout";
    private final ai context;
    private final Timer timer = new Timer();
    private final ConcurrentMap<Integer, TimerTask> timers = new ConcurrentHashMap();

    public BackgroundTimerModule(ag reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    public String getName() {
        return "RNBackgroundTimer";
    }

    @ReactMethod
    public void clearTimeout(int timerId) {
        TimerTask task = (TimerTask) this.timers.remove(Integer.valueOf(timerId));
        if (task != null) {
            task.cancel();
        }
    }

    private void sendEvent(ai reactContext, int timerId) {
        ((RCTDeviceEventEmitter) reactContext.a(RCTDeviceEventEmitter.class)).emit(BACKGROUD_TIMER_EVENT, Integer.valueOf(timerId));
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.timer.cancel();
    }

    @ReactMethod
    public void setTimeout(final int timerId, double jsCallStart, int delay, final boolean repeat) {
        long jsDelay = System.currentTimeMillis() - ((long) jsCallStart);
        long resultingDelay = (long) delay;
        if (jsDelay > 0) {
            resultingDelay = Math.max(0, resultingDelay - jsDelay);
        }
        TimerTask timerTask = new TimerTask(this) {
            final /* synthetic */ BackgroundTimerModule c;

            public final void run() {
                this.c.sendEvent(this.c.context, timerId);
                if (!repeat) {
                    this.c.timers.remove(Integer.valueOf(timerId));
                }
            }
        };
        this.timers.put(Integer.valueOf(timerId), timerTask);
        if (repeat) {
            this.timer.schedule(timerTask, resultingDelay, (long) delay);
        } else {
            this.timer.schedule(timerTask, resultingDelay);
        }
    }
}
