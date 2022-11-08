package com.skype.rt;

import android.util.SparseArray;
import java.util.concurrent.atomic.AtomicInteger;

public final class Auf {
    static final AtomicInteger inited = new AtomicInteger(0);

    public enum AppState {
        FOREGROUND(5),
        BACKGROUND_ACTIVE(10),
        BACKGROUND_CONSTRAINED(15),
        SUSPEND(20),
        SUSPEND_OFFLINE(25);
        
        private static final SparseArray<AppState> stateMap = null;
        private final int state;

        static {
            stateMap = new SparseArray();
            AppState[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                AppState appState = values[i];
                stateMap.put(appState.value(), appState);
                i++;
            }
        }

        public final int value() {
            return this.state;
        }

        public static AppState fromInt(int state) {
            return (AppState) stateMap.get(state);
        }

        private AppState(int state) {
            this.state = state;
        }
    }

    private static native boolean nativeInit();

    private static native void nativeSignalAppStateChange(int i);

    private static native void nativeStop();

    public static boolean init() {
        if (inited.addAndGet(1) > 1) {
            return true;
        }
        return nativeInit();
    }

    public static void stop() {
        if (inited.addAndGet(-1) == 0) {
            if (LogFactory.instance != null) {
                LogFactory f = LogFactory.instance;
                LogFactory.instance = null;
                f.dispose();
            }
            nativeStop();
        }
    }

    public static void signalAppStateChange(AppState newState) {
        nativeSignalAppStateChange(newState.value());
    }
}
